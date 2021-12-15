package com.mayco.desafiospaceflight.ui.dialog

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mayco.desafiospaceflight.network.response.NewsResponse
import com.mayco.desafiospaceflight.repository.NewsRepository
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.CALLS_REAL_METHODS
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
@ExperimentalCoroutinesApi

class DialogDetailsViewModelTest {

    private val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: DialogDetailsViewModel

    @Mock
    private lateinit var repository: NewsRepository

    @Mock
    private lateinit var listener: DialogListener

    @Mock
    private lateinit var newsObserver: Observer<List<NewsResponse>>


    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(dispatcher)
        viewModel = DialogDetailsViewModel(repository)
    }

    @Test
    fun get() = TestCoroutineDispatcher().runBlockingTest {
        val newsResponse : List<NewsResponse> = arrayListOf()

        Mockito.anyList<NewsResponse>()



        verify(newsResponse)

    }

}