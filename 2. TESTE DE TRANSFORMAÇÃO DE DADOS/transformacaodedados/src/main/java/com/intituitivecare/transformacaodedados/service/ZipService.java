package com.intituitivecare.transformacaodedados.service;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intituitivecare.transformacaodedados.abbreviations.Abbreviations;

@Service
public class ZipService {
    @Autowired
    private PDFExtractorService pdfExtractorService;
    @Autowired
    private CSVService csvService;
    @Autowired
    private CompressorService compressorService;

    public File getZipFile() throws URISyntaxException {
        File anexo = new File(getClass()
                .getResource("/Anexo_I_Rol_2021RN_465.2021_RN627L.2024.pdf").toURI());
        List<Abbreviations> abbreviations = new ArrayList<>();
        abbreviations.add(Abbreviations.AMB);
        abbreviations.add(Abbreviations.OD);
        
        var table = pdfExtractorService.extractTableData(anexo, abbreviations);
        
        String tempDir = System.getProperty("java.io.tmpdir");
        String csvFilePath = tempDir + File.separator + "output.csv";
        
        csvService.writeCSV(table, csvFilePath);
        
        List<File> filesList = new ArrayList<>();
        filesList.add(new File(csvFilePath));
        
        String zipFilePath = tempDir + File.separator + "Teste_Guilherme_Beckman.zip";
        
        return compressorService.compressFiles(filesList, zipFilePath);
    }
}
