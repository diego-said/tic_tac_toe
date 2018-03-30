package br.com.doublelogic.tictactoe.persistance.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import br.com.doublelogic.tictactoe.persistance.entities.GameStatistic;

@Dao
public interface GameStatisticDao {

    @Insert
    void insertRecord(GameStatistic statistic);

    @Query("SELECT * FROM GameStatistic")
    LiveData<List<GameStatistic>> fetchAllData();

}