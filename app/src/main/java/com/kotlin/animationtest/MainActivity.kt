package com.kotlin.animationtest

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv.layoutManager = GridLayoutManager(this@MainActivity,2,GridLayoutManager.HORIZONTAL,false)
        btn.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                rv.adapter = TestAdapter(this@MainActivity)
            }
        })
    }

    class TestAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>{

        class TestHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            lateinit var imageView : ImageView

            init {
                imageView = itemView.findViewById(R.id.iv_item)
            }
        }

        lateinit var mContext: Context
        var isShowAnimation : Boolean = false

        constructor(context : Context){
            mContext = context
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return TestHolder(LayoutInflater.from(mContext).inflate(R.layout.recycler_item,parent,false))
        }

        override fun getItemCount(): Int {
            return 50
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

            if (holder is TestHolder){
                var animation : Animation = AnimationUtils.loadAnimation(mContext,R.anim.animation_right_in)
                var animationListener = object : Animation.AnimationListener{
                    override fun onAnimationRepeat(animation: Animation?) {}

                    override fun onAnimationEnd(animation: Animation?) {
                        if (animation != null){
                            isShowAnimation = true
                        }
                    }

                    override fun onAnimationStart(animation: Animation?) {}
                }

                Glide.with(mContext).load(R.mipmap.ic_launcher).into(holder.imageView)

                if(!isShowAnimation){
                    animation.setAnimationListener(animationListener)
                    holder.imageView.startAnimation(animation)
                }

                holder.setIsRecyclable(false)
            }
        }


    }
}
