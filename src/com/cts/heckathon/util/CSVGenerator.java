package com.cts.heckathon.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;

public class CSVGenerator {
	private static final String SAMPLE_CSV_FILE = "C:\\Users\\piyus\\CSVFiles\\sample.csv";

    public static void main(String[] args) throws IOException {
    	String header="ID, Name, Designation, Company";
    	ArrayList<String> lines=new ArrayList<String>();
    	
    	lines.add("1,Sundar Pichai,CEO,Google");
    	lines.add("2,Sundar Pichai,CEO,Google");
    	lines.add("3,Sundar Pichai,CEO,Google");
    	
    	
       
    	generateCSV(header,lines,SAMPLE_CSV_FILE);
    }

	public static void generateCSV(String header, List<String> lines,String file) throws IOException {
		try (
                FileWriter fileWriter= new FileWriter(file);
        		BufferedWriter writer = new BufferedWriter(fileWriter);
			//CSVFormat csvFileFormat = CSVFormat.DEFAULT.withEscape('\\').withQuoteMode(QuoteMode.NONE);	
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(header));
        ) {
            for (String line : lines) {
            	csvPrinter.printRecord(line);
			}
			
            csvPrinter.flush();            
        }
	}
}
