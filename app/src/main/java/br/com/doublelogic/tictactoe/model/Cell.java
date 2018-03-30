package br.com.doublelogic.tictactoe.model;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import br.com.doublelogic.tictactoe.utilities.StringUtility;

@Parcel
public class Cell {

    public Player player;

    @ParcelConstructor
    public Cell(Player player) {
        this.player = player;
    }

    public boolean isEmpty() {
        return player == null || StringUtility.isNullOrEmpty(player.value);
    }
}
