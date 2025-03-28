package com.intituitivecare.webscraping.service;

import org.springframework.stereotype.Service;

@Service 
public class DownloadService {
	private String downloadDir;
	public DownloadService(String downloadDir) {
		this.downloadDir = downloadDir;
	}

}
