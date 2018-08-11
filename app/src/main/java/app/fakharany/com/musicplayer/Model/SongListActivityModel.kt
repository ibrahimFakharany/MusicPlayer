package app.fakharany.com.musicplayer.Model

import app.fakharany.com.musicplayer.POJO.FileModel
import app.fakharany.com.musicplayer.R
import java.util.*

class SongListActivityModel {
    interface ModelListener : RootModelListener {
        fun loadListFinished(list: ArrayList<FileModel>)
    }

    fun loadRawFiles(listener: ModelListener) {
         var list = ArrayList<FileModel>()
        list.add(FileModel("6 wshosho", R.raw.wshosh_6))
        list.add(FileModel("cairokee", R.raw.cairokee))
        listener.loadListFinished(list)
    }
}
