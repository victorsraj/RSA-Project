package RSA;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

public class RSAGenKey {
	public static void main(String[] args) {

		if (args.length == 3) {
			BigInteger p = new BigInteger(args[0]);
			BigInteger q = new BigInteger(args[1]);
			BigInteger e = new BigInteger(args[2]);

			BigInteger n = p.multiply(q);
			BigInteger p_1 = p.subtract(new BigInteger("1"));
			BigInteger q_1 = q.subtract(new BigInteger("1"));
			BigInteger z = p_1.multiply(q_1);

			BigInteger d = e.modInverse(z);

			System.out.println("public key [" + e + "   ,   " + n + "]");
			System.out.println("private key [" + d + "   ,   " + n + "]");	
			
			String newLine = System.getProperty("line.separator");
			String pubKeyLine ="e="+e + newLine + "n="+n;
			String priKeyLine ="d="+d + newLine + "n="+n;
			
			FileWriter pub_writer;
			FileWriter pri_writer;
			try {
				pub_writer = new FileWriter("pub_key.txt", false);
				pub_writer.write(pubKeyLine);
						
				pri_writer = new FileWriter("pri_key.txt", false);
				pri_writer.write(priKeyLine);
				
				pub_writer.close();
				pri_writer.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if (args.length == 1) {
			int k = Integer.parseInt(args[0]);
			Random r = new Random();
			BigInteger p = BigInteger.probablePrime(k, r);
			BigInteger q = BigInteger.probablePrime(k, r);
			System.out.println("Randomly selected p,q are " + p + "," + q);

			BigInteger n = p.multiply(q);
			BigInteger p_1 = p.subtract(new BigInteger("1"));
			BigInteger q_1 = q.subtract(new BigInteger("1"));
			BigInteger z = p_1.multiply(q_1);
			int key = 2;

			while (true) {
				BigInteger eGCD = z.gcd(new BigInteger("" + key));
				if (eGCD.equals(BigInteger.ONE)) {
					break;
				}
				key++;
			}

			BigInteger e = new BigInteger("" + key);
			BigInteger d = e.modInverse(z);
			System.out.println("public key [" + e + "   ,   " + n + "]");
			System.out.println("privet key [" + d + "   ,   " + n + "]");
			
			String newLine = System.getProperty("line.separator");
			String pubKeyLine ="e="+e + newLine + "n="+n;
			String priKeyLine ="d="+d + newLine + "n="+n;
			
			FileWriter pub_writer;
			FileWriter pri_writer;
			try {
				pub_writer = new FileWriter("pub_key.txt", false);
				pub_writer.write(pubKeyLine);
				pub_writer.close();
				
				pri_writer = new FileWriter("pri_key.txt", false);
				pri_writer.write(priKeyLine);
				pri_writer.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else {

			// for test , need to be removed
			BigInteger p = new BigInteger("7");// BigInteger.probablePrime(k, r);
			BigInteger q = new BigInteger("17");// BigInteger.probablePrime(k, r);
			System.out.println("Randomly selected p,q are " + p + "," + q);

			BigInteger n = p.multiply(q);
			BigInteger p_1 = p.subtract(new BigInteger("1"));
			BigInteger q_1 = q.subtract(new BigInteger("1"));
			BigInteger z = p_1.multiply(q_1);

			Integer e = 2;
			for (e = 2; e < z.intValue(); e++) {
				if (gcd(e, z.intValue()) == 1) // e is for public key exponent
				{
					break;
				}
			}
			BigInteger e1 = BigInteger.valueOf(e.intValue());
			BigInteger d = e1.modInverse(z);
			System.out.println("public key [" + e1 + "   ,   " + n + "]");
			System.out.println("privet key [" + d + "   ,   " + n + "]");
		}

	}

	// for test , need to be removed
	public static int gcd(int e, int z) {
		if (e == 0)
			return z;
		else
			return gcd(z % e, e);
	}
}
