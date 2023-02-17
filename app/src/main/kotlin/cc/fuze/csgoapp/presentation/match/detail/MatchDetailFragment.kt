package cc.fuze.csgoapp.presentation.match.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import cc.fuze.csgoapp.R
import cc.fuze.csgoapp.databinding.FragmentMatchDetailBinding
import cc.fuze.csgoapp.domain.Match
import cc.fuze.csgoapp.domain.Player
import coil.load
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class MatchDetailFragment : Fragment() {

    private var binding: FragmentMatchDetailBinding? = null

    private val vm: MatchDetailViewModel by viewModel()

    private val adapter = PlayerAdapter(emptyList())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentMatchDetailBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()

        val match = arguments?.getParcelable("MATCH") as? Match ?: throw Error()

        setupRecycler()

        observeData()

        vm.init(match)
    }

    private fun setupToolbar() {
    }

    private fun setupRecycler() {
        binding?.playersRecycler?.adapter = adapter
    }

    private fun observeData() {
        vm.matchDetailsLiveData.observe(viewLifecycleOwner) {

            when (it) {
                is MatchDetailState.Loading -> {
                    binding?.loading?.visibility = View.VISIBLE
                }
                is MatchDetailState.Success -> {
                    hideLoading()
                    setupMatch(it.match)
                    setupPlayers(it.players)
                }
                is MatchDetailState.Error -> {
                    hideLoading()
                    Toast.makeText(requireContext(), "Ooops, something went wrong getting match details", Toast.LENGTH_LONG).show()
                    activity?.onBackPressed()
                }
            }
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    private fun hideLoading() {
        binding?.loading?.visibility = View.GONE
    }

    @SuppressLint("SetTextI18n")
    private fun setupMatch(match: Match) {
        binding?.leagueNameTextView?.text = "${match.league.name} / ${match.serie.name}"
        binding?.whenTextView?.text = match.date?.formatDateByName()

        match.opponents?.let {
            val t1 = match.opponents[0].opponent
            val t2 = match.opponents[1].opponent

            binding?.t1Icon?.load(t1.image) {
                error(R.drawable.shape_circle_gray)
                placeholder(R.drawable.shape_circle_gray)
                fallback(R.drawable.shape_circle_gray)
            }
            binding?.t1NameTextView?.text = t1.name

            binding?.t2Icon?.load(t2.image) {
                error(R.drawable.shape_circle_gray)
                placeholder(R.drawable.shape_circle_gray)
                fallback(R.drawable.shape_circle_gray)
            }
            binding?.t2NameTextView?.text = t2.name
        }

        binding?.versusTextView?.visibility = View.VISIBLE
    }

    private fun setupPlayers(players: List<Pair<Player, Player>>) {
        adapter.players = players
        adapter.notifyItemRangeChanged(0, players.size)
    }

    companion object {
        fun newInstance(match: Match): MatchDetailFragment {
            val args = Bundle().apply {
                putParcelable("MATCH", match)
            }
            return MatchDetailFragment().apply {
                arguments = args
            }
        }
    }
}

private fun Date.formatDateByName(): String {
    val date = Calendar.getInstance().apply {
        time = this@formatDateByName
    }

    val today = Calendar.getInstance()

    val tomorrow = Calendar.getInstance().apply {
        add(Calendar.DATE, 1)
    }

    return when {
        today.get(Calendar.DAY_OF_YEAR) == date.get(Calendar.DAY_OF_YEAR) -> "Hoje, ${format("hh:mm")}"
        tomorrow.get(Calendar.DAY_OF_YEAR) == date.get(Calendar.DAY_OF_YEAR) -> "Amanhã, ${format("hh:mm")}"
        else -> format("EEEE, dd/MM hh:mm")
    }
}

private fun Date.format(pattern: String): String {
    return SimpleDateFormat(pattern, Locale.getDefault()).format(this)
}