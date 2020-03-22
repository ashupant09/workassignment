package com.assignment.repo.base

import androidx.lifecycle.ViewModel
import com.assignment.repo.di.component.DaggerViewModelInjector
import com.assignment.repo.di.module.NetworkModule
import com.assignment.repo.ui.viewmodel.DeveloperViewModel
import com.assignment.repo.ui.viewmodel.RepositoryViewModel

open class BaseViewModel: ViewModel() {
    private val injector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    private fun inject(){
        when(this){

            is RepositoryViewModel -> injector.inject(this)
            is DeveloperViewModel -> injector.inject(this)
        }
    }
}