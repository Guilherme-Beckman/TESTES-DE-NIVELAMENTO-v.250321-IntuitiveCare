package com.intituitivecare.webscraping.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.intituitivecare.webscraping.exceptions.download.ErrorWhileDownloadFilesException;

@Service 
public class DownloadService {
	private final String downloadDir;
	public DownloadService(String downloadDir) {
		this.downloadDir = downloadDir;
	}
	public DownloadService() {
		this("downloads");
	}
	public List<File> downloadFiles(List<String> pdfLinks) {
		try {
			List<File> downloadedFiles = new ArrayList<>();
			for (String pdfLink : pdfLinks) {
				Path downloadPath = Path.of(downloadDir);
				if(!Files.exists(downloadPath)) {
					Files.createDirectories(downloadPath);
				}
				
				String fileName = pdfLink.substring(pdfLink.lastIndexOf("/")+1);
				
				File outputFile = new File(downloadDir, fileName);
				
				try (BufferedInputStream in = new BufferedInputStream(new URL(pdfLink).openStream())){
					FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
					byte dataBuffer[] = new byte[1024];
					int bytesRead;
					
					while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
						fileOutputStream.write(dataBuffer, 0, bytesRead);
					}
					fileOutputStream.close();
				} 
				downloadedFiles.add(outputFile);
			}
			
			return downloadedFiles;
		} catch (Exception e) {
			throw new ErrorWhileDownloadFilesException();
		}
	}

}
