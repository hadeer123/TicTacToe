package com.example.android.tictactoe.view;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.example.android.tictactoe.R;
import com.example.android.tictactoe.databinding.CellItemBinding;
import com.example.android.tictactoe.viewModel.GameViewModel;

public class BoardAdapter extends BaseAdapter {
    private final GameViewModel viewModel;

    public BoardAdapter(GameViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public int getCount() {
        //populating the grid view according to the gridSize
        int count = viewModel.getGame().getBoardSize();
        return count * count;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        CellItemBinding itemBinding = null;

        //mapping position to 2D array
        int row = position / viewModel.getGame().getBoardSize();
        int col = position % viewModel.getGame().getBoardSize();

        if (view == null) {
            //TODO remove binding ?
            itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.cell_item,
                    parent, false);
            itemBinding.setGameViewModel(viewModel);
            view = itemBinding.getRoot();
        }

        final Button cell = view.findViewById(R.id.button);
        cell.setOnClickListener((View v) -> {
            if (viewModel.cells.get(position) == null) {
                     /*
                     Calls cellSelected passing position as an id and row and col to update the
                     model of the "matrix" board
                      */
                viewModel.cellSelected(position, row, col);

                // updates the button text with the value of the player
                String txt = viewModel.cells.get(position);
                cell.setText(txt);
            }
        });

        return view;
    }
}
