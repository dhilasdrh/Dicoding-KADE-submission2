package com.example.kadesubmission2.view

import com.example.kadesubmission2.model.LeagueDetail

interface LeagueDetailView {
    fun showLoading()
    fun hideLoading()
    fun showLeagueDetail(data: LeagueDetail)
}