package com.test;

public class SortinngProblems {

	/*
	 * k number of element near the ele
	 */
	public static int[] finedClosetElementIncludeELe(int[] arr, int ele, int k) {
		int[] kEle = new int[k];
		int n = arr.length;
		if (k > n - 1) {
			System.out.println("Invalid K");
			return null;
		}
		int kindex = closestElement(arr, 0, n - 1, ele);
		int l = kindex - 1;
		int r = kindex + 1;
		int count = 0;
		kEle[count++] = arr[kindex];
		while (count < k && l >= 0 && r < n) {
			if ((ele - arr[l]) > (arr[r] - ele)) {
				kEle[count] = arr[r++];
			} else {
				kEle[count] = arr[l--];
			}
			count++;
		}
		while (count < k && l >= 0) {
			kEle[count++] = arr[l--];
		}
		while (count < k && r < n) {
			kEle[count++] = arr[r++];
		}
		return kEle;
	}

	/*
	 * k number of element near the ele
	 */
	public static int[] finedClosetElement(int[] arr, int ele, int k) {
		int[] kEle = new int[k];
		int n = arr.length;
		if (k > n - 1) {
			System.out.println("Invalid K");
			return null;
		}
		int kindex = binarySearch(arr, ele);
		if (kindex == -1) {
			return null;// Element Not Found
		}
		int l = kindex - 1;
		int r = kindex + 1;
		for (int i = 0; i < k; i++) {
			if (l >= 0 && r < n) {
				if ((ele - arr[l]) > (arr[r] - ele)) {
					kEle[i] = arr[r++];
				} else {
					kEle[i] = arr[l--];
				}
			} else {
				if (l >= 0) {
					kEle[i] = arr[l--];
				} else {
					kEle[i] = arr[r++];
				}
			}
		}
		return kEle;
	}

	public static int closestElement(int[] arr, int low, int high, int ele) {
		if (arr[low] > ele) {
			return low;
		} else if (arr[high] < ele) {
			return high;
		}
		int mid = (low + high) / 2;
		if (arr[mid] == ele) {
			return mid;
		}

		if (arr[mid] > ele) {
			if (mid - 1 >= 0 && arr[mid - 1] < ele) {
				if (ele - arr[mid - 1] < arr[mid] - ele) {
					return mid - 1;
				} else {
					return mid;
				}
			}
			return closestElement(arr, low, mid - 1, ele);
		} else {
			if (mid + 1 <= high && arr[mid + 1] > ele) {
				if (ele - arr[mid] < arr[mid + 1] - ele) {
					return mid;
				} else {
					return mid + 1;
				}
			}
		}
		return closestElement(arr, mid + 1, high, ele);
	}

	public static int[] closetPair(int[] arr1, int[] arr2, int ele) {
		int[] eles = new int[2];
		int p1 = closestElement(arr1, 0, arr1.length - 1, ele);
		int p2 = closestElement(arr2, 0, arr2.length - 1, ele);
		eles[0] = arr1[p1];
		eles[1] = arr2[p2];
		return eles;
	}

	public static int binarySearch(int[] arr, int ele) {
		int l = 0;
		int r = arr.length - 1;
		while (l <= r) {
			int mid = (l + r) / 2;
			if (arr[mid] == ele) {
				return mid;
			} else if (arr[mid] > ele) {
				r = mid - 1;
			} else {
				l = mid + 1;
			}
		}
		return -1;
	}

	static void printArray(int arr[]) {
		int n = arr.length;
		for (int i = 0; i < n; ++i)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

	static class KthSmallestElement {
		/*
		 * Using Quicksort
		 */
		static int kthSammlest(int[] arr, int l, int r, int k) {
			if (k > 0 && k <= r - l + 1) {
				int pos = partition(arr, l, r);
				if (pos - l == k - 1) {
					return arr[pos];
				}
				if (pos - l > k - 1) {
					return kthSammlest(arr, l, pos - 1, k);
				}
				return kthSammlest(arr, pos + 1, r, k - pos + l - 1);
			}

			return Integer.MAX_VALUE;
		}

		static int randomPartition(int[] arr, int l, int r) {
			int n = r - l + 1;
			int pos = (int) Math.random() % n;
			swap(arr, pos, r);
			return partition(arr, l, r);
		}

		static int partition(int[] arr, int l, int r) {
			int key = arr[r];
			int left = l;
			for (int i = l; i < r; i++) {
				if (arr[i] < key) {
					swap(arr, left, i);
					left++;
				}
			}
			swap(arr, left, r);
			return left;
		}
	}

	// {1,1,1,1,0,0,0,0}
	static int countOneInBInaryArray(int[] arr, int l, int h) {
		if (l <= h) {
			if (arr[h] == 1) {
				return 1 + (h - l);
			}
			if (arr[l] == 0) {
				return 0;
			}
			int mid = l + (h - l) / 2;
			if (arr[mid] == 1) {
				return (mid - l + 1) + countOneInBInaryArray(arr, mid + 1, h);
			} else {
				return countOneInBInaryArray(arr, l, mid - 1);
			}
		}
		return 0;
	}

	static void arrangeZeroOne(int[] arr) {
		int l = 0;
		int r = arr.length - 1;
		while (l <= r) {
			if (arr[r] == 0) {
				swap(arr, l, r);
				l++;
			} else if (arr[l] == 1) {
				swap(arr, l, r);
				r--;
			} else {
				l++;
				r--;
			}
		}

	}

	static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static void main(String[] args) {
		int[] arr = { 2, 5, 7, 8, 9, 11, 12 };
		int[] kEle = finedClosetElementIncludeELe(arr, 9, 4);
		printArray(kEle);
		int[] arr1 = { 10, 9, 4, 6, 7, 2, 5 };
		System.out.println(KthSmallestElement.kthSammlest(arr1, 0, arr1.length - 1, 3));

		int[] closeArr = closetPair(new int[] { 1, 4, 5, 7 }, new int[] { 10, 20, 30, 40 }, 33);
		printArray(closeArr);
		int[] arr2 = { 1, 1, 1, 1, 1, 1, 0, 0 };
		System.out.println(countOneInBInaryArray(arr2, 0, arr2.length - 1));

		int[] zeroOne = { 1, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1 };
		arrangeZeroOne(zeroOne);
		printArray(zeroOne);
	}
}
