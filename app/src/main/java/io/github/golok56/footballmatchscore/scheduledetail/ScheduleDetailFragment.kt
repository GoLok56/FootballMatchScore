package io.github.golok56.footballmatchscore.scheduledetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.github.golok56.footballmatchscore.R
import io.github.golok56.footballmatchscore.model.Schedule
import kotlinx.android.synthetic.main.fragment_schedule_detail.view.*

class ScheduleDetailFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_schedule_detail, container, false)
        val side =  arguments?.getString(SIDE)
        val schedule: Schedule? =  arguments?.getParcelable(SCHEDULE)

        if (side == HOME) {
            view.tvScheduleDetailGk.text = schedule?.homeGk
            view.tvScheduleDetailDf.text = schedule?.homeDf
            view.tvScheduleDetailMf.text = schedule?.homeMf
            view.tvScheduleDetailFw.text = schedule?.homeSub
            view.tvScheduleDetailSub.text = schedule?.homeFw
        } else if (side == AWAY) {
            view.tvScheduleDetailGk.text = schedule?.awayGk
            view.tvScheduleDetailDf.text = schedule?.awayDf
            view.tvScheduleDetailMf.text = schedule?.awayMf
            view.tvScheduleDetailFw.text = schedule?.awaySub
            view.tvScheduleDetailSub.text = schedule?.awayFw
        }

        return view
    }

    companion object {
        private const val SCHEDULE = "schedule"
        private const val SIDE = "side"

        const val HOME = "home"
        const val AWAY = "away"

        fun newInstance(schedule: Schedule, side: String): ScheduleDetailFragment {
            val fragment = ScheduleDetailFragment()
            val bundle = Bundle()
            bundle.putParcelable(SCHEDULE, schedule)
            bundle.putString(SIDE, side)
            fragment.arguments = bundle
            return fragment
        }
    }
}