package com.andrew.videoview.activity

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.andrew.videoview.R
import com.andrew.videoview.adapter.ListViewAdapter
import com.andrew.videoview.item.ListViewItem
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

/**
 * @author Andrew Kim
 * @since 2021. 06. 06
 * @desc VideoView 메인 화면 Activity
 */
class MainActivity : YouTubeBaseActivity() {

    var VIDEOID = ""
    private lateinit var youtubeView: YouTubePlayerView
    private lateinit var player: YouTubePlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        youtubeView = findViewById<YouTubePlayerView>(R.id.youtubeView)    // youtube view init

        initPlayer()    // youtube player 초기

        val listView = findViewById<ListView>(R.id.youtubeContentList)    // list view init

        val items = mutableListOf<ListViewItem>()    // list 객체 생성

        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.ic_launcher_background)!!, "k_OQ_Fuo2Ec", "test1 content", "https://i.ytimg.com/vi/2BwykH5Ja-o/hqdefault.jpg"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.ic_launcher_background)!!, "w_YoGPY7q6E", "test2 content", "https://i.ytimg.com/vi/k_OQ_Fuo2Ec/hqdefault.jpg"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.ic_launcher_background)!!, "WSnjPHWqFGM", "test3 content", "https://i.ytimg.com/vi/WSnjPHWqFGM/hqdefault.jpg"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.ic_launcher_background)!!, "cVo2cF5uWGM", "test4 content", "https://i.ytimg.com/vi/w_YoGPY7q6E/hqdefault.jpg"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.ic_launcher_background)!!, "GaNtvxctoPs", "test5 content", "https://i.ytimg.com/vi/cVo2cF5uWGM/hqdefault.jpg"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.ic_launcher_background)!!, "iOVi0_Kxt9g", "test6 content", "https://i.ytimg.com/vi/GaNtvxctoPs/hqdefault.jpg"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.ic_launcher_background)!!, "prgQtshviPM", "test7 content", "https://i.ytimg.com/vi/Yffa3G4oJSM/hqdefault.jpg"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.ic_launcher_background)!!, "Yffa3G4oJSM", "test8 content", "https://i.ytimg.com/vi/iOVi0_Kxt9g/hqdefault.jpg"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.ic_launcher_background)!!, "omjbXBYpcaY", "test9 content", "https://i.ytimg.com/vi/omjbXBYpcaY/hqdefault.jpg"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.ic_launcher_background)!!, "aeB35-tGnro", "test10 content", "https://i.ytimg.com/vi/wCsCMsuFBTI/hqdefault.jpg"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.ic_launcher_background)!!, "GZrYhhm1dOI", "test11 content", "https://i.ytimg.com/vi/B5Z7WrFRH3Y/hqdefault.jpg"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.ic_launcher_background)!!, "oPAJ6mSOab8", "test12 content", "https://i.ytimg.com/vi/x39ttzWXaIY/hqdefault.jpg"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.ic_launcher_background)!!, "wCsCMsuFBTI", "test13 content", "https://i.ytimg.com/vi/s8OILK213wA/hqdefault.jpg"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.ic_launcher_background)!!, "jQmecRPpPr8", "test14 content", "https://i.ytimg.com/vi/4zlb9wrurdo/hqdefault.jpg"))
        items.add(ListViewItem(ContextCompat.getDrawable(this, R.drawable.ic_launcher_background)!!, "x39ttzWXaIY", "test15 content", "https://i.ytimg.com/vi/vpG8aKNw0T0/hqdefault.jpg"))

        val adapter = ListViewAdapter(items)    // adapter 연결
        listView.adapter = adapter

        // 리스트 아이템 클릭 시 리스트의 영상 아이디값을 가져와 플레이어로 전달하여 영상 재생
        listView.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
            val item = parent.getItemAtPosition(position) as ListViewItem
            Toast.makeText(this, item.title, Toast.LENGTH_LONG).show()
            VIDEOID = item.title

            playVideo(VIDEOID)
        }

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
            }
        }
    }
}