package com.mek.task.lc091;

import com.mek.core.BaseChromsomeCore;
import com.mek.task.nqueen.QueenChromsome;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author: ifelse
 * Date: 2022/6/25
 * VX: 250023777
 * Description: 油漆花费染色体
 * @version: 1.0
 */
public class CostChromsome extends BaseChromsomeCore<Integer, CostChromsome> {
    public CostChromsome() {
    }

    public CostChromsome(int geneLength) {
        if (geneLength < 0) {
            return;
        }

        initialGene(geneLength);
    }

    @Override
    public void initialGene(int geneLength) {
        gene = new Integer[geneLength];

        for (int i = 0; i < geneLength; i++) {
            gene[i] = random.nextInt(3);
        }
    }

    @Override
    public List<CostChromsome> genetic(CostChromsome another) {
        //判断是否可以杂交
        if (!canGenetic(another)) {
            return null;
        }

        CostChromsome p1 = this.copy();
        CostChromsome p2 = another.copy();

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
            gene[random.nextInt(size)] = random.nextInt(3);
        });
    }

    @Override
    public void swapGene(CostChromsome p1, CostChromsome p2, int i) {
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
    public boolean canGenetic(CostChromsome another) {
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
    public CostChromsome copy() {
        CostChromsome copy = new CostChromsome();
        copy.gene = Arrays.copyOf(gene, gene.length);

        return copy;
    }
}
