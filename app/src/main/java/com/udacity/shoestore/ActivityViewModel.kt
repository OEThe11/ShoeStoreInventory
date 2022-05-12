package com.udacity.shoestore

import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.product.Shoe
import java.util.ArrayList


class ActivityViewModel : ViewModel() {

    //Live Data being created
    private var _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList


    //EVENT_NAVIGATE_TO_DETAIL_FRAGMENT
    private val _eventNavigateToShoeDetail = MutableLiveData<Boolean>()
    val eventNavigateToShoeDetail: LiveData<Boolean> =_eventNavigateToShoeDetail
    //  get() = _eventNavigateToShoeDetail

    //EVENT_NAVIGATE_BACK_TO_SHOE_LISTING
    private val _eventNavigateBackToListing = MutableLiveData<Boolean>()
    val eventNavigateBackToListing: LiveData<Boolean>
        get() = _eventNavigateBackToListing


    //LiveData created for all the EditText Views
    private var _name = MutableLiveData<String>()
    val name: LiveData<String>
        get() = _name
    var shoeName = ""

    private var _size = MutableLiveData<String>()
    val size: LiveData<String>
        get() = _size
    var shoeSize = ""

    private var _description = MutableLiveData<String>()
    val description: LiveData<String>
        get() = _description
    var shoeDescription = ""

    private var _company = MutableLiveData<String>()
    val company: LiveData<String>
        get() = _company
    var shoeCompany = ""

    //initalize the Navigation LiveData as well
    init {
        _shoeList.value = ArrayList()
        _eventNavigateBackToListing.value = false
        _eventNavigateToShoeDetail.value= false
    }


    //Creating the function to get the info for the ViewModel
//    fun addShoe(name: String, size: String, company: String, description: String) {
//        val shoe = Shoe(name, size, company, description)
//        _shoeList.value?.add(shoe)
//        _shoeList.value = _shoeList.value
//    }


    //functions for the Layout Buttons
    fun onFABClick() {
        _eventNavigateToShoeDetail.value = true
    }

    fun onSaveButtonClick() {
        _eventNavigateBackToListing.value = true

        _name.value = shoeName
        _size.value = shoeSize
        _description.value = shoeDescription
        _company.value = shoeCompany


        _shoeList.value?.add(Shoe(shoeName, shoeSize, shoeCompany, shoeDescription))



    }
    //called in the ShoeListFragment to loop the FAB button
    fun onNavigateToDetailFragmentComplete() {
        _eventNavigateToShoeDetail.value = false
        _eventNavigateBackToListing.value = false


    }

    override fun onCleared() {
        super.onCleared()
    }
}



