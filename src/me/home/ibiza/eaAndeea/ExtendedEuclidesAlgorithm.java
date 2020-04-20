package me.home.ibiza.eaAndeea;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExtendedEuclidesAlgorithm {

	private BigInteger a;
	
	private BigInteger b;
	
	private List<BigInteger> resultTable;
	
	private List<BigInteger> xRow;
	private List<BigInteger> yRow;

	
	public ExtendedEuclidesAlgorithm(BigInteger a, BigInteger b) {
		this.a = a;
		this.b = b;
		resultTable = new ArrayList<>();
		xRow = new ArrayList<>();
		yRow = new ArrayList<>();
		
	}
	
	public BigInteger doExtendedEuclidesAlgorithm() {
		initializaTable();
		while(true) {
			BigInteger remainder = calculateNextElement();
			
			if(hasToContinue(remainder)) {
				break;
			}
		}
		printResultFormula();
		return resultTable.get(resultTable.size()-1);
	}
	
	private void printResultFormula() {
		BigInteger lnko = resultTable.get(resultTable.size()-1);
		BigInteger x = calculateXvalue();
		BigInteger y = calculateYvalue();
		
		if(isResultCorrect(lnko,x,y)) {
			System.out.printf("%d = %d * %d + %d * %d\n",lnko,resultTable.get(0),x,resultTable.get(1),y);
		}else {
			System.out.println("Something went wrong");
		}
	}

	private boolean isResultCorrect(BigInteger lnko, BigInteger x, BigInteger y) {
		return lnko.compareTo(resultTable.get(0).multiply(x).add(resultTable.get(1).multiply(y))) == 0;
	}

	private BigInteger calculateXvalue() {
		int lastIndex = resultTable.size()-1;
		return xRow.get(lastIndex).multiply(BigInteger.valueOf((long) Math.pow(-1, lastIndex)));
	}
	
	private BigInteger calculateYvalue() {
		int lastIndex = resultTable.size()-1;
		return yRow.get(lastIndex).multiply(BigInteger.valueOf((long) Math.pow(-1, lastIndex+1)));
	}

	private boolean hasToContinue(BigInteger remainder) {
		return resultTable.get(resultTable.size()-1).compareTo(remainder) == 0;
	}

	private boolean checkRemainder(BigInteger nextElement) {
		return nextElement.compareTo(BigInteger.valueOf(0)) == 0;
	}

	private BigInteger calculateNextElement() {
		int indexOfLastElement = resultTable.size()-1;
		
		BigInteger nextElement = resultTable.get(indexOfLastElement-1).remainder(resultTable.get(indexOfLastElement));
		
		calculateNextXandY(resultTable.get(indexOfLastElement-1).divide(resultTable.get(indexOfLastElement)));
		
		if(checkRemainder(nextElement)) {
			return resultTable.get(indexOfLastElement);
		}
		
		resultTable.add(nextElement);
		
		return BigInteger.valueOf(0);
	}
	
	private void calculateNextXandY(BigInteger divisor) {
		int lastXElement = xRow.size()-1;
		int lastYElement = yRow.size()-1;
		
		xRow.add(divisor.multiply(xRow.get(lastXElement)).add(xRow.get(lastXElement-1)));
		yRow.add(divisor.multiply(yRow.get(lastYElement)).add(yRow.get(lastYElement-1)));

	}

	private void initializaTable() {
		if(a.compareTo(b) == 1) {
			resultTable.add(a);
			resultTable.add(b);
		}else {
			resultTable.add(b);
			resultTable.add(a);
		}
		
		xRow.addAll(Arrays.asList(BigInteger.valueOf(1),BigInteger.valueOf(0)));
		yRow.addAll(Arrays.asList(BigInteger.valueOf(0),BigInteger.valueOf(1)));
	}
}
