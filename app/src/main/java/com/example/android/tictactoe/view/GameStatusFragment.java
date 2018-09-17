package com.example.android.tictactoe.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.tictactoe.R;
import com.example.android.tictactoe.databinding.FragmentGameStatusBinding;
import com.example.android.tictactoe.viewModel.GameViewModel;

/**
 * A Fragment to show the status of the game session scores and draws
 * Also highlights players turn in the view
 */
public class GameStatusFragment extends Fragment {
    private GameViewModel viewModel;

    public GameStatusFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get viewModel from parent activity
        viewModel = ViewModelProviders.of(getActivity()).get(GameViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // All of the status is handled by the viewModel in the view (XML) files
        FragmentGameStatusBinding fragmentGameStatusBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_status, container, false);
        fragmentGameStatusBinding.setViewModel(viewModel);
        return fragmentGameStatusBinding.getRoot();
    }

}
