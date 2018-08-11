package app.fakharany.com.musicplayer.Adapter

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import app.fakharany.com.musicplayer.POJO.FileModel
import app.fakharany.com.musicplayer.R
import kotlinx.android.synthetic.main.item_song.view.*

class SongsAdapter(var list: ArrayList<FileModel>, var listener: MyOnItemClickListener) : RecyclerView.Adapter<SongsAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_song, p0, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        var field = list.get(p1)
        p0.songName!!.text = field.name
        p0.group_view!!.setOnClickListener({ view -> listener.onClick( p1)})
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var songName: TextView? = null
        var group_view: ConstraintLayout? = null

        init {
            this.songName = itemView.song_name
            this.group_view = itemView.group_view
        }

    }

    interface MyOnItemClickListener {
        fun onClick(position: Int)
    }
}