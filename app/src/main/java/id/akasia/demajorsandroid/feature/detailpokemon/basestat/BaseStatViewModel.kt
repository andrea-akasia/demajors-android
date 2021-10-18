package id.akasia.demajorsandroid.feature.detailpokemon.basestat

import id.akasia.demajorsandroid.base.BaseViewModel
import id.akasia.demajorsandroid.data.DataManager
import id.akasia.demajorsandroid.model.api.detailpokemon.DetailPokemonResponse
import javax.inject.Inject

class BaseStatViewModel
@Inject constructor(private val dataManager: DataManager) : BaseViewModel(){

    fun generateBaseStat(data: DetailPokemonResponse) = data.stats

}