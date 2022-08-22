package RSA;

import java.io.*;
import java.math.BigInteger;
import java.util.Scanner;

public class RSAEncrypt {
	
	public static void main(String[] args) 
	{
		// Get string array of data from the files
		String data[] = readDataFromFile(args);
		 
		
		String message = data[0];
		BigInteger E = new BigInteger(data[1].substring(2));
		BigInteger N = new BigInteger(data[2].substring(2));
		
		//convert the string message to string of ints
		message = convert_string(message);   

		//encrpyt the message
		String encryption = encrypt(message, E, N);
		
		//output the encryption to a file
		writeNewFile(encryption);

   }

	/*
	 * READS DATA FROM THE FILES AND RETURNS THE DATA BACK AS A STRING ARRAY
	 * */
	@SuppressWarnings("null")
	public static String[] readDataFromFile(String[] args) 
	{
		String message = "";
		String data[] = {"", "", ""};
		try 
		{
			File firstFile = new File(args[0]);
			Scanner reader = new Scanner(firstFile);
			String readerMessage;
		 
			//while file has data, keep reading and adding to the string
		   	while(reader.hasNext()) 
		   	{
		   		// chack to see if there is a space at the end for the next line
			    readerMessage = reader.nextLine();
			    if(readerMessage.charAt(readerMessage.length() - 1) != ' ' && reader.hasNext()) {
			    	readerMessage += " ";
			    }
			    message += readerMessage; 
		   	}
		   	data[0] = message;
		    reader.close();
		} 
		catch (IOException e) 
		{
		   System.out.println("An error occurred.");
		}
		
		try {
		       File secondFile = new File(args[1]);
		       Scanner reader = new Scanner(secondFile);
		       data[1] = reader.nextLine();
		       data[2] = reader.nextLine();
		       //remove any spaces from the file
		       for(int i = 1; i <= 2; i++) {
		    	   data[i] = data[i].replaceAll("\\s", "");
		           
		       }
		       reader.close();
		   } catch (FileNotFoundException e) {
		       System.out.println("An error occurred.");
		   }
		
		return data;
		
	}
	

	/*
	 * Encodes a string of chars to a String of ints with 00-26 encoding
	 * */
	public static String convert_string(String s) {
		String converted = "";
		
		int a = 0;
		String temp = "";
		for(int i = 0; i < s.length(); i++) {
			
			if(Character.isUpperCase( s.charAt(i) ) ) 	//if the character at 'i' is uppercase encode the char to 00-26 int
			{
				a = s.charAt(i) -65;
				temp = Integer.toString(a);
			}
			else if(s.charAt(i) == ' ')					//if the character at 'i' is a space encode the char to 26
			{
				temp = "26";
			}
			else if(s.charAt(i) == ',')					//if the character at 'i' is a comma encode the char to 26
			{
				temp = "27";
			}
			else if(s.charAt(i) == '.')					//if the character at 'i' is a period encode the char to 26
			{
				temp = "28";
			}
			else 
			{
				a = s.charAt(i) - 97;
				temp = Integer.toString(a);
			}
			
			
			if(temp.length() == 1) //check if the encoded string has 2 digits
			{
				converted += "0" + temp;
			}
			else {
				converted += temp;
			}
			
		}
		return converted;
		
	}
	
	/*
	 * Encrypts a String using RSA encryption algorithm
	 * */
	
	public static String encrypt(String s, BigInteger E, BigInteger N) {
		String encryption = "";
		String temp = "";
		int track = 0; 
		
		while(s.length()%6 != 0) //pad the string with spaces to make sure last block has 6 digits
		{
			s += "26";
		}
		for(int i = 0; i < s.length(); i++)
		{
			if(i%6 == 0 && i > 0 || i == s.length() - 1)
			{
				if(i == s.length() - 1) {
					temp += s.charAt(i);
				}
				BigInteger P = new BigInteger(temp);
				BigInteger C = P.modPow(E, N);
				if(track == 0) {
					encryption += C.toString();
					track = 1;
				}
				else {
					encryption += " " + C.toString();
				}
		        temp = "";
			}
			temp += s.charAt(i);
		}

		return encryption;
	}
	
	/*
	 * Outputs the encryption to a file
	 * */
	public static void writeNewFile(String encryption) {
	    try {
	        FileWriter fileWriter = new FileWriter("test.enc");
	        PrintWriter printWriter = new PrintWriter(fileWriter);
	        printWriter.print(encryption);
	        printWriter.close();
	    } 
	    //Error occurred if something doesn't process right.
	    catch (IOException e) {
	        System.out.println("An error occurred. File Not Created");
	        e.printStackTrace();
	    }
	    //Indicates that test.enc file has been created 
	    System.out.println("File Successfully created!");
	}
	
}