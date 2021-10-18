package id.akasia.demajorsandroid.feature.listpokemon

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.paging.*
import id.akasia.demajorsandroid.base.BaseViewModel
import id.akasia.demajorsandroid.data.DataManager
import id.akasia.demajorsandroid.data.local.pokemon.LocalPokemon
import id.akasia.demajorsandroid.model.api.pokemon.Pokemon
import timber.log.Timber
import javax.inject.Inject

class ListPokemonViewModel
@Inject constructor(private val dataManager: DataManager) : BaseViewModel(){

    val pokemonData : LiveData<PagedList<LocalPokemon>> by lazy {
        val dataSourceFactory = dataManager.loadAllPokemonFromLocal()
        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .build()
        LivePagedListBuilder(dataSourceFactory, config).build()
    }

    @SuppressLint("CheckResult")
    fun loadAllPokemonFromNetwork(){
        dataManager.reqPokemon(0, 1118)
            .doOnSubscribe(this::addDisposable)
            .subscribe(
                {res ->
                    if(res.isSuccessful){
                        val response = res.body()
                        response?.results?.let {
                            saveToLocal(convertToLocal(it))
                        }
                    }else{
                        //not 200
                        Timber.i("response code is ${res.code()}")
                    }
                },
                {err ->
                    Timber.e(err)
                }
            )
    }

    private fun convertToLocal(data: List<Pokemon>): List<LocalPokemon> {
        val result: MutableList<LocalPokemon> = mutableListOf()
        for(pokemon in data){
            result.add(
                LocalPokemon(
                    pokemon.getId(),
                    pokemon.name,
                    pokemon.getImage()
                )
            )
        }

        return result
    }

    @SuppressLint("CheckResult")
    fun saveToLocal(data: List<LocalPokemon>) {
        val pokemonList = mutableListOf<LocalPokemon>()
        for (pokemon in data) {
            pokemonList.add(pokemon)
        }

        dataManager.saveAllPokemonToLocal(pokemonList)
            .doOnSubscribe(this::addDisposable)
            .subscribe {
                Timber.i("successfully saved new data to local database")
            }
    }
}