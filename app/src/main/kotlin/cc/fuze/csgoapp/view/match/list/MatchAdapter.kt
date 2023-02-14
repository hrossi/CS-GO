package cc.fuze.csgoapp.view.match.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cc.fuze.csgoapp.databinding.ItemMatchListBinding

class MatchAdapter(
    private val matches: List<String>, // TODO - Change View Type
    private val onMatchClick: () -> Unit
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
            onMatchClick()
        }
    }

    override fun getItemCount(): Int {
        return matches.size
    }

    inner class ViewHolder(val item: ItemMatchListBinding) : RecyclerView.ViewHolder(item.root) {

        fun bind(match: String) {
            item.descriptionTextView.text = match
        }
    }
}