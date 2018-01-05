package li.cheng.clapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    String tag="MainActivity";
    private VideoView video_ad;
    private MediaPlayer mediaPlayer;
    boolean isOnCreate=false;
    String url="http://125.76.233.80:9700/upload/201712/28/201712281059215070.mp4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isOnCreate=true;
        setContentView(R.layout.activity_main);
        video_ad= (VideoView) findViewById(R.id.video_ad);
        video_ad.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.e(tag,"VideoView onPrepared");
                mediaPlayer=mp;
                mp.setVolume(0F, 0F);
                mp.setLooping(false);
//                if(!isOnCreate){
//                    video_ad.start();
//                    video_ad.seekTo(videoPos);
//                }
            }
        });
        video_ad.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //需要更新时，视频播放完毕不跳转
                Log.e(tag,"视频播放完成");
            }
        });
        video_ad.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.e(tag,"视频解析错误");
                return false;
            }
        });
        video_ad.setVideoPath(url);
        video_ad.start();
    }

    private Handler handler=new Handler(){

    };
    int videoPos;

    @Override
    protected void onPause() {
        super.onPause();
        isOnCreate=false;
        if(video_ad!=null){
            videoPos=video_ad.getCurrentPosition();
            if(video_ad.isPlaying()){
                video_ad.pause();
            }
        }

//        if(mediaPlayer!=null){
//            videoPos=mediaPlayer.getCurrentPosition();
//            if(mediaPlayer.isPlaying()){
//                mediaPlayer.pause();
//            }
//        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(!isOnCreate){
            if(video_ad!=null){
                video_ad.start();
                video_ad.seekTo(videoPos);
            }
        }
//        if(!isOnCreate){
//            if(mediaPlayer!=null){
//                mediaPlayer.start();
//            }
//        }
    }
}
