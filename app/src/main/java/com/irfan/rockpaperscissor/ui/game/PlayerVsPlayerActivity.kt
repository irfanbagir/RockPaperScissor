package com.irfan.rockpaperscissor.ui.game


import android.os.Bundle

import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.irfan.rockpaperscissor.*
import com.irfan.rockpaperscissor.databinding.PlayerVsPlayerBinding
import com.irfan.rockpaperscissor.enum.PlayerPick
import com.irfan.rockpaperscissor.enum.Players
import com.irfan.rockpaperscissor.ui.gamefinisheddialog.GameFinishedDialog
import com.irfan.rockpaperscissor.ui.gamefinisheddialog.GameFinishedDialogLIstener


class PlayerVsPlayerActivity : AppCompatActivity(), GameFinishedDialogLIstener {


    private lateinit var binding: PlayerVsPlayerBinding
    private var userPick: Int = 0
    private var userTwoPick: Int = 0
    private var isGameFinished: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PlayerVsPlayerBinding.inflate(layoutInflater)

        setContentView(binding.root)
        supportActionBar?.hide()
        hidePlayerTwo()
        setClickListener()
        resetGame()

    }


    private fun setClickListener() {

        binding.flPlayerRock.setOnClickListener {
            if (!isGameFinished) {
                userPick = 0
                setPlayerPick(players = Players.PLAYER1, userPick)

            }

        }

        binding.flPlayerPaper.setOnClickListener {
            if (!isGameFinished) {
                userPick = 1
                setPlayerPick(players = Players.PLAYER1, userPick)

            }

        }
        binding.flPlayerScissor.setOnClickListener {
            if (!isGameFinished) {
                userPick = 2
                setPlayerPick(players = Players.PLAYER1, userPick)


            }
        }
        binding.flComRock.setOnClickListener {
            if (!isGameFinished) {
                userTwoPick = 0
                setPlayerPick(players = Players.PLAYER2, userTwoPick)
                checkWinner()
            }
        }
        binding.flComPaper.setOnClickListener {
            if (!isGameFinished) {
                userTwoPick = 1
                setPlayerPick(players = Players.PLAYER2, userTwoPick)
                checkWinner()
            }
        }
        binding.flComScissor.setOnClickListener {
            if (!isGameFinished) {
                userTwoPick = 2
                setPlayerPick(players = Players.PLAYER2, userTwoPick)
                checkWinner()
            }

        }
        binding.icRefresh.setOnClickListener {
            resetGame()
        }


    }

    private fun setPlayerPick(
        players: Players,
        pickPlayer: Int
    ) {
        val pick = PlayerPick.fromInt(pickPlayer)
        var flScissor: FrameLayout? = null
        var flPaper: FrameLayout? = null
        var flRock: FrameLayout? = null

        if (players == Players.PLAYER1) {
            flScissor = binding.flPlayerScissor
            flPaper = binding.flPlayerPaper
            flRock = binding.flPlayerRock
            hidePlayerOne()


        } else {
            flScissor = binding.flComScissor
            flPaper = binding.flComPaper
            flRock = binding.flComRock
        }
        when (pick) {
            PlayerPick.ROCK -> {
                flRock.setBackgroundResource(R.drawable.bg_action_button)
                flPaper.setBackgroundResource(0)
                flScissor.setBackgroundResource(0)


            }
            PlayerPick.PAPER -> {
                flPaper.setBackgroundResource(R.drawable.bg_action_button)
                flRock.setBackgroundResource(0)
                flScissor.setBackgroundResource(0)


            }
            PlayerPick.SCISSOR -> {
                flScissor.setBackgroundResource(R.drawable.bg_action_button)
                flPaper.setBackgroundResource(0)
                flRock.setBackgroundResource(0)


            }

        }


        showPlayerTwo()


    }

    private fun checkWinner() {
        val text = binding.tvResult
        val result: String
        showPlayerOne()
        if (userTwoPick == 0) {
            Toast.makeText(applicationContext, "Player 2 picks Rock", Toast.LENGTH_SHORT).show()
        } else if (userTwoPick == 1) {
            Toast.makeText(applicationContext, "Player 2 picks Paper", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(applicationContext, "Player 2 picks Scissor", Toast.LENGTH_SHORT).show()

        }

        when {
            (userPick + 1) % 3 == userTwoPick -> {
                text.text = getString(R.string.result_player_two_wins)
                result = "Player 2 Wins"
                GameFinishedDialog.newInstance(result, this).show(supportFragmentManager, null)

            }
            userPick == userTwoPick -> {
                text.text = getString(R.string.result_draw)
                result = "Draw"
                GameFinishedDialog.newInstance(result, this).show(supportFragmentManager, null)

            }
            else -> {
                text.text = getString(R.string.result_player_win)
                result = "Player 1 Wins"
                GameFinishedDialog.newInstance(result, this).show(supportFragmentManager, null)

            }
        }

        isGameFinished = true
    }


    private fun hidePlayerTwo() {
        val hidePlayerTwo = binding.llComRight
        hidePlayerTwo.visibility = View.INVISIBLE

    }

    private fun hidePlayerOne() {
        val hidePlayerOne = binding.llPlayerLeft
        hidePlayerOne.visibility = View.INVISIBLE
    }

    private fun showPlayerOne() {
        val hidePlayerOne = binding.llPlayerLeft
        hidePlayerOne.visibility = View.VISIBLE
    }

    private fun showPlayerTwo() {
        val hidePlayerTwo = binding.llComRight
        hidePlayerTwo.visibility = View.VISIBLE
    }

    private fun resetGame() {
        binding.tvResult.text = getString(R.string.result_start_text)
        binding.flPlayerPaper.setBackgroundResource(0)
        binding.flPlayerRock.setBackgroundResource(0)
        binding.flPlayerScissor.setBackgroundResource(0)
        binding.flComPaper.setBackgroundResource(0)
        binding.flComRock.setBackgroundResource(0)
        binding.flComScissor.setBackgroundResource(0)
        showPlayerOne()
        hidePlayerTwo()
        isGameFinished = false

    }

    override fun onResetGameClicked() {
        resetGame()
    }


}