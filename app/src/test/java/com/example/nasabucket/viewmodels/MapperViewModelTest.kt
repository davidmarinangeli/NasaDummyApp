package com.example.nasabucket.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nasabucket.utils.PowerConnectionReceiver
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.subjects.PublishSubject
import org.junit.Rule
import org.junit.Test


class MapperViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    val powerConnectionReceiver = mockk<PowerConnectionReceiver>()
    val mapper = MapperViewModel(powerConnectionReceiver)


    @Test
    fun test1(){
        val subj = PublishSubject.create<Int>()
        every { powerConnectionReceiver.observable } returns subj

        mapper.init()
        subj.onNext(1)

        assert(mapper.livedata.value == "ciao 1")
        verify(exactly = 0) { powerConnectionReceiver.onReceive(any(), any()) }
        verify(exactly = 0) { powerConnectionReceiver.getObservable(any()) }
        verify(exactly = 1) { powerConnectionReceiver.observable }
    }
}