package io.github.golok56.footballmatchscore.teamdetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.tabs.TabLayout
import io.github.golok56.footballmatchscore.R
import io.github.golok56.footballmatchscore.base.BaseApplication
import io.github.golok56.footballmatchscore.base.load
import io.github.golok56.footballmatchscore.model.Team
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class TeamDetailActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: TeamDetailViewModelFactory

    private lateinit var vm: TeamDetailViewModel
    private lateinit var team: Team

    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)
        (application as BaseApplication).createTeamDetailComponent().inject(this)

        team = intent.getParcelableExtra(EXTRA_TEAM)

        vm = ViewModelProviders.of(this, factory)
            .get(TeamDetailViewModel::class.java)
            .apply {
                team.id?.let {
                    isFavorite(it)
                }
                viewState.observe(this@TeamDetailActivity, Observer { handleState(it) })
            }
        initView()
    }

    override fun onDestroy() {
        super.onDestroy()
        (application as BaseApplication).releaseTeamDetailComponent()
    }

    private fun initView() {
        team.logo?.let { ivTeamDetailLogo.load(it) }
        tvTeamDetailName.text = team.name
        tvTeamDetailStadium.text = if (team.stadium == null) "-" else team.stadium
        tvTeamDetailYear.text = if (team.formedYear == null) "-" else getString(
            R.string.label_since,
            team.formedYear
        )

        tlTeamDetail.addOnTabSelectedListener(object :
            TabLayout.BaseOnTabSelectedListener<TabLayout.Tab> {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                var fragment = Fragment()
                if (tab?.position == 0) {
                    fragment = TeamDetailOverviewFragment.newInstance(team.description)
                } else if (tab?.position == 1) {
                    fragment = TeamDetailPlayerFragment.newInstance(team.id!!)
                }
                replaceFragment(fragment)
            }
        })

        replaceFragment(TeamDetailOverviewFragment.newInstance(team.description))

        ivTeamDetailAddFavs.setOnClickListener {
            if (isFavorite) vm.removeFavorite(team)
            else vm.addFavorite(team)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.flTeamDetail, fragment)
            .commit()
    }

    private fun handleState(viewState: TeamDetailViewState?) {
        handleData(viewState?.data)
        viewState?.error?.let { toast(it) }
    }

    private fun handleData(data: MutableMap<String, Any?>?) {
        data?.let {
            it[TeamDetailViewModel.FAVORITE]?.let { isFavorite ->
                if (isFavorite is Boolean) {
                    if (isFavorite) ivTeamDetailAddFavs.load(R.drawable.ic_star_red_24dp)
                    else ivTeamDetailAddFavs.load(R.drawable.ic_star_black_24dp)

                    this.isFavorite = isFavorite
                }
            }
        }
    }

    companion object {
        const val EXTRA_TEAM = "intent-extra:team"
    }
}
