package com.mek;

import com.mek.task.knapsack01.Knapsack01;
import com.mek.task.lc091.Lc091;
import com.mek.task.nqueen.NQueue;

/**
 * @author: ifelse
 * Date: 2022\6\17 0017
 * Time: 9:12
 * vx: 250023777
 * Description: 遗传算法演示类
 */
public class App {
    public static void main(String[] args) {
//        Knapsack01 algorithm = new Knapsack01(4, 1);
        NQueue algorithm = new NQueue(8, 1, 500, 500, 0.2);
//        int[][] costs = new int[][]{
////                {17, 2, 17}, {16, 16, 5}, {14, 3, 19}
////                {7,6,2}
//                {3, 5, 3}, {6, 17, 6}, {7, 13, 18}, {9, 10, 18}
//        };
//
//        int popSize = costs.length / 2 * 100;
//        popSize = popSize > 500 ? 500 : popSize;
//        popSize = popSize < 100 ? 100 : popSize;
//
//        int maxIterNum = costs.length / 2 * 200;
//        maxIterNum = maxIterNum > 500 ? 500 : maxIterNum;
//        maxIterNum = maxIterNum < 200 ? 200 : maxIterNum;
//        Lc091 algorithm = new Lc091(costs.length, 1, popSize, maxIterNum, 0.5);
//        algorithm.setCosts(costs);
        algorithm.setDebug(false);
        algorithm.start();

//        System.out.println(codeToCost(algorithm.getCode(), costs));
    }

    private static int codeToCost(String code, int[][] costs) {
        int size = costs.length;

        int sum = 0;
        for (int i = 0; i < size; i++) {
            int codeiInt = Integer.parseInt(code.substring(i, i + 1));
            sum += costs[i][codeiInt];
        }

        return sum;
    }
}
