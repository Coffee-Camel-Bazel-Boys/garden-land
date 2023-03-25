package ca.ccbmb.gardenland.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PictureDto {
    private String pictureNumber;
    private byte[] picture;
    private String fileType;
}
