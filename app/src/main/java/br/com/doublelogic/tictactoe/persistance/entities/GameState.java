package br.com.doublelogic.tictactoe.persistance.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import br.com.doublelogic.tictactoe.persistance.converter.GameStateCellsConverter;

@Entity
public class GameState {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "player1_name")
    private String player1Name;

    @ColumnInfo(name = "player2_name")
    private String player2Name;

    @ColumnInfo(name = "current_player")
    private int currentPlayer;

    @TypeConverters({GameStateCellsConverter.class})
    private int[][] cells;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayer1Name() {
        return player1Name;
    }

    public void setPlayer1Name(String player1Name) {
        this.player1Name = player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }

    public void setPlayer2Name(String player2Name) {
        this.player2Name = player2Name;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public int[][] getCells() {
        return cells;
    }

    public void setCells(int[][] cells) {
        this.cells = cells;
    }
}
