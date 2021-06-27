package com.irfan.rockpaperscissor.ui.menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.irfan.rockpaperscissor.data.preference.UserPreference
import com.irfan.rockpaperscissor.databinding.ActivityGameMenuBinding
import com.irfan.rockpaperscissor.ui.game.PlayerVsComActivity
import com.irfan.rockpaperscissor.ui.game.PlayerVsPlayerActivity

class GameMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameMenuBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val name = UserPreference(this).userName
        binding = ActivityGameMenuBinding.inflate(layoutInflater)
        Snackbar.make(findViewById(android.R.id.content),"Selamat datang $name!!" ,Snackbar.LENGTH_LONG).setAction("Tutup") {}.show()
        setContentView(binding.root)
        setPlayerName()
        setClickListeners()
    }

    private fun setPlayerName() {
        binding.tvPlayerVsPlayer.text =UserPreference(this).userName + " VS Pemain"
        binding.tvPlayerVsCom.text = UserPreference(this).userName + " VS Computer"

    }

    private fun setClickListeners() {
        binding.ivPlayerVsComputer.setOnClickListener {
            val intent = Intent(this, PlayerVsComActivity::class.java)
            startActivity(intent)
        }
        binding.ivPlayerVsPlayer.setOnClickListener {
            val intent = Intent(this, PlayerVsPlayerActivity::class.java)
            startActivity(intent)
        }
    }
}