package com.demajors.demajorsapp.di.modules

import dagger.Module
import androidx.lifecycle.ViewModel
import com.demajors.demajorsapp.di.scopes.ViewModelKey
import com.demajors.demajorsapp.feature.listpokemon.ListPokemonViewModel
import dagger.multibindings.IntoMap
import dagger.Binds
import com.demajors.demajorsapp.base.AppViewModelFactory
import androidx.lifecycle.ViewModelProvider
import com.demajors.demajorsapp.feature.detailpokemon.DetailPokemonViewModel
import com.demajors.demajorsapp.feature.detailpokemon.basestat.BaseStatViewModel
import com.demajors.demajorsapp.feature.detailpokemon.moves.MovesViewModel
import com.demajors.demajorsapp.feature.home.HomeViewModel
import com.demajors.demajorsapp.feature.login.LoginViewModel
import com.demajors.demajorsapp.feature.main.MainViewModel
import com.demajors.demajorsapp.feature.profile.ProfileViewModel

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(profileViewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

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
