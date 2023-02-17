package cc.fuze.csgoapp.presentation.match.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import cc.fuze.csgoapp.R
import cc.fuze.csgoapp.databinding.ItemMatchListBinding
import cc.fuze.csgoapp.domain.Match
import coil.load
import java.text.SimpleDateFormat
import java.util.*

class MatchAdapter(
    var matches: List<Match>, // TODO - Change View Type
    private val onMatchClick: (Match) -> Unit
) : RecyclerView.Adapter<MatchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemMatchListBinding.inflate(inflater, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val match = matches[position]
        holder.bind(match)
        holder.itemView.setOnClickListener {
            onMatchClick(match)
        }
    }

    override fun getItemCount(): Int {
        return matches.size
    }

    inner class ViewHolder(private val item: ItemMatchListBinding) : RecyclerView.ViewHolder(item.root) {

        @SuppressLint("SetTextI18n")
        fun bind(match: Match) {
            item.leagueNameTextView.text = "${match.league.name} / ${match.serie.name}"

            item.leagueIconImageView.load(match.league.image)

            val sdf = SimpleDateFormat("dd.MM HH:mm", Locale.getDefault())

            match.date?.let {
                item.whenTextView.text = sdf.format(it)
                item.whenTextView.background =
                    AppCompatResources.getDrawable(item.whenTextView.context, R.drawable.shape_match_item_hug_gray)
            }

            if (match.opponents?.size == 2) {
                val t1 = match.opponents[0]
                val t2 = match.opponents[1]

                item.t1Icon.load(t1.opponent.image) {
                    error(R.drawable.shape_circle_gray)
                    placeholder(R.drawable.shape_circle_gray)
                    fallback(R.drawable.shape_circle_gray)
                }
                item.t1NameTextView.text = t1.opponent.name

                item.t2Icon.load(t2.opponent.image) {
                    error(R.drawable.shape_circle_gray)
                    placeholder(R.drawable.shape_circle_gray)
                    fallback(R.drawable.shape_circle_gray)
                }
                item.t2NameTextView.text = t2.opponent.name
            } else {
                item.t2Icon.load(null)
                item.t2Icon.load(null)
            }
        }
    }
}