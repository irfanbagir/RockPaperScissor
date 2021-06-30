package com.irfan.rockpaperscissor.ui.intro

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro2
import com.github.appintro.AppIntroCustomLayoutFragment
import com.github.appintro.AppIntroFragment
import com.irfan.rockpaperscissor.R

class AppIntroActivity : AppIntro2() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        isSkipButtonEnabled = false

        addSlide(
            AppIntroCustomLayoutFragment.newInstance(R.layout.fragment_player_vs_player)

        )

        addSlide(
            AppIntroCustomLayoutFragment.newInstance(R.layout.fragment_player_vs_com)

        )


        addSlide(PlayerNameFormFragment())
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        // Decide what to do when the user clicks on "Skip"
        finish()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        if (currentFragment is PlayerNameFormFragment) {
            currentFragment.navigateToMenuGame()
        }

    }
}