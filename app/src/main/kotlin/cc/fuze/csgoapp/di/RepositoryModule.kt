package cc.fuze.csgoapp.di

import cc.fuze.csgoapp.data.MatchRepository
import org.koin.dsl.module

val repositoryModule = module {

    factory {
        MatchRepository(get())
    }
}