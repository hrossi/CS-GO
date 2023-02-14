package cc.fuze.csgoapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cc.fuze.csgoapp.R
import cc.fuze.csgoapp.view.match.detail.MatchDetailFragment
import cc.fuze.csgoapp.view.match.list.MatchListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().also {
            it.replace(R.id.container, MatchListFragment.newInstance())
            it.addToBackStack("MatchListFragment")
            it.commit()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStackImmediate()
        } else {
            finish()
        }
    }
}