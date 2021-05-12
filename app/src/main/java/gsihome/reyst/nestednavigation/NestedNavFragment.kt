package gsihome.reyst.nestednavigation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions

class NestedNavFragment: Fragment(R.layout.nested_nav_fragment) {

    private val navController: NavController by lazy {
        (childFragmentManager.findFragmentById(R.id.nested_host) as NavHostFragment).navController
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btn1).setOnClickListener {
            navController
                .also { Log.wtf("INSPECT", "is main: ${it.graph.id == R.id.main_graph}") }
                .navigate(
                    R.id.dstFragment2,
                    bundleOf(
                        "name" to "2",
                        "dst" to R.id.dstFragment,
                    ),
                    navOptions { launchSingleTop = true },
                )
        }

        view.findViewById<Button>(R.id.btn2).setOnClickListener {
            navController
                .also { Log.wtf("INSPECT", "is main: ${it.graph.id == R.id.main_graph}") }
                .navigate(
                    R.id.dstFragment2,
                    bundleOf(
                        "name" to "1",
                        "dst" to R.id.dstFragment,
                    ),
                    navOptions { launchSingleTop = true },
                )
        }

        navController.setGraph(
            R.navigation.nested_graph,
            bundleOf(
                "name" to "1",
                "dst" to R.id.dstFragment,
            ),
        )
    }


}