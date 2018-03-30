package br.com.doublelogic.tictactoe.model;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

@Parcel
public class Player {

    public String name;
    public String value;

    @ParcelConstructor
    public Player(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (name != null ? !name.equals(player.name) : player.name != null) return false;
        return value != null ? value.equals(player.value) : player.value == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}