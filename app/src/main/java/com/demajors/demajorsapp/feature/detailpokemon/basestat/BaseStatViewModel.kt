package com.demajors.demajorsapp.feature.detailpokemon.basestat

import com.demajors.demajorsapp.base.BaseViewModel
import com.demajors.demajorsapp.data.DataManager
import com.demajors.demajorsapp.model.api.detailpokemon.DetailPokemonResponse
import javax.inject.Inject

class BaseStatViewModel
@Inject constructor(private val dataManager: DataManager) : BaseViewModel() {

    fun generateBaseStat(data: DetailPokemonResponse) = data.stats
}
