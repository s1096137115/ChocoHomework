package tw.com.maxting.chocohomework.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import tw.com.maxting.chocohomework.ui.info.InfoViewModel
import tw.com.maxting.chocohomework.ui.list.ListViewModel

val viewModelModule = module {
    viewModel { ListViewModel(get()) }
    viewModel { InfoViewModel(get()) }
}