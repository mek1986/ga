package com.mek.core;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * @author: ifelse
 * Date: 2022/6/24
 * VX: 250023777
 * Description: 染色体核心接口
 * @version: 1.0
 */
public interface ChromsomeCore<T> {
    /**
     * 初始化随机基因序列
     *
     * @param geneLength 基因序列长度
     */
    void initialGene(int geneLength);

    /**
     * 杂交产生下一代
     *
     * @param another
     * @return
     */
    List<T> genetic(T another);

    /**
     * 基因变异
     *
     * @param num 变异次数
     */
    void mutation(int num);

    /**
     * 交换染色体1和染色体2索引i处的基因序列
     *
     * @param p1 染色体1
     * @param p2 染色体2
     * @param i  基因的索引
     */
    void swapGene(T p1, T p2, int i);

    /**
     * 获取交换基因的起始和终止索引
     *
     * @return 2个元素的数组
     * 元素1：起始索引
     * 元素2：终止索引
     */
    int[] changeGenePosition();

    /**
     * 是否可以和另一个染色体遗传
     *
     * @param another 另一个染色体
     * @return true-可以，false-不可以
     */
    boolean canGenetic(T another);

    /**
     * 获取染色体基因序列的数字编码的字符串形式
     *
     * @return 数字编码的字符串形式
     */
    String serialNumber();

    /**
     * 染色体克隆自己
     * @return 克隆对象
     */
    T copy();
}
