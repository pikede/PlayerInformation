package com.example.playerinformation.playerInformation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.playerinformation.R
import com.example.playerinformation.databinding.FragmentPlayerBinding
import com.example.playerinformation.models.Player
import com.example.playerinformation.playerHistory.PlayerHistoryFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayerFragment : Fragment(), PlayerSelected {
    private var _binding: FragmentPlayerBinding? = null
    private val binding: FragmentPlayerBinding get() = _binding!!
    private lateinit var playerAdapter: PlayerAdapter
    private val viewModel: PlayerViewModel by viewModel()

    companion object {
        const val PLAYER_NAME = "PLAYER_NAME"
        fun newInstance(bundle: Bundle) = PlayerFragment().apply { arguments = bundle }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentPlayerBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString(PLAYER_NAME)?.let {
            viewModel.getPlayers(it)
        }
        setupObservers()
        setupUI()
    }

    private fun setupObservers() {
        with(viewModel) {
            players.observe(viewLifecycleOwner) { updatePlayers(it) }
            errorMessage.observe(viewLifecycleOwner) { showError(it) }
        }
    }

    private fun setupUI() {
        binding.players.run {
            layoutManager = LinearLayoutManager(requireContext())
            playerAdapter = PlayerAdapter(this@PlayerFragment)
            adapter = playerAdapter
        }
    }

    private fun updatePlayers(it: List<Player>) {
        binding.loadingProgress.isVisible = true
        playerAdapter.updatePlayers(it)
        binding.loadingProgress.isVisible = false
    }

    private fun showError(errorMessage: String) {
        Toast.makeText(
            requireContext(),
            errorMessage,
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun getPlayerPreviousTeams(player: Player) {
        showPlayerHistoryFragment(player.playerId)
    }

    private fun showPlayerHistoryFragment(playerId: String) {
        activity?.supportFragmentManager?.beginTransaction()?.replace(
            R.id.container,
            PlayerHistoryFragment.newInstance(bundleOf(PlayerHistoryFragment.Player_ID to playerId))
        )?.addToBackStack(null)?.commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}