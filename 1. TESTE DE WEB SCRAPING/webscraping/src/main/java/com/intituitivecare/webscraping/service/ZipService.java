package com.intituitivecare.webscraping.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZipService {
	@Autowired
	private ScraperService scraperService;
	@Autowired
	private DownloadService downloadService;
	@Autowired
	private CompressorService compressorService;

	public File getZipFiles() {
		List<String> links = scraperService.fetchPdfLinks();
		List<File> files = downloadService.downloadFiles(links);
		File zipedFiles = compressorService.compressFiles(files, "zipedFiles");
		return zipedFiles;
	}

}
