package ca.ccbmb.gardenland.core.plot.picture;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PlotPictureValidator {
    private static final String INVALID_FILE_TYPE = "INVALID_FILE_TYPE";
    private static final String FILE_TOO_LARGE = "FILE_TOO_LARGE";
    private static final List<String> ACCEPTED_FILE_TYPES = List.of(
            "image/jpeg",
            "image/jpg",
            "image/png");

    private static final int MAX_SIZE = 10485760; //bytes = 10mb

    public Map<String, String> validateForSave(MultipartFile file) {
        Map<String, String> validationErrors = new HashMap<>();

        if (!ACCEPTED_FILE_TYPES.contains(file.getContentType())) {
            validationErrors.put("file", INVALID_FILE_TYPE);
        }

        if (file.getSize() > MAX_SIZE) {
            validationErrors.put("file", FILE_TOO_LARGE);
        }

        return validationErrors;
    }
}
