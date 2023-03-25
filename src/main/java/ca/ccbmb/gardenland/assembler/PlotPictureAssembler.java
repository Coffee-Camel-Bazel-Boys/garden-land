package ca.ccbmb.gardenland.assembler;

import ca.ccbmb.gardenland.core.plot.Plot;
import ca.ccbmb.gardenland.core.plot.PlotRepository;
import ca.ccbmb.gardenland.core.plot.picture.PlotPicture;
import ca.ccbmb.gardenland.dto.PictureDto;
import ca.ccbmb.gardenland.exception.PlotNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UncheckedIOException;

@Component
@RequiredArgsConstructor
public class PlotPictureAssembler {
    private final PlotRepository plotRepository;

    public PictureDto assemble(PlotPicture entity) {
        return new PictureDto()
                .setPicture(entity.getPicture())
                .setPictureNumber(String.valueOf(entity.getPlotPictureNumber()))
                .setFileType(entity.getFileType());
    }

    public PlotPicture disassemble(MultipartFile file, String plotNumber) {
        Plot plot = plotRepository.findByPlotNumber(Integer.valueOf(plotNumber).intValue())
                .orElseThrow(() -> new PlotNotFoundException(plotNumber));
        try {
            return PlotPicture.newInstance(plot.getPlotId(), file.getContentType(), file.getBytes()).setPlot(plot);
        } catch (IOException ioException) {
            throw new UncheckedIOException(ioException);
        }
    }
}
