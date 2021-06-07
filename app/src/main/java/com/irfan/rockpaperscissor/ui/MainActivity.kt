package com.irfan.rockpaperscissor.ui

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.ImageView
import com.irfan.rockpaperscissor.R
import com.irfan.rockpaperscissor.databinding.ActivityMainBinding
import com.irfan.rockpaperscissor.enum.PlayerPick
import com.irfan.rockpaperscissor.enum.Players
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName

    private lateinit var binding: ActivityMainBinding
    private var userPick: Int = 0
    private var comPick: Int = 0
    private var isGameFinished: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setClickListener()
        resetGame()

    }

    private fun setClickListener() {
        binding.flPlayerRock.setOnClickListener {
            userPick = 0
            setPlayerPick(players = Players.HUMAN, userPick)
            Log.d(TAG, "User Pick:$userPick ")
            checkWinner()

        }
        binding.flPlayerPaper.setOnClickListener {
            userPick = 1
            setPlayerPick(players = Players.HUMAN, userPick)
            Log.d(TAG, "User Pick:$userPick ")
            checkWinner()
        }
        binding.flPlayerScissor.setOnClickListener {
            userPick = 2
            setPlayerPick(players = Players.HUMAN, userPick)
            Log.d(TAG, "User Pick: $userPick")
            checkWinner()

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

        if (players == Players.HUMAN) {
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
        var text = binding.tvResult
        comPick = Random.nextInt (0, 3)
        Log.d(TAG, "Computer Pick: $comPick")
        setPlayerPick(players = Players.COMPUTER,comPick)

        if ((userPick+1)%3 == comPick) {
            text.setText(getString(R.string.result_com_win))
        }else if (userPick == comPick){
            text.setText(getString(R.string.result_draw))
    }else{
            text.setText(getString(R.string.result_player_win))
    }
    }
    private fun resetGame(){
        binding.icRefresh.setOnClickListener{
            binding.tvResult.text = getString(R.string.result_start_text)
            binding.flPlayerPaper.setBackgroundResource(0)
            binding.flPlayerRock.setBackgroundResource(0)
            binding.flPlayerScissor.setBackgroundResource(0)
            binding.flComPaper.setBackgroundResource(0)
            binding.flComRock.setBackgroundResource(0)
            binding.flComScissor.setBackgroundResource(0)


        }
    }

    }

