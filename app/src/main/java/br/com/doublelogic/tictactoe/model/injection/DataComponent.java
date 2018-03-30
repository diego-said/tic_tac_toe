package br.com.doublelogic.tictactoe.model.injection;

import javax.inject.Singleton;

import br.com.doublelogic.tictactoe.viewmodel.GameDataViewModel;
import br.com.doublelogic.tictactoe.viewmodel.GameViewModel;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, DataModule.class})
public interface DataComponent {

    void inject(GameDataViewModel gameDataViewModel);
    void inject(GameViewModel gameViewModel);

}