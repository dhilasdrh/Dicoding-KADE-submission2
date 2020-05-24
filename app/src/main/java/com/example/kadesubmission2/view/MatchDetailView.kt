package com.example.kadesubmission2.view

import com.example.kadesubmission2.model.MatchDetail
import com.example.kadesubmission2.model.Team

interface MatchDetailView {
    fun showLoading()
    fun hideLoading()
    fun showMatchDetail(data: MatchDetail)
    fun showTeamBadge(team: Team, isHomeTeam: Boolean)
}