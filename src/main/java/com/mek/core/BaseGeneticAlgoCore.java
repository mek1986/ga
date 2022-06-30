package com.mek.core;

import java.util.*;

/**
 * @author: ifelse
 * Date: 2022\6\16 0016
 * Time: 18:17
 * vx: 250023777
 * Description: 算法核心逻辑实现类
 * @version: 1.0
 */
public abstract class BaseGeneticAlgoCore<T extends BaseChromsomeCore> implements GeneticAlgoCore<T> {
    //随机器
    protected Random random = new Random();

    //是否打印调试信息
    protected boolean debug = true;

    //种群
    protected List<T> population = new ArrayList<>();

    //种族数量
    protected int popSize;
    //基因最大长度
    public int geneSize;
    //最大迭代次数
    protected int maxIterNum;
    //染色体变异的概率
    protected double mutationRate;
    //最大变异步长
    protected int maxMutationNum;

    //当前遗传到第几代
    protected int generation = 1;

    //最好得分
    protected double bestScore;
    //最坏得分
    protected double worstScore;
    //总得分
    protected double totalScore;
    //平均得分
    protected double averageScore;

    //记录历史种群中最好的染色体的基因编码的字符串形式
    protected String code;
    //记录历史种群中最好的染色体所在的代数
    protected int geneI;

    @Override
    public void start() {
        generation = 1;
        initPopulation();

        do {
            //种族进化
            evolve();

            if (debug) {
                print();
            }
            generation++;
        } while (!isEvolveDone());

        System.out.println("******进化结束******");
        print();
    }

    @Override
    public void evolve() {
        if (debug) {
            System.out.println("evolve...");
        }
        List<T> childrenPopulation = breeding();

        population.clear();
        population.addAll(childrenPopulation);

        //基因突变
        mutation();
        //计算新种群适应度
        calculateGroupScore();
    }

    /**
     * 繁衍
     *
     * @return 下一代
     */
    private List<T> breeding() {
        if (debug) {
            System.out.println("breeding...");
        }
        List<T> childrenPopulation = new ArrayList<>();

        while (childrenPopulation.size() < popSize) {
            /**
             * 选择
             */
            T p1 = selectParent();
            T p2 = selectParent();
            if (p1 == null || p2 == null) {
                if (debug) {
                    System.out.println("parent null");
                }
                continue;
            }

            //交叉杂交
            List<T> children = p1.genetic(p2);
            if (children == null) {
                if (debug) {
                    System.out.println("can not genetic");
                }
                continue;
            }

            childrenPopulation.addAll(children);
        }


        if (debug) {
            System.out.println("breeding...finish");
        }

        return childrenPopulation;
    }

    @Override
    public T selectParent() {
        double slice = Math.random() * totalScore;
        double sum = 0;

        T ret = null;
        int times = 0;
        while (ret == null && times < 100) {
            for (T chrom :
                    population) {
                sum += chrom.getScore();
                if (sum > slice && chrom.getScore() >= averageScore) {
                    ret = chrom;
                    break;
                }
            }
            times++;
            sum = 0;
            slice = Math.random() * totalScore;
            Collections.shuffle(population);
        }

        return ret;
    }

    @Override
    public void calculateGroupScore() {
        if (debug) {
            System.out.println("calculateGroupScore...");
        }
        setEntityScore(population.get(0));
        bestScore = population.get(0).getScore();
        worstScore = population.get(0).getScore();
        totalScore = 0;

        for (T chrom :
                population) {
            setEntityScore(chrom);

            //设置最好基因值
            if (chrom.getScore() > bestScore) {
                updateBest(chrom);
            }

            //设置最坏基因值
            if (chrom.getScore() < worstScore) {
                worstScore = chrom.getScore();
            }

            totalScore += chrom.getScore();
        }

        averageScore = totalScore / popSize;

        //因为精度问题导致的平均值大于最好值，将平均值设置成最好值
        averageScore = Math.min(averageScore, bestScore);
    }

    /**
     * 更新最好基因值
     *
     * @param chrom 染色体对象
     */
    private void updateBest(T chrom) {
        bestScore = chrom.getScore();
        code = chrom.serialNumber();
        geneI = generation;
    }

    @Override
    public void mutation() {
        if (debug) {
            System.out.println("mutation...");
        }
        population.stream().forEach(v -> {
            if (Math.random() < mutationRate) {
                int mutationNum = random.nextInt(maxMutationNum);
                v.mutation(mutationNum);
            }
        });
    }

    @Override
    public void setEntityScore(T chromsome) {
        if (chromsome == null) {
            return;
        }
        chromsome.setScore(calculateScore(chromsome));
    }

    public abstract double calculateScore(T chromsome);

    /**
     * 输出遗传结果信息
     */
    private void print() {
        System.out.println("--------------------------------");
        System.out.println("the generation is:" + generation);
        System.out.println("the best fitness is:" + bestScore);
        System.out.println("the worst fitness is:" + worstScore);
        System.out.println("the average fitness is:" + averageScore);
        System.out.println("the total fitness is:" + totalScore);
        System.out.println("geneI:" + geneI + "\tcode:" + code);
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public int getPopSize() {
        return popSize;
    }

    public int getGeneSize() {
        return geneSize;
    }

    public int getMaxIterNum() {
        return maxIterNum;
    }

    public double getMutationRate() {
        return mutationRate;
    }

    public int getMaxMutationNum() {
        return maxMutationNum;
    }

    public int getGeneration() {
        return generation;
    }

    public double getBestScore() {
        return bestScore;
    }

    public double getWorstScore() {
        return worstScore;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public int getGeneI() {
        return geneI;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
