package com.mek.task.knapsack01;

import com.mek.core.BaseChromsomeCore;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author: ifelse
 * Date: 2022\6\16 0016
 * Time: 16:25
 * vx: 250023777
 * Description: 01背包物品染色体
 */
public class GoodsChromsome extends BaseChromsomeCore<Boolean, GoodsChromsome> {
    /**
     * 构造函数
     *
     * @param geneLength 基因长度
     */
    public GoodsChromsome(int geneLength) {
        if (geneLength < 0) {
            return;
        }

        initialGene(geneLength);
    }

    /**
     * 无参构造函数
     */
    public GoodsChromsome() {
    }

    @Override
    public void initialGene(int geneLength) {
        gene = new Boolean[geneLength];

        for (int i = 0; i < geneLength; i++) {
            gene[i] = Math.random() > 0.5;
        }
    }

    @Override
    public List<GoodsChromsome> genetic(GoodsChromsome another) {
        //判断是否可以杂交
        if (!canGenetic(another)) {
            return null;
        }

        GoodsChromsome p1 = this.copy();
        GoodsChromsome p2 = another.copy();

        /**
         * 杂交
         */
        int[] positions = changeGenePosition();
        IntStream.rangeClosed(positions[0], positions[1]).forEach(i -> {
            swapGene(p1, p2, i);
        });

        return Arrays.asList(p1, p2);
    }

    @Override
    public void mutation(int num) {
        int size = gene.length;
        IntStream.range(0, num).forEach(pos -> {
            int at = random.nextInt(size);
            gene[at] = !(Math.random() > 0.5);
        });
    }

    @Override
    public void swapGene(GoodsChromsome p1, GoodsChromsome p2, int i) {
        boolean temp = p1.gene[i];
        p1.gene[i] = p2.gene[i];
        p2.gene[i] = temp;
    }

    @Override
    public int[] changeGenePosition() {
        int size = gene.length;
        int a = random.nextInt(size);
        int b = random.nextInt(size);

        return new int[]{
                Math.min(a, b), Math.max(a, b)
        };
    }

    @Override
    public boolean canGenetic(GoodsChromsome another) {
        if (another == null) {
            return false;
        }

        if (this.gene == null || another.gene == null) {
            return false;
        }

        if (this.gene.length != another.gene.length) {
            return false;
        }

        //不允许无性繁殖
        if (this.equals(another)) {
            return false;
        }

        return true;
    }

    @Override
    public String serialNumber() {
        if (gene == null) {
            return "";
        }

        return Arrays.stream(gene).map(v -> v ? "1" : "0").collect(Collectors.joining(""));
    }

    @Override
    public GoodsChromsome copy() {
        GoodsChromsome copy = new GoodsChromsome();
        copy.gene = Arrays.copyOf(gene, gene.length);

        return copy;
    }
}
