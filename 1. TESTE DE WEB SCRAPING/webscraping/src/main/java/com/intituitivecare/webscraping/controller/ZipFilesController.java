package com.intituitivecare.webscraping.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("zip")
public class ZipFilesController {
	@Autowired
	private ZipService zipService;

	@GetMapping
	public ResponseEntity<Resource> getZipedFile() {
		File zippedFile = zipService.getZipFIles();
		Resource resource = new FileSystemResource(zippedFile);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + zippedFile.getName())
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.body(resource);
	}
	
}
