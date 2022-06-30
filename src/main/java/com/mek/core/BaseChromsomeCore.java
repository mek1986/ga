package com.mek.core;

import com.mek.core.ChromsomeCore;

import java.util.*;

/**
 * @author: ifelse
 * Date: 2022/6/24
 * VX: 250023777
 * Description: 染色体核心实现类
 * @version: 1.0
 */
public abstract class BaseChromsomeCore<T,M> implements ChromsomeCore<M> {
    //基因序列
    protected T[] gene;

    //对应得分
    protected double score;

    //是否开启调试
    public boolean isDebug = false;

    //随机种子
    protected Random random = new Random();

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
