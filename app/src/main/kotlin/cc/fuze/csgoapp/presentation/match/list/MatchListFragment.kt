package cc.fuze.csgoapp.presentation.match.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cc.fuze.csgoapp.R
import cc.fuze.csgoapp.databinding.FragmentMatchListBinding
import cc.fuze.csgoapp.presentation.match.detail.MatchDetailFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MatchListFragment : Fragment() {

    private var binding: FragmentMatchListBinding? = null

    private val vm: MatchListViewModel by viewModel()

    private val adapter = MatchAdapter(emptyList(), this::onMatchClickListener)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentMatchListBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycler()
        setupObservers()
        setupListeners()
        vm.refresh()
    }

    private fun setupRecycler() {
        binding?.matchesRecycler?.adapter = adapter
    }

    private fun setupObservers() {
        vm.matchesLiveData.observe(viewLifecycleOwner) {
            adapter.matches = it
            adapter.notifyItemRangeChanged(0, it.size)
            binding?.loading?.visibility = View.GONE
            binding?.matchesSwipeRefreshLayout?.isRefreshing = false
        }
    }

    private fun setupListeners() {
        binding?.matchesSwipeRefreshLayout?.setOnRefreshListener {
            vm.refresh()
        }
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