package com.samedtemiz.dagger_hilt_learning

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PostAdapter : RecyclerView.Adapter<PostAdapter.MyHolderView>() {

    var liveData: List<Post>? = null

    fun setList(liveData:List<Post>){
        this.liveData = liveData
    }

    class MyHolderView(view: View) : RecyclerView.ViewHolder(view){
        val titleText: TextView = view.findViewById(R.id.post_title)
        val bodyText: TextView = view.findViewById(R.id.post_body)

        fun bindItems(post: Post){
            titleText.text = post.title
            bodyText.text = post.body
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.MyHolderView {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        return MyHolderView(view)
    }

    override fun onBindViewHolder(holder: PostAdapter.MyHolderView, position: Int) {
        holder.bindItems(liveData!!.get(position))
    }

    override fun getItemCount(): Int {
        if(liveData == null){
            return 0
        }else{
            return liveData!!.size
        }
    }
}