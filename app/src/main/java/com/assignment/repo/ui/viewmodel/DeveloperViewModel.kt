package com.assignment.repo.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.assignment.repo.base.BaseViewModel
import com.assignment.repo.network.PostApi
import com.assignment.repo.network.State
import com.assignment.repo.pojo.DeveloperList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DeveloperViewModel : BaseViewModel() {
    @Inject
    lateinit var postApi: PostApi

    val developerData: MutableLiveData<State> = MutableLiveData()

    private lateinit var subscription: Disposable

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    fun loadDevelperData() {
        subscription = postApi.getDevelopers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                developerData.value = State.isLoading(true)
            }
            .doOnTerminate {
                developerData.value = State.isLoading(false)
            }
            .subscribe(
                { result ->
                    if (result.isSuccessful) {
                        when (result.code()) {
                            200 -> {
                                developerData.value = State.onSuccess(DeveloperList(result.body()))
                            }
                            else -> {
                                developerData.value = State.onFailure("some error")
                            }
                        }
                    } else {
                        developerData.value = State.onFailure("some error")
                    }
                },
                { error ->
                    developerData.value = State.onFailure(error.message)
                }
            )
    }
}