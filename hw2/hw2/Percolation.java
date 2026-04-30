package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

//It's easy to see that we need to use a two-dimensional array to mark whether a square is open when solving this problem. 
//Then, the key is to consider how to determine if a square is fully connected. We can think of using sets( like WeightedQuickUnionUF class) to solve this problem, putting connected squares into a set and directly checking if it's full. So you need to be familler with the API of WQUUF class.
public class Percolation {
    private final boolean[][] grid;
    private final WeightedQuickUnionUF uf;   
    private final WeightedQuickUnionUF ufFull; 
	 	private final int N;
    private int openSitesCount;
    private final int virtualTop;
    private final int virtualBottom;

   
    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException("N must be > 0");
        this.N = N;
        this.grid = new boolean[N][N];
        this.openSitesCount = 0;

        
        this.virtualTop = N * N;
        this.virtualBottom = N * N + 1;

        
        this.uf = new WeightedQuickUnionUF(N * N + 2);
        this.ufFull = new WeightedQuickUnionUF(N * N + 1);
    }

    private int getIndex(int row, int col) {
        return row * N + col;
    }

    private void validate(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

    public void open(int row, int col) {
        validate(row, col);
        if (isOpen(row, col)) return;

        grid[row][col] = true;
        openSitesCount++;
        int current = getIndex(row, col);

        
        if (row == 0) {
            uf.union(current, virtualTop);
            ufFull.union(current, virtualTop);
        }
        
 				if (row == N - 1) {
            uf.union(current, virtualBottom);
        }

 
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        for (int i = 0; i < 4; i++) {
            int newRow = row + dx[i];
            int newCol = col + dy[i];

            
            if (newRow >= 0 && newRow < N && newCol >= 0 && newCol < N && isOpen(newRow, newCol)) {
                int neighbor = getIndex(newRow, newCol);
                uf.union(current, neighbor);
                ufFull.union(current, neighbor);
            }
        }
    }

    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        if (!isOpen(row, col)) return false;
        
        return ufFull.connected(getIndex(row, col), virtualTop);
    }

    public int numberOfOpenSites() {
        return openSitesCount;
    }

    public boolean percolates() {
        if (N == 1) return isOpen(0, 0); 
        return uf.connected(virtualTop, virtualBottom);
    }

    public static void main(String[] args) {
        
    }
}
