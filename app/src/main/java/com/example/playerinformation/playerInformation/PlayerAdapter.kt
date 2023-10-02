package com.example.playerinformation.playerInformation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.playerinformation.databinding.PlayerDetailsBinding
import com.example.playerinformation.models.Player
import com.squareup.picasso.Picasso

class PlayerAdapter(private val listener: PlayerSelected) :
    RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {
    private val players = mutableListOf<Player>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PlayerDetailsBinding.inflate(layoutInflater, parent, false)
        return PlayerViewHolder(binding)
    }

    override fun getItemCount() = players.size

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bind(players[position], listener)
    }

    fun updatePlayers(newPlayers: List<Player>) {
        val diffUtil = PlayerDiffUtil(players, newPlayers)
        val result = DiffUtil.calculateDiff(diffUtil)
        players.clear()
        players.addAll(newPlayers)
        result.dispatchUpdatesTo(this)
    }

    inner class PlayerViewHolder(private val binding: PlayerDetailsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(player: Player, listener: PlayerSelected) {
            binding.playerName.text = player.playerName
            binding.teamName.text = player.teamName
            player.thumbNail?.let {
                Picasso.get().load(it).fit().into(binding.playerImage)
            }
            binding.root.setOnClickListener {
                listener.getPlayerPreviousTeams(player)
            }
        }
    }

    inner class PlayerDiffUtil(
        private val oldList: List<Player>,
        private val newList: List<Player>,
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].playerId == newList[newItemPosition].playerId
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].playerId == newList[newItemPosition].playerId
                    && oldList[oldItemPosition].thumbNail == newList[newItemPosition].thumbNail
                    && oldList[oldItemPosition].playerName == newList[newItemPosition].playerName
                    && oldList[oldItemPosition].sport == newList[newItemPosition].sport
                    && oldList[oldItemPosition].dateBorn == newList[newItemPosition].dateBorn
                    && oldList[oldItemPosition].playerNumber == newList[newItemPosition].playerNumber
        }
    }
}

interface PlayerSelected {
    fun getPlayerPreviousTeams(player: Player)
}