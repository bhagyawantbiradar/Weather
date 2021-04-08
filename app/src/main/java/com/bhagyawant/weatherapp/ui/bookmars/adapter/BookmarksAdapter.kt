package com.bhagyawant.weatherapp.ui.bookmars.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bhagyawant.weatherapp.R
import com.bhagyawant.weatherapp.data.db.entities.Bookmark
import kotlinx.android.synthetic.main.item_bookmark.view.*

class BookmarksAdapter(
    val bookmarks: ArrayList<Bookmark>,
    val bookmarkItemClickListener: BookmarkItemClickListener
) :
    RecyclerView.Adapter<BookmarksAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_bookmark, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bookmark = bookmarks.get(position)
        holder.view.tv_city.text = bookmark.city

        holder.view.setOnClickListener {
            bookmarkItemClickListener.onItemClicked(bookmark)
        }

        holder.view.iv_delete.setOnClickListener {
            bookmarkItemClickListener.onDeleteClicked(bookmark)
            bookmarks.remove(bookmark)
            notifyItemRemoved(position)
        }
    }

    override fun getItemCount() = bookmarks.size


    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    interface BookmarkItemClickListener {
        fun onItemClicked(bookmark: Bookmark)
        fun onDeleteClicked(bookmark: Bookmark)
    }
}