package org.salever.j2se.common.java6;

import java.io.PrintStream;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class Navigable {
	public static void main(String args[]) {
		Calendar now = Calendar.getInstance();
		Locale locale = Locale.getDefault();
		PrintStream console = System.out;
		Map<String, Integer> names = now.getDisplayNames(Calendar.DAY_OF_WEEK,
				Calendar.LONG, locale);
		NavigableMap<String, Integer> nav = new TreeMap<String, Integer>(names);
		console.printf("Whole list:%n%s%n", nav);
		console.printf("First key: %s\tFirst entry: %s%n", nav.firstKey(),
				nav.firstEntry());
		console.printf("Last key: %s\tLast entry: %s%n", nav.lastKey(),
				nav.lastEntry());

		console.printf("Map before Sunday: %s%n",
				nav.subMap("Manday", "Sunday"));

		console.printf("Key floor before Sunday: %s%n", nav.floorKey("Sunday"));
		console.printf("Key lower before Sunday: %s%n", nav.lowerKey("Sunday"));
		console.printf("Key ceiling after Sunday: %s%n",
				nav.ceilingKey("Sunday"));
		console.printf("Key higher after Sunday: %s%n", nav.higherKey("Sunday"));
	}
}