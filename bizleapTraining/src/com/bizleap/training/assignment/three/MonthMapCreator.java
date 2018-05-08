package com.bizleap.training.assignment.three;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;

public class MonthMapCreator {
	public Map<Integer, Object> monthMap = new HashMap<Integer, Object>();
	List<Integer> listOfYear = new ArrayList<Integer>();

	private class Month {
		String name;
		int numberOfDays, year;

		public Month(String name, int numberOfDays, int year) {
			this.name = name;
			this.numberOfDays = numberOfDays;
			this.year = year;
		}

		public String toString() {
			return this.name + "/" + this.year;
		}
	}

	private boolean isValid(int year) {
		return year > 0;
	}

	private boolean isValid(int fromYear, int toYear) {
		return !(fromYear <= 0 || toYear <= 0 || fromYear > toYear);
	}

	private boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
	}

	private List<Month> createMonthsForYear(int fromYear) {
		List<Month> monthList = new ArrayList<Month>();

		monthList.add(new Month("January", 31, fromYear));

		if (isLeapYear(fromYear))
			monthList.add(new Month("February", 29, fromYear));
		else
			monthList.add(new Month("February", 28, fromYear));

		monthList.add(new Month("March", 31, fromYear));
		monthList.add(new Month("April", 30, fromYear));
		monthList.add(new Month("May", 31, fromYear));
		monthList.add(new Month("June", 30, fromYear));
		monthList.add(new Month("July", 31, fromYear));
		monthList.add(new Month("August", 31, fromYear));
		monthList.add(new Month("September", 30, fromYear));
		monthList.add(new Month("October", 31, fromYear));
		monthList.add(new Month("November", 30, fromYear));
		monthList.add(new Month("December", 31, fromYear));

		return monthList;
	}

	private List<Month> createMonthsForYearRange(int fromYear, int toYear) {
		List<Month> monthList = new ArrayList<Month>();
		for (int year = fromYear; year <= toYear; year++) {
			monthList.addAll(createMonthsForYear(year));
		}
		return monthList;
	}

	public Map<Integer, Object> createMonthMap(int year) {
		if (isValid(year)) {
			for (Month month : createMonthsForYear(year)) {
				addMonthToMap(month);
			}
		} else {
			monthMap.put(year, "Year is invalid");
		}
		return monthMap;
	}

	private Map<Integer, Object> createMonthMap(int fromYear, int toYear) {
		if (isValid(fromYear, toYear)) {
			for (int year = fromYear; year <= toYear; year++) {
				createMonthMap(year);
				addYearToList(year);
			}
		} else {
			monthMap.put(fromYear, "Range or Years are invalid!");
			monthMap.put(toYear, "Range or Years are invalid!");
		}
		return monthMap;
	}

	public Map<Integer, Object> createMonthMapRange(int fromYear, int toYear) {
		if (isValid(fromYear, toYear)) {
			for (Month month : createMonthsForYearRange(fromYear, toYear)) {
				addMonthToMap(month);
			}
		} else {
			monthMap.put(fromYear, "Range or years are invalid");
			monthMap.put(toYear, "Range or years are invalid");
		}
		return monthMap;
	}

	private void addMonthToMap(Month month) {
		List<Month> monthList = (List<Month>) monthMap.get(month.numberOfDays);
		if (monthList != null)
			monthList.add(month);
		else {
			monthList = new ArrayList<Month>();
			monthList.add(month);
			monthMap.put(month.numberOfDays, monthList);
		}
	}

	public void prettyPrint() {
		Iterator<Entry<Integer, Object>> iterator = monthMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Integer, Object> mapEntry = (Entry<Integer, Object>) iterator.next();
			System.out.println("Months For " + mapEntry.getKey() + " Days = \"" + mapEntry.getValue() + "\"");
		}
	}

	public void addYearToList(int year) {
		listOfYear.add(year);
	}

	private boolean hasValue(int year, List<Month> monthList) {
		for (Month month : monthList) {
			if (month.year == year)
				return true;
		}
		return false;
	}

	public void prettyPrint2() {
		for (int day : monthMap.keySet()) {
			System.out.println("Months for " + day + " Days in given year \n");
			for (int year : listOfYear) {
				List<Month> monthList = (List<Month>) monthMap.get(day);
				if (hasValue(year, monthList)) {
					for (Month month : monthList) {
						if (monthList != null && monthList.size() > 0) {
							if (month.year == year)
								System.out.print(month + "  ");
						}
					}
					System.out.println();
				}
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		MonthMapCreator monthMapCreator = new MonthMapCreator();
		monthMapCreator.createMonthMap(2000, 2007);
		monthMapCreator.prettyPrint();
		System.out.println();
		monthMapCreator.prettyPrint2();
	}
}