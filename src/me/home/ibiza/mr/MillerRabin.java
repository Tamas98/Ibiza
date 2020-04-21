package me.home.ibiza.mr;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MillerRabin {
	
	private BigInteger n;
	
	private int testTime;

	private BigInteger a;
	
	private BigInteger d;
	
	private BigInteger s;
	
	private List<BigInteger> r;
	
	private List<BigInteger> alreadyTestedWith;
	
	public MillerRabin(BigInteger n,int testTimes) {
		if(n.remainder(BigInteger.TWO).compareTo(BigInteger.ZERO) == 0) {
			throw new IllegalArgumentException("n cannot be odd");
		}
		this.n = n;
		this.testTime = testTimes;
		d = calculateDandSetS();
		r = fillR();
		alreadyTestedWith = new ArrayList<>();
	}
	
	public boolean doMRtest() {
		boolean result = true;
		int runnedTestCases = 0;
		while(runnedTestCases < testTime) {
			boolean isPrime = false;
			this.a = generateRandomA();
			System.out.println(a + "\t" + doTestWithoutPowers() + "\t" + doTestWithPowers());
			isPrime = isPrime || doTestWithoutPowers() | doTestWithPowers();
			result = result && isPrime;
			runnedTestCases++;
		}
		
		return result;
	}


	private BigInteger generateRandomA() {
		Random rand = new Random();
        BigInteger min = BigInteger.TWO;
        BigInteger center = n.subtract(min);
        int maxNumBitLength = n.bitLength();
        BigInteger result = new BigInteger(maxNumBitLength,rand);
       
        if (result.compareTo(min) < 0)
            result = result.add(min);
          if (result.compareTo(n) >= 0)
            result = result.mod(center).add(min);
        
        
        while(result.equals(BigInteger.ZERO) || result.equals(BigInteger.ONE) || alreadyTestedWith.contains(result)) {
        	result = new BigInteger(n.bitCount(), rand);
        }
        
        alreadyTestedWith.add(result);
        return result;
	}

	private boolean doTestWithPowers() {
		
		boolean isPrime = false;
		
		if(r.size() == 1) {
			
			return a.modPow(d.multiply(BigInteger.TWO.pow(r.get(0).intValue())), n).equals(n.subtract(BigInteger.ONE));
		
		}else {
		
			for(BigInteger power : r) {
				BigInteger nextMod = a.modPow(d.multiply(BigInteger.TWO.pow(power.intValue())), n);
				isPrime = isPrime || nextMod.equals(BigInteger.ONE.negate()) || nextMod.equals(n.subtract(BigInteger.ONE));
			}
		
		}
		
		return isPrime;
	}

	private boolean doTestWithoutPowers() {
		BigInteger baseValue = a.modPow(d, n);
		return baseValue.equals(BigInteger.ONE);
	}

	private List<BigInteger> fillR() {
		List<BigInteger> result = new ArrayList<>();

		for(BigInteger i = BigInteger.ZERO; i.compareTo(s) < 0;i = i.add(BigInteger.ONE)) {
			result.add(i);
		}
		
		return result;
	}


	private BigInteger calculateDandSetS() {
		BigInteger base = n.subtract(BigInteger.ONE);
		BigInteger counter = BigInteger.ZERO;
		
		while(!base.remainder(BigInteger.TWO).equals(BigInteger.ONE)) {
			base = base.divide(BigInteger.TWO);
			counter = counter.add(BigInteger.ONE);
		}
		
		this.s = counter;
		return base;
	}

}
