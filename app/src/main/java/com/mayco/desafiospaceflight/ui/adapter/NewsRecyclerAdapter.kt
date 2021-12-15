package com.mayco.desafiospaceflight.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mayco.desafiospaceflight.R
import com.mayco.desafiospaceflight.network.response.NewsResponse
import java.util.*
import kotlin.collections.ArrayList
import kotlin.properties.Delegates

class NewsRecyclerAdapter(private val getnews: (NewsResponse) -> Unit) :
    RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder>(), Filterable {


    var items: List<NewsResponse> by Delegates.observable(emptyList()) { _, old, new ->
        if (old != new) notifyDataSetChanged()
    }

    var newList: List<NewsResponse> by Delegates.observable(emptyList()) { _, old, new ->
        if (old != new) notifyDataSetChanged()
    }


    var filterList = ArrayList<NewsResponse>()


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(newsResponse: NewsResponse, getnews: (NewsResponse) -> Unit) {

            val name = itemView.findViewById<TextView>(R.id.textTitle)
            val image = itemView.findViewById<ImageView>(R.id.imageView)
            val click = itemView.findViewById<ConstraintLayout>(R.id.itemClick)



            name.text = newsResponse.title

            Glide.with(image.context).load(newsResponse.imageUrl).into(image)

            click.setOnClickListener {
                getnews(newsResponse)
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_news, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newsResponse = items[position]
        holder.bind(newsResponse, getnews)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charResult = constraint.toString()
                filterList = if (charResult.isEmpty()) {
                    newList as ArrayList<NewsResponse>
                } else {
                    val resultList = ArrayList<NewsResponse>()
                    for (row in newList) {
                        if (row.title?.toLowerCase()
                                ?.contains(constraint.toString().lowercase(Locale.getDefault()))!!
                        )
                            resultList.add(row)
                    }
                    resultList
                }

                val filterResults = FilterResults()
                filterResults.values = filterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                items = results?.values as List<NewsResponse>
                notifyDataSetChanged()
            }

        }
    }
}