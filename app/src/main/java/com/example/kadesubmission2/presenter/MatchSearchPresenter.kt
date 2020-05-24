package com.example.kadesubmission2.presenter

import com.example.kadesubmission2.api.ApiRepository
import com.example.kadesubmission2.api.TheSportDBApi
import com.example.kadesubmission2.model.MatchSearchResponse
import com.example.kadesubmission2.view.MatchSearchView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchSearchPresenter(private val view: MatchSearchView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson
){
    fun getMatchSearchResult(query: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getMatchSearch(query)),
                MatchSearchResponse::class.java
            )

            uiThread {
                view.hideLoading()
                if (data.event.isNullOrEmpty()){
                    view.showEmpty()
                } else {
                    view.showMatchSearchResult(data.event)
                }
            }
        }
    }
}