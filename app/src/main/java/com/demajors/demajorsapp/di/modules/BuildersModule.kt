package com.demajors.demajorsapp.di.modules

import com.demajors.demajorsapp.feature.listpokemon.ListPokemonActivity
import com.demajors.demajorsapp.feature.detailpokemon.DetailPokemonActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.demajors.demajorsapp.feature.login.LoginActivity
import com.demajors.demajorsapp.feature.main.MainActivity
import com.demajors.demajorsapp.feature.myartist.detail.ArtistDetailActivity
import com.demajors.demajorsapp.feature.nft.DetailNFTActivity
import com.demajors.demajorsapp.feature.song.DetailSongActivity

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun bindDetailNFTActivity(): DetailNFTActivity

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun bindDetailSongActivity(): DetailSongActivity

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun bindArtistDetailActivity(): ArtistDetailActivity

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun bindDetailPokemonActivity(): DetailPokemonActivity

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun bindListPokemonActivity(): ListPokemonActivity
}
