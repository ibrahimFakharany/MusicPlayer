package app.fakharany.com.musicplayer.Presenter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import app.fakharany.com.musicplayer.Adapter.SongsAdapter
import app.fakharany.com.musicplayer.Component.SongDetailActivity
import app.fakharany.com.musicplayer.Model.SongListActivityModel
import app.fakharany.com.musicplayer.POJO.FileModel
import app.fakharany.com.musicplayer.R
import app.fakharany.com.musicplayer.Utilities.Constants
import app.fakharany.com.musicplayer.View.SongsListActivityView
import java.util.*

class SongListPresenter(var mView: SongsListActivityView, var model: SongListActivityModel,
                        var context: Context)
    : SongListActivityModel.ModelListener, SongsAdapter.MyOnItemClickListener {


    // handle on item click listener in the list
    override fun onClick(position: Int) {
        var intent = Intent(context, SongDetailActivity::class.java)
        var bundle = Bundle()
        bundle.putInt(Constants.BUNDLE_KEY_LIST_ITEM_POSITION, position)
        intent.putExtras(bundle)
        mView.openDetailActivity(intent)
    }

    override fun loadListFinished(list: ArrayList<FileModel>) {
        var adapter = SongsAdapter(list, this)
        mView.showRawFilesList(adapter)
    }

    override fun onSuccess() {
        mView.showMessage(context.getString(R.string.Success))
    }

    override fun onFailed() {
        mView.showMessage(context.getString(R.string.failed_message))
    }


    fun onCreateActivity() {
        model.loadRawFiles(this)
    }
}