package com.udacity.shoestore.product

import android.os.Bundle
import android.provider.SyncStateContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.ActivityViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailsBinding
import kotlinx.android.synthetic.main.fragment_shoe_details.*


class ShoeDetailsFragment : Fragment() {

    private val viewModel: ActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val binding: FragmentShoeDetailsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_details,
            container, false
        )
        //binding the layout to the viewModel
        binding.shoeViewModel = viewModel
        //making binding observe live data
        binding.lifecycleOwner = this


        //Using the viewModel to Navigate back to the ShoeList
        viewModel.eventNavigateBackToListing.observe(viewLifecycleOwner) {eventNavigateBack ->
            if (eventNavigateBack) {
                findNavController().navigateUp()
            }

        }


        //initializing the button and clearing the views once canceled
        binding.cancelButton.setOnClickListener { v: View ->
            v.findNavController().navigateUp()

            binding.shoeName.text.clear()
            binding.shoeSize.text.clear()
            binding.companyName.text.clear()
            binding.shoeDescription.text.clear()

        }
        //initializing the button and saving the info to transfer to the shoeList
//        binding.saveeButton.setOnClickListener { v: View ->
//            v.findNavController().navigateUp()
//

//            val name = shoe_name.text.toString()
//            val size = shoe_size.text.toString()
//            val brand = company_name.text.toString()
//            val details = shoe_description.text.toString()
//            viewModel.addShoe(name, size, brand, details)
//        }

        return binding.root
    }
}