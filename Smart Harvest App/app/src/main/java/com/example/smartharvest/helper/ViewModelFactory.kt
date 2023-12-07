package com.example.smartharvest.helper

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.smartharvest.data.repository.Repository
import com.example.smartharvest.dependencyInjection.Injection
import com.example.smartharvest.ui.home.HomeViewModel
import com.example.smartharvest.ui.login.LoginViewModel
import com.example.smartharvest.ui.main.MainViewModel
import com.example.smartharvest.ui.register.RegisterViewModel

class ViewModelFactory(private val repository: Repository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(repository) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repository) as T
            }
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(repository) as T
            }
//            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
//                HomeViewModel(repository) as T
//            }
//            modelClass.isAssignableFrom(QRViewModel::class.java) -> {
//                QRViewModel(repository) as T
//            }
//            modelClass.isAssignableFrom(DashboardViewModel::class.java) -> {
//                DashboardViewModel(repository) as T
//            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = com.example.smartharvest.helper.ViewModelFactory(
                        Injection.provideRepository(context)
                    )
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}