/**
 * 
 */
package org.salever.j2se.common.sort;

/**
 * @author LiXiaopeng
 * 
 */
public class HeapSort {

	/**
	 * 升序。如何使用最大堆来完成升序排序呢，我们知道最大堆的根节点一定是最大的，所以遍历整个堆，每次取出根节点，得到的就是一个升序的数组。每次可以不取出
	 * ，直接与最后一个元素交换，同时减小堆的大小，这样可以不用额外分配空间。
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
	 * <p>
	 * 此方法将会使以i为根节点的树成为最大堆。
	 * </p>
	 * 
	 * <p>
	 * 最大堆的要求是根节点大于等于子节点，这方法前提为以i的子节点2i和2i+1都是最大堆。
	 * </p>
	 * 
	 * <p>
	 * 在i、2i、2i+1这三个节点处，寻找最大值:
	 * <li>若i本身就是最大值，退出。</li>
	 * <li>若2i本身是最大值，交换i、2i处的元素，然后再递归使得2i为根节点的子树成为最大堆。</li>
	 * <li>若2i+1本身是最大值，交换i、2i+1处的元素，然后再递归使得2i+1为根节点的子树成为最大堆。</li>
	 * </p>
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
	 * 构建最大堆的时候，递归调用maxHeapify方法就行了，但是要注意maxHeapify的前提，左右子节点对应的子树都是最大堆，这里从length
	 * / 2 + 1开始，因为length / 2 + 1以后的元素都是只有一个节点的子树，一定是最大堆，满足前提条件。依次从length / 2 +
	 * 1到0，就可以把整个数组变成最大堆。
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
}
