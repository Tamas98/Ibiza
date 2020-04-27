package me.home.ibiza.fme;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastModularExponentation {
	
	private BigInteger a;

	private BigInteger b;

	private BigInteger c;

	public FastModularExponentation(BigInteger a, BigInteger b, BigInteger c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	public BigInteger doFastModularExponentation() {
		BigInteger firstMod = a.mod(c);
		if(BisPowerOfTwo()) {
			return fastModularWhenBisPowerOfTwo(firstMod);
		}else {
			return fastModularWhenBisNotPowerOfTwo(firstMod);
		}
	}

	private BigInteger fastModularWhenBisNotPowerOfTwo(BigInteger firstMod) {
		List<BigInteger> listOfTwoPowers = generateListOfTwoPowersFromB();	
		BigInteger nextMod = (firstMod.multiply(firstMod)).mod(c);
		List<BigInteger> resultList = new ArrayList<>();
		
		if(listOfTwoPowers.contains(BigInteger.ONE)) {
			resultList.add(firstMod);
		}
		
		if(listOfTwoPowers.contains(BigInteger.TWO)) {
			resultList.add(nextMod);
		}
			
		for(long i = 4; i <= b.intValue(); i = i*2) {
			nextMod = (nextMod.multiply(nextMod)).mod(c);
			if(listOfTwoPowers.contains(BigInteger.valueOf(i))){
				resultList.add(nextMod);
			}
		}
				
		return calculateFinalResult(resultList);
	}

	private BigInteger fastModularWhenBisPowerOfTwo(BigInteger firstMod) {
		BigInteger nextMod = (firstMod.multiply(firstMod)).mod(c);
		for(int i = 4; i <= b.intValue(); i = i*2) {
			nextMod = (nextMod.multiply(nextMod)).mod(c);
		}
		return (nextMod.multiply(nextMod)).mod(c);
	}
	
	private BigInteger calculateFinalResult(List<BigInteger> resultList) {
		BigInteger result = BigInteger.ONE;
		 
		for(BigInteger partialResult : resultList) {
			result = result.multiply(partialResult);
		}
						
		return result.mod(c);
	}

	private List<BigInteger> generateListOfTwoPowersFromB() {
		BigInteger cpyB = b;
		String binary = cpyB.toString(2);
		String[] chars = binary.split("");
		List<BigInteger> result = new ArrayList<>();
				
		for(int i = 0;i < chars.length;i++) {
			if(chars[chars.length-1-i].equals("1")) {
				result.add(BigInteger.valueOf((long)Math.pow(2, i)));
			}
		}
		/*
		int i = 0;
		while(cpyB.compareTo(BigInteger.ONE) != 0) {
			if(cpyB.remainder(BigInteger.TWO).compareTo(BigInteger.ONE) == 0) {
				break;
			}else {
				result.add(BigInteger.valueOf((long)Math.pow(2, i)));
				cpyB = cpyB.divide(BigInteger.TWO);
				i++;
			}
		}*/

		return result;
	}

	private boolean BisPowerOfTwo() {
		return b.mod(BigInteger.TWO).equals(BigInteger.ZERO);
	}
	
}

