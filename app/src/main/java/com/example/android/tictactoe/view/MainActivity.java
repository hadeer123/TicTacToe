package com.example.android.tictactoe.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.android.tictactoe.R;
import com.example.android.tictactoe.databinding.FragmentGameStatusBinding;
import com.example.android.tictactoe.model.Game;
import com.example.android.tictactoe.viewModel.GameViewModel;

public class MainActivity extends AppCompatActivity {
    Button button;
    RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.activityButton);
        radioGroup = findViewById(R.id.radio);
        button.setOnClickListener(v -> {
            startGame();
        });
    }

    //TODO get players name from users
    // TODO put this in a viewModel and bind it
    private void startGame() {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(Game.BOARD_SIZE, getBoardSize());
        startActivity(intent);
    }

    // getBoard Size from the selected radio button
    private int getBoardSize(){
        int selectedId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(selectedId);
        int boardSize = (radioButton.getText().equals(getString(R.string._3_x_3)))?Game.DEFAULT_BOARD_SIZE:Game.BIGGER_BOARD_SIZE;
        return boardSize;
    }

}
