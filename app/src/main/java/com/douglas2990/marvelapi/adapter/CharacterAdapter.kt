package com.douglas2990.marvelapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.douglas2990.marvelapi.R
import com.douglas2990.marvelapi.model.modelDaniel.character.CharacterModel

class CharacterAdapter(private val list: List<CharacterModel>,
                       private var listener: CharacterInterface ? = null): RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.marvel_adapter, parent, false)

        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterAdapter.CharacterViewHolder, position: Int) {
        val character = list[position]

        val nameCharacter = character.name
        //val idCharacter = character.id
        val idCharacter = character.id.toString()

        holder.textViewName.text = nameCharacter
        holder.textViewName.setOnClickListener {
            listener?.onclick(idCharacter)
        }


    }

    class CharacterViewHolder(view: View):RecyclerView.ViewHolder(view){
        var textViewName: TextView = view.findViewById(R.id.textViewName)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface CharacterInterface{
        //fun onclick(characterId:Int)
        fun onclick(characterId:String)
    }

}