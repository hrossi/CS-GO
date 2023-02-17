package cc.fuze.csgoapp.presentation.match.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import cc.fuze.csgoapp.R
import cc.fuze.csgoapp.databinding.FragmentMatchListBinding
import cc.fuze.csgoapp.domain.Match
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
        vm.matchListStateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is MatchListState.Loading -> {
                    binding?.loading?.visibility = View.VISIBLE
                }

                is MatchListState.Success -> {
                    hideLoading()
                    adapter.matches = it.matches
                    adapter.notifyItemRangeChanged(0, it.matches.size)
                }

                is MatchListState.Error -> {
                    hideLoading()
                    showError()
                }
            }
        }
    }

    private fun setupListeners() {
        binding?.matchesSwipeRefreshLayout?.setOnRefreshListener {
            vm.refresh()
        }
    }

    private fun onMatchClickListener(match: Match) {
        parentFragmentManager.beginTransaction().also {
            it.replace(R.id.container, MatchDetailFragment.newInstance(match))
            it.addToBackStack("MatchDetailFragment")
            it.commit()
        }
    }

    private fun showError() {
        AlertDialog.Builder(context)
            .setTitle(R.string.ooppss)
            .setMessage(R.string.error_getting_match_list_message)
            .setPositiveButton(R.string.yes) { _, _ ->
                vm.refresh()
            }
            .setNegativeButton(R.string.no) { _, _ ->
                Toast.makeText(requireContext(), R.string.try_again_later, Toast.LENGTH_LONG)
                    .show()
                activity?.finish()
            }
            .show()
    }

    private fun hideLoading() {
        binding?.loading?.visibility = View.GONE
        binding?.matchesSwipeRefreshLayout?.isRefreshing = false
    }

    companion object {
        fun newInstance(): MatchListFragment {
            return MatchListFragment()
        }
    }
}