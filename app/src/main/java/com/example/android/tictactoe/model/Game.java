package com.example.android.tictactoe.model;

import android.arch.lifecycle.MutableLiveData;

public class Game {
    private static final String TAG = Game.class.getSimpleName();
    public static final int DEFAULT_BOARD_SIZE = 3;
    public static final String PLAYER_ONE = "X";
    public static final String PLAYER_TWO = "O";

    public Player currentPlayer;
    public Player player1;
    public Player player2;

    private int boardSize;

    public MutableLiveData<String> winner;
    public Cell[][] board;

    public Game(int boardSize, String playerOne, String playerTwo) {
        this.boardSize = boardSize;
        board = new Cell[boardSize][boardSize];
        player1 = new Player(playerOne, PLAYER_ONE);
        player2 = new Player(playerTwo, PLAYER_TWO);
        winner = new MutableLiveData<>();
        currentPlayer = player1;
    }

    public void resetGame(){
        board = new Cell[boardSize][boardSize];
        currentPlayer = player1;
        winner.hasActiveObservers();
        winner = new MutableLiveData<>();
    }

    public int getBoardSize () {return boardSize;}
    public void nextPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public boolean gameEnded(int row, int column) {

        if (checkRow(row, currentPlayer) || checkColumn(column, currentPlayer) || checkDiagonal(currentPlayer)) {
            winner.setValue(currentPlayer.getValue());
            return true;
        }

        if (isBoardFull()) {
            winner.setValue("Draw");
            return true;
        }

        return false;
    }

    // check if the current Row has same values
    protected boolean checkRow(int r, Player player) {
        for (int i = 0; i < boardSize; i++) {
            Cell cell = board[r][i];
            if (cell == null ||!cell.player.equals(player)) return false;
        }
        return true;
    }

    // check if the current column has same values
    protected boolean checkColumn(int c, Player player) {
        for (int i = 0; i < boardSize; i++) {
            Cell cell = board[i][c];
            if (cell == null ||!cell.player.equals(player)) return false;
        }
        return true;
    }

    //check if it has the same value diagonally
    public boolean checkDiagonal(Player player) {
        for (int row = 0; row < boardSize; row++) {
            Cell cell = board[row][row];
            if (cell == null ||!cell.player.equals(player)) return false;
        }
        return true;
    }


    public boolean isBoardFull() {
        for (Cell[] row : board)
            for (Cell cell : row)
                if (cell == null || cell.isEmpty())
                    return false;
        return true;
    }
}
