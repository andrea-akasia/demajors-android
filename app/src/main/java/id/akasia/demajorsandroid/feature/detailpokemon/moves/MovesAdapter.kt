package id.akasia.demajorsandroid.feature.detailpokemon.moves

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.akasia.demajorsandroid.databinding.ViewPokemonMoveBinding

class MovesAdapter(val data: List<String>) : RecyclerView.Adapter<MovesAdapter.MoveHolder>() {
    class MoveHolder(val binding: ViewPokemonMoveBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoveHolder {
        return MoveHolder(
            ViewPokemonMoveBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MoveHolder, position: Int) {
        with(holder) {
            binding.name.text = data[position]
        }
    }

    override fun getItemCount(): Int = data.size
}
