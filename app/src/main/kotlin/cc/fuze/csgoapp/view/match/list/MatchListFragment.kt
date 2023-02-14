package cc.fuze.csgoapp.view.match.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cc.fuze.csgoapp.R
import cc.fuze.csgoapp.databinding.FragmentMatchListBinding
import cc.fuze.csgoapp.view.match.detail.MatchDetailFragment

class MatchListFragment : Fragment() {

    private var binding: FragmentMatchListBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentMatchListBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycler()
    }

    private fun setupRecycler() {
        binding?.matchesRecycler?.adapter = MatchAdapter(listOf("aa", "bb"), this::onMatchClickListener)
    }

    private fun onMatchClickListener() {
        parentFragmentManager.beginTransaction().also {
            it.replace(R.id.container, MatchDetailFragment.newInstance())
            it.addToBackStack("MatchDetailFragment")
            it.commit()
        }
    }

    companion object {
        fun newInstance(): MatchListFragment {
            return MatchListFragment()
        }
    }
}