package com.example.kadesubmission2.presenter

import com.example.kadesubmission2.api.ApiRepository
import com.example.kadesubmission2.api.TheSportDBApi
import com.example.kadesubmission2.model.LeagueDetailResponse
import com.example.kadesubmission2.view.LeagueDetailView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class LeagueDetailPresenter (private val view: LeagueDetailView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
    )
{
    fun getLeagueDetail(idLeague: String){
        view.showLoading()
        doAsync {
           val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getLeagueDetail(idLeague)),
                LeagueDetailResponse::class.java)

            uiThread {
                view.hideLoading()
                view.showLeagueDetail(data.leagues[0])
            }
        }
    }
}