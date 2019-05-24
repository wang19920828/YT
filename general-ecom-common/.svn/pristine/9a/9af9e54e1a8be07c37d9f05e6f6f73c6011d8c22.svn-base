package org.fh.general.ecom.common.utils;

/**
 * 根据首字母ASCII码从小到大排序（字典序），排序字符串组
 * 本排序采用快速排序
 * 比较大小的基准点为数组中的最后一位
 * 开始点为数组中第一位
 * @author 延卿
 *
 */
public class QuickSortUtils {

	/**
	 * 以r为基准点对p-r位置之间的元素进行排序(r同时为快排比较结束点)
	 * @param a
	 * @param p
	 * @param r
	 * @return
	 */
	public static int Partition(String a[], int p, int r) {
		int i = p - 1;
		for (int j = p; j <= r - 1; j++) {
			//与r位置基准元素进行比较
			if (Judge(a, j - 1, r - 1)) {
				//小于r位置元素的个数加一
				i++;
				//如果该位置元素小于r位置元素则将该位置元素向左移动至上一个小于r位置元素的位置后面
				swap(a, j - 1, i - 1);
			}
		}
		//将基准元素位置交换 实现左小 右大
		swap(a, r - 1, i);
		//返回基准元素所在位置
		return i + 1;
	}

	/**
	 * 将数组中i，和j位置的元素交换
	 * @param a
	 * @param i
	 * @param j
	 */
	private static void swap(String a[], int i, int j) {
		String temp = "";
		temp = a[i];
		a[i] = a[j];
		a[j] = temp;

	}

	/**
	 * 快排
	 * @param a 基准数组
	 * @param p 快排起始基准点
	 * @param r 快排比较基准点(快排比较结束点)
	 */
	public static void QuickSort(String a[], int p, int r) {
		//判断
		if (p < r) {
			//获取新的基准点
			int q = Partition(a, p, r);
			//对基准点左边重新进行快排
			QuickSort(a, p, q - 1);
			//对基准点右边数组进行快排
			QuickSort(a, q + 1, r);
		}
	}

	/**
	 * 按ASCII码对字符串进行比较大小 p位置的ASCII小于r位置则返回TRUE
	 * @param a
	 * @param p
	 * @param r
	 * @return
	 */
	public static Boolean Judge(String a[], int p, int r) {
		int len;
		Boolean result;
		//判断p r字符串长度大小  长度小的在前面字符相同情况下默认在前，完全相同情况下默认为p>q
		if (a[p].length() < a[r].length()) {
			len = a[p].length();
			result = true;
		} else {
			len = a[r].length();
			result = false;
		}
		//对字符串内字符大小进行判断 同为比较 由首字母开始相同则退位
		for (int i = 0; i < len; i++) {
			if (a[p].charAt(i) < a[r].charAt(i)) {
				result = true;
				break;
			} else if (a[p].charAt(i) > a[r].charAt(i)) {
				result = false;
				break;
			} else {
				continue;
			}
		}
		return result;
	}

	/**
	 * 调用实例
	 * @param str
	 */
	public static void EgForUse() {
		//字符串
		String a[] = { "12", "abc", "abf", "as", "df", "bd", "23", "43",
				"1qwe", "w23", "qae", "q", "qw", "qwe", "qwf", "qwa", "wa",
				"qqq", "qws", "12sd", "11", "123" };
		//数组长度
		int len = a.length;
		//快排
		QuickSort(a, 1, len);
		//打印数组
		for (int i = 1; i <= len; i++)
			System.out.println(a[i - 1]);

	}

}