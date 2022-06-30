# 摘要

遗传算法(Genetic Algorithm,GA)java实现。包含3个测试用例：01背包问题，N皇后问题，力扣编号091问题。

# 项目结构

![image](https://user-images.githubusercontent.com/31432203/176586716-e4967280-6269-4fee-85f9-5a19f92a06ad.png)

1. core目录

   算法核心实现目录
   
   - GeneticAlgoCore接口，算法核心逻辑接口
   - ChromsomeCore接口，染色体核心接口  
   - BaseGeneticAlgoCore抽象类，算法核心逻辑实现类
   - BaseChromsomeCore，染色体核心实现类 
   
2. task目录
   
   测试用例目录
   
   - knapsack01目录，01背包问题
   - lc091目录，力扣编号091问题
   - nqueen目录，N皇后问题

3. APP类

   算法测试demo类
   
# 如何使用

对于实际要解决的问题，模仿task目录里的做法。继承BaseGeneticAlgoCore和BaseChromsomeCore

1. 创建问题核心逻辑类。继承BaseGeneticAlgoCore，主要自定义实现下面3个方法:
  -  calculateScore，适应度函数
  -  initPopulation，种群初始化函数
  -  isEvolveDone，遗传停止判断函数
2. 创建染色体类。继承BaseChromsomeCore。这个类要实现的方法比较多，我就不一一列举了，可以参考测试用例，大部分逻辑基本都是一模一样的，只要copy过来稍微改一下就行了。
3. 调参。不同的问题，不同的参数会有完全不同的结果，需要不断尝试寻找合适的参数。关键的参数如下:
  -  geneSize，基因长度
  -  maxMutationNum，最大变异步长
  -  popSize，种群数量
  -  maxIterNum，最大迭代次数
  -  mutationRate，染色体变异的概率

# 关于调试

BaseGeneticAlgoCore和BaseChromsomeCore都有debug字段，如果需要查看运行过程信息，可以设置为true。

# 后记

代码有什么问题和不足之处，欢迎大家指正。
