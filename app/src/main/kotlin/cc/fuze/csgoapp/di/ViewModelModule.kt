package cc.fuze.csgoapp.di

import cc.fuze.csgoapp.presentation.match.detail.MatchDetailViewModel
import cc.fuze.csgoapp.presentation.match.list.MatchListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        MatchListViewModel(get())
    }

    viewModel {
        MatchDetailViewModel(get())
    }
}