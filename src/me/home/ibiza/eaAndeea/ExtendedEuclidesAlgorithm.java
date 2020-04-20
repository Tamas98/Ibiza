package me.home.ibiza.eaAndeea;

import java.util.ArrayList;
import java.util.List;

public class ExtendedEuclidesAlgorithm {

	private int a;
	
	private int b;
	
	private List<Integer> resultTable;

	public ExtendedEuclidesAlgorithm(int a, int b) {
		this.a = a;
		this.b = b;
		resultTable = new ArrayList<>();
	}
	
	public int doExtendedEuclidesAlgorithm() {
		initializaTable();
		while(true) {
			double remainder = calculateNextElement();
			
			if(hasToContinue(remainder)) {
				break;
			}
		}
		
		return resultTable.get(resultTable.size()-1);
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
		
		if(checkRemainder(nextElement)) {
			return resultTable.get(indexOfLastElement);
		}
		
		resultTable.add(nextElement);
		
		return 0;
	}

	private void initializaTable() {
		if(a > b) {
			resultTable.add(a);
			resultTable.add(b);
		}else {
			resultTable.add(b);
			resultTable.add(a);
		}
	}
}
