package com.intituitivecare.webscraping.service;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.internal.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class DownloadServiceTest {
	
	private static final String DOWNLOAD_DIR = "download_test";
	
	//Primeiro vou precisar de um metodo para limpar tudo quando terminar
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
	//Vou precisar de um metodo que recebe uma List<String> e faça o download usando o link
	@Test
	public void testDownloadPdf() throws Exception{
		//uma lista de strings que vai começar com os links dos anexos
		List<String> pdfLinks = new ArrayList<>();
		pdfLinks.add("https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos/Anexo_I_Rol_2021RN_465.2021_RN627L.2024.pdf");
		pdfLinks.add("https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos/Anexo_II_DUT_2021_RN_465.2021_RN628.2025_RN629.2025.pdf");
		
		//aqui eu vou precisar de uma instancia do download service
		//e é melhor ele receber o diretorio com argumento no constutor
		DownloadService downloadService = new DownloadService(DOWNLOAD_DIR);
		
		//ai eu chamo um metodo para fazer o download
		List<File> downloadedFiles = downloadService.downloadFiles(pdfLinks);
		

	}
	
}
