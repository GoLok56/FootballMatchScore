package io.github.golok56.footballmatchscore.team

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.golok56.footballmatchscore.R
import io.github.golok56.footballmatchscore.R.id.ivTeamLogo
import io.github.golok56.footballmatchscore.base.BaseViewHolder
import io.github.golok56.footballmatchscore.base.load
import io.github.golok56.footballmatchscore.model.Team
import io.github.golok56.footballmatchscore.teamdetail.TeamDetailActivity
import kotlinx.android.synthetic.main.item_team.view.*
import org.jetbrains.anko.startActivity

class TeamAdapter : RecyclerView.Adapter<BaseViewHolder<Team>>() {
    private val items = mutableListOf<Team>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: BaseViewHolder<Team>, position: Int) {
        holder.bindItem(items[position])
    }

    fun updateData(data: MutableList<Team>?) {
        items.clear()
        data?.let { items.addAll(data) }
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : BaseViewHolder<Team>(view) {
        override fun bindItem(item: Team) {
            itemView.tvTeamName.text = item.name
            itemView.setOnClickListener {
                it.context.startActivity<TeamDetailActivity>(
                    TeamDetailActivity.EXTRA_TEAM to item)
            }
            item.logo?.let { itemView.ivTeamLogo.load(it) }
        }
    }
}
