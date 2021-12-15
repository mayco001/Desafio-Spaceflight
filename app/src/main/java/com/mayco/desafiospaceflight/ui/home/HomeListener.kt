package com.mayco.desafiospaceflight.ui.home

interface HomeListener {
    fun apiSuccess()
    fun apiError(message: String)
}