package com.example.android.tictactoe.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.android.tictactoe.R;
import com.example.android.tictactoe.viewModel.GameViewModel;

/**
 * Fragment for the board (matrix) game populated with buttons depending on the game boardSize
 * using an Adaptor to handel clicking events
 */
public class BoardFragment extends Fragment {
    private GameViewModel viewModel;
    private GridView gridView;

    public BoardFragment() {
        // Required empty public constructor
    }

    public static BoardFragment newInstance() {
        BoardFragment fragment = new BoardFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get ViewModel
        viewModel = ViewModelProviders.of(getActivity()).get(GameViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_board_grid, container, false);
        gridView = view.findViewById(R.id.gridView);
        return view;
    }
//    private void forHardCodedView(LayoutInflater inflater){
//        FragmentBoardGridBinding fragmentBoardGridBinding;
//        fragmentBoardGridBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_board_grid,container,false);
//        fragmentBoardGridBinding.setGameViewModel(viewModel);
//        view = fragmentBoardGridBinding.getRoot();
//    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //set Adapter here to make sure viewModel is not null
        BoardAdapter boardAdapter = new BoardAdapter(viewModel);
        gridView.setAdapter(boardAdapter);
    }
}
