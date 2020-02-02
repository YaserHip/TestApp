package com.example.phunapp.view.ui.mainfeed

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.phunapp.R
import com.example.phunapp.databinding.MainFeedFragmentBinding
import com.example.phunapp.view.adapters.mainfeed.FeedAdapter
import com.example.phunapp.view.adapters.mainfeed.FeedTabletDecoration

class MainFeedFragment : Fragment() {

    companion object {
        fun newInstance() = MainFeedFragment()
    }

    private lateinit var bindings: MainFeedFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindings = MainFeedFragmentBinding.inflate(inflater, container, false).apply {
            viewmodel =
                ViewModelProviders.of(this@MainFeedFragment).get(MainFeedViewModel::class.java)
            lifecycleOwner = viewLifecycleOwner
        }
        return bindings.root
    }

    private fun setupRecyclerView() {
        val isTablet = resources.getBoolean(R.bool.isTablet)
        if (isTablet) {
            bindings.rvFeed.apply {
                val decoration =
                    FeedTabletDecoration(activity!!.applicationContext, R.dimen.item_feed_margin)
                layoutManager = GridLayoutManager(context, 2)
                addItemDecoration(decoration)
            }
        } else {
            bindings.rvFeed.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
            }
        }
        bindings.rvFeed.adapter = FeedAdapter(bindings.viewmodel!!)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
    }

}
