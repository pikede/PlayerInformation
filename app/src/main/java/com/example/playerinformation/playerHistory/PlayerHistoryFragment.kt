package com.example.playerinformation.playerHistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.playerinformation.databinding.FragmentPlayerHistoryBinding
import com.example.playerinformation.models.FormerTeam
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayerHistoryFragment : Fragment() {
    private val viewModel: PlayerHistoryViewModel by viewModel()
    private var _binding: FragmentPlayerHistoryBinding? = null
    private val binding: FragmentPlayerHistoryBinding get() = _binding!!
    private lateinit var playerHistoryAdapter: PlayerHistoryAdapter

    companion object {
        const val Player_ID = "Player_ID"
        fun newInstance(bundle: Bundle) =
            PlayerHistoryFragment().apply { arguments = bundle }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentPlayerHistoryBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupUI()
    }

    private fun setupObservers() {
        with(viewModel) {
            formerTeams.observe(viewLifecycleOwner) { showPreviousTeams(it) }
            errorMessage.observe(viewLifecycleOwner) { showError(it) }
            arguments?.getString(Player_ID)?.let {
                getPlayerFormerTeams(it)
            }
        }
    }

    private fun setupUI() {
        binding.playerPreviousTeams.run {
            layoutManager = LinearLayoutManager(requireContext())
            playerHistoryAdapter = PlayerHistoryAdapter()
            adapter = playerHistoryAdapter
        }
    }

    private fun showPreviousTeams(it: List<FormerTeam>) {
        playerHistoryAdapter.updateTeams(it)
    }

    private fun showError(errorMessage: String) {
        Toast.makeText(
            requireContext(),
            errorMessage,
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}