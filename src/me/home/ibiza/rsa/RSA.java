package me.home.ibiza.rsa;

import java.math.BigInteger;
import java.util.Map;
import java.util.Random;

import me.home.ibiza.eaAndeea.ExtendedEuclidesAlgorithm;
import me.home.ibiza.fme.FastModularExponentation;
import me.home.ibiza.mr.MillerRabin;

public class RSA {
	
	private BigInteger p;
	
	private BigInteger q;
	
	private BigInteger n;
	
	private BigInteger eulerN;
	
	private BigInteger e;
	
	private BigInteger d;
	
	public RSA() {
		p = generateRandomPrime();
		q = generateRandomPrime();
		this.n = p.multiply(q);
		this.eulerN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		this.e = searchE();
	}

	private BigInteger searchE() {
		BigInteger testedNumber = BigInteger.valueOf(3);
		while(!testedNumber.equals(this.eulerN)) {
			ExtendedEuclidesAlgorithm eea = new ExtendedEuclidesAlgorithm(this.eulerN,testedNumber);
			Map<String, BigInteger> euclidesResult = eea.doExtendedEuclidesAlgorithm();
			if(euclidesResult.get("lnko").equals(BigInteger.ONE)) {
				this.d = euclidesResult.get("d");
				if(this.d.compareTo(BigInteger.ONE) <= 0) {
					this.d = this.d.add(eulerN);
				}
				return testedNumber;
			}
			
			testedNumber = testedNumber.add(BigInteger.ONE);
		}
		throw new RuntimeException("Error while calculating e");
	}

	private BigInteger generateRandomPrime() {
		BigInteger underTest = BigInteger.ZERO;
		while(true) {
			 underTest = generateRandomBigInteger();
			MillerRabin mr = new MillerRabin(underTest,3);
			if(mr.doMRtest()) {
				return underTest;
			}
		}
	}
	
	private BigInteger generateRandomBigInteger() {
		Random rand = new Random();
		BigInteger max = BigInteger.valueOf(100000000000000l);
        BigInteger min = BigInteger.valueOf(1000000000l);
        BigInteger center = max.subtract(min);
        int maxNumBitLength = max.bitLength();
        BigInteger result = new BigInteger(maxNumBitLength,rand);
       
        if (result.compareTo(min) < 0)
            result = result.add(min);
        if (result.compareTo(max) >= 0)
            result = result.mod(center).add(min);
        
        return result;
       
	}
	
	public BigInteger encrypt(BigInteger message) {
		
		if(n.compareTo(message) == -1) {
			throw new IllegalArgumentException("Your message is greater than n!CANNOT ENCRYPT");
		}

		FastModularExponentation fme = new FastModularExponentation(message,e,n);
		BigInteger result = fme.doFastModularExponentation();

		return result;
	}
	
	public BigInteger decrypt(BigInteger message) {
		return message.modPow(d, n);
	}
}
