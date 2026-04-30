package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private final double[] thresholds; // 存储每次实验得到的阈值
    private final int T;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N and T must be greater than 0");
        }
        
        this.T = T;
        this.thresholds = new double[T];

        for (int i = 0; i < T; i++) {
            // 使用 pf 创建 Percolation 对象，不要直接 new
            Percolation p = pf.make(N);
            
            // 持续打开格子直到渗透
            while (!p.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                
                // 如果已经是开的，就继续找下一个随机点
                if (!p.isOpen(row, col)) {
                    p.open(row, col);
                }
            }
            
            // 计算当前实验的阈值：打开的格子数 / 总格子数
            thresholds[i] = (double) p.numberOfOpenSites() / (N * N);
        }
    }

    // 计算样本均值
    public double mean() {
        return StdStats.mean(thresholds);
    }

    // 计算样本标准差
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    // 95% 置信区间低端
    public double confidenceLow() {
        return mean() - (1.96 * stddev() / Math.sqrt(T));
    }

    // 95% 置信区间高端
    public double confidenceHigh() {
        return mean() + (1.96 * stddev() / Math.sqrt(T));
    }
}
