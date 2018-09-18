package com.example.android.tictactoe.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
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
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        createFragment();
        gameViewModel = ViewModelProviders.of(this).get(GameViewModel.class);

        //TODO In theory you can update th board size from here
        gameViewModel.startSession(Game.DEFAULT_BOARD_SIZE, "Player1", "Player2");
        gameViewModel.getWinner().observeForever(winner -> startNewGame(winner));
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
