package com.demajors.demajorsapp.di.modules

import com.demajors.demajorsapp.feature.detailpokemon.basestat.BaseStatFragment
import com.demajors.demajorsapp.feature.detailpokemon.moves.MovesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.demajors.demajorsapp.feature.home.HomeFragment
import com.demajors.demajorsapp.feature.myartist.nft.ListArtistNFTFragment
import com.demajors.demajorsapp.feature.myartist.rilisan.ListRilisanFragment
import com.demajors.demajorsapp.feature.profile.ProfileFragment

@Module
abstract class FragmentBuildersModule {
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
