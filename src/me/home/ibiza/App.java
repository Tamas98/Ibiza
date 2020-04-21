package me.home.ibiza;

import java.math.BigInteger;

import me.home.ibiza.eaAndeea.ExtendedEuclidesAlgorithm;
import me.home.ibiza.fme.FastModularExponentation;
import me.home.ibiza.mr.MillerRabin;

public class App {

	public static void main(String[] args) {
		System.out.println("Extended Euclisdes Algorithm:");
		ExtendedEuclidesAlgorithm eea = new ExtendedEuclidesAlgorithm(BigInteger.valueOf(1200),BigInteger.valueOf(35));
		System.out.println("LNKO of 1200 and 35 is: " + eea.doExtendedEuclidesAlgorithm() + "\n");
		
		System.out.println("Fast Modular Exponentation:");
		FastModularExponentation fme = new FastModularExponentation(BigInteger.valueOf(5),BigInteger.valueOf(117),BigInteger.valueOf(19));
		System.out.printf("%d on the power of %d mod %d = %d\n",5,117,19,fme.doFastModularExponentation());
		
		System.out.println("Miller-Rabin test:");
		MillerRabin mr = new MillerRabin(BigInteger.valueOf(127),3);
		System.out.println("127 is probably prime: " + mr.doMRtest() + "! tested 3 times");

	}

}
