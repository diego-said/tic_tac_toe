package br.com.doublelogic.tictactoe.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;

import org.parceler.Parcels;

import java.util.Observable;
import java.util.Observer;

import br.com.doublelogic.tictactoe.R;
import br.com.doublelogic.tictactoe.databinding.ActivityGameBinding;
import br.com.doublelogic.tictactoe.model.Game;
import br.com.doublelogic.tictactoe.viewmodel.GameViewModel;

public class GameActivity extends AppCompatActivity implements Observer {

    public static final String GAME_KEY = "game_key";

    private static final String GAME_BEGIN_DIALOG_TAG = "game_dialog_tag";
    private static final String GAME_END_DIALOG_TAG = "game_end_dialog_tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Parcelable obj = getIntent().getParcelableExtra(GAME_KEY);
        if(obj != null) {
            Game game = Parcels.unwrap(obj);
            initDataBinding(game);
        } else {
            promptForPlayers();
        }
    }

    public void promptForPlayers() {
        GameBeginDialog dialog = GameBeginDialog.newInstance(this);
        dialog.show(getFragmentManager(), GAME_BEGIN_DIALOG_TAG);
    }

    public void onPlayersSet(String player1, String player2) {
        initDataBinding(player1, player2);
    }

    private void initDataBinding(String player1, String player2) {
        ActivityGameBinding activityGameBinding = DataBindingUtil.setContentView(this, R.layout.activity_game);
        GameViewModel gameViewModel = new GameViewModel(getApplication(), player1, player2);
        activityGameBinding.setGameViewModel(gameViewModel);
        gameViewModel.addObserver(this);
    }

    private void initDataBinding(Game game) {
        ActivityGameBinding activityGameBinding = DataBindingUtil.setContentView(this, R.layout.activity_game);
        GameViewModel gameViewModel = new GameViewModel(getApplication(), game);
        activityGameBinding.setGameViewModel(gameViewModel);
        gameViewModel.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        GameEndDialog dialog = GameEndDialog.newInstance(this, (String) arg);
        dialog.show(getFragmentManager(), GAME_END_DIALOG_TAG);
    }

}