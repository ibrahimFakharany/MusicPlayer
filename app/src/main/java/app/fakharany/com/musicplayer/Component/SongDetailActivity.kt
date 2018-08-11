package app.fakharany.com.musicplayer.Component

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import app.fakharany.com.musicplayer.Model.SongDetailModel
import app.fakharany.com.musicplayer.POJO.FileModel
import app.fakharany.com.musicplayer.Presenter.SongDetailPresenter
import app.fakharany.com.musicplayer.R
import app.fakharany.com.musicplayer.View.SongDetailView
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.extractor.ExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveVideoTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelection
import com.google.android.exoplayer2.trackselection.TrackSelector
import com.google.android.exoplayer2.upstream.*
import kotlinx.android.synthetic.main.activity_song_detail.*


class SongDetailActivity : AppCompatActivity(), SongDetailView, View.OnClickListener {
    override fun onClick(v: View?) {
        if (v!!.getId() == R.id.btnPlay) {
            //on play button click
            player!!.setPlayWhenReady(true)

        } else if (v!!.getId() == R.id.btnPause) {
            player!!.setPlayWhenReady(false)

        }
    }

    override fun destroyExoplayer() {
        player!!.setPlayWhenReady(false)
        player!!.release()
    }


    private var player: SimpleExoPlayer? = null
    private var bandwidthMeter: BandwidthMeter? = null
    private var extractorsFactory: ExtractorsFactory? = null
    private var trackSelectionFactory: TrackSelection.Factory? = null
    private var trackSelector: TrackSelector? = null

    override fun receiveRawFileId(fiel: FileModel) {


        bandwidthMeter = DefaultBandwidthMeter()
        extractorsFactory = DefaultExtractorsFactory()

        trackSelectionFactory = AdaptiveVideoTrackSelection.Factory(bandwidthMeter)

        trackSelector = DefaultTrackSelector(trackSelectionFactory)
        player = ExoPlayerFactory.newSimpleInstance(this, trackSelector, DefaultLoadControl());

        var rawResourceDataSource = RawResourceDataSource(this);
        var dataSpec = DataSpec(RawResourceDataSource.buildRawResourceUri(fiel.raw_id));

        rawResourceDataSource.open(dataSpec);

        var factory: DataSource.Factory = DataSource.Factory {

            rawResourceDataSource;

        }
        var audioSource: MediaSource = ExtractorMediaSource(rawResourceDataSource.getUri(), factory, DefaultExtractorsFactory(), null, null);
        player!!.prepare(audioSource);
    }


    var presenter = SongDetailPresenter(this, SongDetailModel(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_detail)
        btnPlay!!.setOnClickListener(this)
        btnPause!!.setOnClickListener(this)

        presenter.onCreateDetailActivity(intent)
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onDestroy()
    }
}
