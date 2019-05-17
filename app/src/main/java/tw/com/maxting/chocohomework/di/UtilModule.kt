package tw.com.maxting.chocohomework.di

import org.koin.dsl.module
import tw.com.maxting.chocohomework.util.DatabaseUtils
import tw.com.maxting.chocohomework.util.NetworkUtils

val utilModule = module {
    factory { NetworkUtils.provideChocoServices() }
    factory { DatabaseUtils.provideDb(get()) }
    factory { DatabaseUtils.provideDramaDao(get()) }
}