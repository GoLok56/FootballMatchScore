package io.github.golok56.footballmatchscore.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import io.github.golok56.footballmatchscore.R
import io.github.golok56.footballmatchscore.base.load
import io.github.golok56.footballmatchscore.league.LeagueActivity
import kotlinx.android.synthetic.main.fragment_about.*
import org.jetbrains.anko.find

class AboutFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_about, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as LeagueActivity).find<Toolbar>(R.id.tbLeague).visibility = View.GONE

        ivAboutBanner.load(R.drawable.banner)
        ivAboutIg.load(R.drawable.instagram)
        ivAboutLinked.load(R.drawable.linkedin)
        ivAboutGithub.load(R.drawable.github)
        ivAboutAvatar.load(R.drawable.avatar, true)

        ivAboutIg.setOnClickListener { openBrowser(getString(R.string.url_instagram)) }
        ivAboutLinked.setOnClickListener { openBrowser(getString(R.string.url_linked_in)) }
        ivAboutGithub.setOnClickListener { openBrowser(getString(R.string.url_github)) }
        tvAboutFindMore.setOnClickListener { openBrowser(getString(R.string.url_github_pages)) }
    }

    private fun openBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}