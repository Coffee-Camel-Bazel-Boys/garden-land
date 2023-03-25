package ca.ccbmb.gardenland.controller;

import ca.ccbmb.gardenland.dto.PictureDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/plots/{plotNumber}/pictures")
@RequiredArgsConstructor
public class PlotPictureController {

    @GetMapping("")
    public List<PictureDto> findAllForPlot(@PathVariable String plotNumber) {
        return null;
    }
    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PictureDto addPicture(@PathVariable String plotNumber, @RequestPart("picture") PictureDto picture, @RequestPart("file") MultipartFile file) {
        return null;
    }

    @DeleteMapping("/{pictureNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public PictureDto deletePicture(@PathVariable String plotNumber, @PathVariable("pictureNumber") String pictureNumber) {
        return null;
    }
}
