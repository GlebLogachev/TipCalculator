package com.example.tipcalculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.tipcalculator.databinding.FragmentTipCalcBinding
import androidx.core.widget.doAfterTextChanged as doAfterTextChanged1


class TipCalcFragment : Fragment() {
    private lateinit var  viewModel: TipCalcViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentTipCalcBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_tip_calc, container, false)


        val tipCalcViewModel= ViewModelProviders.of(this).get(TipCalcViewModel::class.java)
        viewModel = tipCalcViewModel
        binding.tipCalcViewModel = viewModel
        binding.setLifecycleOwner(this)
        viewModel.createBillForOnePerson(0.0)


       binding.totalRestaurantBillEt.doAfterTextChanged1 {
            if (it?.isNotEmpty()!! && it.toString() != ".")  {

                val value = it.toString().toDouble()
                viewModel.createBillForOnePerson(value)


                binding.addTipIw.setOnClickListener {
                    viewModel.addTipPercent()
                    viewModel.createBillForOnePerson(value)
                }
                binding.removeTipIw.setOnClickListener {
                    viewModel.removeTipPercent()
                    viewModel.createBillForOnePerson(value)
                }
                binding.addClientsIw.setOnClickListener {
                    viewModel.addBuddies()
                    viewModel.createBillForOnePerson(value)
                }
                binding.removeClientsIw.setOnClickListener {
                    viewModel.removeBuddies()
                    viewModel.createBillForOnePerson(value)
                }
            }
        }
        return binding.root
    }

}
