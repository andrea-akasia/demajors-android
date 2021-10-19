package id.akasia.demajorsandroid.feature.detailpokemon.moves

import id.akasia.demajorsandroid.base.BaseViewModel
import id.akasia.demajorsandroid.data.DataManager
import id.akasia.demajorsandroid.model.api.detailpokemon.MovesItem
import com.google.gson.Gson
import javax.inject.Inject

class MovesViewModel
@Inject constructor(private val dataManager: DataManager) : BaseViewModel() {

    fun generateMoves(data: String): MutableList<String> {
        val result: MutableList<String> = mutableListOf()
        val moves = Gson().fromJson(data, Array<MovesItem>::class.java).asList()
        for (m in moves) {
            result.add(m.move.name)
        }

        return result
    }
}
