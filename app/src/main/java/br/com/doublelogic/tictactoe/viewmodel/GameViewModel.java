package br.com.doublelogic.tictactoe.viewmodel;

import android.app.Application;
import android.databinding.ObservableArrayMap;

import java.util.Observable;

import javax.inject.Inject;

import br.com.doublelogic.tictactoe.model.Cell;
import br.com.doublelogic.tictactoe.model.Game;
import br.com.doublelogic.tictactoe.model.Player;
import br.com.doublelogic.tictactoe.model.TicTacToeApp;
import br.com.doublelogic.tictactoe.model.constants.PlayerTypes;
import br.com.doublelogic.tictactoe.model.localstorage.LocalStorageRepository;

import static br.com.doublelogic.tictactoe.utilities.StringUtility.stringFromNumbers;

public class GameViewModel extends Observable {

    private static final String NO_WINNER = "No one";

    public ObservableArrayMap<String, String> cells = new ObservableArrayMap<>();
    public Game game;

    @Inject
    LocalStorageRepository localStorageRepository;

    public GameViewModel(Application application, String player1, String player2) {
        ((TicTacToeApp)application).getDataComponent().inject(this);
        game = new Game();
        beginGame(new Player(player1, PlayerTypes.PLAYER_1.getSymbol()), new Player(player2, PlayerTypes.PLAYER_2.getSymbol()));
    }

    public GameViewModel(Application application, Game game) {
        ((TicTacToeApp)application).getDataComponent().inject(this);
        this.game = game;
        initCells();
    }

    public void beginGame(Player player1, Player player2) {
        game.player1 = player1;
        game.player2 = player2;
        game.currentPlayer = player1;
    }

    public void saveGame() {
        localStorageRepository.saveGame(game);
    }

    public void onClickedCellAt(int row, int column) {
        game.cells[row][column] = new Cell(game.currentPlayer);
        cells.put(stringFromNumbers(row, column), game.currentPlayer.value);
        if (game.hasGameEnded())
            onGameHasEnded();
        game.switchPlayer();
    }

    private void onGameHasEnded() {
        setChanged();
        localStorageRepository.saveStatistic(game);
        notifyObservers(game.winner == null ? NO_WINNER : game.winner.name);
        game.reset();
    }

    private void initCells() {
        for(int i=0; i<game.cells.length; i++) {
            Cell[] row = game.cells[i];
            for(int j=0; j<row.length; j++) {
                Cell cell = row[j];
                if(cell != null) {
                    cells.put(stringFromNumbers(i, j), cell.player.value);
                }
            }
        }
    }

}