package br.com.doublelogic.tictactoe.model.localstorage;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.List;

import br.com.doublelogic.tictactoe.model.Game;
import br.com.doublelogic.tictactoe.persistance.TicTacToeDatabase;
import br.com.doublelogic.tictactoe.persistance.entities.GameStatistic;

public class LocalStorageRepository {

    private final Context context;
    private TicTacToeDatabase db;

    private static LocalStorageRepository instance;

    public LocalStorageRepository(Context context) {
        this.context = context;
        db = Room.databaseBuilder(context, TicTacToeDatabase.class, "tictactoe-db").build();
    }

    public void saveStatistic(Game game) {
        Game clone = new Game();
        clone.player1 = game.player1;
        clone.player2 = game.player2;
        clone.winner = game.winner;
        clone.cells = game.cells;

        SaveStatisticAsync saveStatisticAsync = new SaveStatisticAsync(db);
        saveStatisticAsync.execute(clone);
    }

    public LiveData<List<GameStatistic>> loadStatistics() {
        return db.gameStatisticDao().fetchAllData();
    }

    public void saveGame(Game game) {
        SaveGameAsync saveGameAsync = new SaveGameAsync(context, db);
        saveGameAsync.execute(game);
    }

    public void loadGame(LoadGameAsync.LoadGameAsyncResponse delegate) {
        LoadGameAsync loadGameAsync = new LoadGameAsync(db, delegate);
        loadGameAsync.execute();
    }

}