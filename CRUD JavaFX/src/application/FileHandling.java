package application;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandling {
	
	private static final String FOLDER_NAME = "receipts";
	
	public static String create_file(String fileName) throws IOException {
        // Make the folder
        File folder = new File(FOLDER_NAME);
        if (!folder.exists()) {
            folder.mkdirs();
            System.out.println("[+] Receipts folder created");
        }
        
        // Make a file inside the folder
        File temp_file = new File(folder, fileName + ".txt");
        if (!temp_file.exists()) {
            temp_file.createNewFile();
            System.out.printf("[+] File %s created\n", fileName + ".txt");
        } else {
            System.out.printf("[!] File %s already exists!\n", fileName + ".txt");
        }
        return temp_file.getAbsolutePath();
    }
	
	
	public static void write_file(String fileName, String text) throws IOException {
        // Find the folder
        File folder = new File(FOLDER_NAME);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Write file inside the folder
        File file = new File(folder, fileName + ".txt");
        // if file doesn't exist, make a new one
        if (!file.exists()) {
            file.createNewFile();
            System.out.printf("[+] File %s created\n", fileName + ".txt");
        }

        
        FileWriter pointer_output = new FileWriter(file);
        pointer_output.write(text);
        pointer_output.close();
        System.out.println("[+] Successfully wrote to: " + fileName + ".txt");
    }
	


	public static String read_file(String fileName) throws IOException {
        File file = new File(FOLDER_NAME, fileName + ".txt");
        FileReader pointer_read = new FileReader(file);

        char[] charArray = new char[200];
        int length = pointer_read.read(charArray);

        // if the read file is empty
        if (length == -1) {
            System.out.println("[!] .txt file is empty!\n");
            return "empty";
        }

        String read_output = new String(charArray, 0, length);
        System.out.printf("[+] Successfully read \"%s\"\n", read_output);
        pointer_read.close();
        return read_output;
    }
	

	
	public static void delete_file(String fileName) {
        File file = new File(FOLDER_NAME, fileName + ".txt");
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("[+] Deleted: " + fileName + ".txt");
            } else {
                System.out.println("[!] Failed to delete: " + fileName + ".txt");
            }
        } else {
            System.out.println("[!] File not found: " + fileName + ".txt");
        }
    }
	

	
}
