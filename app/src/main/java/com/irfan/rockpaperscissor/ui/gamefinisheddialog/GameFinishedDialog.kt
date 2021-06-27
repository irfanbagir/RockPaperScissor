package com.irfan.rockpaperscissor.ui.gamefinisheddialog

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.irfan.rockpaperscissor.databinding.FragmentGameFinishedDialogBinding
import com.irfan.rockpaperscissor.ui.menu.GameMenuActivity


class GameFinishedDialog(val listener : GameFinishedDialogLIstener?) : DialogFragment() {
    private lateinit var binding: FragmentGameFinishedDialogBinding
    private var gameResult: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        arguments?.let {
            gameResult = it.getString(GAME_RESULT)
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentGameFinishedDialogBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvGameResult.text = gameResult.orEmpty()
        binding.btnBackToMenu.setOnClickListener {
            startActivity(Intent(context, GameMenuActivity::class.java))
            dismiss()
        }
        binding.btnPlayAgain.setOnClickListener {
            listener?.onResetGameClicked()
            dismiss()

        }
    }

    companion object {
        private const val GAME_RESULT = "GAME_RESULT"

        @JvmStatic
        fun newInstance(result: String, listener: GameFinishedDialogLIstener?) =
            GameFinishedDialog(listener).apply {
                arguments = Bundle().apply {
                    putString(GAME_RESULT, result)

                }
            }


    }
}
