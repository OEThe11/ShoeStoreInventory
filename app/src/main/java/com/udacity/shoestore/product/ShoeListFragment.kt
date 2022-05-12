package com.udacity.shoestore.product

import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.ActivityViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import kotlinx.android.synthetic.main.fragment_shoe_list.*

class ShoeListFragment : Fragment() {

    private val viewModel: ActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentShoeListBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_list,
            container, false)

        //to have the menu items be optimal
        setHasOptionsMenu(true)

         //binding the layout to the viewModel
         binding.shoeViewModel= viewModel
         //making binding observe live data
         binding.lifecycleOwner = this


        //observing the viewModel and creating a container within the linear layout of the Scroll
        // View to store the info from the ShoeDetailFragment
        viewModel.shoeList.observe(viewLifecycleOwner, Observer {
            //initializing the data class to the Fragment
            val iterator = it.listIterator()
            for (item in iterator) {
                val name = TextView(this.context)
                name.text = item.name
                val size = TextView(this.context)
                size.text = item.size
                val company = TextView(this.context)
                company.text = item.company
                val description = TextView(this.context)
                description.text = item.description
                //Setting text color
                name.setTextColor(Color.parseColor("#ffffff"))
                size.setTextColor(Color.parseColor("#ffffff"))
                company.setTextColor(Color.parseColor("#ffffff"))
                description.setTextColor(Color.parseColor("#ffffff"))
                //Setting Text Size
                name.textSize= 26F
                size.textSize=20F
                company.textSize=15F
                description.textSize=15F
                //Setting up the Background (Container)
                val parentLayout = LinearLayout(this.context)
                val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
                )
                params.setMargins(5,0,5,25)
                parentLayout.orientation = LinearLayout.VERTICAL;

                parentLayout.setBackgroundColor(Color.parseColor("#bd0c09"))
                parentLayout.setPadding(10,10,10,10)
                //Adding the views to the Layout Container
                parentLayout.addView(name)
                parentLayout.addView(size)
                parentLayout.addView(company)
                parentLayout.addView(description)
                //Adding the container into the Linear Layout inside the Scroll View
                binding.shoeListContainer.addView(parentLayout,params)


            }
        })

        //initializing the button
//        binding.shoeDetailButton.setOnClickListener { v: View ->
//            v.findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailsFragment())
//        }

        //Using viewModel to Navigating from the ShoeList to the Shoe Details Fragment
        viewModel.eventNavigateToShoeDetail.observe(viewLifecycleOwner) { eventNavigate ->
            if (eventNavigate) {
                findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailsFragment())
                viewModel.onNavigateToDetailFragmentComplete()
            }
        }



        return binding.root
    }


    //Inflating the Menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //First, when creating the menu, make sure the menu id is the same as the destination
        // on the Navigation graph. This needs to happen for it to work.
        /** This is used in the menu options to link the menu to the destination in the Navigation tree**/
        //the or operation is used as a safeguard. if the Navigation doesn't work,
        // onOptionsItemSelected will be called instead
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}

//All Layouts are switched to data binding layouts so that it can be called in the fragments
//as well as the ability to use the navigation component