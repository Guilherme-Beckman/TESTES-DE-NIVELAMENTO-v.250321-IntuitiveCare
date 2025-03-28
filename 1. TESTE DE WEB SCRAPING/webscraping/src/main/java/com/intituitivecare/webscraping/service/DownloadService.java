package com.intituitivecare.webscraping.service;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;

@Service 
public class DownloadService {
	private final String downloadDir;
	public DownloadService(String downloadDir) {
		this.downloadDir = downloadDir;
	}
	public List<File> downloadFiles(List<String> pdfLinks) {
		// TODO Auto-generated method stub
		return null;
	}

}
