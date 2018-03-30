package br.com.doublelogic.tictactoe.view;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.parceler.Parcels;

import java.util.List;

import br.com.doublelogic.tictactoe.R;
import br.com.doublelogic.tictactoe.model.Game;
import br.com.doublelogic.tictactoe.model.constants.PlayerTypes;
import br.com.doublelogic.tictactoe.model.localstorage.LoadGameAsync;
import br.com.doublelogic.tictactoe.persistance.entities.GameStatistic;
import br.com.doublelogic.tictactoe.viewmodel.GameDataViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;

public class GameWelcomeActivity extends AppCompatActivity implements Observer<List<GameStatistic>>, LoadGameAsync.LoadGameAsyncResponse {

    private static final String GAME_STATISTIC_DIALOG_TAG = "game_statistic_dialog_tag";

    @BindView(R.id.textViewWelcome) TextView welcome;

    @BindView(R.id.buttonNewGame) Button newGame;
    @BindView(R.id.buttonLoadGame) Button loadGame;
    @BindView(R.id.buttonStatistics) Button statistics;

    private GameDataViewModel gameDataViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_welcome);
        ButterKnife.bind(this);

        gameDataViewModel = new GameDataViewModel(getApplication());

        newGame.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), GameActivity.class);
            startActivity(intent);
        });

        loadGame.setOnClickListener(v -> {
            gameDataViewModel.loadGame(this);
        });

        statistics.setOnClickListener(v -> {
            gameDataViewModel.loadStoredStatistics(this, this);
        });
    }

    @Override
    public void onChanged(@Nullable List<GameStatistic> gameStatistics) {
        if(gameStatistics != null) {
            int player1Wins = 0;
            int player2Wins = 0;
            int draws = 0;
            for(GameStatistic statistic : gameStatistics) {
                if(PlayerTypes.PLAYER_1.getCode() == statistic.getWinner())
                    player1Wins++;
                else if(PlayerTypes.PLAYER_2.getCode() == statistic.getWinner())
                    player2Wins++;
                else
                    draws++;
            }
            GameStatisticsDialog dialog = GameStatisticsDialog.newInstance(player1Wins, player2Wins, draws);
            dialog.show(getFragmentManager(), GAME_STATISTIC_DIALOG_TAG);
        }
        gameDataViewModel.removeDataObserver(this);
    }

    @Override
    public void loadFinish(Game game) {
        if(game != null) {
            Intent intent = new Intent(getApplicationContext(), GameActivity.class);
            intent.putExtra(GameActivity.GAME_KEY, Parcels.wrap(game));
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), R.string.no_game_saved, Toast.LENGTH_SHORT).show();
        }
    }
}