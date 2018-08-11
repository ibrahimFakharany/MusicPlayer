package app.fakharany.com.musicplayer.View

import app.fakharany.com.musicplayer.POJO.FileModel

interface SongDetailView : RootView {
    fun receiveRawFileId(fiel: FileModel)
    fun destroyExoplayer()
}