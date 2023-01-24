package com.eyosiyas.contributors.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eyosiyas.contributors.model.Contributor
import com.eyosiyas.contributors.repository.ContributorsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import com.eyosiyas.contributors.util.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContributorsViewModel @Inject constructor(private val repository: ContributorsRepository) :
    ViewModel() {
    private val _contributors = MutableLiveData<Result<List<Contributor>>>()
    val contributors: LiveData<Result<List<Contributor>>> get() = _contributors

    fun fetchContributors() {
        viewModelScope.launch {
            _contributors.value = repository.fetchContributors()
        }
    }
}