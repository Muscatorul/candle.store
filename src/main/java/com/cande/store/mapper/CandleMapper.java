package com.cande.store.mapper;

import com.cande.store.dto.CandleDto;
import com.cande.store.entity.Candle;
import com.cande.store.entity.FileCover;
import com.cande.store.repository.FileCoverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Component
public class CandleMapper {
    private static String DIRECTORY=System.getProperty("user.dir")+ "/src/main/resources/static/img/";
    @Autowired
    private FileCoverRepository fileCoverRepository;
    public Candle candleMapper(CandleDto candleDto, MultipartFile file) throws IOException {
        Path fileName= Paths.get(DIRECTORY);
        FileCover fileCover=new FileCover();
        fileCover.setPath(fileName.toFile().getPath());
        FileCover fileSaved=fileCoverRepository.save(fileCover);

        final String fileExtension = Optional.ofNullable(file.getOriginalFilename())
                .flatMap(CandleMapper::getFileExtension)
                .orElse("");

        final String targetFileName = fileSaved.getId() + "." + fileExtension;
        final Path targetPath = fileName.resolve(targetFileName);

        File f = new File(String.valueOf(targetPath));
        file.transferTo(f);
        Candle candle=new Candle();
        candle.setTitle(candleDto.getTitle());
        candle.setDescription(candleDto.getDescription());
        candle.setPrice(Double.parseDouble(candleDto.getPrice()));
        candle.setQuantity(Integer.parseInt(candleDto.getQuantity()));
        candle.setFileCover(fileSaved);
        return candle;
    }
    private static Optional<String> getFileExtension(String fileName) {
        final int indexOfLastDot = fileName.lastIndexOf('.');

        if (indexOfLastDot == -1) {
            return Optional.empty();
        } else {
            return Optional.of(fileName.substring(indexOfLastDot + 1));
        }
    }
}
