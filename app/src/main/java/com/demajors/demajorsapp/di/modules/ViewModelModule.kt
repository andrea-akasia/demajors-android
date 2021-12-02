package com.demajors.demajorsapp.di.modules

import dagger.Module
import androidx.lifecycle.ViewModel
import com.demajors.demajorsapp.di.scopes.ViewModelKey
import com.demajors.demajorsapp.feature.listpokemon.ListPokemonViewModel
import dagger.multibindings.IntoMap
import dagger.Binds
import com.demajors.demajorsapp.base.AppViewModelFactory
import androidx.lifecycle.ViewModelProvider
import com.demajors.demajorsapp.feature.animation.AnimationViewModel
import com.demajors.demajorsapp.feature.cart.CartViewModel
import com.demajors.demajorsapp.feature.detailpokemon.DetailPokemonViewModel
import com.demajors.demajorsapp.feature.detailpokemon.basestat.BaseStatViewModel
import com.demajors.demajorsapp.feature.detailpokemon.moves.MovesViewModel
import com.demajors.demajorsapp.feature.home.HomeViewModel
import com.demajors.demajorsapp.feature.login.LoginViewModel
import com.demajors.demajorsapp.feature.main.MainViewModel
import com.demajors.demajorsapp.feature.merchandise.MerchandiseViewModel
import com.demajors.demajorsapp.feature.myartist.MyArtistViewModel
import com.demajors.demajorsapp.feature.myartist.detail.ArtistViewModel
import com.demajors.demajorsapp.feature.nft.detail.DetailNFTViewModel
import com.demajors.demajorsapp.feature.nft.list.ListNFTViewModel
import com.demajors.demajorsapp.feature.profile.ProfileViewModel
import com.demajors.demajorsapp.feature.profile.mynft.MyNFTViewModel
import com.demajors.demajorsapp.feature.rilisan.RilisanViewModel
import com.demajors.demajorsapp.feature.song.SongViewModel

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MyNFTViewModel::class)
    abstract fun bindMyNFTViewModel(myNFTViewModel: MyNFTViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AnimationViewModel::class)
    abstract fun bindAnimationViewModel(animationViewModel: AnimationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CartViewModel::class)
    abstract fun bindCartViewModel(cartViewModel: CartViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ListNFTViewModel::class)
    abstract fun bindListNFTViewModel(listNFTViewModel: ListNFTViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RilisanViewModel::class)
    abstract fun bindRilisanViewModel(rilisanViewModel: RilisanViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MerchandiseViewModel::class)
    abstract fun bindMerchandiseViewModel(merchandiseViewModel: MerchandiseViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailNFTViewModel::class)
    abstract fun bindDetailNFTViewModel(detailNFTViewModel: DetailNFTViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SongViewModel::class)
    abstract fun bindSongViewModel(songViewModel: SongViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MyArtistViewModel::class)
    abstract fun bindMyArtistViewModel(myArtistViewModel: MyArtistViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ArtistViewModel::class)
    abstract fun bindArtistViewModel(artistViewModel: ArtistViewModel): ViewModel

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
