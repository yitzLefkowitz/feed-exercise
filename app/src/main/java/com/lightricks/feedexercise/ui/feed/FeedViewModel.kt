package com.lightricks.feedexercise.ui.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lightricks.feedexercise.data.FeedItem
import com.lightricks.feedexercise.util.Event

/**
 * This view model manages the data for Feed.
 */
class FeedViewModel : ViewModel() {
    private val stateInternal: MutableLiveData<State> = MutableLiveData<State>(DEFAULT_STATE)
    private val networkErrorEvent = MutableLiveData<Event<String>>()

    fun getIsLoading(): LiveData<Boolean> {
        //todo: fix the implementation
        return MutableLiveData()
    }

    fun getIsEmpty(): LiveData<Boolean> {
        //todo: fix the implementation
        return MutableLiveData()
    }

    fun getFeedItems(): LiveData<List<FeedItem>> {
        //todo: fix the implementation
        return MutableLiveData()
    }

    fun getNetworkErrorEvent(): LiveData<Event<String>> = networkErrorEvent

    init {
        refresh()
    }

    fun refresh() {
        //todo: fix the implementation
    }

    private fun updateState(transform: State.() -> State) {
        stateInternal.value = transform(getState())
    }

    private fun getState(): State {
        return stateInternal.value!!
    }

    data class State(
        val feedItems: List<FeedItem>?,
        val isLoading: Boolean
    )

    companion object {
        private val DEFAULT_STATE = State(
            feedItems = null,
            isLoading = false
        )
    }
}
