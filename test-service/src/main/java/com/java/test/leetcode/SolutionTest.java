package com.java.test.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author pengyongjian
 * @Description:
 * @date 2020-11-03 10:08
 */
public class SolutionTest {
    public static void main(String[] args) {
//        int[] arr = {1, 2, 3, 4, 2, 1};
//        boolean b = validMountainArray(arr);
//        System.out.println(b);

        int[] arr = {1, 2, 3, 4, 2, 1, 12, 5, 34};
        arr = sortByMerge(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }



    }

    /**
     * @Description: 最接近原点的 K 个点
     * @Author: pengyongjian
     * @Date: 2020/11/10 11:30 上午
     * @param: points
     * @param: K
     * @return: int[][]
     */
    public static int[][] kClosest(int[][] points, int K) {

        return null;
    }

    /**
     * @Description:
     * @Author: pengyongjian
     * @Date: 2020/11/10 1:42 下午
     * @param: arr
     * @return: int[]
     */
    public static int[] sortByMerge(int[] arr){
        if(arr.length < 2) return arr;
        int middle = arr.length/2;
        int[] left = Arrays.copyOfRange(arr, 0, middle);
        int[] right = Arrays.copyOfRange(arr, middle, arr.length);
        return merge(sortByMerge(left), sortByMerge(right));
    }

    /**
     * @Date: 2020/11/10 2:19 下午
     * @param: null
     * @return: null
     */
    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for (int i = 0, j = 0, index = 0; index < result.length; index++) {
            if (i >= left.length) {
                result[index] = right[j++];
            } else if (j >= right.length) {
                result[index] = left[i++];
            } else if (left[i] > right[j]) {
                result[index] = right[j++];
            } else {
                result[index] = left[i++];
            }
        }
        return result;
    }

    /**
     * @Description: 插入区间
     * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
     * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
     * 示例 1：
     * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
     * 输出：[[1,5],[6,9]]
     * 示例 2：
     * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
     * 输出：[[1,2],[3,10],[12,16]]
     * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
     *
     * @Author: pengyongjian
     * @Date: 2020-11-04 10:45
     * @param: intervals
     * @param: newInterval
     * @return: int[][]
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if(intervals == null){
            if(newInterval == null){
                return null;
            } else {
                return new int[][]{newInterval};
            }
        }
        if(newInterval == null){
            return intervals;
        }


        List<int[]> arr = new ArrayList<>();
        Integer begin = null;
        Integer end = null;
        for (int i = 0; i < intervals.length; i++) {

        }

        return null;
    }

    /**
     * @Description: 有效的山脉数组
     * @Author: pengyongjian
     * @Date: 2020-11-03 10:09
     * @param: arr
     * @return: boolean
     */
    public static boolean validMountainArray(int[] arr) {
        if(arr == null){
            return false;
        }
        int length = arr.length;
        if(length < 3){
            return false;
        }

        boolean up = false;
        boolean down = false;
        int index = 0;
        for (int i = 0; i < length - 1; i++) {
            if(arr[i] < arr[i + 1]){
                up = true;
            } else {
                index = i;
                break;
            }
        }
        if(up){
            for (int i = index; i < length - 1; i++) {
                if(arr[i] > arr[i+1]){
                    if (!down) down = true;
                } else {
                    return false;
                }
            }
        }

        return up & down;
    }
}
