package com.amadoutirera.mobile_ui.mapper

interface UiMapper<in P, out U> {
    fun mapToUi(presentation: P): U
}