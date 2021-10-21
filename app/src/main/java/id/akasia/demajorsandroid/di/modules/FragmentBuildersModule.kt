package id.akasia.demajorsandroid.di.modules

import id.akasia.demajorsandroid.feature.detailpokemon.basestat.BaseStatFragment
import id.akasia.demajorsandroid.feature.detailpokemon.moves.MovesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.akasia.demajorsandroid.feature.home.HomeFragment
import id.akasia.demajorsandroid.feature.profile.ProfileFragment

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeBaseStatFragment(): BaseStatFragment

    @ContributesAndroidInjector
    abstract fun contributeMovesFragment(): MovesFragment
}
