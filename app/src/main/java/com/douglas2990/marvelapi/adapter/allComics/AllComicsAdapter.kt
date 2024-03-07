package com.douglas2990.marvelapi.adapter.allComics

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.douglas2990.marvelapi.R
import com.douglas2990.marvelapi.model.allComics.Result

class AllComicsAdapter(private val list: List<Result>,
                       private var listener: AllComicsInterface ? = null)
    : RecyclerView.Adapter<AllComicsAdapter.AllComicsViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AllComicsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.all_comics_adapter, parent, false)

        return AllComicsViewHolder(view)
    }

    override fun onBindViewHolder(holder: AllComicsAdapter.AllComicsViewHolder, position: Int) {
        val comic = list[position]

        val nameComic = comic.title
        //val idCharacter = character.id
        val idComic = comic.id.toString()

        val path = comic.thumbnail.path.toString().replace("http","https")
        val extension = comic.thumbnail.extension.toString()

        holder.textViewName.text = nameComic
        holder.textViewName.setOnClickListener {
            listener?.onclick(idComic)
        }

        Glide.with(holder.imageViewComic)
            .load(path+"."+extension)
            .into(holder.imageViewComic)


    }

    class AllComicsViewHolder(view: View): RecyclerView.ViewHolder(view){
        var textViewName: TextView = view.findViewById(R.id.textViewName)
        var imageViewComic: ImageView = view.findViewById(R.id.draweeViewComic)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface AllComicsInterface{
        //fun onclick(characterId:Int)
        fun onclick(comicId:String)
    }

}