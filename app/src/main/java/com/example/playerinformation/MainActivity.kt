package com.example.playerinformation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import com.example.playerinformation.databinding.ActivityMainBinding
import com.example.playerinformation.playerHistory.PlayerHistoryFragment
import com.example.playerinformation.playerInformation.PlayerFragment

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private lateinit var binding: ActivityMainBinding
    private var previousSearchedPlayerName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val rootView = binding.root
        setContentView(rootView)
        setupUI()
    }

    private fun setupUI() {
        binding.playerSearchName.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(playerName: String?): Boolean {
        playerName?.let {
            if (previousSearchedPlayerName == it) {
                return@let
            }
            previousSearchedPlayerName = it
            replacePlayerFragment(previousSearchedPlayerName)
        }
        return false
    }

    private fun replacePlayerFragment(playerName: String) {
        supportFragmentManager.beginTransaction()
            .replace(
                binding.container.id,
                PlayerFragment.newInstance(bundleOf(PlayerFragment.PLAYER_NAME to playerName))
            ).commit()
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if (count == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
            return
        }
        val fragment = supportFragmentManager.findFragmentById(R.id.container)
        if (fragment is PlayerHistoryFragment) {
            supportFragmentManager.beginTransaction().remove(fragment).commit()
            return
        }
    }
}