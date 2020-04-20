package me.home.ibiza.eaAndeea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExtendedEuclidesAlgorithm {

	private int a;
	
	private int b;
	
	private List<Integer> resultTable;
	
	private List<Integer> xRow;
	private List<Integer> yRow;

	
	public ExtendedEuclidesAlgorithm(int a, int b) {
		this.a = a;
		this.b = b;
		resultTable = new ArrayList<>();
		xRow = new ArrayList<>();
		yRow = new ArrayList<>();
		
	}
	
	public int doExtendedEuclidesAlgorithm() {
		initializaTable();
		while(true) {
			double remainder = calculateNextElement();
			
			if(hasToContinue(remainder)) {
				break;
			}
		}
		printResultFormula();
		return resultTable.get(resultTable.size()-1);
	}
	
	private void printResultFormula() {
		int lnko = resultTable.get(resultTable.size()-1);
		double x = calculateXvalue();
		double y = calculateYvalue();
		
		if(isResultCorrect(lnko,x,y)) {
			System.out.printf("%d = %d * %.0f + %d * %.0f\n",lnko,resultTable.get(0),x,resultTable.get(1),y);
		}else {
			System.out.println("Something went wrong");
		}
	}

	private boolean isResultCorrect(int lnko, double x, double y) {
		return lnko == resultTable.get(0)*x + resultTable.get(1)*y;
	}

	private double calculateXvalue() {
		int lastIndex = resultTable.size()-1;
		return Math.pow(-1, lastIndex)*xRow.get(lastIndex);
	}
	
	private double calculateYvalue() {
		int lastIndex = resultTable.size()-1;
		return Math.pow(-1, lastIndex+1)*yRow.get(lastIndex);
	}

	private boolean hasToContinue(double remainder) {
		return resultTable.get(resultTable.size()-1) == remainder;
	}

	private boolean checkRemainder(double remainder) {
		return remainder == 0;
	}

	private double calculateNextElement() {
		int indexOfLastElement = resultTable.size()-1;
		
		int nextElement = resultTable.get(indexOfLastElement-1)%resultTable.get(indexOfLastElement);
		
		calculateNextXandY((int) Math.floor(resultTable.get(indexOfLastElement-1)/resultTable.get(indexOfLastElement)));
		
		if(checkRemainder(nextElement)) {
			return resultTable.get(indexOfLastElement);
		}
		
		resultTable.add(nextElement);
		
		return 0;
	}
	
	private void calculateNextXandY(int divisor) {
		int lastXElement = xRow.size()-1;
		int lastYElement = yRow.size()-1;
		
		xRow.add(divisor*xRow.get(lastXElement)+xRow.get(lastXElement-1));
		yRow.add(divisor*yRow.get(lastYElement)+yRow.get(lastYElement-1));

	}

	private void initializaTable() {
		if(a > b) {
			resultTable.add(a);
			resultTable.add(b);
		}else {
			resultTable.add(b);
			resultTable.add(a);
		}
		
		xRow.addAll(Arrays.asList(1,0));
		yRow.addAll(Arrays.asList(0,1));
	}
}
