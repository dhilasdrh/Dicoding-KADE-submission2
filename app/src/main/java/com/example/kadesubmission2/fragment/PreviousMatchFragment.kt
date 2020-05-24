package com.example.kadesubmission2.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kadesubmission2.R
import com.example.kadesubmission2.activity.MatchDetailActivity
import com.example.kadesubmission2.adapter.MatchAdapter
import com.example.kadesubmission2.api.ApiRepository
import com.example.kadesubmission2.invisible
import com.example.kadesubmission2.model.Match
import com.example.kadesubmission2.presenter.MatchPresenter
import com.example.kadesubmission2.view.MatchView
import com.example.kadesubmission2.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_last_match.*
import org.jetbrains.anko.support.v4.startActivity

class PreviousMatchFragment(private val idLeague: String?) : Fragment(), MatchView {
    private var match: MutableList<Match> = mutableListOf()
    private lateinit var presenter: MatchPresenter
    private lateinit var adapter: MatchAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = MatchPresenter(this, ApiRepository(), Gson())

        val actionBar: ActionBar? = (activity as AppCompatActivity?)!!.supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        return inflater.inflate(R.layout.fragment_last_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.getPreviousMatch(idLeague)

        adapter = MatchAdapter(match) {
            startActivity<MatchDetailActivity>("EXTRA_MATCH" to it)
        }
        rvLastMatch.layoutManager = LinearLayoutManager(context)
        rvLastMatch.adapter = adapter
    }

    override fun showLoading() {
        progressBar_lastMatch.visible()
    }

    override fun hideLoading() {
        progressBar_lastMatch.invisible()
    }

    override fun showMatchList(data: List<Match>) {
        match.clear()
        match.addAll(data)
        adapter.notifyDataSetChanged()
    }
}
