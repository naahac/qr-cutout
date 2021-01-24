package com.natanaelahac.canvas.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.natanaelahac.canvas.R

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_simple_qr).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        view.findViewById<Button>(R.id.button_simple_with_corners).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_cutoutWithCornersFragment)
        }
        view.findViewById<Button>(R.id.button_simple_with_rounded_corners).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_cutoutWithRoundedCornersFragment)
        }
    }
}