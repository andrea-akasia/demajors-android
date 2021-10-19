package id.akasia.demajorsandroid.network

import id.akasia.demajorsandroid.model.api.pokemon.PokemonResponse
import id.akasia.demajorsandroid.model.api.detailpokemon.DetailPokemonResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {
    @GET("pokemon/{name}")
    fun requestDetailPokemon(
        @Path("name") name: String
    ): Single<Response<DetailPokemonResponse>>

    @GET("pokemon")
    fun requestListPokemon(
        @Query("limit") limit: Int,
        @Query("offset") page: Int
    ): Single<Response<PokemonResponse>>
}
