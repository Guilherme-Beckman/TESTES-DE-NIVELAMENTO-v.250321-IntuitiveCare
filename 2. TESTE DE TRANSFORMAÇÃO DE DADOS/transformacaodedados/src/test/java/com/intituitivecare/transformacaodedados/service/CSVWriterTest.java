package com.intituitivecare.transformacaodedados.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class CSVWriterTest {

	@Test
	public void shouldGenerateCSVFile() throws IOException {
		Path tempDir = Files.createTempDirectory("csv");
		List<List<String>> table = new ArrayList<>();

		List<String> header = Arrays.asList("PROCEDIMENTO", "RN (alteração)", "VIGÊNCIA", "Seg. Odontológica",
				"Seg. Ambulatorial", "HCO", "HSO", "REF", "PAC", "DUT", "SUBGRUPO", "GRUPO", "CAPÍTULO");
		List<String> row1 = Arrays.asList("CONSULTA ODONTOLÓGICA PARA AVALIAÇÃO TÉCNICA DE AUDITORIA", "", "",
				"Seg. Odontológica", "", "", "", "", "", "",
				"CONSULTAS, VISITAS HOSPITALARES OU ACOMPANHAMENTO DE PACIENTES", "PROCEDIMENTOS GERAIS",
				"PROCEDIMENTOS GERAIS");

		List<String> row2 = Arrays.asList(
				"ATENDIMENTO/ACOMPANHAMENTO EM HOSPITAL-DIA PSIQUIÁTRICO (COM DIRETRIZ DE UTILIZAÇÃO)", "", "", "", "",
				"HCO", "HSO", "REF", "", "109", "CONSULTAS, VISITAS HOSPITALARES OU ACOMPANHAMENTO DE PACIENTES",
				"PROCEDIMENTOS GERAIS", "PROCEDIMENTOS GERAIS");

		table.add(header);
		table.add(row1);
		table.add(row2);
		Path csvFile = tempDir.resolve("output.csv");
		CSVService csvService = new CSVService();
		csvService.writeCSV(table, csvFile.toString());
		String content = Files.readString(csvFile);
		String expected = """
				"PROCEDIMENTO","RN (alteração)","VIGÊNCIA","Seg. Odontológica","Seg. Ambulatorial","HCO","HSO","REF","PAC","DUT","SUBGRUPO","GRUPO","CAPÍTULO"
				"CONSULTA ODONTOLÓGICA PARA AVALIAÇÃO TÉCNICA DE AUDITORIA","","","Seg. Odontológica","","","","","","","CONSULTAS, VISITAS HOSPITALARES OU ACOMPANHAMENTO DE PACIENTES","PROCEDIMENTOS GERAIS","PROCEDIMENTOS GERAIS"
				"ATENDIMENTO/ACOMPANHAMENTO EM HOSPITAL-DIA PSIQUIÁTRICO (COM DIRETRIZ DE UTILIZAÇÃO)","","","","","HCO","HSO","REF","","109","CONSULTAS, VISITAS HOSPITALARES OU ACOMPANHAMENTO DE PACIENTES","PROCEDIMENTOS GERAIS","PROCEDIMENTOS GERAIS"
				""";

		assertEquals(expected, content);

	}

}
