package com.mek.task.knapsack01;

import com.google.common.base.Objects;
import com.mek.core.BaseGeneticAlgoCore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author: ifelse
 * Date: 2022\6\23 0023
 * Time: 16:32
 * vx: 250023777
 * Description: 01背包问题
 * @version: 1.0
 */
public class Knapsack01 extends BaseGeneticAlgoCore<GoodsChromsome> {
    private final int C = 9;
    private final List<Goods> goodsList = Arrays.asList(
            new Goods(2, 3),
            new Goods(3, 4),
            new Goods(4, 5),
            new Goods(5, 7)
    );

    @Override
    public void initPopulation() {

        population = new ArrayList<>();

        IntStream.range(0, popSize).forEach(v -> {
            population.add(new GoodsChromsome(geneSize));
        });

        calculateGroupScore();
    }

    /**
     * 构造函数
     *
     * @param geneSize       基因长度
     * @param maxMutationNum 最大变异步长
     */
    public Knapsack01(int geneSize, int maxMutationNum) {
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
    public Knapsack01(int geneSize, int maxMutationNum, int popSize, int maxIterNum, double mutationRate) {
        this.popSize = popSize;
        this.geneSize = geneSize;
        this.maxIterNum = maxIterNum;
        this.mutationRate = mutationRate;
        this.maxMutationNum = maxMutationNum;
    }

    /**
     * 计算染色体适应度
     *
     * @return 适应度
     */
    @Override
    public double calculateScore(GoodsChromsome chromsome) {
        String s = chromsome.serialNumber();

        String[] strings = s.split("");
        int totalSize = 0;
        int totalValue = 0;

        for (int i = 0; i < goodsList.size(); i++) {
            if (Objects.equal(strings[i], "1")) {
                totalSize += goodsList.get(i).weight;
                totalValue += goodsList.get(i).value;
            }
        }

        //不合格，适应度置0，相当于淘汰
        if (totalSize > C) {
            return 0;
        }

        return (double) totalValue;
    }

    /**
     * 背包物品类
     */
    private class Goods {
        //物品重量
        public int weight;
        //物品价值
        public int value;

        public Goods(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    @Override
    public boolean isEvolveDone() {
        int generationDiff = generation - geneI;

        return generation >= maxIterNum || generationDiff > (maxIterNum * 0.5);
    }
}
