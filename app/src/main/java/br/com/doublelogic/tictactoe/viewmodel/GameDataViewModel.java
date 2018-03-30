package br.com.doublelogic.tictactoe.viewmodel;

import android.app.Application;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;

import java.util.List;

import javax.inject.Inject;

import br.com.doublelogic.tictactoe.model.TicTacToeApp;
import br.com.doublelogic.tictactoe.model.localstorage.LoadGameAsync;
import br.com.doublelogic.tictactoe.model.localstorage.LocalStorageRepository;
import br.com.doublelogic.tictactoe.persistance.entities.GameStatistic;

public class GameDataViewModel {

    @Inject
    LocalStorageRepository localStorageRepository;

    private LiveData<List<GameStatistic>> data;

    public GameDataViewModel(Application application) {
        ((TicTacToeApp)application).getDataComponent().inject(this);
    }

    public void loadStoredStatistics(LifecycleOwner owner, Observer<List<GameStatistic>> observer) {
        data = localStorageRepository.loadStatistics();
        if(data != null) {
            data.observe(owner, observer);
        }
    }

    public void removeDataObserver(LifecycleOwner observer) {
        if(data != null) {
            data.removeObservers(observer);
        }
    }

    public void loadGame(LoadGameAsync.LoadGameAsyncResponse delegate) {
        localStorageRepository.loadGame(delegate);
    }
}