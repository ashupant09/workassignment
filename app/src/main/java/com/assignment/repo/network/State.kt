package com.assignment.repo.network

import retrofit2.Response

sealed class State {
    data class isLoading(var isLoading: Boolean = false): State()
    data class onSuccess(var data: AppResponse?, var isLoading: Boolean = false): State()
    data class onFailure(var data: Any?, var isLoading: Boolean = false): State()
}