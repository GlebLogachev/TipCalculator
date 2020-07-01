package com.example.tipcalculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.math.RoundingMode
import java.text.DecimalFormat


class TipCalcViewModel : ViewModel() {

companion object {
   const val ADDCLIENTS = 1
    const val  REMOVECLIENTS = 1
    const val ADDTIP = 5
    const val REMOVETIP = 5
    val df = DecimalFormat("#.##")
}


private var _percent = MutableLiveData<Int>()
    val percent: LiveData<Int>
    get() = _percent

    private var _buddies = MutableLiveData<Int>()
    val buddies: LiveData<Int>
    get() = _buddies

    private var _billForOnePerson = MutableLiveData<Double>()

    val billForOnePerson: LiveData<Double>
    get() = _billForOnePerson


    init {
        _buddies.value = 1
        _percent.value = 5
    }

    fun addTipPercent(){
            _percent.value = _percent.value?.plus(ADDTIP)
    }
    fun removeTipPercent(){
        if (_percent.value!! > 0)
            _percent.value = _percent.value?.minus(REMOVETIP)
    }

    fun addBuddies(){
        _buddies.value = _buddies.value?.plus(ADDCLIENTS)

    }
    fun removeBuddies(){
        if (_buddies.value!! > 1 )
       _buddies.value = _buddies.value?.minus(REMOVECLIENTS)
    }


    fun createBillForOnePerson(totalBill: Double)   {
        val percents = (_percent.value!!.toDouble() / 100) * totalBill
        _billForOnePerson.value = df.format((totalBill + percents) / _buddies.value!!).toDouble()
    }

}