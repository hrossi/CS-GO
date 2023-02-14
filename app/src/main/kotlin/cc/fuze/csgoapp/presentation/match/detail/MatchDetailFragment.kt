package cc.fuze.csgoapp.presentation.match.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cc.fuze.csgoapp.databinding.FragmentMatchDetailBinding

class MatchDetailFragment : Fragment() {

    private var binding: FragmentMatchDetailBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentMatchDetailBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    companion object {
        fun newInstance(): MatchDetailFragment {
            return MatchDetailFragment()
        }
    }
}