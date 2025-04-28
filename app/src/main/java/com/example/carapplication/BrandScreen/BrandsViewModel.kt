package com.example.carapplication.BrandScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Brand
import com.example.domain.usecases.GetBrandsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BrandsViewModel @Inject constructor(
   private val brandsUseCase:GetBrandsUseCase
):ViewModel(){

    val brandsLiveData  = MutableLiveData<List<Brand?>?>()

   fun getBrands(){

       viewModelScope.launch {
         val result =  brandsUseCase.invoke()
           brandsLiveData.postValue(result)
       }

    }

}