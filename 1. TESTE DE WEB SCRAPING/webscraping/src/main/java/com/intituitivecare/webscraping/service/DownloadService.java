package com.intituitivecare.webscraping.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
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
		List<File> downloadedFiles = new ArrayList<>();
		try {

			ensureDownloadDirectoryExists();
			for (String pdfLink : pdfLinks) {
				File downloadedFile = downloadSingleFile(pdfLink);
				downloadedFiles.add(downloadedFile);
			}
			
			return downloadedFiles;
		} catch (Exception e) {
			throw new ErrorWhileDownloadFilesException();
		}
	}
	
	public void ensureDownloadDirectoryExists() throws IOException {
		Path downloadPath = Path.of(downloadDir);
		if(!Files.exists(downloadPath)) {
			Files.createDirectories(downloadPath);
		}
	}
	private File downloadSingleFile(String pdfLink) throws MalformedURLException, IOException {
		String fileName = extractFileNameFromUrl(pdfLink);
		
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
		return outputFile; 
	}
	  private String extractFileNameFromUrl(String url) {
	        return url.substring(url.lastIndexOf("/") + 1);
	    }

}
