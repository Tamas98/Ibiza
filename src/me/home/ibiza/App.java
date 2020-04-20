package me.home.ibiza;

import java.math.BigInteger;

import me.home.ibiza.eaAndeea.ExtendedEuclidesAlgorithm;

public class App {

	public static void main(String[] args) {
		ExtendedEuclidesAlgorithm eea = new ExtendedEuclidesAlgorithm(BigInteger.valueOf(1200),BigInteger.valueOf(35));
		System.out.println("LNKO of 1200 and 35 is: " + eea.doExtendedEuclidesAlgorithm());
	}

}
