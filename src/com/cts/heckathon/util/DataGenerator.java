package com.cts.heckathon.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;

public class DataGenerator {

	public static void main(String[] args) {
	   
		List<Integer> accountList=new ArrayList<Integer>();
		accountList.add(100001);
		accountList.add(100002);
		accountList.add(100003);
		accountList.add(100004);
		accountList.add(100005);
		accountList.add(100006);
		try {
			DataGenerator dataGen = new DataGenerator();
			List<String> cashEventList= dataGen.getCashEventList(accountList,2017);
			String header="account_id,amount,value_date,dayOfweek,load_time,eod";
			String file="C:\\Users\\piyus\\CSVFiles\\sample.csv";
			generateCSV(header, cashEventList,file);
			
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public  List<String>  getCashEventList(List<Integer> accountList,int year) throws ParseException{
		Calendar cal =Calendar.getInstance();
		cal.set(Calendar.YEAR, 2017);
		ArrayList<String> cashEventList= new ArrayList<String>();
		for(int i=0;i<12 ;i++){
			cal.set(Calendar.MONTH, i);
			int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			System.out.println("Month "+cal.get(Calendar.MONTH)+" days "+days);
			for(int j=1;j<=days;j++){
				cal.set(Calendar.DATE, j);
				int dayOfweek=cal.get(Calendar.DAY_OF_WEEK);
				if(dayOfweek==1||dayOfweek==7){
					continue;
				}
				int numRec=getRandomNumberInRange(1000, 10000);
				System.out.println("num reocrds "+numRec);
				 String sod=j+"-"+(i+1)+"-"+year+" 00:00:00";
			     String eod=j+"-"+(i+1)+"-"+year+" 23:00:00";
			     List<String> valueDateList= new ArrayList<String>(); 
			     valueDateList = getValueDateStringList(cal);
			     
			   for(int k=1;k<=numRec;k++){
			     Integer accountId= getRandomList(accountList);		
				 double amount = getRandomNumberInRange(10000.00, 1000000.00);
				 String loadtime= nextDateString(sod, eod);
				 String valueDate= getRandomStringList(valueDateList);
				 String line = ""+accountId + ","+amount+","+valueDate+","+dayOfweek+","+loadtime+","+eod;
				 cashEventList.add(line);
				}
				
			}
		}
		
		
		
		
		
		
		return cashEventList;
	}

	private List<Date> getValueDateList(Calendar cal) {
		List<Date> dateList= new ArrayList<Date>();
		for(int i=-3;i<3;i++){
		Calendar calNew=cal;
		calNew.add(Calendar.DATE, i);
		dateList.add(calNew.getTime());
	}
		return null;
	}

	private List<String> getValueDateStringList(Calendar cal) {
		List<String> dateList= new ArrayList<String>();
		for(int i=-3;i<3;i++){
		Calendar calNew=cal;
		calNew.add(Calendar.DATE, i);
		dateList.add(getDateString(calNew.getTime(),false));
	}
		return dateList;
	}
	
	private  int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
	private   double getRandomNumberInRange(double min, double max) {
	    Random r = new Random();
	    return (r.nextInt((int)((max-min)*10+1))+min*10) / 10.0;
	}
	 
	
	DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	 public Date nextDate(String str_date1, String str_date2) throws ParseException {

		
        Calendar cal=Calendar.getInstance();
       

        cal.setTime(formatter.parse(str_date1));
        Long value1 = cal.getTimeInMillis();

        cal.setTime(formatter.parse(str_date2));
        Long value2 = cal.getTimeInMillis();

        long value3 = (long)(value1 + Math.random()*(value2 - value1));
        cal.setTimeInMillis(value3);
        System.out.println(formatter.format(cal.getTime()));
		return cal.getTime();
	}
   
	 public String getDateString(Date date,boolean timeRequired){
		 DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		 if(timeRequired)
		    formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		 Calendar cal =Calendar.getInstance();
		 cal.setTime(date);
		 String dateStr=formatter.format(cal.getTime());
		 return dateStr;
		 
		 
	 }
	 
	public String nextDateString(String str_date1, String str_date2) throws ParseException {
		//System.out.println(str_date1+ " "+ str_date2);
		Calendar cal =Calendar.getInstance();
		Date date=nextDate(str_date1, str_date2);
		cal.setTime(date);
		
        String dateStr=formatter.format(cal.getTime());
		return dateStr;
	}
	
	public Integer getRandomList(List<Integer> list) {

	    //0-4
	    int index = ThreadLocalRandom.current().nextInt(list.size());		
	    return list.get(index);
	    
	}
	
	public Date getRandomDateList(List<Date> list) {

	    //0-4
	    int index = ThreadLocalRandom.current().nextInt(list.size());		
	    return list.get(index);
	    
	}
	
	public String getRandomStringList(List<String> list) {

	    //0-4
	    int index = ThreadLocalRandom.current().nextInt(list.size());		
	    return list.get(index);
	    
	}
	
	public static void generateCSV(String header, List<String> lines,String file) throws IOException {
		try (
                FileWriter fileWriter= new FileWriter(file);
        		BufferedWriter writer = new BufferedWriter(fileWriter);
			   
		  ) {
			 fileWriter.write(header);
			 fileWriter.write("\n");
			for (String line : lines) {
            	fileWriter.write(line);
            	fileWriter.write("\n");
          }
			
            fileWriter.flush();
            fileWriter.close();
        }
	}
	
/*	public static void generateCSV(String header, List<String> lines,String file) throws IOException {
		try (
                FileWriter fileWriter= new FileWriter(file);
        		BufferedWriter writer = new BufferedWriter(fileWriter);
			//CSVFormat csvFileFormat = CSVFormat.DEFAULT.withEscape('\\').withQuoteMode(QuoteMode.NONE);	
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(header).withEscape('\\').withQuoteMode(QuoteMode.NONE));
        ) {
            for (String line : lines) {
            	csvPrinter.printRecord(line);
			}
			
            csvPrinter.flush();            
        }
	}*/
	
}
