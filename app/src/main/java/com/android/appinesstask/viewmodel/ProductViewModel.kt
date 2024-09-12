package com.android.appinesstask.viewmodel


import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import androidx.lifecycle.viewModelScope
import com.android.appinesstask.dao.AppDatabase
import com.android.appinesstask.dao.ProductDao
import com.android.appinesstask.dao.ProductRepository
import com.android.appinesstask.model.ProductModel
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : AndroidViewModel(application) {

    private val productRepository: ProductRepository
    private val productDao: ProductDao

    init {
        val database = Room.databaseBuilder(application, AppDatabase::class.java, "app_db")
            .fallbackToDestructiveMigration()
            .build()
        productDao = database.productDao()
        productRepository = ProductRepository(productDao)
    }

    val products: MutableLiveData<List<ProductModel>> = MutableLiveData()

    fun syncData() {
        viewModelScope.launch {
            if (isInternetAvailable(getApplication())) {
                try {
                    productRepository.syncProducts()
                } catch (e: Exception) {

                    Log.e("SyncError", "Error syncing products: ${e.message}")
                }
            }
            // Always load data from the local database, whether internet is available or not
            products.postValue(productRepository.getAllProducts())
        }
    }


    private fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }
}
