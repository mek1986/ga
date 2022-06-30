package com.mek.core;

/**
 * @author: ifelse
 * Date: 2022\6\16 0016
 * Time: 17:33
 * vx: 250023777
 * Description: 算法核心逻辑接口
 */
public interface GeneticAlgoCore<T> {
    /**
     * 开始
     */
    void start();

    /**
     * 初始化种群
     */
    void initPopulation();

    /**
     * 种群进化
     */
    void evolve();

    /**
     * 选择要遗传的染色体
     *
     * @return 染色体
     */
    T selectParent();

    /**
     * 计算种群适应度
     */
    void calculateGroupScore();

    /**
     * 基因突变
     */
    void mutation();

    /**
     * 计算单个染色体分数
     *
     * @param chromsome 染色体
     */
    void setEntityScore(T chromsome);

    /**
     * 进化是否完成
     *
     * @return true-是，false-否
     */
    boolean isEvolveDone();
}
