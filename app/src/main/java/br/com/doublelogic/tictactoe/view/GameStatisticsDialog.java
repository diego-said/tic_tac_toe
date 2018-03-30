package br.com.doublelogic.tictactoe.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import br.com.doublelogic.tictactoe.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class GameStatisticsDialog extends DialogFragment {

    private int player1Wins = 0;
    private int player2Wins = 0;
    private int draws = 0;

    @BindView(R.id.textViewPlayer1Wins) TextView textViewPlayer1Wins;
    @BindView(R.id.textViewPlayer2Wins) TextView textViewPlayer2Wins;
    @BindView(R.id.textViewDraws) TextView textViewDraws;

    private View rootView;

    public static GameStatisticsDialog newInstance(int player1Wins, int player2Wins, int draws) {
        GameStatisticsDialog dialog = new GameStatisticsDialog();
        dialog.player1Wins = player1Wins;
        dialog.player2Wins = player2Wins;
        dialog.draws = draws;
        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        initViews();
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setView(rootView)
                .setCancelable(false)
                .setPositiveButton(R.string.done, (((dialog, which) -> dismiss())))
                .create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        return alertDialog;
    }

    private void initViews() {
        rootView = LayoutInflater.from(getActivity())
                .inflate(R.layout.activity_game_statistics_dialog, null);
        ButterKnife.bind(this, rootView);

        textViewPlayer1Wins.setText(String.valueOf(player1Wins));
        textViewPlayer2Wins.setText(String.valueOf(player2Wins));
        textViewDraws.setText(String.valueOf(draws));
    }
}