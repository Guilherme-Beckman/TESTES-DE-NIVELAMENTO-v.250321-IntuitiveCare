package com.intituitivecare.webscraping.service;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class CompressorServiceTest {

	@Test
	public void shouldCompactFiles() throws IOException {
		//criar arquivos temporarios
		File file1 =  File.createTempFile("file1", ".pdf");
		File file2 =  File.createTempFile("file2", ".pdf");
		
		
		//escrever algo dentro dos arquivos temporarios
		Files.writeString(file1.toPath(), "Conteudo arquivo 1");
		Files.writeString(file2.toPath(), "Conteudo arquivo 2");
		//criar uma lista com esses arquivos
		List<File> filesList = new ArrayList<>();
		filesList.add(file1);
		filesList.add(file2);
		
		//criar um dir zip
		File zipDir = File.createTempFile("anexos", ".zip");
		
		//deletar o conteudo do dir zip
		zipDir.delete();
		//instanciar o servico de compressao
		CompressorService compressorService =  new CompressorService();
		
		//chamar o metodo e pegar o arquivo zipado
		File zipFile = compressorService.compressFiles(filesList);
		
        assertNotNull(zipFile, "O arquivo zip não deve ser nulo");
        assertTrue(zipFile.exists(), "O arquivo zip deve existir");
        assertTrue(zipFile.length() > 0, "O arquivo zio não deve estar vazio");

        file1.delete();
        file2.delete();
        zipFile.delete();
		
		
		
	}
}
