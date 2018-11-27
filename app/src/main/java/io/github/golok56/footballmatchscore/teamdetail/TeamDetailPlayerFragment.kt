package io.github.golok56.footballmatchscore.teamdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import io.github.golok56.footballmatchscore.R
import io.github.golok56.footballmatchscore.base.BaseApplication
import io.github.golok56.footballmatchscore.model.Player
import kotlinx.android.synthetic.main.fragment_team_detail_player.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class TeamDetailPlayerFragment : Fragment() {
    @Inject
    lateinit var factory: TeamDetailViewModelFactory

    private lateinit var vm: TeamDetailViewModel
    private lateinit var adapter: TeamDetailPlayerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_team_detail_player, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val teamId = arguments?.getString(TEAM_ID)

        adapter = TeamDetailPlayerAdapter()
        rvTeamDetailPlayer.adapter = adapter

        activity?.let {
            (it.application as BaseApplication).createTeamDetailComponent().inject(this)
            vm = ViewModelProviders.of(it, factory).get(TeamDetailViewModel::class.java)
                .apply {
                    teamId?.let { findPlayers(teamId) }
                    viewState.observe(this@TeamDetailPlayerFragment, Observer { state ->
                        handleState(state)
                    })
                }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        (activity?.application as BaseApplication).releaseTeamDetailComponent()
    }

    private fun handleState(viewState: TeamDetailViewState?) {
        viewState?.data?.let {
            it[TeamDetailViewModel.PLAYER]?.let { players ->
                if (players is MutableList<*>) adapter.updateData(players as MutableList<Player>)
            }
        }

        viewState?.error?.let { activity?.toast(it) }
    }

    companion object {
        private const val TEAM_ID = "teamId"

        fun newInstance(teamId: String): TeamDetailPlayerFragment {
            val fragment = TeamDetailPlayerFragment()
            val bundle = Bundle()
            bundle.putString(TEAM_ID, teamId)
            fragment.arguments = bundle

            return fragment
        }
    }
}