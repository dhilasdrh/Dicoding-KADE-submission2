package com.example.kadesubmission2.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kadesubmission2.*
import com.example.kadesubmission2.api.ApiRepository
import com.example.kadesubmission2.model.Match
import com.example.kadesubmission2.model.MatchDetail
import com.example.kadesubmission2.model.Team
import com.example.kadesubmission2.presenter.MatchDetailPresenter
import com.example.kadesubmission2.view.MatchDetailView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_match_detail.*

class MatchDetailActivity : AppCompatActivity(), MatchDetailView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        val match = intent?.getParcelableExtra<Match>("EXTRA_MATCH")
        val presenter = MatchDetailPresenter(this, ApiRepository(), Gson())

        presenter.getMatchDetail(match?.idEvent)
        presenter.getTeamBadge(match?.idHomeTeam, true)
        presenter.getTeamBadge(match?.idAwayTeam, false)

        supportActionBar?.title = "Match Detail"
    }

    override fun showLoading() {
        progressBar_matchDetail.visible()
    }

    override fun hideLoading() {
        progressBar_matchDetail.invisible()
    }

    override fun showMatchDetail(data: MatchDetail) {
        detail_matchDate.text = data.eventDate?.formatDate()
        detail_matchTime.text = data.eventTime?.formatTime()

        detail_homeTeam.text = data.homeTeam
        detail_homeScore.text = data.homeScore
        home_goals.text = data.homeGoalDetails?.parseDetail()
        home_redCards.text = data.homeRedCards?.parseDetail()
        home_yellowCards.text = data.homeYellowCards?.parseDetail()
        home_goalKeeper.text = data.homeGoalKeeper?.parseLineup()
        home_defenders.text = data.homeDefense?.parseLineup()
        home_midfielders.text = data.homeMidfield?.parseLineup()
        home_forwards.text = data.homeForward?.parseLineup()
        home_substitutes.text = data.homeSubstitutes?.parseLineup()

        detail_awayTeam.text = data.awayTeam
        detail_awayScore.text = data.awayScore
        away_goals.text = data.awayGoalDetails?.parseDetail()
        away_redCards.text = data.awayRedCards?.parseDetail()
        away_yellowCards.text = data.awayYellowCards?.parseDetail()
        away_goalKeeper.text = data.awayGoalKeeper?.parseLineup()
        away_defenders.text = data.awayDefense?.parseLineup()
        away_midfielders.text = data.awayMidfield?.parseLineup()
        away_forwards.text = data.awayForward?.parseLineup()
        away_substitutes.text = data.awaySubstitutes?.parseLineup()
    }

    override fun showTeamBadge(team: Team, isHomeTeam: Boolean) {
        Picasso.get().load(team.teamBadge).into(
            if(isHomeTeam) {
                detail_homeBadge
            } else detail_awayBadge
        )
    }
}
