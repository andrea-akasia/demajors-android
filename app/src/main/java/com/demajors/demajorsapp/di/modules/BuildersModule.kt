package com.demajors.demajorsapp.di.modules

import com.demajors.demajorsapp.feature.animation.AnimationActivity
import com.demajors.demajorsapp.feature.listpokemon.ListPokemonActivity
import com.demajors.demajorsapp.feature.detailpokemon.DetailPokemonActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.demajors.demajorsapp.feature.login.LoginActivity
import com.demajors.demajorsapp.feature.main.MainActivity
import com.demajors.demajorsapp.feature.merchandise.DetailMerchandiseActivity
import com.demajors.demajorsapp.feature.myartist.detail.ArtistDetailActivity
import com.demajors.demajorsapp.feature.nft.detail.DetailNFTActivity
import com.demajors.demajorsapp.feature.profile.mynft.MyNftActivity
import com.demajors.demajorsapp.feature.profile.mynft.unopened.UnopenedMyNFTDetailActivity
import com.demajors.demajorsapp.feature.rilisan.DetailRilisanActivity
import com.demajors.demajorsapp.feature.signup.SignUpActivity
import com.demajors.demajorsapp.feature.signup.VerifyEmailActivity
import com.demajors.demajorsapp.feature.song.DetailSongActivity

@Module
abstract class BuildersModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun bindVerifyEmailActivity(): VerifyEmailActivity

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun bindSignUpActivity(): SignUpActivity

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun bindUnopenedMyNFTDetailActivity(): UnopenedMyNFTDetailActivity

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun bindMyNftActivity(): MyNftActivity

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun bindAnimationActivity(): AnimationActivity

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun bindDetailRilisanActivity(): DetailRilisanActivity

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun bindDetailMerchandiseActivity(): DetailMerchandiseActivity

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
