package gsihome.reyst.nestednavigation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions

class DstFragment : Fragment(R.layout.destination_fragment) {

    private val name: String by lazy { "Fragment ${arguments?.getString("name").orEmpty()}" }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.name).text = name
        view.findViewById<Button>(R.id.btn).setOnClickListener {
            arguments
                ?.getInt("dst")
                ?.also { id ->

                    //                    requireActivity().findNavController(R.id.main_host)

                    val navController = parentFragment
                        ?.parentFragment
                        ?.findNavController()
                        ?: findNavController()

                    navController
                        .also { Log.wtf("INSPECT", "is main: ${it.graph.id == R.id.main_graph}") }
                        .navigate(
                            id,
                            bundleOf(
                                "name" to name,
                                "dst" to R.id.nestedNavFragment
                            ),
                            navOptions { launchSingleTop = true },
                        )
                }
        }
    }

}