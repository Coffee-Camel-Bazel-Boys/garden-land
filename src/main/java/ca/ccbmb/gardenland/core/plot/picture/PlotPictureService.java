package ca.ccbmb.gardenland.core.plot.picture;

import ca.ccbmb.gardenland.assembler.PlotPictureAssembler;
import ca.ccbmb.gardenland.dto.PictureDto;
import ca.ccbmb.gardenland.exception.PlotPictureNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PlotPictureService {
    private final PlotPictureRepository repository;
    private final PlotPictureAssembler assembler;
    private final PlotPictureValidator validator;

    public List<PictureDto> findAllByPlotNumber(String plotNumber) {
        return repository.findAllByPlot_PlotNumber(Integer.valueOf(plotNumber).intValue()).stream()
                .map(assembler::assemble)
                .collect(Collectors.toList());
    }

    public PictureDto create(String plotNumber, MultipartFile file) {
        validator.validateForSave(file);
        return assembler.assemble(repository.save(assembler.disassemble(file, plotNumber)));
    }

    public void delete(String plotPictureNumber) {
        PlotPicture entity = getByNumber(plotPictureNumber);
        repository.delete(entity);
    }

    private PlotPicture getByNumber(String number) {
        return repository.findByPlotPictureNumber(Integer.valueOf(number))
                .orElseThrow(() -> new PlotPictureNotFoundException(number));
    }
}
