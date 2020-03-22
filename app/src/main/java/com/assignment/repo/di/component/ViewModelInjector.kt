package com.assignment.repo.di.component

import com.assignment.repo.di.module.NetworkModule
import com.assignment.repo.ui.viewmodel.DeveloperViewModel
import com.assignment.repo.ui.viewmodel.RepositoryViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(repositoryViewModel: RepositoryViewModel)

    fun inject(developerViewModel: DeveloperViewModel)

    @Component.Builder
    interface Builder{
        fun build(): ViewModelInjector
        fun networkModule(networkModule: NetworkModule): Builder
    }
}