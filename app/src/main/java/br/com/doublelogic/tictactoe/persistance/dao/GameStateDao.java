package br.com.doublelogic.tictactoe.persistance.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import br.com.doublelogic.tictactoe.persistance.entities.GameState;

@Dao
public interface GameStateDao {

    @Insert
    void insertRecord(GameState state);

    @Delete
    void deleteRecord(GameState state);

    @Query("SELECT * FROM GameState")
    GameState fetchData();

}
