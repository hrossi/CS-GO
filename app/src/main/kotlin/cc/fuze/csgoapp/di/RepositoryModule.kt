package cc.fuze.csgoapp.di

import cc.fuze.csgoapp.data.MatchRepository
import cc.fuze.csgoapp.data.TeamRepository
import org.koin.dsl.module

val repositoryModule = module {

    factory {
        MatchRepository(get())
    }

    factory {
        TeamRepository(get())
    }
}