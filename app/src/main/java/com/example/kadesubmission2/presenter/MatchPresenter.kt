package com.example.kadesubmission2.presenter

import com.example.kadesubmission2.api.ApiRepository
import com.example.kadesubmission2.api.TheSportDBApi
import com.example.kadesubmission2.model.MatchResponse
import com.example.kadesubmission2.view.MatchView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchPresenter(private val view: MatchView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson){
    fun getPreviousMatch(idLeague: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getPastMatch(idLeague)),
                MatchResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showMatchList(data.events)
            }
        }
    }

    fun getNextMatch(idLeague: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getNextMatch(idLeague)),
                MatchResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showMatchList(data.events)
            }
        }
    }
}