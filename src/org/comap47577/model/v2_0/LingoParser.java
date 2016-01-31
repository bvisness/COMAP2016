package org.comap47577.model.v2_0;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LingoParser {
	
	public static final String BREAK_WORD = "go";
	
	private String getLingo() {
		Scanner in = new Scanner(System.in);
		
		while (true) {
			String line = in.nextLine();
			if (line.indexOf(BREAK_WORD) > -1) {
				break;
			}
		}
		System.out.println("Paste your LINGO output below, then press ctrl-d:");
		
		String input = "";
		while (true) {
			String line = in.nextLine();
			if (line.indexOf(BREAK_WORD) > -1) {
				break;
			}
			input += line + "\n";
		}
		
		return input;
	}
	
	public void parseLingo(Model model) {
		String input = getLingo();
		
		Pattern objValuePattern = Pattern.compile("Objective value: +(-?[0-9]+[.][0-9]+)");
		Matcher objValueMatcher = objValuePattern.matcher(input);
		
		Pattern dPattern = Pattern.compile("D([0-9]+) +(-?[0-9]+[.][0-9]+) +(-?[0-9]+[.][0-9]+)");
		Matcher dMatcher = dPattern.matcher(input);
		
		Pattern uPattern = Pattern.compile("U([0-9]+) +(-?[0-9]+[.][0-9]+) +(-?[0-9]+[.][0-9]+)");
		Matcher uMatcher = uPattern.matcher(input);
		
		if (!objValueMatcher.find()) {
			System.out.println("ERROR: Could not find objective function value.");
			return;
		}
		
		double objValue = Double.parseDouble(objValueMatcher.group(1));
		
		int index = input.indexOf("Variable");
		ArrayList<Double> dValues = new ArrayList<Double>();
		ArrayList<Double> dReducedCosts = new ArrayList<Double>();
		ArrayList<Double> uValues = new ArrayList<Double>();
		ArrayList<Double> uReducedCosts = new ArrayList<Double>();
		
		while (dMatcher.find(index)) {
			index = dMatcher.start() + 1;
			int dNumber = Integer.parseInt(dMatcher.group(1));
			double dValue = Double.parseDouble(dMatcher.group(2));
			double dReducedCost = Double.parseDouble(dMatcher.group(3));
			dValues.add(dValue);
			dReducedCosts.add(dReducedCost);
		}
		
		while (uMatcher.find(index)) {
			index = uMatcher.start() + 1;
			int uNumber = Integer.parseInt(uMatcher.group(1));
			double uValue = Double.parseDouble(uMatcher.group(2));
			double uReducedCost = Double.parseDouble(uMatcher.group(3));
			uValues.add(uValue);
			uReducedCosts.add(uReducedCost);
		}
		
		// ---------------------------------------------------
		// Now the fun begins
		// ---------------------------------------------------
		
		{
			System.out.print("Systems in use: ");
			boolean addComma = false;
			for (int i = 0; i < uValues.size(); i++) {
				if (uValues.get(i) == 0) {
					break;
				}
				
				if (addComma) {
					System.out.print(", ");
				}
				System.out.print(i + 1);
				addComma = true;
			}
			System.out.println();
		}
		
		double totalDebrisPerYear = 0;
		double totalDebrisOverall = 0;
		{
			for (int i = 0; i < uValues.size(); i++) {
				if (uValues.get(i) == 0) {
					break;
				}
				
				RemovalSystem system = model.systems[i];
				double debrisPerYear = dValues.get(i) * system.debrisPerDeployment;
				double debrisOverall = debrisPerYear * model.years;
				
				System.out.println("Deployments per year for system " + (i + 1) + ": " + dValues.get(i));
				System.out.println(" - Debris brought down per year: " + debrisPerYear);
				System.out.println(" - Debris brought down over all " + model.years + " years: " + debrisOverall);
				
				totalDebrisPerYear += debrisPerYear;
				totalDebrisOverall += debrisOverall;
			}
		}
		
		double requiredDebris = model.minDebrisPerYear * model.years;
		System.out.println("Debris totals: ");
		System.out.println(" - Total debris brought down per year: " + totalDebrisPerYear);
		System.out.println("   - Excess beyond required " + model.minDebrisPerYear + " debris per year: " + (totalDebrisPerYear - model.minDebrisPerYear));
		System.out.println(" - Total debris brought down overall:  " + totalDebrisOverall);
		System.out.println("   - Excess beyond required " + requiredDebris + " debris: " + (totalDebrisOverall - requiredDebris));
		
		double totalRevenue = model.totalRevenue(dValues);
		double totalCost = model.totalCost(dValues, uValues);
		System.out.println("Total profit: " + objValue + " million");
		System.out.println(" - Total revenue: " + totalRevenue + " million");
		System.out.println(" - Total cost: " + totalCost + " million");
		System.out.println(" - Difference: " + (totalRevenue - totalCost) + " million");
	}

}
