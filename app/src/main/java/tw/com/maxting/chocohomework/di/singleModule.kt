package tw.com.maxting.chocohomework.di

import org.koin.dsl.module
import tw.com.maxting.chocohomework.data.Repository

val singleModule = module {
    single { Repository(get(), get()) }
}