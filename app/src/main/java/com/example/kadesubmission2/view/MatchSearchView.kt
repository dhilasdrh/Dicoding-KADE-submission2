package com.example.kadesubmission2.view

import com.example.kadesubmission2.model.Match

interface MatchSearchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchSearchResult(data: List<Match>)
    fun showEmpty()
}