package br.com.doublelogic.tictactoe.persistance;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import br.com.doublelogic.tictactoe.persistance.dao.GameStateDao;
import br.com.doublelogic.tictactoe.persistance.dao.GameStatisticDao;
import br.com.doublelogic.tictactoe.persistance.entities.GameState;
import br.com.doublelogic.tictactoe.persistance.entities.GameStatistic;

@Database(entities = {GameState.class, GameStatistic.class}, version = 1)
public abstract class TicTacToeDatabase extends RoomDatabase {
    public abstract GameStateDao gameStateDao();
    public abstract GameStatisticDao gameStatisticDao();
}