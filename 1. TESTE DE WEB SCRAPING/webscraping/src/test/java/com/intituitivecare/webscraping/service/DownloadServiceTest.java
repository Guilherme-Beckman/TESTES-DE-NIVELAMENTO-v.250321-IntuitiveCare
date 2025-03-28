package com.intituitivecare.webscraping.service;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.AfterEach;
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

	
}
