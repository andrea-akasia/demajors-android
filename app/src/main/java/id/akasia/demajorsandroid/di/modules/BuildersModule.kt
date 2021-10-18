package id.akasia.demajorsandroid.di.modules

import id.akasia.demajorsandroid.feature.listpokemon.ListPokemonActivity
import id.akasia.demajorsandroid.feature.detailpokemon.DetailPokemonActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.akasia.demajorsandroid.feature.login.LoginActivity

@Module
abstract class BuildersModule{

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun bindDetailPokemonActivity(): DetailPokemonActivity

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun bindListPokemonActivity(): ListPokemonActivity
}