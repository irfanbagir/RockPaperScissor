package com.irfan.rockpaperscissor.ui.game

import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.irfan.rockpaperscissor.*
import com.irfan.rockpaperscissor.databinding.PlayerVsComBinding
import com.irfan.rockpaperscissor.enum.PlayerPick
import com.irfan.rockpaperscissor.enum.Players
import com.irfan.rockpaperscissor.ui.gamefinisheddialog.GameFinishedDialog
import com.irfan.rockpaperscissor.ui.gamefinisheddialog.GameFinishedDialogLIstener
import kotlin.random.Random


class PlayerVsComActivity : AppCompatActivity(), GameFinishedDialogLIstener {
    private val TAG = PlayerVsComActivity::class.java.simpleName

    private lateinit var binding: PlayerVsComBinding
    private var userPick: Int = 0
    private var comPick: Int = 0
    private var isGameFinished: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = PlayerVsComBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setClickListener()
        resetGame()

    }

    private fun setClickListener() {

        binding.flPlayerRock.setOnClickListener {
            if (!isGameFinished) {
                userPick = 0
                setPlayerPick(players = Players.PLAYER1, userPick)
                Log.d(TAG, "User Pick:$userPick ")
                checkWinner()
            }
        }
        binding.flPlayerPaper.setOnClickListener {
            if (!isGameFinished) {
                userPick = 1
                setPlayerPick(players = Players.PLAYER1, userPick)
                Log.d(TAG, "User Pick:$userPick ")
                checkWinner()
            }
        }
        binding.flPlayerScissor.setOnClickListener {
            if (!isGameFinished) {
                userPick = 2
                setPlayerPick(players = Players.PLAYER1, userPick)
                Log.d(TAG, "User Pick: $userPick")
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


    }


    private fun checkWinner() {
        val text = binding.tvResult
        val result: String
        comPick = Random.nextInt(0, 3)
        Log.d(TAG, "Computer Pick: $comPick")
        setPlayerPick(players = Players.COMPUTER, comPick)
        when (comPick) {
            0 -> {

                Toast.makeText(applicationContext, "Com picks Rock", Toast.LENGTH_SHORT).show()
            }
            1 -> {
                Toast.makeText(applicationContext, "Com picks Paper", Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(applicationContext, "Com picks Scissor", Toast.LENGTH_SHORT).show()

            }
        }
        when {
            (userPick + 1) % 3 == comPick -> {
                text.text = getString(R.string.result_com_win)
                result = "Com Wins"
                GameFinishedDialog.newInstance(result, this).show(supportFragmentManager, null)


            }
            userPick == comPick -> {
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
    }

    private fun resetGame() {
        binding.tvResult.text = getString(R.string.result_start_text)
        binding.flPlayerPaper.setBackgroundResource(0)
        binding.flPlayerRock.setBackgroundResource(0)
        binding.flPlayerScissor.setBackgroundResource(0)
        binding.flComPaper.setBackgroundResource(0)
        binding.flComRock.setBackgroundResource(0)
        binding.flComScissor.setBackgroundResource(0)
        isGameFinished = false

    }

    override fun onResetGameClicked() {
        resetGame()
    }

}

