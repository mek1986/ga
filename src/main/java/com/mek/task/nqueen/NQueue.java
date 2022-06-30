package com.mek.task.nqueen;

import com.mek.core.BaseGeneticAlgoCore;

import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * @author: ifelse
 * Date: 2022/6/24
 * VX: 250023777
 * Description: N皇后问题
 * @version: 1.0
 */
public class NQueue extends BaseGeneticAlgoCore<QueenChromsome> {
    private int queen_num;
    private final static int DONE_SCORE = 10000;

    /**
     * 构造函数
     *
     * @param geneSize       基因长度
     * @param maxMutationNum 最大变异步长
     */
    public NQueue(int geneSize, int maxMutationNum) {
        this.queen_num = geneSize;
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
    public NQueue(int geneSize, int maxMutationNum, int popSize, int maxIterNum, double mutationRate) {
        this.queen_num = geneSize;
        this.popSize = popSize;
        this.geneSize = geneSize;
        this.maxIterNum = maxIterNum;
        this.mutationRate = mutationRate;
        this.maxMutationNum = maxMutationNum;
    }

    @Override
    public double calculateScore(QueenChromsome chromsome) {
        String s = chromsome.serialNumber();

        int prise = 0;
        boolean isDone = true;
        for (int i = 0; i < queen_num; i++) {
            int siCol = Integer.parseInt(s.substring(i, i + 1));
            for (int j = i + 1; j < queen_num; j++) {
                int sjCol = Integer.parseInt(s.substring(j, j + 1));
                if (Math.abs(j - i) == Math.abs(sjCol - siCol) || siCol == sjCol) {
                    isDone = false;
                    continue;
                }
                prise += 1;
            }
        }

        return isDone ? DONE_SCORE : prise;
    }

    @Override
    public void initPopulation() {
        population = new ArrayList<>();
        IntStream.range(0, popSize).forEach(v -> {
            population.add(new QueenChromsome(geneSize));
        });

        calculateGroupScore();
    }

    @Override
    public boolean isEvolveDone() {
        int generationDiff = generation - geneI;

        return generation >= maxIterNum || bestScore == DONE_SCORE || generationDiff > (maxIterNum * 0.5);
    }
}
