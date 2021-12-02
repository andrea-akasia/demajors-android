package com.demajors.demajorsapp.di.modules

import com.demajors.demajorsapp.feature.cart.CartFragment
import com.demajors.demajorsapp.feature.detailpokemon.basestat.BaseStatFragment
import com.demajors.demajorsapp.feature.detailpokemon.moves.MovesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.demajors.demajorsapp.feature.home.HomeFragment
import com.demajors.demajorsapp.feature.myartist.MyArtistFragment
import com.demajors.demajorsapp.feature.myartist.detail.merchandise.ListArtistMerchandiseFragment
import com.demajors.demajorsapp.feature.myartist.detail.nft.ListArtistNFTFragment
import com.demajors.demajorsapp.feature.myartist.detail.rilisan.ListRilisanFragment
import com.demajors.demajorsapp.feature.nft.list.ListNftFragment
import com.demajors.demajorsapp.feature.profile.ProfileFragment
import com.demajors.demajorsapp.feature.profile.mynft.OpenedMyNFTFragment
import com.demajors.demajorsapp.feature.profile.mynft.unopened.UnopenedMyNFTFragment

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeOpenedMyNFTFragment(): OpenedMyNFTFragment

    @ContributesAndroidInjector
    abstract fun contributeUnopenedMyNFTFragment(): UnopenedMyNFTFragment

    @ContributesAndroidInjector
    abstract fun contributeCartFragment(): CartFragment

    @ContributesAndroidInjector
    abstract fun contributeListNftFragment(): ListNftFragment

    @ContributesAndroidInjector
    abstract fun contributeMyArtistFragment(): MyArtistFragment

    @ContributesAndroidInjector
    abstract fun contributeListArtistMerchandiseFragment(): ListArtistMerchandiseFragment

    @ContributesAndroidInjector
    abstract fun contributeListArtistNFTFragment(): ListArtistNFTFragment

    @ContributesAndroidInjector
    abstract fun contributeListRilisanFragment(): ListRilisanFragment

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeBaseStatFragment(): BaseStatFragment

    @ContributesAndroidInjector
    abstract fun contributeMovesFragment(): MovesFragment
}
