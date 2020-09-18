package com.example.recycleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.recycleview.models.BlogPost
import kotlinx.android.synthetic.main.layout_blog_list_item.view.*

class BlogRecyclerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<BlogPost> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return blogViewHolder(parent)
    }

    private fun blogViewHolder(parent: ViewGroup): BlogViewHolder {
        return BlogViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_blog_list_item, parent, false)

        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        happen(holder, position)
    }

    private fun happen(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        when (holder) {
            is BlogViewHolder -> {
                holder.bind(
                    items.get(position)
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return  items.size
    }

    fun submitList(blogPost: List<BlogPost>){
        items = blogPost
    }

    class BlogViewHolder constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){
        val blog_image: ImageView=itemView.blog_image
        val blog_title: TextView=itemView.blog_title
        val blog_author:TextView=itemView.blog_author

        fun bind(blogPost: BlogPost){
            blog_title.setText(blogPost.title)
            blog_author.setText(blogPost.username)
            //the image using the grind library
            val requestOptions=RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(blogPost.image)
                .into(blog_image)
        }
    }
}