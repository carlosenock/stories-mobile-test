package com.cenock.reigntest.hnmobiletest.presentation.story

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import android.widget.Toast
import com.cenock.reigntest.hnmobiletest.R
import com.cenock.reigntest.hnmobiletest.injection.Injection
import com.cenock.reigntest.hnmobiletest.model.Story
import com.cenock.reigntest.hnmobiletest.presentation.storyDetail.StoryDetailActivity
import com.cenock.reigntest.hnmobiletest.presentation.storyDetail.StoryDetailActivity.Companion.STORY_URL
import kotlinx.android.synthetic.main.activity_story.*
import com.cenock.reigntest.hnmobiletest.presentation.utils.ViewDrawer.drawableToBitmap


class StoryActivity : AppCompatActivity(), StoryContract.View {

    override lateinit var presenter: StoryContract.Presenter
    private var storyAdapter: StoryAdapter? = null

    private val p = Paint()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story)

        StoryPresenter(Injection.provideMoviesRepository(), this)

        presenter.start()
    }

    override fun loadStories(stories: List<Story>) {
        if (stories.isNotEmpty()) {
            if (storyAdapter == null) {
                storyAdapter = StoryAdapter(stories.toMutableList(), this) { movie: Story ->
                    presenter.onStoryClick(movie)
                }
                recycler_view.adapter = storyAdapter
                recycler_view.layoutManager = GridLayoutManager(this, 1)
                recycler_view.setHasFixedSize(true)
                recycler_view.itemAnimator = DefaultItemAnimator()
            } else {
                storyAdapter?.updateAdapter(stories)
            }
        }
        initSwipe()
    }

    override fun goToStorieseDetail(story: Story) {
        if (!story.url.isNullOrEmpty()) {
            val intent = Intent(this, StoryDetailActivity::class.java)
            intent.putExtra(STORY_URL, story.url)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
            startActivity(intent)
        } else {
            Toast.makeText(
                this,
                getString(R.string.url_error),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun initSwipe() {
        val simpleItemTouchCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    if (direction == ItemTouchHelper.LEFT) {
                        storyAdapter!!.removeItem(position)
                    }
                }
                override fun onChildDraw(
                    c: Canvas,
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    dX: Float,
                    dY: Float,
                    actionState: Int,
                    isCurrentlyActive: Boolean
                ) {
                    val icon: Bitmap
                    if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                        val itemView = viewHolder.itemView
                        val height = itemView.bottom.toFloat() - itemView.top.toFloat()
                        val width = height / 3
                        if (dX < 0) {
                            p.color = Color.parseColor("#D32F2F")
                            val background = RectF(
                                itemView.right.toFloat() + dX,
                                itemView.top.toFloat(),
                                itemView.right.toFloat(),
                                itemView.bottom.toFloat()
                            )
                            c.drawRect(background, p)
                            val drawable = getDrawable(R.drawable.ic_delete_white)
                            icon = drawableToBitmap(drawable)
                            val icon_dest = RectF(
                                itemView.right.toFloat() - 2 * width,
                                itemView.top.toFloat() + width,
                                itemView.right.toFloat() - width,
                                itemView.bottom.toFloat() - width
                            )
                            c.drawBitmap(icon, null, icon_dest, p)
                        }
                    }
                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                }
            }
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(recycler_view)
    }

    override fun showLoading() {
        ll_container.visibility = View.GONE
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        ll_container.visibility = View.VISIBLE
        progress_bar.visibility = View.GONE
    }

}
