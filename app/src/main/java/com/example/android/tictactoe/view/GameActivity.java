package com.example.android.tictactoe.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.tictactoe.R;
import com.example.android.tictactoe.model.Game;
import com.example.android.tictactoe.viewModel.GameViewModel;

public class GameActivity extends AppCompatActivity {
    BoardFragment boardFragment;
    private GameViewModel gameViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);
        initToolBar();
        initGame();

        // observe winner
        gameViewModel.getWinner().observeForever(winner -> startNewGame(winner));
    }

    private void initGame() {
        int boardSize = getIntent().getIntExtra(Game.BOARD_SIZE, Game.DEFAULT_BOARD_SIZE);
        createFragment();
        gameViewModel = ViewModelProviders.of(this).get(GameViewModel.class);
        //TODO update player Name too
        gameViewModel.startSession(boardSize, getString(R.string.player1), getString(R.string.player2));
    }

    private void initToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void startNewGame(String winner) {
        String msg = (winner.equals("Draw")) ? winner : winner + " won";
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        resetFragment();
    }

    private void resetFragment() {
        createFragment();
    }

    private void createFragment() {
        boardFragment = BoardFragment.newInstance();
        getSupportFragmentManager().beginTransaction().
                replace(R.id.boardContainer, boardFragment, BoardFragment.class.getSimpleName()).commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        //exit Game
        alertUser();
        return true;
    }

    private void alertUser() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Add the buttons
        builder.setPositiveButton(R.string.ok, (dialog, id) -> {
            // User clicked OK button
            onBackPressed();
        });
        builder.setNegativeButton(R.string.cancel, (dialog, id) -> {
            // User cancelled the dialog
            dialog.cancel();
        });
        builder.setTitle(R.string.dlg_msg_title);
        builder.setMessage(R.string.dlg_msg);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.restart_game:
                restart();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /*Restart Game
     */
    private void restart() {
        gameViewModel.restartGame();
        resetFragment();
    }

}
