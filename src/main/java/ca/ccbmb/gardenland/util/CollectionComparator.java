package ca.ccbmb.gardenland.util;

import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.*;
import java.util.function.*;

@RequiredArgsConstructor
public class CollectionComparator<L, R> {

    private final Collection<L> leftCollection;
    private final Collection<R> rightCollection;

    public static <L, R> CollectionComparator<L, R> of(Collection<L> leftCollection, Collection<R> rightCollection) {
        return new CollectionComparator<>(leftCollection, rightCollection);
    }

    public final CollectionComparatorResult<L, R> compareWith(BiPredicate<L, R> areEqual) {
        CollectionComparatorResult<L, R> result = new CollectionComparatorResult<>(this.leftCollection, this.rightCollection);

        for (R right : rightCollection) {
            boolean exists = false;
            for (L left : leftCollection) {
                if (areEqual.test(left, right)) {
                    exists = true;
                    result.getExists().put(left, right);
                    break;
                }
            }

            if (!exists) {
                result.getAdded().add(right);
            }
        }

        for (L left : leftCollection) {
            boolean exists = false;
            for (R right : rightCollection) {
                if (areEqual.test(left, right)) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                result.getRemoved().add(left);
            }
        }

        return result;
    }

    @Value
    public static final class CollectionComparatorResult<L, R> {
        private final Collection<L> left;
        private final Collection<R> right;
        private final List<L> removed = new ArrayList<>();
        private final Map<L, R> exists = new HashMap<>();
        private final List<R> added = new ArrayList<>();

        public CollectionComparatorResult<L, R> ifRemoved(Consumer<L> removed) {
            this.removed.forEach(removed);
            return this;
        }

        public CollectionComparatorResult<L, R> ifExists(BiConsumer<L, R> merged) {
            this.exists.entrySet().forEach(m -> merged.accept(m.getKey(), m.getValue()));
            return this;
        }

        public CollectionComparatorResult<L, R> ifAdded(Consumer<R> added) {
            this.added.forEach(added);
            return this;
        }

        public <T extends Collection<L>> T collectInto(T collection, BiFunction<L, R, L> merge, Function<R, L> create) {
            collection.removeAll(this.removed);
            this.exists.entrySet()
                    .forEach(m -> merge.apply(m.getKey(), m.getValue()));
            this.added
                    .forEach(a -> collection.add(create.apply(a)));
            return collection;
        }
    }
}
