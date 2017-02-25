package util;

import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Test {
	
	
	
	public static void testing(String[] args){
		System.out.println("is studTest");

		File reference = new File("");
		String st;
		String ClassMetrics = new String("");
		ArrayList<String> listForFile = new ArrayList<String>();
		ArrayList<String> listForReference = new ArrayList<String>();
		File file = new File(args[1]);
		if(!file.exists()){
			System.out.println("file not found");	
			return;
		}
		
		
		try{
			BufferedReader read = new BufferedReader(new FileReader(file));
			
			while((st = read.readLine()) != null){
				if(st.matches(".*METRICS CLASS.*")){
					ClassMetrics = st.substring(st.indexOf("picard.analysis")+"picard.analysis.".length());
					System.out.print("start test "+ClassMetrics+"... ");
					reference = new File("src/main/resources/outFiles/" + ClassMetrics + "_OUT.txt");
					if(!reference.exists()){
						System.out.println("this metric is not found");	
						return;
					}		
				}
				filter(st,listForFile);
			}
			read.close();

			read = new BufferedReader(new FileReader(reference));
			while((st = read.readLine()) != null)
				filter(st,listForReference);
			read.close();
		
			if(listForFile.equals(listForReference))
				System.out.println("checked");
			else
				System.out.println("unchecked");
			
		}
		catch(Exception e){e.printStackTrace();}

/*		try(FileOutputStream  fin=new FileOutputStream (tempMultiThreadOut.getAbsolutePath())){
		Files.lines(file.toPath()).filter(s -> !s.startsWith("#")). ;
		
		}
		catch(IOException e){
			e.printStackTrace();
		}*/
		
		
	}
	
	static private void filter(String st, ArrayList list){
		if(!st.startsWith("#") && st.length()>0){
			list.add(st);
		}
	}
	
	
	
	
	

}
