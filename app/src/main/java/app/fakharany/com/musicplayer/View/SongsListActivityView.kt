package app.fakharany.com.musicplayer.View

import android.content.Intent
import app.fakharany.com.musicplayer.Adapter.SongsAdapter

interface SongsListActivityView : RootView {
    fun showRawFilesList(adapter: SongsAdapter)
    fun openDetailActivity(intent: Intent)
}