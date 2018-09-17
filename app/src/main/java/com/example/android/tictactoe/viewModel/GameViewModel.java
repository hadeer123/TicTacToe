package com.example.android.tictactoe.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableArrayMap;
import android.databinding.ObservableField;

import com.example.android.tictactoe.model.Cell;
import com.example.android.tictactoe.model.Game;

public class GameViewModel extends ViewModel {

    // used for the hardcoded view
    // public ObservableArrayMap<String, String> cells;
    public ObservableArrayMap<Integer, String> cells;

    // PlayerScores available all game
    public ObservableField<Integer> player1Score = new ObservableField<>();
    public ObservableField<Integer> player2Score = new ObservableField<>();
    public ObservableField<Integer> totalDraws = new ObservableField<>();

    // used for the view to highlight the currentPlayer
    public ObservableField<String> currentPlayer = new ObservableField<>();

    // to Notify the MainActivity of the new winner
    public LiveData<String> getWinner() {
        return game.winner;
    }

    private Game game;
    public Game getGame() {
        return game;
    }
    public int boardSize;
    public String player1, player2;


    public void startSession(int boardSize, String playerOne, String playerTwo) {
        player1 = playerOne;
        player2 = playerTwo;
        startGame(boardSize, playerOne, playerTwo);
        setInitialScore();
    }

    public void startGame(int boardSize, String playerOne, String playerTwo) {
        game = new Game(boardSize, playerOne, playerTwo);
        this.boardSize = boardSize;
        // cells = new ObservableArrayMap<>();
        cells = new ObservableArrayMap<>();
        currentPlayer.set(game.currentPlayer.getValue());
    }

    private void setInitialScore() {
        // initial score to start the game
        player1Score.set(0);
        player2Score.set(0);
        totalDraws.set(0);
    }

    public void restartGame() {
        cells.clear();
        game.resetGame();
    }

    public void cellSelected(int id, int row, int column) {
        if (game.board[row][column] == null) {
            game.board[row][column] = new Cell(game.currentPlayer);
            cells.put(id, game.currentPlayer.getValue());
            // cells.put(stringFromNumbers(row, column), game.currentPlayer.getValue());
            if (game.gameEnded(row, column)) {
                updateScores();
                restartGame();
            } else {
                game.nextPlayer();
                currentPlayer.set(game.currentPlayer.getValue());
            }
        }
    }

    private void updateScores() {
        if (game.winner.getValue() != null) {
            if (game.winner.getValue().equals(Game.PLAYER_ONE)) {
                int val = player1Score.get();
                player1Score.set(++(val));
            } else if (game.winner.getValue().equals(Game.PLAYER_TWO)) {
                int val = player2Score.get();
                player2Score.set(++(val));
            } else {
                int val = totalDraws.get();
                totalDraws.set(++(val));
            }
        }
    }
}
