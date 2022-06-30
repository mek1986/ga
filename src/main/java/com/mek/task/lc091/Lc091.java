package com.mek.task.lc091;

import com.mek.core.BaseGeneticAlgoCore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author: ifelse
 * Date: 2022/6/25
 * VX: 250023777
 * Description: 力扣 剑指 Offer II 091. 粉刷房子
 * @version: 1.0
 */
public class Lc091 extends BaseGeneticAlgoCore<CostChromsome> {
    private int M;
    private int MAX_SCORE;
    private int BEST_SCORE;
    private int[][] costs;

    public int[][] getCosts() {
        return costs;
    }

    public void setCosts(int[][] costs) {
        this.costs = costs;
        this.M = costs.length;

        MAX_SCORE = Arrays.stream(costs).map(v -> {
            int max = -1;
            max = Math.max(v[0], max);
            max = Math.max(v[1], max);
            max = Math.max(v[2], max);

            return max;
        }).mapToInt(v -> v).sum() + 1;

        BEST_SCORE = Arrays.stream(costs).map(v -> {
            int min = Integer.MAX_VALUE;
            min = Math.min(v[0], min);
            min = Math.min(v[1], min);
            min = Math.min(v[2], min);

            return min;
        }).mapToInt(v -> v).sum();
    }

    /**
     * 构造函数
     *
     * @param geneSize       基因长度
     * @param maxMutationNum 最大变异步长
     */
    public Lc091(int geneSize, int maxMutationNum) {
        this.geneSize = geneSize;
        this.maxMutationNum = maxMutationNum;
        this.popSize = 100;
        this.maxIterNum = 500;
        this.mutationRate = 0.1;
    }

    /**
     * 构造函数
     *
     * @param geneSize       基因长度
     * @param maxMutationNum 最大变异步长
     * @param popSize        种群数量
     * @param maxIterNum     最大迭代次数
     * @param mutationRate   染色体变异的概率
     */
    public Lc091(int geneSize, int maxMutationNum, int popSize, int maxIterNum, double mutationRate) {
        this.popSize = popSize;
        this.geneSize = geneSize;
        this.maxIterNum = maxIterNum;
        this.mutationRate = mutationRate;
        this.maxMutationNum = maxMutationNum;
    }

    @Override
    public double calculateScore(CostChromsome chromsome) {
        String s = chromsome.serialNumber();

        int score = 0;
        int pre = -1;
        for (int i = 0; i < M; i++) {
            int siRow = Integer.parseInt(s.substring(i, i + 1));
            if (siRow == pre) {
                score = MAX_SCORE;
                break;
            }
            score += costs[i][siRow];
            pre = siRow;
        }

        return MAX_SCORE - score;
    }

    @Override
    public void initPopulation() {
        population = new ArrayList<>();
        IntStream.range(0, popSize).forEach(v -> {
            population.add(new CostChromsome(geneSize));
        });

        calculateGroupScore();
    }

    @Override
    public boolean isEvolveDone() {
        int generationDiff = generation - geneI;

        return generation >= maxIterNum || bestScore == MAX_SCORE - BEST_SCORE || generationDiff > (maxIterNum * 0.6);
    }
}
