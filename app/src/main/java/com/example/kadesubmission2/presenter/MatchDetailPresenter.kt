package com.example.kadesubmission2.presenter

import com.example.kadesubmission2.api.ApiRepository
import com.example.kadesubmission2.api.TheSportDBApi
import com.example.kadesubmission2.model.MatchDetailResponse
import com.example.kadesubmission2.model.TeamResponse
import com.example.kadesubmission2.view.MatchDetailView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchDetailPresenter(private val view: MatchDetailView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson){
    fun getMatchDetail(idEvent: String?){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getMatchDetail(idEvent)),
                MatchDetailResponse::class.java)

            uiThread {
                view.hideLoading()
                view.showMatchDetail(data.events[0])
            }
        }
    }

    fun getTeamBadge(idTeam: String?, isHomeTeam: Boolean = true) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeamDetail(idTeam)),
                TeamResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showTeamBadge(data.teams[0], isHomeTeam)
            }
        }
    }
}