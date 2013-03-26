/**
 * 
 */
package org.salever.common.j2se.sort;

import java.io.IOException;

import org.salever.j2se.common.sort.HeapSort;
import org.salever.j2se.common.sort.SortUtil;

/**
 * @author LiXiaopeng
 *
 */
public class SortTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int length = 1000000;
	   // testInsertSort(length);
	    //testInsertSortWhenSorted(length);
		//testSelectSort(length);
		//testSelectSortWhenSorted(length);
		testMergeSort(length);
		//testMergeSortWhenSorted(length);
		//testBubbleSort(length);
		//testBubbleSortWhenSorted(length);
		//testInsertSortRecu(length);
		//testInsertSortRecuWhenSorted(length);
		testHeapSort(length);
		QuickSortTest.testInsertSort(length);
		
	}
	
	public static void testInsertSort(int length){
		int[] array = SortUtil.createArray(length);
		SortUtil.printArray(array);
		long l1 = System.currentTimeMillis();
		SortUtil.insertSort(array);
		long l2 = System.currentTimeMillis();
		System.out.println("InsertSort time takes " + (l2 - l1) + " ms");
		SortUtil.printArray(array);
		
	}
	
	public static void testInsertSortWhenSorted(int length){
		int[] array = SortUtil.createArray(length);
		SortUtil.mergeSort(array, 0, length -1);
		SortUtil.printArray(array);
		long l1 = System.currentTimeMillis();
		SortUtil.insertSort(array);
		long l2 = System.currentTimeMillis();
		System.out.println("InsertSortWhenSorted time takes " + (l2 - l1) + " ms");
		SortUtil.printArray(array);
		
	}
	
	public static void testSelectSort(int length){
		int[] array = SortUtil.createArray(length);
		SortUtil.printArray(array);
		long l1 = System.currentTimeMillis();
		SortUtil.selectSort(array);
		long l2 = System.currentTimeMillis();
		System.out.println("SelectSort time takes " + (l2 - l1) + " ms");
		SortUtil.printArray(array);
	}
	
	public static void testSelectSortWhenSorted(int length){
		int[] array = SortUtil.createArray(length);
		SortUtil.mergeSort(array, 0, length -1);
		SortUtil.printArray(array);
		long l1 = System.currentTimeMillis();
		SortUtil.selectSort(array);
		long l2 = System.currentTimeMillis();
		System.out.println("testSelectSortWhenSorted time takes " + (l2 - l1) + " ms");
		SortUtil.printArray(array);
	}

	public static void testMergeSort(int length){
		int[] array = SortUtil.createArray(length);
		SortUtil.printArray(array);
		long l1 = System.currentTimeMillis();
		SortUtil.mergeSort(array, 0, length -1);
		long l2 = System.currentTimeMillis();
		System.out.println("MergeSort time takes " + (l2 - l1) + " ms");
		SortUtil.printArray(array);
	}
	
	public static void testMergeSortWhenSorted(int length){
		int[] array = SortUtil.createArray(length);
		SortUtil.insertSort(array);
		SortUtil.printArray(array);
		long l1 = System.currentTimeMillis();
		SortUtil.mergeSort(array, 0, length -1);
		long l2 = System.currentTimeMillis();
		System.out.println("MergeSortWhenSorted time takes " + (l2 - l1) + " ms");
		SortUtil.printArray(array);
	}
	
	public static void testBubbleSort(int length){
		int[] array = SortUtil.createArray(length);
		SortUtil.printArray(array);
		long l1 = System.currentTimeMillis();
		SortUtil.bubbleSort(array);
		long l2 = System.currentTimeMillis();
		System.out.println("BubbleSort time takes " + (l2 - l1) + " ms");
		SortUtil.printArray(array);
	}
	
	public static void testBubbleSortWhenSorted(int length){
		int[] array = SortUtil.createArray(length);
		SortUtil.printArray(array);
		SortUtil.mergeSort(array, 0, length -1);
		long l1 = System.currentTimeMillis();
		SortUtil.bubbleSort(array);
		long l2 = System.currentTimeMillis();
		System.out.println("BubbleSortWhenSorted time takes " + (l2 - l1) + " ms");
		SortUtil.printArray(array);
	}
	
	public static void testInsertSortRecu(int length){
		int[] array = SortUtil.createArray(length);
		SortUtil.printArray(array);
		long l1 = System.currentTimeMillis();
		SortUtil.insertSortWithRecu(array, 0, length -1);
		long l2 = System.currentTimeMillis();
		System.out.println("InsertSortRecu time takes " + (l2 - l1) + " ms");
		SortUtil.printArray(array);
	}
	
	public static void testInsertSortRecuWhenSorted(int length){
		int[] array = SortUtil.createArray(length);
		SortUtil.printArray(array);
		SortUtil.mergeSort(array, 0, length -1);
		long l1 = System.currentTimeMillis();
		SortUtil.insertSortWithRecu(array, 0, length -1);
		long l2 = System.currentTimeMillis();
		System.out.println("InsertSortRecu time takes " + (l2 - l1) + " ms");
		SortUtil.printArray(array);
	}
	
	public static void testHeapSort(int length){
		int[] array = SortUtil.createArray(length);
		SortUtil.printArray(array);
		long l1 = System.currentTimeMillis();
		HeapSort.heapSort(array);
		long l2 = System.currentTimeMillis();
		System.out.println("HeapSort time takes " + (l2 - l1) + " ms");
		SortUtil.printArray(array);
	}
}
