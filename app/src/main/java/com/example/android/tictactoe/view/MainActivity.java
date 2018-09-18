package com.example.android.tictactoe.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.tictactoe.R;
import com.example.android.tictactoe.model.Game;
import com.example.android.tictactoe.viewModel.GameViewModel;

public class MainActivity extends AppCompatActivity {
    BoardFragment boardFragment;
    private GameViewModel gameViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

   
}
