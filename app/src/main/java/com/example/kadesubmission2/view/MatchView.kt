package com.example.kadesubmission2.view

import com.example.kadesubmission2.model.Match

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<Match>)
}