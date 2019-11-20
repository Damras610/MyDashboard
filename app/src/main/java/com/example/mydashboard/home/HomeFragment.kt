package com.example.mydashboard.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.mydashboard.R
import com.example.mydashboard.authentication.model.logindata.AuthenticationState
import com.example.mydashboard.home.model.Widget
import com.example.mydashboard.widget.WidgetFragmentFactory
import com.example.mydashboard.widget.model.storage.WidgetStorageState
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import javax.inject.Inject


class HomeFragment : Fragment() {

    // ViewModel
    @Inject
    lateinit var viewModel : HomeViewModel

    // Views
    private lateinit var noWidgetTextView: TextView
    private lateinit var addWidgetFab: FloatingActionButton
    private lateinit var widgetsList: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        // Init the views
        noWidgetTextView = view.home_no_widget_tv
        addWidgetFab = view.home_add_widget_fab
        widgetsList = view.home_widgets_container

        // Observe models
        viewModel.loginUserData.authState.observe(this, Observer {authState ->
            if(authState != AuthenticationState.AUTHENTICATED) {
                // Navigate to the splash fragment if the user is not connected
                navController.navigate(R.id.action_homeFragment_to_splashFragment)
            }
        })
        viewModel.widgets.observe(this, Observer { widgets ->
            if (widgets.isEmpty()) {
                noWidgetTextView.visibility = View.VISIBLE
                widgetsList.visibility = View.GONE

            } else {
                noWidgetTextView.visibility = View.GONE
                widgetsList.visibility = View.VISIBLE
                addWidgetToView(widgets)
            }
        })
        viewModel.widgetToStoreData.storageState.observe(this, Observer {storageState ->
            if (storageState == WidgetStorageState.TO_STORE) {
                viewModel.storeWidget()
                viewModel.loadWidgets()
                viewModel.widgetToStoreData.storageState.value = WidgetStorageState.IDLE
            }
        })

        // Init onClick listener
        addWidgetFab.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_addServiceFragment)
        }

        viewModel.loadWidgets()

        // Manage back button
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            requireActivity().finish()
        }
    }

    override fun onResume() {
        super.onResume()
        requireActivity().toolbar.visibility = View.VISIBLE
        requireActivity().toolbar.setTitle(R.string.app_name)
        requireActivity().toolbar.subtitle = null
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    fun addWidgetToView(widget: Array<Widget>) {

        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        // Remove old fragments
        fragmentManager.popBackStack("widgetsBackStack", FragmentManager.POP_BACK_STACK_INCLUSIVE)

        // Add new fragments
        fragmentTransaction.addToBackStack("widgetsBackStack")
        val widgetFragmentFactory = WidgetFragmentFactory(requireContext())
        widget.forEach { widget ->
            val fragment = widgetFragmentFactory.create(widget)
            fragmentTransaction.add(R.id.home_widgets_container, fragment)
        }
        fragmentTransaction.commit()
    }
}
