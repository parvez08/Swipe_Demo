package com.example.swipedemo.utils

import org.koin.dsl.module

val networkModule = module {
    single { RestAPIClass().getClient() }
}

