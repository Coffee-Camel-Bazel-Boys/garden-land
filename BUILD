java_library(
    name = "lombok",
    exported_plugins = [":lombok_plugin"],
    exports = ["@maven//:org_projectlombok_lombok"],
    visibility = ["//visibility:public"],
)

java_plugin(
    name = "lombok_plugin",
    processor_class = "lombok.launch.AnnotationProcessorHider$AnnotationProcessor",
    visibility = ["//visibility:public"],
    deps = [
        "@maven//:org_projectlombok_lombok",
    ],
)