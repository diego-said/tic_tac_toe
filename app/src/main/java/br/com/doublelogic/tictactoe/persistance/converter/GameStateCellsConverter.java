package br.com.doublelogic.tictactoe.persistance.converter;

import android.arch.persistence.room.TypeConverter;

import br.com.doublelogic.tictactoe.model.Game;

public class GameStateCellsConverter {

    @TypeConverter
    public static String fromArrayToString(int[][] array) {
        StringBuilder value = new StringBuilder();
        for (int[] row : array)
            for (int cell : row)
                value.append(cell);
        return value.toString();
    }

    @TypeConverter
    public static int[][] fromStringToArray(String value) {
        int row = 0;
        int column = 0;
        int[][] array = new int[Game.BOARD_SIZE][Game.BOARD_SIZE];
        for (int i = 0; i < value.length(); i++) {
            if(column > 2) {
                row++;
                column = 0;
            }
            array[row][column] = Integer.valueOf(String.valueOf(value.charAt(i)));
            column++;
        }
        return array;
    }

}