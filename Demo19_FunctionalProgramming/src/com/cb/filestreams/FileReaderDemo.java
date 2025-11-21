package com.cb.filestreams;

import java.io.BufferedReader;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class FileReaderDemo {
public static void main(String[] args) {
	String fileloc="content.txt";
	int count=0;
	try(FileReader fr=new FileReader(fileloc);
			BufferedReader bufr=new BufferedReader(fr)){
		
		
		/*first way
		while((line=bufr.readLine())!=null) {
			String words[]=line.split(" ");
			for(String w:words) {
				if(w.equalsIgnoreCase("India"))count++;
		}*/

		/*second way
while((line=bufr.readLine())!=null) {
	String words[]=line.split(" ");
count+=Arrays.stream(words)
   .filter(word->word.equalsIgnoreCase("India"))
   .count();
			
		}
		*/
	
		//3rd way
 count=(int) Files.lines(Paths.get(fileloc))
		 .flatMap(line->Arrays.stream(line.split(" ")))
		 .filter(word->word.equalsIgnoreCase("India"))
		 .count();
	
		
		
		
	}catch(Exception e) {
			e.printStackTrace();
		}
	System.out.println("India repeated "+count+" times in the file");
	
	
	
}
}
