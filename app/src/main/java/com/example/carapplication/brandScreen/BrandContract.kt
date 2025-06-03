package com.example.carapplication.brandScreen

import androidx.lifecycle.LiveData
import com.example.domain.model.Brand

class BrandContract  {


    // event , state
    interface  ViewModel{

        val event:LiveData<Event>
        val state:LiveData<State>

        fun invokeAction(action:Action)
    }

    sealed class Event{

        data object Navigate : Event()
    }
    sealed class State{

        class Loading(message:String):State()
        class Success(val brand :List<Brand>):State()
        class Error(message:String):State()

    }
    sealed class Action{
        class LoadBrand():Action()
    }



}