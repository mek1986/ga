package com.mek.task.nqueen;

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
 * Description: 皇后染色体
 */
public class QueenChromsome extends BaseChromsomeCore<Integer, QueenChromsome> {
    /**
     * 构造函数
     *
     * @param geneLength 基因长度
     */
    public QueenChromsome(int geneLength) {
        if (geneLength < 0) {
            return;
        }

        initialGene(geneLength);
    }

    /**
     * 无参构造函数
     */
    public QueenChromsome() {
    }

    @Override
    public void initialGene(int geneLength) {
        gene = new Integer[geneLength];

        for (int i = 0; i < geneLength; i++) {
            gene[i] = random.nextInt(geneLength);
        }
    }

    @Override
    public List<QueenChromsome> genetic(QueenChromsome another) {
        //判断是否可以杂交
        if (!canGenetic(another)) {
            return null;
        }

        QueenChromsome p1 = this.copy();
        QueenChromsome p2 = another.copy();

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
            gene[random.nextInt(size)] = random.nextInt(size);
        });
    }

    @Override
    public void swapGene(QueenChromsome p1, QueenChromsome p2, int i) {
        Integer temp = p1.gene[i];
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
    public boolean canGenetic(QueenChromsome another) {
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

        return Arrays.stream(gene).map(v -> v + "").collect(Collectors.joining(""));
    }

    @Override
    public QueenChromsome copy() {
        QueenChromsome copy = new QueenChromsome();
        copy.gene = Arrays.copyOf(gene, gene.length);

        return copy;
    }
}
