package io.github.golok56.footballmatchscore.teamdetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.golok56.footballmatchscore.R
import io.github.golok56.footballmatchscore.base.BaseViewHolder
import io.github.golok56.footballmatchscore.base.load
import io.github.golok56.footballmatchscore.model.Player
import io.github.golok56.footballmatchscore.playerdetail.PlayerDetailActivity
import kotlinx.android.synthetic.main.item_player.view.*
import org.jetbrains.anko.startActivity

class TeamDetailPlayerAdapter : RecyclerView.Adapter<BaseViewHolder<Player>>() {
    private val items = mutableListOf<Player>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_player, parent, false)
    )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: BaseViewHolder<Player>, position: Int) {
        holder.bindItem(items[position])
    }

    fun updateData(players: MutableList<Player>?) {
        items.clear()
        players?.let { items.addAll(it) }
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : BaseViewHolder<Player>(view) {
        override fun bindItem(item: Player) {
            if (item.cutout == null) itemView.ivPlayerCutout.load(R.drawable.ic_person_black_24dp)
            else itemView.ivPlayerCutout.load(item.cutout)

            itemView.tvPlayerName.text = item.name
            itemView.tvPlayerPosition.text = item.position
            itemView.setOnClickListener {
                itemView.context.startActivity<PlayerDetailActivity>(PlayerDetailActivity.EXTRA_PLAYER to item)
            }
        }
    }
}