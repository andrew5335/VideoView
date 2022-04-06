package com.andrew.videoview.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.andrew.videoview.R
import com.andrew.videoview.R.string.eye2webApiKey
import com.andrew.videoview.adapter.ListViewAdapter
import com.andrew.videoview.dto.MediaItem
import com.andrew.videoview.item.ListViewItem
import com.andrew.videoview.service.MediaService
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author Andrew Kim
 * @since 2021. 06. 06
 * @desc VideoView 메인 화면 Activity
 */
class MainActivity : YouTubeBaseActivity() {

    var VIDEOID: String = ""
    private lateinit var youtubeView: YouTubePlayerView
    private lateinit var player: YouTubePlayer
    var apiKey: String = ""
    var offset: Int = 0
    var limit: Int = 100

    var items = mutableListOf<ListViewItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiKey = getString(R.string.eye2webApiKey)
        youtubeView = findViewById<YouTubePlayerView>(R.id.youtubeView)    // youtube view init

        initPlayer()    // youtube player 초기화

        val listView = findViewById<ListView>(R.id.youtubeContentList)    // list view init

            // list 객체 생성
        var mediaItemList = MediaService.mediaInterface.getMediaList(apiKey, offset, limit, "아이유").enqueue(object:
            Callback<MutableList<MediaItem>> {
            override fun onResponse(call: Call<MutableList<MediaItem>>, response: Response<MutableList<MediaItem>>) {
                val mediaResponse = response.body()
                val mediaList : MutableList<MediaItem> = mediaResponse!!
                Log.d("medialist", "$mediaList")

                for(i in 0 until mediaList.count()) {
                    items.add(ListViewItem(ContextCompat.getDrawable(applicationContext, R.drawable.ic_launcher_background)!!, mediaList[i].title, mediaList[i].description, mediaList[i].imgUrl, mediaList[i].videoId))
                }

                val adapter = ListViewAdapter(items)    // adapter 연결
                listView.adapter = adapter

                listView.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
                    var item = parent.getItemAtPosition(position) as ListViewItem
                    //Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()

                    VIDEOID = item.videoId
                    playVideo(VIDEOID)
                }
            }

            override fun onFailure(call: Call<MutableList<MediaItem>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                Log.d("Error", "${t.message}")
            }

        })
/**
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.ic_launcher_background)!!, "k_OQ_Fuo2Ec", "test1 content", "https://i.ytimg.com/vi/2BwykH5Ja-o/hqdefault.jpg", "k_OQ_Fuo2Ec"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.ic_launcher_background)!!, "w_YoGPY7q6E", "test2 content", "https://i.ytimg.com/vi/k_OQ_Fuo2Ec/hqdefault.jpg", "w_YoGPY7q6E"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.ic_launcher_background)!!, "WSnjPHWqFGM", "test3 content", "https://i.ytimg.com/vi/WSnjPHWqFGM/hqdefault.jpg", "WSnjPHWqFGM"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.ic_launcher_background)!!, "cVo2cF5uWGM", "test4 content", "https://i.ytimg.com/vi/w_YoGPY7q6E/hqdefault.jpg", "cVo2cF5uWGM"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.ic_launcher_background)!!, "GaNtvxctoPs", "test5 content", "https://i.ytimg.com/vi/cVo2cF5uWGM/hqdefault.jpg", "GaNtvxctoPs"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.ic_launcher_background)!!, "iOVi0_Kxt9g", "test6 content", "https://i.ytimg.com/vi/GaNtvxctoPs/hqdefault.jpg", "iOVi0_Kxt9g"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.ic_launcher_background)!!, "prgQtshviPM", "test7 content", "https://i.ytimg.com/vi/Yffa3G4oJSM/hqdefault.jpg", "prgQtshviPM"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.ic_launcher_background)!!, "Yffa3G4oJSM", "test8 content", "https://i.ytimg.com/vi/iOVi0_Kxt9g/hqdefault.jpg", "Yffa3G4oJSM"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.ic_launcher_background)!!, "omjbXBYpcaY", "test9 content", "https://i.ytimg.com/vi/omjbXBYpcaY/hqdefault.jpg", "omjbXBYpcaY"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.ic_launcher_background)!!, "aeB35-tGnro", "test10 content", "https://i.ytimg.com/vi/wCsCMsuFBTI/hqdefault.jpg", "aeB35-tGnro"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.ic_launcher_background)!!, "GZrYhhm1dOI", "test11 content", "https://i.ytimg.com/vi/B5Z7WrFRH3Y/hqdefault.jpg", "GZrYhhm1dOI"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.ic_launcher_background)!!, "oPAJ6mSOab8", "test12 content", "https://i.ytimg.com/vi/x39ttzWXaIY/hqdefault.jpg", "oPAJ6mSOab8"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.ic_launcher_background)!!, "wCsCMsuFBTI", "test13 content", "https://i.ytimg.com/vi/s8OILK213wA/hqdefault.jpg", "wCsCMsuFBTI"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.ic_launcher_background)!!, "jQmecRPpPr8", "test14 content", "https://i.ytimg.com/vi/4zlb9wrurdo/hqdefault.jpg", "jQmecRPpPr8"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.ic_launcher_background)!!, "x39ttzWXaIY", "test15 content", "https://i.ytimg.com/vi/vpG8aKNw0T0/hqdefault.jpg", "x39ttzWXaIY"))
**/
        //val adapter = ListViewAdapter(items)    // adapter 연결
        //listView.adapter = adapter

        // 리스트 아이템 클릭 시 리스트의 영상 아이디값을 가져와 플레이어로 전달하여 영상 재생
        /**
        listView.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
            var item = parent.getItemAtPosition(position) as ListViewItem
            //Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
            VIDEOID = item.videoId

            playVideo(VIDEOID)
        }
        **/

    }

    /**
     * youtube player 초기화
     */
    fun initPlayer() {
        youtubeView.initialize("develop", object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
                provider: YouTubePlayer.Provider,
                player: YouTubePlayer,
                wasRestored: Boolean
            ) {
                if(!wasRestored) {
                    setUpPlayer(player)    // player 초기화 처리
                }

                player.setPlayerStateChangeListener(object : YouTubePlayer.PlayerStateChangeListener{
                    override fun onLoading() {
                        // media loading 중 처리 로직 추가
                    }

                    override fun onLoaded(VIDEOID: String?) {
                        player.play()
                    }

                    override fun onAdStarted() {
                        // 광고 시작된 경우 처리 로직 추가
                        Toast.makeText(applicationContext, "광고가 진행중입니다. 조금만 기다려주세요.", Toast.LENGTH_LONG).show()
                    }

                    override fun onVideoStarted() {
                        // media가 시작된 경우 처리 로직 추가
                    }

                    override fun onVideoEnded() {
                        // media가 끝난 경우 처리 로직 추가
                    }

                    override fun onError(p0: YouTubePlayer.ErrorReason?) {
                        // 오류 발생 시 처리 로직 추가
                        Toast.makeText(applicationContext, "Error : " + p0.toString(), Toast.LENGTH_LONG).show()
                    }

                })
            }

            override fun onInitializationFailure(
                provider: YouTubePlayer.Provider?,
                result: YouTubeInitializationResult?
            ) {
                Toast.makeText(applicationContext, "init fail : " + result.toString(), Toast.LENGTH_LONG).show()
            }

        })
    }

    /**
     * youtube player 초기화
     */
    fun setUpPlayer(_youTubePlayer: YouTubePlayer) {
        player = _youTubePlayer
        player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)
        //player.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS)
    }

    /**
     * 영상 재생 
     */
    fun playVideo(videoId: String) {
        if(null != player) {
            if (null != videoId) {
                if (player.isPlaying) {
                    player.pause()
                }

                player.loadVideo(videoId)
                player.loadVideo(videoId)
            }
        }
    }
}