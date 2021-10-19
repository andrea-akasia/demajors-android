package id.akasia.demajorsandroid.di.component

import id.akasia.demajorsandroid.App
import id.akasia.demajorsandroid.di.modules.NetworkModule
import dagger.BindsInstance
import id.akasia.demajorsandroid.di.modules.BuildersModule
import id.akasia.demajorsandroid.di.modules.AppModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        BuildersModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        @BindsInstance
        fun networkModule(networkModule: NetworkModule): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}
