package br.com.doublelogic.tictactoe.model.localstorage;

import android.os.AsyncTask;

import br.com.doublelogic.tictactoe.model.Cell;
import br.com.doublelogic.tictactoe.model.Game;
import br.com.doublelogic.tictactoe.model.constants.PlayerTypes;
import br.com.doublelogic.tictactoe.persistance.TicTacToeDatabase;
import br.com.doublelogic.tictactoe.persistance.entities.GameStatistic;

public class SaveStatisticAsync extends AsyncTask<Game, Void, Boolean> {

    private final TicTacToeDatabase database;

    public SaveStatisticAsync(TicTacToeDatabase database) {
        this.database = database;
    }

    @Override
    protected Boolean doInBackground(Game... games) {
        if(games != null && games.length > 0) {
            for(Game game : games) {
                GameStatistic statistic = new GameStatistic();
                statistic.setMoves(totalMoves(game));
                statistic.setWinner(gameWinner(game));
                database.gameStatisticDao().insertRecord(statistic);
            }
            return true;
        }
        return false;
    }

    private int gameWinner(Game game) {
        if(game.winner == null)
            return 0;
        else if(PlayerTypes.PLAYER_1.getSymbol().equals(game.winner.value))
            return PlayerTypes.PLAYER_1.getCode();
        else
            return PlayerTypes.PLAYER_2.getCode();
    }

    private int totalMoves(Game game) {
        int total = 0;
        for (Cell[] row : game.cells)
            for (Cell cell : row)
                if (cell == null || cell.isEmpty())
                    total++;
        return total;
    }

}