package com.example.alro;

import java.util.Stack;

/** 
* @date 2019-09-30 11:39:34
* @author LEI
* TODO
*/

public class Demo {
	/**
	 * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
	 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
	 */
//	public static boolean find(int target, int[][] array) {
//		if(array.length==0||array[0].length==0) {
//			return false;
//		}
//		int i;
//		for(i = 0;i<array.length;i++) {
//			if(array[i][0]<=target) {
//				for(int data : array[i]) {
//					if(target<data) {
//						break;
//					}
//					if(target==data) {
//						return true;
//					}
//				}
//			}
//		}
//		return false;
//	}
	/**
	 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
	 */
//	public String repalceSpace(StringBuffer str) {
//		return str.toString().replace(" ", "%20");
//	}
	
	/**
	 * 输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
	 */
//	public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
//        ArrayList<Integer> list = new ArrayList<Integer>();
//        while(listNode!=null) {
//        	list.add(0, listNode.val);
//        	listNode = listNode.next;
//        }
//		return list;
//    }
	/**
	 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
	 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
	 */
//	class TreeNode {
//		int val;
//		TreeNode left;
//		TreeNode right;
//		TreeNode(int x) { val = x; }
//	}
//	public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
//		if(pre.length==0||in.length==0) {
//			return null;
//		}
//		TreeNode tree = new TreeNode(pre[0]);
//		for(int i=0;i<in.length;i++) {
//			if(pre[0]==in[i]) {
//				tree.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1,i+1), Arrays.copyOfRange(in, 0, i));
//				tree.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i+1, pre.length), Arrays.copyOfRange(in, i+1, in.length));
//			}
//		}
//		return tree;
//	}
	/**
	 * 给你一根长度为n的绳子，请把绳子剪成m段（m、n都是整数，n>1并且m>1），每段绳子的长度记为k[0],k[1],...,k[m]。
	 * 请问k[0]xk[1]x...xk[m]可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
	 */
//	public static int cutRope(int target) {
//       return cutRope(target, 0);
//    }
//	public static int cnt=0;
//    public static int cutRope(int target, int max) {
//        int maxValue = max;
//        for(int i = 1; i < target; ++i){
//            maxValue = Math.max(maxValue, i*cutRope(target -i, target -i));
//        }
//        return maxValue;
//    }
	/**
	 * 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于k的格子。 
	 * 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
	 */
	public int movingCount(int threshold, int rows, int cols){
        int cnt = 0;
        for(int i=0;i<rows;i++) {
        	for(int j=0;j<cols;j++) {
        		if(threshold>=sum(i)+sum(j)) {
        			System.out.println(String.format("k:%d,row:%d,col:%d", threshold,i,j));
        			cnt++;
        		}
        	}
        }
		return cnt;
    }
	public int sum(int i) {
		int sum = 0;
		do {
			sum+=i%10;
			i/=10;
		}while(i>0);
		return sum;
	}
	
	public static void main(String[] args) {
		Demo demo = new Demo();
		System.out.println(demo.movingCount(10, 1, 100));
	}

}
