package br.com.doublelogic.tictactoe.model.constants;

public enum PlayerTypes {

    PLAYER_1(1, "X"),
    PLAYER_2(2, "O");

    private final int code;
    private final String symbol;

    PlayerTypes(int code, String symbol) {
        this.code = code;
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getCode() {
        return code;
    }
}