package RSA;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;

public class RSADecrypt {

	public static void main(String[] args) 
	{
		// Get string array of data from the files
		String data[] = readDataFromFile(args);
		 
		String encryption = data[0];
		BigInteger D = new BigInteger(data[1].substring(2));
		BigInteger N = new BigInteger(data[2].substring(2));

		//decrpyt the message
		String decryption = decrypt(encryption, D, N);
		
		//convert the decryption to text
		decryption = convertToText(decryption);
		
		//output the encryption to a file
		writeNewFile(decryption);

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
	 * Decrypts a String using RSA Decryption algorithm
	 * */
	
	public static String decrypt(String encryption, BigInteger D, BigInteger N) {
		String decryption = "";
		String temp = "";
		 
		
		for(int i = 0; i < encryption.length(); i++)
		{
			if(encryption.charAt(i) == ' ' || i == encryption.length() - 1 )
			{
				if(i == encryption.length() - 1) 
				{
					temp += encryption.charAt(i);
				}
				BigInteger C = new BigInteger(temp);
				BigInteger P = C.modPow(D, N);
				temp = P.toString();
				 while(temp.length() < 6) 
				 {
					 temp = '0' + temp;
				 }
				decryption += temp;
		        temp = "";
			}
			else 
			{

				temp += encryption.charAt(i);
			}

		}

		return decryption;
	}
	/*
	 * Converts the decryption integers to text
	 * */
	public static String convertToText(String decryption) {
		
		String s = "";
		String message = "";
		
		for(int i = 0; i < decryption.length(); i++) {
			if(s.length() == 2 || i == decryption.length() - 1 ) {
					if(i == decryption.length() - 1) {
						s += decryption.charAt(i);
					}
				message += Convert(s);
				
				s = "" + decryption.charAt(i);
			}	
			else {
				s += decryption.charAt(i);
			}	
		}
		return message;
	}
	/*
	 * Outputs the Decryption to a file
	 * */
	public static void writeNewFile(String encryption) {
	    try {
	        FileWriter fileWriter = new FileWriter("test.dec");
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
	
	/*
	 * //Converts string of ints to a char and returns the char
	 * */
		public static char Convert(String c)
		  {
			
		    // substitute the old char for our CASCII value
		    switch ( c )
		    {
		      case "00":
		    	  
			    return 'A'; 
		      case "01":
		        return 'B';

		      case "02":
		        return 'C';

		      case "03":
		        return 'D';

		      case "04":
		        return 'E';

		      case "05":
		        return 'F';

		      case "06":
		        return 'G';

		      case "07":
		        return 'H';

		      case "08":
		        return 'I';

		      case "09":
		        return 'J';

		      case "10":
		        return 'K';

		      case "11":
		        return 'L';

		      case "12":
		        return 'M';

		      case "13":
		        return 'N';

		      case "14":
		        return 'O';

		      case "15":
		        return 'P';

		      case "16":
		        return 'Q';

		      case "17":
		        return 'R';

		      case "18":
		        return 'S';

		      case "19":
		        return 'T';

		      case "20":
		        return 'U';
		        
		      case "21":
			    return 'V';
		      
		      case "22":
			    return 'W';
		      
		      case "23":
			    return 'X';
		      
		      case "24":
			    return 'Y';
		      
		      case "25":
			    return 'Z';
			  
		      case "26":
		    	return ' ';
		      case "27":
		    	return ',';
		      case "28":
		    	return '.';
		    	
		      default:
		        throw new java.lang.IllegalArgumentException(
		            "Error with " + c);
		    }
		  }
}
