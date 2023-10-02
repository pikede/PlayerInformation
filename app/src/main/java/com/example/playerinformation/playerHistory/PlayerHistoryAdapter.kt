package com.example.playerinformation.playerHistory

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.playerinformation.databinding.TeamHistoryItemBinding
import com.example.playerinformation.models.FormerTeam
import com.squareup.picasso.Picasso

class PlayerHistoryAdapter :
    RecyclerView.Adapter<PlayerHistoryAdapter.TeamHistoryViewHolder>() {
    private val teams = mutableListOf<FormerTeam>()
    private val selectedTeams = mutableSetOf<FormerTeam>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamHistoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TeamHistoryItemBinding.inflate(layoutInflater, parent, false)
        return TeamHistoryViewHolder(binding)
    }

    override fun getItemCount() = teams.size

    override fun onBindViewHolder(holder: TeamHistoryViewHolder, position: Int) {
        holder.bind(teams[position])
    }

    override fun onViewRecycled(holder: TeamHistoryViewHolder) {
        super.onViewRecycled(holder)
        val team = teams[holder.layoutPosition]
        if (selectedTeams.contains(team)) {
            holder.setSelectedTeamBackground()
        }
    }

    fun updateTeams(newTeams: List<FormerTeam>) {
        val diffUtil = FormerTeamDiffUtil(teams, newTeams)
        val result = DiffUtil.calculateDiff(diffUtil)
        teams.clear()
        teams.addAll(newTeams)
        result.dispatchUpdatesTo(this)
    }

    inner class TeamHistoryViewHolder(private val binding: TeamHistoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(team: FormerTeam) {
            binding.clubName.text = team.strFormerTeam
            binding.years.text = "${team.strJoined} - ${team.strDeparted}"
            team.strTeamBadge.let {
                Picasso.get().load(it).fit().into(binding.teamBadge)
            }
            binding.root.setOnClickListener {
                handleSelectedTeamBackGround(team)
            }
        }

        private fun handleSelectedTeamBackGround(team: FormerTeam) {
            when {
                selectedTeams.contains(team) -> {
                    selectedTeams.remove(team)
                    removeSelectedTeamBackground()
                }

                else -> {
                    selectedTeams.add(team)
                    setSelectedTeamBackground()
                }
            }
        }

        fun setSelectedTeamBackground() {
            binding.root.setBackgroundColor(Color.DKGRAY)
        }

        private fun removeSelectedTeamBackground() {
            binding.root.setBackgroundColor(Color.WHITE)
        }
    }

    inner class FormerTeamDiffUtil(
        private val oldList: List<FormerTeam>,
        private val newList: List<FormerTeam>,
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].idFormerTeam == newList[newItemPosition].idFormerTeam
                    && oldList[oldItemPosition].strFormerTeam == newList[newItemPosition].strFormerTeam
                    && oldList[oldItemPosition].strTeamBadge == newList[newItemPosition].strTeamBadge
                    && oldList[oldItemPosition].strDeparted == newList[newItemPosition].strDeparted
                    && oldList[oldItemPosition].strJoined == newList[newItemPosition].strJoined
                    && oldList[oldItemPosition].strSport == newList[newItemPosition].strSport
        }
    }
}
