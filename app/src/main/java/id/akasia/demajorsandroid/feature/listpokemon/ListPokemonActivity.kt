package id.akasia.demajorsandroid.feature.listpokemon

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import id.akasia.demajorsandroid.base.BaseActivity
import id.akasia.demajorsandroid.databinding.ActivityListPokemonBinding

class ListPokemonActivity : BaseActivity<ListPokemonViewModel>() {

    override val viewModelClass: Class<ListPokemonViewModel> get() = ListPokemonViewModel::class.java
    private lateinit var binding: ActivityListPokemonBinding

    private var adapter: ListPokemonAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ListPokemonAdapter()
        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = adapter

        viewModel.pokemonData.observe(
            this,
            {
                adapter?.submitList(it)
            }
        )

        viewModel.loadAllPokemonFromNetwork()
    }
}
