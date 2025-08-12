package application;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandling {
	
	public static String create_file(String fileName) throws IOException {
		File temp_file = new File(fileName + ".txt");
		if(!temp_file.exists()) {
			temp_file.createNewFile();
			System.out.printf("[+] File %s created\n", fileName + ".txt");
		} else {
			System.out.printf("[!] File %s already exists!\n", fileName + ".txt");
			
		}
		System.out.println(temp_file.getAbsolutePath());
		return(temp_file.getAbsolutePath());
	}
	
	public static void write_file(String fileName, String text) throws IOException {
		FileWriter pointer_output = new FileWriter(fileName + ".txt");
		
		// write to the file
		pointer_output.write(text);
		System.out.println("[+] Sucessfully wrote to the file\n");
		
		pointer_output.close();
	}
	
	public static String read_file(String fileName) throws IOException {
		FileReader pointer_read = new FileReader(fileName + ".txt");
		
		char[] charArray = new char[200];
		int length = pointer_read.read(charArray);
		
		// if the read file is empty
		if(length == -1) {
			System.out.println("[!].txt file is empty!\n");
			return "empty";
		}
		
		String read_output = new String(charArray,0,length);
		System.out.printf("[+] Successfully read \"%s\"\n", read_output);
		pointer_read.close();
		return read_output;
	}
	
	public static void delete_file() throws IOException {
		File pointer = new File("file to be deleted.txt");
		pointer.createNewFile();
		
		File pointer2 = new File("file to be deleted.txt");
		pointer2.delete();
	}
	

	
}
