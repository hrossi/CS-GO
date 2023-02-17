package cc.fuze.csgoapp.presentation.match.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cc.fuze.csgoapp.databinding.ItemPlayerBinding
import cc.fuze.csgoapp.domain.Player
import coil.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation

class PlayerAdapter(var players: List<Pair<Player, Player>>) : RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemPlayerBinding.inflate(inflater, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val obj = players[position]
        holder.bind(obj)
    }

    override fun getItemCount(): Int {
        return players.size
    }

    inner class ViewHolder(private val binding: ItemPlayerBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Pair<Player, Player>) {
            binding.p1SlugTextView.text = item.first.name
            binding.p1NameTextView.text = item.first.firstName.plus(" ").plus(item.first.lastName)
            binding.p1PictureImageView.load(item.first.image) {
                transformations(RoundedCornersTransformation(16f))
                scale(Scale.FILL)
            }

            binding.p2SlugTextView.text = item.second.name
            binding.p2NameTextView.text = item.second.firstName.plus(" ").plus(item.second.lastName)
            binding.p2PictureImageView.load(item.second.image) {
                transformations(RoundedCornersTransformation(16f))
                scale(Scale.FILL)
            }
        }
    }
}