package com.assignment.repo.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.assignment.repo.base.BaseViewModel
import com.assignment.repo.network.PostApi
import com.assignment.repo.network.State
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DeveloperViewModel: BaseViewModel() {
    @Inject
    lateinit var postApi: PostApi

    val repositoryData: MutableLiveData<State> = MutableLiveData()

    private lateinit var subscription: Disposable

    init {
        loadDevelperData()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadDevelperData(){
        subscription = postApi.getDevelopers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{
                repositoryData.value = State.isLoading(true)
            }
            .doOnTerminate{
                repositoryData.value = State.isLoading(false)
            }
            .subscribe(
                {result->
                    if(result.isSuccessful){
                        when(result.code()){
                            200 -> {
                                repositoryData.value = State.onSuccess(result.body())
                            }
                            else ->{
                                repositoryData.value = State.onFailure("some error")
                            }
                        }
                    }else{
                        repositoryData.value = State.onFailure("some error")
                    }
                },
                {
                        error->
                    repositoryData.value = State.onFailure(error.message)
                }
            )
    }
}