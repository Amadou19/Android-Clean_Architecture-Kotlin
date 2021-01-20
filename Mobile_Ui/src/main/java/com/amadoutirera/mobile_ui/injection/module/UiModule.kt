package com.amadoutirera.mobile_ui.injection.module

import com.amadoutirera.domain.executor.PostExecutionThread
import com.amadoutirera.mobile_ui.UiThread
import com.amadoutirera.mobile_ui.album.AlbumList.ListAlbumsActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UiModule {

    @Binds
    abstract fun bidPostExecutionThread(uiThread: UiThread): PostExecutionThread

    @ContributesAndroidInjector
    abstract fun contributesListAlbumsActivity(): ListAlbumsActivity


}