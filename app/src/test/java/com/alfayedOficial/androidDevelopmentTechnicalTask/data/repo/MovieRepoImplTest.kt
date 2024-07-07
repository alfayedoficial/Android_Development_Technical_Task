@file:OptIn(ExperimentalCoroutinesApi::class)
@file:Suppress("DEPRECATION")

package com.alfayedOficial.androidDevelopmentTechnicalTask.data.repo

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.runner.AndroidJUnit4
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.alfayedOficial.androidDevelopmentTechnicalTask.data.fakeData.DataFake.fakeMovieEntity
import com.alfayedOficial.androidDevelopmentTechnicalTask.domain.model.Movie
import com.alfayedOficial.coreLocal.dao.MovieDao
import com.alfayedOficial.coreLocal.database.AppDatabase
import com.alfayedOficial.coreLocal.model.MovieEntity
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import androidx.paging.DifferCallback
import androidx.paging.NullPaddedList
import androidx.paging.PagingDataDiffer
import kotlinx.coroutines.ExperimentalCoroutinesApi


/**
 * Created by [Ali Al Fayed](https://www.linkedin.com/in/alfayedoficial)
 * About class :
 * Date Created: 06/07/2024 - 3:44 PM
 * Last Modified: 06/07/2024 - 3:44 PM
 */


@RunWith(AndroidJUnit4::class)
class MovieRepoImplTest {

    @MockK
    lateinit var pager: Pager<Int, MovieEntity>
    lateinit var movieDao: MovieDao
    lateinit var sut: MovieRepoImpl //system under test


    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        movieDao = appDatabase.movieDao()
        MockKAnnotations.init(this)
        sut = MovieRepoImpl(movieDao, pager)
    }

    @Test
    fun `given existing id, when call getMovieDetailsById then return Movie `() = runTest {
        // given
        movieDao.upsertAll(listOf(fakeMovieEntity))

        // when
        val actualMovie = sut.getMovieDetailsById(323)

        //then
        assertThat(actualMovie).isNotNull()
    }

    @Test
    fun `given not existing id, when call getMovieDetailsById then return null `() = runTest {
        //given
        val id = 300

        // when
        val actualMovie = sut.getMovieDetailsById(id)

        //then
        assertThat(actualMovie).isNull()
    }

    @Test
    fun `given list when call  getPopularMoviesPagingFlow then return flow paging data`() = runTest {
        //given
        val list = listOf(
            fakeMovieEntity,
            fakeMovieEntity.apply {
                primaryKey = 4385
                id = 324
            },
            fakeMovieEntity.apply {
                primaryKey = 4386
                id = 325
            },
            fakeMovieEntity.apply {
                primaryKey = 4387
                id = 326
            },
        )

        //when
        coEvery {
            pager.flow
        } returns flow { emit(PagingData.from(list)) }

        sut.getPopularMoviesPagingFlow().test {
            val pagingData: PagingData<Movie> = awaitItem()
            val movie = pagingData.collectDataForTest().firstOrNull()
            assertThat(movie).isNotNull()
            cancelAndIgnoreRemainingEvents()
        }
    }



    private suspend fun <T : Any> PagingData<T>.collectDataForTest(): List<T> {
        val dcb = object : DifferCallback {
            override fun onChanged(position: Int, count: Int) {}
            override fun onInserted(position: Int, count: Int) {}
            override fun onRemoved(position: Int, count: Int) {}
        }
        val items = mutableListOf<T>()
        val dif = object : PagingDataDiffer<T>(dcb, UnconfinedTestDispatcher()) {

            override suspend fun presentNewList(
                previousList: NullPaddedList<T>,
                newList: NullPaddedList<T>,
                lastAccessedIndex: Int,
                onListPresentable: () -> Unit
            ): Int? {
                for (idx in 0 until newList.size)
                    items.add(newList.getFromStorage(idx))
                onListPresentable()
                return null
            }
        }
        dif.collectFrom(this)
        return items
    }

    @After
    fun tearDown() = runTest {
        movieDao.clearAll()
    }
}