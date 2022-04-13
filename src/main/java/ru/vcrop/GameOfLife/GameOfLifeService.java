package ru.vcrop.GameOfLife;

import org.springframework.stereotype.Service;

@Service
public class GameOfLifeService {

    private int value(int row, int col, int[][] board) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length)
            return 0;
        return board[row][col] & 1;
    }

    public void gameOfLife(int[][] board) {
        for (int row = 0; row < board.length; row++)
            for (int col = 0; col < board[0].length; col++)
                board[row][col] >>>= 1;

        for (int row = 0; row < board.length; row++)
            for (int col = 0; col < board[0].length; col++) {
                int ones = 0;
                ones += value(row - 1, col - 1, board);
                ones += value(row - 1, col, board);
                ones += value(row - 1, col + 1, board);
                ones += value(row, col - 1, board);
                ones += value(row, col + 1, board);
                ones += value(row + 1, col - 1, board);
                ones += value(row + 1, col, board);
                ones += value(row + 1, col + 1, board);

                if (ones ==3) board[row][col] += 2;
                if (ones == 2 && board[row][col] == 1) board[row][col] += 2;
            }

    }

}
