package io.github.golok56.footballmatchscore.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import io.github.golok56.footballmatchscore.R
import kotlinx.android.synthetic.main.fagrment_schedule.*
import kotlinx.android.synthetic.main.fagrment_schedule.view.*
import org.jetbrains.anko.find

class ScheduleFragment : Fragment() {
    private lateinit var schedulePagerAdapter: SchedulePagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fagrment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.find<Toolbar>(R.id.tbLeague)?.visibility = View.VISIBLE

        schedulePagerAdapter = SchedulePagerAdapter(fragmentManager!!)
        view.vpSchedule.adapter = schedulePagerAdapter
        view.tlSchedule.setupWithViewPager(vpSchedule)
    }
}
