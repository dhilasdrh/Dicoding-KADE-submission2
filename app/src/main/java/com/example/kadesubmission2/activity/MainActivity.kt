package com.example.kadesubmission2.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kadesubmission2.R
import com.example.kadesubmission2.adapter.LeagueAdapter
import com.example.kadesubmission2.model.League
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {
    private var leagues: MutableList<League> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        recyclerView {
            lparams(width = matchParent, height = wrapContent)
            layoutManager = GridLayoutManager(context, 2)
            adapter =
                LeagueAdapter(leagues) {
                    startActivity<LeagueDetailActivity>("LEAGUE_ID" to it.id, "LEAGUE_NAME" to it.name)
                }
        }
    }

    private fun initData() {
        val image = resources.obtainTypedArray(R.array.league_img)
        val name = resources.getStringArray(R.array.league_name)
        val id = resources.getStringArray(R.array.league_id)

        leagues.clear()
        for (i in name.indices) {
            leagues.add(
                League(
                    id[i],
                    name[i],
                    image.getResourceId(i, 0)
                )
            )
        }
        image.recycle()
    }
}
