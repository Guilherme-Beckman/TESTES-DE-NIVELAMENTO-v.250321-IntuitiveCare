package com.intituitivecare.transformacaodedados.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PDFExtractorService {

	public List<String[]> extractTableData(File pdf) {
		List<String[]> test = new ArrayList<>();
		test.add(new String[] {"Procedimentos"});
		test.add(new String[] {"dados, dados"});
		
		return test;
	}

}
