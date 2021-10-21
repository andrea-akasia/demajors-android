package id.akasia.demajorsandroid.di.modules

import dagger.Module
import androidx.lifecycle.ViewModel
import id.akasia.demajorsandroid.di.scopes.ViewModelKey
import id.akasia.demajorsandroid.feature.listpokemon.ListPokemonViewModel
import dagger.multibindings.IntoMap
import dagger.Binds
import id.akasia.demajorsandroid.base.AppViewModelFactory
import androidx.lifecycle.ViewModelProvider
import id.akasia.demajorsandroid.feature.detailpokemon.DetailPokemonViewModel
import id.akasia.demajorsandroid.feature.detailpokemon.basestat.BaseStatViewModel
import id.akasia.demajorsandroid.feature.detailpokemon.moves.MovesViewModel
import id.akasia.demajorsandroid.feature.login.LoginViewModel
import id.akasia.demajorsandroid.feature.main.MainViewModel

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BaseStatViewModel::class)
    abstract fun bindBaseStatViewModel(baseStatViewModel: BaseStatViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovesViewModel::class)
    abstract fun bindMovesViewModel(movesViewModel: MovesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailPokemonViewModel::class)
    abstract fun bindDetailPokemonViewModel(detailPokemonViewModel: DetailPokemonViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ListPokemonViewModel::class)
    abstract fun bindListPokemonViewModel(listPokemonViewModel: ListPokemonViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory
}
