package com.amadoutirera.mobile_ui

import android.app.Activity
import android.app.Application
import androidx.multidex.MultiDex
import com.amadoutirera.mobile_ui.injection.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class AlbumApp: Application(), HasActivityInjector{
    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>




    override fun onCreate() {
        super.onCreate()

        MultiDex.install(this)
        setupTimber()

        DaggerApplicationComponent
            .builder()
            .application(this)
            .build()
            .inject(this)
    }


    private fun setupTimber(){
        Timber.plant(Timber.DebugTree())
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }


}