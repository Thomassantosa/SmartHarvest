package com.example.smartharvest.data.repository

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.smartharvest.data.api.ApiService
import com.example.smartharvest.data.pref.UserPreference
import com.example.smartharvest.data.responses.LoginResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(private val apiService: ApiService, private val userPreference: UserPreference) {

    suspend fun login(loginResult: LoginResult) {
        userPreference.login(loginResult)
    }

    fun getUser(): LiveData<LoginResult> {
        return userPreference.getUser().asLiveData()
    }

    suspend fun logout() {
        userPreference.logout()
    }

//    fun getStoryData(): LiveData<PagingData<ListStoryItem>> {
//        return Pager(
//            config = PagingConfig(
//                pageSize = 5
//            ),
//            pagingSourceFactory = {
//                StoryPagingSource(apiService, userPreference.getToken())
//            }
//        ).liveData
//    }
}