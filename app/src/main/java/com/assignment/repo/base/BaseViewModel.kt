package com.assignment.repo.base

import androidx.lifecycle.ViewModel
import com.assignment.repo.di.component.DaggerViewModelInjector
import com.assignment.repo.di.module.NetworkModule

class BaseViewModel: ViewModel() {
    private val injector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    private fun inject(){
        when(this){

        }
    }
}