package dev.haenara.lorempicsum.event

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class UiEventListener(
    private val fragment: Fragment
) {
    private val context: Context by lazy { fragment.requireContext() }

    fun onEventReceived(event: UiEvent) {
        when (event) {
            is UiEvent.None -> doNothing()
            is UiEvent.Toast -> showToast(event)
            is UiEvent.Dialog -> shoDialog(event)
            is UiEvent.Navigation -> navigate(event)
        }
    }

    private fun doNothing() {
        // do nothing
    }

    private fun showToast(event: UiEvent.Toast) {
        Toast.makeText(context, context.toString(event.stringOrRes), Toast.LENGTH_SHORT).show()
    }

    private fun shoDialog(event: UiEvent.Dialog) {
        AlertDialog.Builder(context)
            .setMessage(context.toString(event.stringOrRes))
            .setPositiveButton(context.toString(event.button.text)) { _, _ ->
                event.button.onClick()
            }
            .create()
            .show()
    }

    private fun navigate(event: UiEvent.Navigation) {
        fragment.findNavController().navigate(event.destination)
    }
}
