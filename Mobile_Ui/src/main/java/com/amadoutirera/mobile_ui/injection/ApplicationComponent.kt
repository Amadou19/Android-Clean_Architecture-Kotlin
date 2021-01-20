package com.amadoutirera.mobile_ui.injection

import android.app.Application
import com.amadoutirera.mobile_ui.AlbumApp
import com.amadoutirera.mobile_ui.injection.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ApplicationModule::class,
        CacheModule::class,
        DataModule::class,
        RemoteModule::class,
        PresentationModule::class,
        UiModule::class
    ]
)

interface ApplicationComponent {

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(albumApp: AlbumApp)
}