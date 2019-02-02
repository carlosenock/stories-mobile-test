package com.cenock.reigntest.hnmobiletest.presentation.story

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cenock.reigntest.hnmobiletest.R
import com.cenock.reigntest.hnmobiletest.model.Story
import kotlinx.android.synthetic.main.adapter_story_item.view.*
import java.util.*

class StoryAdapter(
    private var mList: List<Story>,
    private var mContext: Context,
    private val mItemListener: (Story) -> Unit
) : RecyclerView.Adapter<StoryAdapter.StoryViewHolder>() {


    companion object {
        private var itemListener: RecyclerViewClickListener? = null
    }

    interface RecyclerViewClickListener {
        fun recyclerViewListClicked(v: View, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryAdapter.StoryViewHolder {
        val viewItem = LayoutInflater.from(parent.context).inflate(R.layout.adapter_story_item, parent, false)
        return StoryViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: StoryAdapter.StoryViewHolder, position: Int) {

        val story = mList[position]
        holder.bindItems(story, mContext, mItemListener)
        holder.itemView.setOnClickListener { mItemListener(story) }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class StoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(item: Story, context: Context, itemListener: (Story) -> Unit) {
            itemView.tv_title.text = item.storyTitle
            itemView.tv_author_date.text = context.getString(
                R.string.author_date_format,
                item.author,
                DateUtils.getRelativeTimeSpanString(item.createdAtI, System.currentTimeMillis()/1000, 0L,DateUtils.FORMAT_ABBREV_ALL)
            )
            itemView.setOnClickListener { itemListener(item) }
        }
    }

    fun updateAdapter(items: List<Story>) {
        this.mList = items
        notifyDataSetChanged()
    }
}