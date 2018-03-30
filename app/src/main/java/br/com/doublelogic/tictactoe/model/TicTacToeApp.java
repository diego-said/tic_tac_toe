package br.com.doublelogic.tictactoe.model;

import android.app.Application;

import br.com.doublelogic.tictactoe.model.injection.AppModule;
import br.com.doublelogic.tictactoe.model.injection.DaggerDataComponent;
import br.com.doublelogic.tictactoe.model.injection.DataComponent;
import br.com.doublelogic.tictactoe.model.injection.DataModule;

public class TicTacToeApp extends Application {

    private DataComponent dataComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // Dagger%COMPONENT_NAME%
        dataComponent = DaggerDataComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appModule(new AppModule(this))
                .dataModule(new DataModule())
                .build();
    }

    public DataComponent getDataComponent() {
        return dataComponent;
    }
}