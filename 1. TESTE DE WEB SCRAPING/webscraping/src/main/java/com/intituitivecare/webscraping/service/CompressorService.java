package com.intituitivecare.webscraping.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.stereotype.Service;

import com.intituitivecare.webscraping.exceptions.compress.ErrorWhileCompressFilesException;

@Service
public class CompressorService {

	public File compressFiles(List<File> filesList, String absolutePath) {
		File zipFile = new File(absolutePath);
		
		try (FileOutputStream fileOutputStream = new FileOutputStream(zipFile);
			ZipOutputStream outputStream = new ZipOutputStream(fileOutputStream);	
				){
			for (File file : filesList) {
				addFileOnZip(file, outputStream);
			}
			
		} catch (IOException e) {
			throw new ErrorWhileCompressFilesException();
		} 
		return zipFile;
	}

	private void addFileOnZip(File file, ZipOutputStream outputStream) throws IOException {
		try (FileInputStream fileInputStream = new FileInputStream(file)){
			ZipEntry zipEntry = new ZipEntry(file.getName());
			outputStream.putNextEntry(zipEntry);
			
			byte[] buffer = new byte[1024];
			int readBytes;
			
			while((readBytes = fileInputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0 , readBytes);
			}
			outputStream.closeEntry();
			
	}

}
}
