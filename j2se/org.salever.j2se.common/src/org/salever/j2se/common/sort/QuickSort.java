/**
 * 
 */
package org.salever.j2se.common.sort;

/**
 * @author LiXiaopeng
 * 
 */
public class QuickSort {

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
	 * <p>
	 * 分解数组，得到一个索引tagIndex，索引tagIndex处的值满足所有左边的元素的值都小于或者等于它，而所有右边的元素的值都大于它。
	 * 实现的方法为
	 * ，将参考值tag设置为数组的末尾array[end]元素，将分解索引tagIndex设置began-1，然后从数组头部array[begin
	 * ]开始遍历， 对于任何小于或者等于tag元素，交换began+1和tagIndex。 这样可以实现原地分解数组。</p>
	 * 
	 * <p>基本的原理为：经过一次遍历，得出比tag小的元素的个数，同时将tagIndex设置为这些元素的个数，
	 * 这时候tagIndex左边的一定都是小于tag的，而因为我们没有对大于tag的元素进行交换，因此，tagIndex右边的元素一定都大于tag
	 * </p>
	 * 
	 * 快速排序的最坏情况下，时间复杂度为O（N×N），而平均情况下为O（n×lgn）
	 * 
	 * @param array
	 * @param begin
	 * @param end
	 * @return
	 */
	private static int partition(int[] array, int begin, int end) {
		int tag = array[end];
		int tagIndex = begin - 1;
		for (int index = begin; index < end; index++) {
			if (array[index] <= tag) {
				tagIndex++;
				swap(array, index, tagIndex);
			}
		}
		swap(array, tagIndex + 1, end);
		return tagIndex + 1;
	}

	private static void swap(int[] array, int x, int y) {
		int temp = array[x];
		array[x] = array[y];
		array[y] = temp;
		// array[x] = array[x] ^ array[y];
		// array[y] = array[x] ^ array[y];
		// array[x] = array[x] ^ array[y];
	}
}
