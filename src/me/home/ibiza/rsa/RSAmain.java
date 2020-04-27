package me.home.ibiza.rsa;

import java.math.BigInteger;
import java.util.Scanner;

public class RSAmain {

	private final static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		RSA rsa = new RSA();
		String nextStep = "";
		while(true) {
			System.out.print("What you wanna do?(e = enc,d = dec, q = quit)");
			nextStep = scanner.nextLine();
			if(nextStep.equalsIgnoreCase("q")) {
				break;
			}else if(nextStep.equals("e")) {
				System.out.print("Message to encrypt: ");
				String messageToEncrypt = scanner.nextLine();
				System.out.printf("Result: %d\n",rsa.encrypt(new BigInteger(messageToEncrypt)));
				System.out.println("NextStep!");
			}else if(nextStep.equals("d")) {
				System.out.print("Message to decrypt: ");
				String messageToDecrypt = scanner.nextLine();
				System.out.printf("Result: %d\n",rsa.decrypt(new BigInteger(messageToDecrypt)));
				System.out.println("NextStep!");
			}
		}
	}

}
