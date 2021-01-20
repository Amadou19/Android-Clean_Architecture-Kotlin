package com.amadoutirera.cache.test.factory

import com.amadoutirera.cache.model.Config

object ConfigDataFactory {
    fun makeCachedConfig(): Config {
        return Config(-1, DataFactory.randomLong())
    }
}