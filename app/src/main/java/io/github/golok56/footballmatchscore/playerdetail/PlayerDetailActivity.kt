package io.github.golok56.footballmatchscore.playerdetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.golok56.footballmatchscore.R
import io.github.golok56.footballmatchscore.base.load
import io.github.golok56.footballmatchscore.model.Player
import kotlinx.android.synthetic.main.activity_player_detail.*

class PlayerDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        val player = intent.getParcelableExtra<Player>(EXTRA_PLAYER)

        if (player.thumbnail == null) ivPlayerDetailBanner.load(R.color.colorPrimaryDark)
        else ivPlayerDetailBanner.load(player.thumbnail)

        if (player.cutout == null) ivPlayerDetailAvatar.load(R.drawable.ic_person_black_24dp)
        else ivPlayerDetailAvatar.load(player.cutout)

        tvPlayerDetailName.text = player.name
        tvPlayerDetailPos.text = player.position
        tvPlayerDetailHeight.text = player.height?.replace("kg", "")?: "-"
        tvPlayerDetailWeight.text = player.weight?.replace("m", "")?: "-"
        tvPlayerDetailDescription.text = player.description
    }

    companion object {
        const val EXTRA_PLAYER = "intent-exra:player"
    }
}