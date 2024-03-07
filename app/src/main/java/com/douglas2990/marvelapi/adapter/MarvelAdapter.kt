package com.douglas2990.marvelapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.douglas2990.marvelapi.R
import com.douglas2990.marvelapi.model.Item
import com.douglas2990.marvelapi.model.Result

class MarvelAdapter (private val list: List<Result>,
//class MarvelAdapter (private val list: List<Item>,
                     private var listener: MarvelInterface ? = null)
    :RecyclerView.Adapter<MarvelAdapter.MarvelViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarvelViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.marvel_adapter, parent, false)

        return MarvelViewHolder(view)
    }

    override fun onBindViewHolder(holder: MarvelViewHolder, position: Int) {
        val marvel = list[position]

        val nameMarvel = marvel.name

        val characterId = marvel.id.toString()
        //val characterId = marvel.name

        holder.textViewName.text = nameMarvel

        holder.textViewName.setOnClickListener {
            listener?.onclick(characterId)
        }
    }

    class MarvelViewHolder(view: View):RecyclerView.ViewHolder(view){
        var textViewName: TextView = view.findViewById(R.id.textViewName)

    }

    override fun getItemCount(): Int {
        return list.size
    }



    interface MarvelInterface{
        fun onclick(marvelId:String)
    }


}

