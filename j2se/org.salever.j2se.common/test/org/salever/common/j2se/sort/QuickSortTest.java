/**
 * 
 */
package org.salever.common.j2se.sort;

import java.util.Arrays;

import org.salever.j2se.common.sort.QuickSort;
import org.salever.j2se.common.sort.SortUtil;

/**
 * @author LiXiaopeng
 *
 */
public class QuickSortTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		testInsertSort(1000000);
		

	}

	public static void testInsertSort(int length){
		int[] array = SortUtil.createArray(length);
		SortUtil.printArray(array);
		long l1 = System.currentTimeMillis();
		QuickSort.quickSort(array);
		long l2 = System.currentTimeMillis();
		System.out.println("QuickSort time takes " + (l2 - l1) + " ms");
		SortUtil.printArray(array);
		
		array = SortUtil.createArray(length);
		SortUtil.printArray(array);
		long l4 = System.currentTimeMillis();
		Arrays.sort(array);
		long l3 = System.currentTimeMillis();
		System.out.println("QuickSort API time takes " + (l3 - l4) + " ms");
		SortUtil.printArray(array);
	}
}
