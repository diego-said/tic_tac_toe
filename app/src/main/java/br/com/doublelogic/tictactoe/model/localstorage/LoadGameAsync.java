package br.com.doublelogic.tictactoe.model.localstorage;

import android.os.AsyncTask;

import br.com.doublelogic.tictactoe.model.Cell;
import br.com.doublelogic.tictactoe.model.Game;
import br.com.doublelogic.tictactoe.model.Player;
import br.com.doublelogic.tictactoe.model.constants.PlayerTypes;
import br.com.doublelogic.tictactoe.persistance.TicTacToeDatabase;
import br.com.doublelogic.tictactoe.persistance.entities.GameState;

public class LoadGameAsync extends AsyncTask<Void, Void, Game> {

    private final TicTacToeDatabase database;
    private final LoadGameAsyncResponse delegate;

    public LoadGameAsync(TicTacToeDatabase database, LoadGameAsyncResponse delegate) {
        this.database = database;
        this.delegate = delegate;
    }

    public interface LoadGameAsyncResponse {
        void loadFinish(Game game);
    }

    @Override
    protected Game doInBackground(Void... voids) {
        GameState state = database.gameStateDao().fetchData();
        if(state != null) {
            Game game = new Game();
            game.player1 = new Player(state.getPlayer1Name(), PlayerTypes.PLAYER_1.getSymbol());
            game.player2 = new Player(state.getPlayer2Name(), PlayerTypes.PLAYER_2.getSymbol());
            if(state.getCurrentPlayer() == PlayerTypes.PLAYER_1.getCode())
                game.currentPlayer = game.player1;
            else
                game.currentPlayer = game.player2;
            parseGameCells(state.getCells(), game);
            return game;
        }
        return null;
    }

    private void parseGameCells(int[][] cells, Game game) {
        for(int i=0; i<Game.BOARD_SIZE; i++) {
            for(int j=0; j<Game.BOARD_SIZE; j++) {
                if(cells[i][j] == PlayerTypes.PLAYER_1.getCode())
                    game.cells[i][j] = new Cell(game.player1);
                else if(cells[i][j] == PlayerTypes.PLAYER_2.getCode())
                    game.cells[i][j] = new Cell(game.player2);
            }
        }
    }

    @Override
    protected void onPostExecute(Game game) {
        delegate.loadFinish(game);
    }
}
