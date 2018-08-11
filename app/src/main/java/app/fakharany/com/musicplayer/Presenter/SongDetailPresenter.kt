package app.fakharany.com.musicplayer.Presenter

import android.content.Context
import android.content.Intent
import app.fakharany.com.musicplayer.Model.SongDetailModel
import app.fakharany.com.musicplayer.POJO.FileModel
import app.fakharany.com.musicplayer.Utilities.Constants
import app.fakharany.com.musicplayer.View.SongDetailView

class SongDetailPresenter(var mView: SongDetailView, var model: SongDetailModel, var context: Context)
    : SongDetailModel.ModelListener {
    override fun onSuccess() {

    }

    override fun onSuccess(listItem: FileModel) {
        mView.receiveRawFileId(listItem)
    }

    override fun onFailed() {
    }

    fun onCreateDetailActivity(intent: Intent) {
        var id: Int = intent.extras.getInt(Constants.BUNDLE_KEY_LIST_ITEM_POSITION)
        mView.showMessage("" + id)
        model.getRawFile(id, this)
    }

    fun onResume() {

    }

    fun onDestroy(){

        mView.destroyExoplayer()

    }

}