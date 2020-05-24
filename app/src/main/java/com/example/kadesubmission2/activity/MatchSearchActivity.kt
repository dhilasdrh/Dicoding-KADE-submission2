package com.example.kadesubmission2.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kadesubmission2.R
import com.example.kadesubmission2.adapter.MatchAdapter
import com.example.kadesubmission2.api.ApiRepository
import com.example.kadesubmission2.invisible
import com.example.kadesubmission2.model.Match
import com.example.kadesubmission2.presenter.MatchSearchPresenter
import com.example.kadesubmission2.view.MatchSearchView
import com.example.kadesubmission2.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_match_search.*
import org.jetbrains.anko.startActivity

class MatchSearchActivity : AppCompatActivity(), MatchSearchView, MenuItem.OnActionExpandListener {
    private var match: MutableList<Match> = mutableListOf()
    private lateinit var presenter: MatchSearchPresenter
    private lateinit var adapter: MatchAdapter

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_search)

        presenter = MatchSearchPresenter(this,  ApiRepository(), Gson())
        adapter = MatchAdapter(match) {
            startActivity<MatchDetailActivity>("EXTRA_MATCH" to it)
        }
        rv_matchSearch.layoutManager = LinearLayoutManager(this)
        rv_matchSearch.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)

        val searchItem = menu?.findItem(R.id.searchView)
        val searchView = searchItem?.actionView as SearchView
        searchItem.expandActionView()

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.getMatchSearchResult(query)
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!!.length > 1){
                    presenter.getMatchSearchResult(newText)
                }
                return true
            }
        })

        searchItem.setOnActionExpandListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    override fun showLoading() {
        progressBar_search.visible()
    }

    override fun hideLoading() {
        progressBar_search.invisible()
    }

    override fun showMatchSearchResult(data: List<Match>) {
        img_empty.invisible()
        tv_empty.invisible()

        match.clear()
        data.forEach {
            if(it.sport.equals("Soccer")){
                match.add(it)
            }
        }
        adapter.notifyDataSetChanged()
    }

    override fun showEmpty() {
        img_empty.visible()
        tv_empty.visible()

        match.clear()
        adapter.notifyDataSetChanged()
    }

    override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
        return true
    }

    override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
        onBackPressed()
        return true
    }

}
