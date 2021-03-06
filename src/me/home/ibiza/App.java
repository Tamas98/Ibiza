package me.home.ibiza;

import java.math.BigInteger;
import java.util.Map;

import me.home.ibiza.eaAndeea.ExtendedEuclidesAlgorithm;
import me.home.ibiza.fme.FastModularExponentation;
import me.home.ibiza.mr.MillerRabin;

public class App {

	public static void main(String[] args) {
		System.out.println("Extended Euclisdes Algorithm:");
		ExtendedEuclidesAlgorithm eea = new ExtendedEuclidesAlgorithm(BigInteger.valueOf(210700),BigInteger.valueOf(3));
		Map<String, BigInteger> result =  eea.doExtendedEuclidesAlgorithm();
		System.out.println("LNKO of 1200 and 35 is: " + result.get("lnko") + "\nX: " + result.get("d") + "\n");
		
		System.out.println("Fast Modular Exponentation:");
		FastModularExponentation fme = new FastModularExponentation(BigInteger.valueOf(488691720833l),BigInteger.valueOf(477439151597l),BigInteger.valueOf(596800498919l));
		System.out.printf("%d on the power of %d mod %d = %d\n",5,117,19,fme.doFastModularExponentation());
		
		System.out.println("Miller-Rabin test:");
		MillerRabin mr = new MillerRabin(BigInteger.valueOf(15),3);
		System.out.println("15 is probably prime: " + mr.doMRtest() + "! tested 3 times");

		MillerRabin mr2 = new MillerRabin(BigInteger.valueOf(17),5);
		System.out.println("17 is probably prime: " + mr2.doMRtest() + "! tested 5 times");

		
		FastModularExponentation fme2 = new FastModularExponentation(BigInteger.valueOf(5),BigInteger.valueOf(117),BigInteger.valueOf(19));

		fme2.doFastModularExponentation();
	}

}
