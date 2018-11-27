package io.github.golok56.footballmatchscore.teamdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.github.golok56.footballmatchscore.R
import kotlinx.android.synthetic.main.fragment_team_detail_overview.view.*

class TeamDetailOverviewFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_team_detail_overview, container, false)
        val overview = arguments?.getString(OVERVIEW)

        view.tvTeamDetailOverview.text = overview

        return view
    }

    companion object {
        private const val OVERVIEW = "overview"

        fun newInstance(overview: String?): TeamDetailOverviewFragment {
            val fragment = TeamDetailOverviewFragment()
            val bundle = Bundle()
            bundle.putString(OVERVIEW, overview)
            fragment.arguments = bundle

            return fragment
        }
    }
}