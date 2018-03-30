package br.com.doublelogic.tictactoe.model.injection;

import android.app.Application;

import javax.inject.Singleton;

import br.com.doublelogic.tictactoe.model.localstorage.LocalStorageRepository;
import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @Provides
    @Singleton
    LocalStorageRepository provideLocalStorageRepository(Application application) {
        LocalStorageRepository localStorageRepository = new LocalStorageRepository(application);
        return localStorageRepository;
    }

}