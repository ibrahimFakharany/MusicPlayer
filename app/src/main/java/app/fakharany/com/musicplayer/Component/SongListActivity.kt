package app.fakharany.com.musicplayer.Component

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import app.fakharany.com.musicplayer.Adapter.SongsAdapter
import app.fakharany.com.musicplayer.Model.SongListActivityModel
import app.fakharany.com.musicplayer.Presenter.SongListPresenter
import app.fakharany.com.musicplayer.R
import app.fakharany.com.musicplayer.View.SongsListActivityView
import kotlinx.android.synthetic.main.activity_main.*

class SongListActivity : AppCompatActivity(), SongsListActivityView {
    override fun openDetailActivity(intent: Intent) {
        startActivity(intent)
    }

    override fun showRawFilesList(adapter: SongsAdapter) {
        song_list.layoutManager = LinearLayoutManager(this)
        song_list.adapter = adapter
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    var presenter = SongListPresenter(this, SongListActivityModel(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.onCreateActivity()

    }
}
