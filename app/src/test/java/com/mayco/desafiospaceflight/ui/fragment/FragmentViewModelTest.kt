package com.spaceflight.ui.fragment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mayco.desafiospaceflight.network.response.NewsResponse
import com.mayco.desafiospaceflight.repository.NewsRepository
import com.mayco.desafiospaceflight.ui.fragment.FragmentListener
import com.mayco.desafiospaceflight.ui.fragment.FragmentViewModel
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
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner.Silent::class)
@ExperimentalCoroutinesApi
class NewsViewModelTest {
    private val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: FragmentViewModel

    @Mock
    private lateinit var repository: NewsRepository

    @Mock
    private lateinit var listener: FragmentListener

    @Mock
    private lateinit var newsObserver: Observer<List<NewsResponse>>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(dispatcher)
        viewModel = FragmentViewModel(repository)
    }

    @Test
    fun initViewModel() {
        val newsResponse: List<NewsResponse> = arrayListOf()
        viewModel.listener = listener
        viewModel.newsList.observeForever(newsObserver)
        viewModel.initViewModel()

        verify(newsObserver).onChanged(newsResponse)
        // verify(listener).onSearch()
    }

//    @Test
//    fun saveClick() {
//        viewModel.listener = listener
//        viewModel.saveClick(
//            NewsResponse(
//                id = 12,
//                title = "teste",
//                summary = "teste descricao",
//                newsSite = "",
//                imageUrl = "",
//                featured = false,
//                url = "",
//                events = arrayListOf(),
//                launches = arrayListOf()
//            )
//
//        )
//    }

    @Test
    fun getNewsPage() = TestCoroutineDispatcher().runBlockingTest {
        val newsResponse: List<NewsResponse> = arrayListOf()
        Mockito.`when`(repository.getNews(1, 1)).thenReturn(Response.success(newsResponse))

        viewModel.listener = listener
        viewModel.newsList.observeForever(newsObserver)
        viewModel.getNewsPage(16)

        verify(newsObserver).onChanged(newsResponse)

    }
}