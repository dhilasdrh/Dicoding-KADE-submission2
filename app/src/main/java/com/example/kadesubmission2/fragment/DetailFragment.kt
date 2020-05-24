package com.example.kadesubmission2.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.kadesubmission2.R
import com.example.kadesubmission2.api.ApiRepository
import com.example.kadesubmission2.model.LeagueDetail
import com.example.kadesubmission2.presenter.LeagueDetailPresenter
import com.example.kadesubmission2.invisible
import com.example.kadesubmission2.visible
import com.example.kadesubmission2.view.LeagueDetailView
import com.google.gson.Gson
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment(),LeagueDetailView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val idLeague = activity?.intent?.getStringExtra("LEAGUE_ID")
        val leagueName = activity?.intent?.getStringExtra("LEAGUE_NAME")

        if (idLeague != null) {
            val presenter = LeagueDetailPresenter(this, ApiRepository(), Gson())
            presenter.getLeagueDetail(idLeague)
        }

        val actionBar: ActionBar? = (activity as AppCompatActivity?)!!.supportActionBar
        actionBar?.title = leagueName
    }

    override fun showLoading() {
        progressBarDetail.visible()
    }

    override fun hideLoading() {
        progressBarDetail.invisible()
        imgEmpty.invisible()
    }

    override fun showLeagueDetail(data: LeagueDetail) {
        Picasso.get().load(data.leagueFanart).into(league_banner)
        Picasso.get().load(data.leaguePoster).into(league_badge)
        league_name.text = data.leagueName
        league_formedYear.text = data.formedYear
        league_country.text = data.country
        league_description.text = data.description
    }
}