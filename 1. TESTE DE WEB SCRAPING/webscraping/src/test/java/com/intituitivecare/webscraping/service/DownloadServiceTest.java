package com.intituitivecare.webscraping.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class DownloadServiceTest {
	
	private static final String DOWNLOAD_DIR = "download_test";
	
	@AfterEach
	public void cleanUp() throws Exception{
		Path downloadPath = Path.of(DOWNLOAD_DIR);
		
		if(Files.exists(downloadPath)) {
			Files.walk(downloadPath)
			.map(Path::toFile)
			.forEach(File::delete);
			Files.deleteIfExists(downloadPath);
		}
	}
	@Test
	public void shouldDownloadPdfs() throws Exception{
		List<String> pdfLinks = new ArrayList<>();
		pdfLinks.add("https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos/Anexo_I_Rol_2021RN_465.2021_RN627L.2024.pdf");
		pdfLinks.add("https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos/Anexo_II_DUT_2021_RN_465.2021_RN628.2025_RN629.2025.pdf");
		
		DownloadService downloadService = new DownloadService(DOWNLOAD_DIR);
		
		List<File> downloadedFiles = downloadService.downloadFiles(pdfLinks);
		
		assertNotNull(downloadedFiles, "Os arquivos não podem ser nulos");
		assertEquals(2, downloadedFiles.size(), "A lista de pdfs baixados deve conter "
				+ "exatamente 2 elementos");
		assertTrue(downloadedFiles.get(0).exists(), "O primeiro arquivo deve existir");
		assertTrue(downloadedFiles.get(1).exists(), "O segundo arquivo deve existir");
		assertTrue(downloadedFiles.get(0).getName().endsWith(".pdf"), "O primeiro arquivo deve ser um pdf");
		assertTrue(downloadedFiles.get(1).getName().endsWith(".pdf"), "O Segundo arquivo deve ser um pdf");
	}
	
}
