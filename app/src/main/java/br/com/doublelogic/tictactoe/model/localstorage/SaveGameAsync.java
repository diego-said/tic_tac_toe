package br.com.doublelogic.tictactoe.model.localstorage;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.com.doublelogic.tictactoe.R;
import br.com.doublelogic.tictactoe.model.Cell;
import br.com.doublelogic.tictactoe.model.Game;
import br.com.doublelogic.tictactoe.model.constants.PlayerTypes;
import br.com.doublelogic.tictactoe.persistance.TicTacToeDatabase;
import br.com.doublelogic.tictactoe.persistance.entities.GameState;

public class SaveGameAsync extends AsyncTask<Game, Void, Boolean> {

    private final Context context;
    private final TicTacToeDatabase database;

    public SaveGameAsync(Context context, TicTacToeDatabase database) {
        this.context = context;
        this.database = database;
    }

    @Override
    protected Boolean doInBackground(Game... games) {
        if(games != null && games.length > 0) {
            //remove last state
            GameState state = database.gameStateDao().fetchData();
            if(state != null) {
                database.gameStateDao().deleteRecord(state);
            }

            // save new state
            Game game = games[0];

            state = new GameState();
            state.setPlayer1Name(game.player1.name);
            state.setPlayer2Name(game.player2.name);
            if(game.currentPlayer.equals(game.player1))
                state.setCurrentPlayer(PlayerTypes.PLAYER_1.getCode());
            else
                state.setCurrentPlayer(PlayerTypes.PLAYER_2.getCode());
            state.setCells(parseGameCells(game));

            database.gameStateDao().insertRecord(state);

            return true;
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if(result) {
            Toast.makeText(context, R.string.game_saved, Toast.LENGTH_SHORT).show();
        }
    }

    private int[][] parseGameCells(Game game) {
        int[][] cells = new int[Game.BOARD_SIZE][Game.BOARD_SIZE];
        for(int i=0; i<Game.BOARD_SIZE; i++) {
            for(int j=0; j<Game.BOARD_SIZE; j++) {
                Cell cell = game.cells[i][j];
                if(cell != null) {
                    if(game.player1.equals(cell.player)) {
                        cells[i][j] = PlayerTypes.PLAYER_1.getCode();
                    } else if(game.player2.equals(cell.player)) {
                        cells[i][j] = PlayerTypes.PLAYER_2.getCode();
                    } else {
                        cells[i][j] = 0;
                    }
                }
            }
        }
        return cells;
    }
}