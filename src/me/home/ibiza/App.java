package me.home.ibiza;

import me.home.ibiza.eaAndeea.ExtendedEuclidesAlgorithm;

public class App {

	public static void main(String[] args) {
		ExtendedEuclidesAlgorithm eea = new ExtendedEuclidesAlgorithm(100,35);
		System.out.println("LNKO of 100 and 35 is: " + eea.doExtendedEuclidesAlgorithm());
	}

}
