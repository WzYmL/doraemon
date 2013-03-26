/**
 * 
 */
package org.salever.j2se.common.sort;

import java.util.Arrays;

/**
 * @author LiXiaopeng
 * 
 */
public class SortUtil {

	public static int[] createArray(int length) {
		int[] array = new int[length];
		for (int index = 0; index < length; index++) {
			array[index] = (int) (Math.random() * 1000 + Math.random() * 100 + Math
					.random() * 10);
		}
		return array;
	}

	public static void printArray(int[] array) {
		if (array.length > 20) {
			return;
		}
		System.out.println(Arrays.toString(array));
	}

	/**
	 * 插入排序，就像玩纸牌时候，每次将一张新的牌插入到手里已经排好序的牌中，这里可能会有很多元素的移动。升序，从第二个元素i开始，
	 * 将左边的视为已经排序的子数组
	 * ，需要将i插到已经排序完毕的子数组中。这时候要遍历子数组，从子数组的末尾元素i-1开始，如果array[i-1]大于array
	 * [i]，表明子数组将有元素移动
	 * ，为了给新来的array[i]腾出位置，将array[i]处的值设为array[i-1]，i-1处的值设为i-2（如果array
	 * [i-2]也大于array[i]），如此移动直到array[j]<array[i]，通知移动，将i的值回写到j处，一次插入完毕。
	 * 
	 * 最好情况，不需要移动，遍历n-1次就完毕，时间复杂度O（N），最坏情况下O（N*N），每次都要移动（数组是降序的）
	 * 
	 * @param array
	 */

	public static void insertSort(int[] array) {
		for (int i = 1; i < array.length; i++) {
			int key = array[i];
			int j = i - 1;
			for (; j > -1 && key < array[j]; j--) {
				array[j + 1] = array[j];
			}
			array[j + 1] = key;
		}
	}

	/**
	 * 选择排序，遍历一遍，选出第k小的元素，放到k处。无论数组是否有序，它都会遍历，然后找寻相应大小的元素，并尝试交换。
	 * 
	 * 最坏和平均都与哦O（N*N），而且不稳定，因为在第k小的元素与其他元素交换的时候，可能打乱顺序。
	 * 
	 * @param array
	 */
	public static void selectSort(int[] array) {
		for (int i = 0; i < array.length; i++) {
			int min = i;
			for (int j = i; j < array.length; j++) {
				if (array[min] < array[j]) {
					min = j;
				}
			}
			if (min != i) {
				int temp = array[i];
				array[i] = array[min];
				array[min] = temp;
			}
		}
	}

	/**
	 * 合并排序，首先将数组按照正中间的下标分隔，然后合并两个子数组。为了保证子数组有序，一定要从只有一个元素的子数组开始，需要递归。合并的步长定位数组长的二分之一。
	 * 
	 * @param array
	 * @param begin
	 * @param end
	 */
	public static void mergeSort(int[] array, int begin, int end) {
		if (end > begin) {
			int sep = (begin + end) / 2;
			mergeSort(array, begin, sep);
			mergeSort(array, sep + 1, end);
			merge(array, begin, sep, end);
		}
	}

	/**
	 * 合并的精髓在于将两个已经有序的子数组，合并为一个新的数组，而这个数组可以保证有序。首先按照合并分隔的下标，将数组分为左右两个子数组，
	 * 然后依次取出元素进行比较
	 * ，小的将被取出放到新的数组中，知道其中任意一个子数组为空，此时再将另外一个数组的剩余元素直接复制到新数组中。如果在合并的过程中
	 * ，相等的元素统一以左边子数组为准取出，那么此排序为稳定的。
	 * 
	 * @param array
	 *            - 源数据
	 * @param begin
	 *            - 数组的起始下标
	 * @param sep
	 *            - 数组的合并分隔下标
	 * @param end
	 *            - 数组的末尾下标
	 */
	private static void merge(int[] array, int begin, int sep, int end) {
		int[] left = new int[sep - begin + 1];
		int[] right = new int[end - sep];
		System.arraycopy(array, begin, left, 0, sep - begin + 1);
		System.arraycopy(array, sep + 1, right, 0, end - sep);

		int i = 0, j = 0;

		// Copy left or right array to the array
		while (i < left.length && j < right.length) {
			if (left[i] <= right[j]) {
				array[begin + i + j] = left[i];
				i++;
			} else {
				array[begin + i + j] = right[j];
				j++;
			}
		}
		// If left array has more elements
		if (i < left.length) {
			System.arraycopy(left, i, array, begin + i + j, left.length - i);
		} else if (j < right.length) { // If right array has more elements
			System.arraycopy(right, j, array, begin + i + j, right.length - j);
		}
	}

	/**
	 * 冒泡排序，每次将一个第k大的元素“冒”到第k后的位置去，交换很多次。有可能在某次遍历中，没有任何元素的交换，表明已经有序，提前退出。
	 * 
	 * 时间复杂度O（N*N），稳定排序。
	 * 
	 * @param array
	 */
	public static void bubbleSort(int[] array) {
		for (int i = 0; i < array.length; i++) {
			boolean isSorted = true;
			for (int j = 0; j < array.length - i - 1; j++) {
				int temp;
				if (array[j] > array[j + 1]) {
					isSorted = false;
					temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
			if (isSorted) {
				break;
			}
		}
	}

	public static void insertSortWithRecu(int[] array, int begin, int end) {
		if (end > begin) {
			insertSortWithRecu(array, begin, end - 1);
			insert(array, begin, end - 1, end);
		}
	}

	private static void insert(int[] array, int begin, int end, int key) {
		int i = 0;
		int value = array[key];
		for (i = end; i >= begin && value < array[i]; i--) {
			array[i + 1] = array[i];
		}
		array[i + 1] = value;
	}

	/**
	 * Heap sort in ASC.
	 * 
	 * @param array
	 *            - source array
	 */
	public static void heapSort(int[] array) {
		buildMaxHeap(array);
		int length = array.length;
		for (int index = length - 1; index > 0; index--) {
			swap(array, index, 0);
			length--;
			maxHeapify(array, 0, length);
		}
	}

	/**
	 * Make tree max.
	 * 
	 * @param array
	 *            - source array
	 * @param i
	 *            - root node index.
	 * @param length
	 *            - tree length.
	 */
	private static void maxHeapify(int[] array, int i, int length) {
		int left = i * 2;
		int right = i * 2 + 1;
		int largest;
		if (left < length && array[left] > array[i]) {
			largest = left;
		} else {
			largest = i;
		}

		if (right < length && array[right] > array[largest]) {
			largest = right;
		}

		if (largest != i) {
			swap(array, largest, i);
			maxHeapify(array, largest, length);
		}

	}

	/**
	 * Swap two elements in array.
	 * 
	 * @param array
	 *            - source array.
	 * @param j
	 * @param i
	 */
	private static void swap(int[] array, int j, int i) {
		int temp = array[j];
		array[j] = array[i];
		array[i] = temp;
	}

	/**
	 * Build max heap tree.
	 * 
	 * @param array
	 *            - source array.
	 */
	private static void buildMaxHeap(int[] array) {
		int length = array.length;
		for (int index = length / 2 + 1; index >= 0; index--) {
			maxHeapify(array, index, length);
		}
	}

	public static void quickSort(int[] array) {
		quickSort(array, 0, array.length - 1);
	}

	public static void quickSort(int[] array, int begin, int end) {

		if (end > begin) {
			int separater = partition(array, begin, end);
			quickSort(array, begin, separater - 1);
			quickSort(array, separater + 1, end);
		}
	}

	/**
	 * Get partition tag index.
	 * 
	 * @param array
	 *            - source array
	 * @param begin
	 *            - begin index
	 * @param end
	 *            - end index
	 * @return - tag index
	 */
	private static int partition(int[] array, int begin, int end) {
		int tag = array[end];
		int tagIndex = -1;
		for (int index = 0; index < end; index++) {
			if (array[index] <= tag) {
				tagIndex++;
				swap(array, index, tagIndex);
			}
		}
		swap(array, tagIndex + 1, end);
		return tagIndex + 1;
	}
}
