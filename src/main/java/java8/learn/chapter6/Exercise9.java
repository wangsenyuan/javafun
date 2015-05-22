package java8.learn.chapter6;

import java.util.Arrays;

/**
 * Created by senyuanwang on 15/5/20.
 */
public class Exercise9 {

    static class Matrix {
        /*
        *a b
        *c d
        * */
        final int grid[][];

        Matrix(int[][] grid) {
            this.grid = grid;
        }

        public Matrix mul(Matrix that) {
            int m = that.grid[0].length;
            int n = this.grid.length;

            int[][] board = new int[n][m];

            for (int i = 0; i < n; i++) {
                for (int k = 0; k < that.grid.length; k++) {
                    for (int j = 0; j < m; j++) {
                        board[i][j] += this.grid[i][k] * that.grid[k][j];
                    }
                }
            }
            return new Matrix(board);
        }

        public Matrix pow(int k) {
//            int m = this.grid[0].length;
            int n = this.grid.length;

            int[][] board = new int[n][n];
            for (int i = 0; i < n; i++) {
                board[i][i] = 1;
            }
            Matrix a = this;
            Matrix b = new Matrix(board);
            while (n > 0) {
                if ((n & 1) == 1) {
                    b = a.mul(b);
                }
                a = a.mul(a);
                n >>= 1;
            }
            return b;
        }
    }

    public int fib(int n) {
        int[][] grid = {{1, 1}, {1, 0}};
        Matrix[] ms = new Matrix[n];

        Arrays.parallelSetAll(ms, i -> new Matrix(grid));

        Arrays.parallelPrefix(ms, Matrix::mul);

        return ms[n - 1].grid[0][0];
    }
}
