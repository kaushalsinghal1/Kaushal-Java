package com.ace.training.algo;

public class TBinaryTreeIsMaxHeap {
	/*
	 * A Binary Tree node
	 */
	class Node {
		int data;
		Node left, right;

		Node(int item) {
			data = item;
			left = right = null;
		}
	}

	class GfG {
		int countNode(Node root) {
			if (root == null) {
				return 0;
			}
			return (1 + countNode(root.left) + countNode(root.right));
		}

		boolean isCompleteTree(Node root, int index, int count) {
			if (root == null) {
				return true;
			}
			if (index >= count) {
				return false;

			}
			return isCompleteTree(root.left, 2 * index + 1, count) && isCompleteTree(root.right, 2 * index + 2, count);
		}

		/* You are required to complete this method */
		boolean isHeapUtill(Node tree) {
			if (tree == null) {
				return true;
			}
			if (tree.left == null && tree.right == null) {
				return true;
			}

			if (tree.right == null) {
				return tree.data >= tree.left.data;
			} else {
				if (tree.left.data <= tree.data && tree.right.data <= tree.data) {
					return isHeap(tree.left) && isHeap(tree.right);
				} else {
					return false;
				}

			}

		}

		boolean isHeap(Node tree) {
			int count = countNode(tree);
			if (!isCompleteTree(tree, 0, count)) {
				return false;
			}

			return isHeapUtill(tree);
			// Your code here
		}
	}
}
