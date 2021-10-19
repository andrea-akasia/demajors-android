package id.akasia.demajorsandroid.di.modules

import id.akasia.demajorsandroid.feature.detailpokemon.basestat.BaseStatFragment
import id.akasia.demajorsandroid.feature.detailpokemon.moves.MovesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeBaseStatFragment(): BaseStatFragment

    @ContributesAndroidInjector
    abstract fun contributeMovesFragment(): MovesFragment
}
