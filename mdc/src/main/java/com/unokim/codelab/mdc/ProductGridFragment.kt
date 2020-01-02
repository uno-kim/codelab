package com.unokim.codelab.mdc

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.*
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.unokim.codelab.logger.Logger
import com.unokim.codelab.mdc.network.ProductEntry
import kotlinx.android.synthetic.main.shr_product_grid_fragment.view.*

class ProductGridFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Logger.d(TAG, "onCreate")

        setHasOptionsMenu(true)
    }

    @SuppressLint("ObsoleteSdkInt")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        Logger.d(TAG, "onCreateView")

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.shr_product_grid_fragment, container, false)

        // Set up the toolbar.
        (activity as AppCompatActivity).setSupportActionBar(view.app_bar)
        view.app_bar.setNavigationOnClickListener(
            NavigationIconClickListener(
                requireActivity(),
                view.product_grid,
                AccelerateDecelerateInterpolator(),
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.shr_branded_menu
                ), // Menu open icon
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.shr_close_menu
                ) // Menu close icon
            )
        )

        // Set up the RecyclerView
        view.recycler_view.setHasFixedSize(true)
        view.recycler_view.layoutManager =
            GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
        val adapter = ProductCardRecyclerViewAdapter(
            ProductEntry.initProductEntryList(resources)
        )
        view.recycler_view.adapter = adapter
        val largePadding = resources.getDimensionPixelSize(R.dimen.shr_product_grid_spacing)
        val smallPadding = resources.getDimensionPixelSize(R.dimen.shr_product_grid_spacing_small)
        view.recycler_view.addItemDecoration(ProductGridItemDecoration(largePadding, smallPadding))

        // Set cut corner background for API 23+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.product_grid.background =
                context?.getDrawable(R.drawable.shr_product_grid_background_shape)
        }

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.shr_toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, menuInflater)
    }

    companion object {
        private const val TAG = "ProductGridFragment"
    }
}
