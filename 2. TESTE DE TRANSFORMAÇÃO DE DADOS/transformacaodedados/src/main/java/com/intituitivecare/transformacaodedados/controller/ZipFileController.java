package com.intituitivecare.transformacaodedados.controller;

import java.io.File;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intituitivecare.transformacaodedados.service.ZipService;



@RestController
@RequestMapping("zip")
public class ZipFileController {
	@Autowired
	private ZipService zipService;

	@GetMapping
	public ResponseEntity<Resource> getZipedFile() throws URISyntaxException {
		File zippedFile = zipService.getZipFile();
		Resource resource = new FileSystemResource(zippedFile);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + zippedFile.getName())
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.body(resource);
	}
	
}