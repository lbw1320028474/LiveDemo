package center.h5game.mt.com.livedemo;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Created by SUMSUNG on 2018/7/19.
 */

public class SimpleDroidWallpaper extends WallpaperService {
    private final Handler handler = new Handler();

    @Override
    public Engine onCreateEngine() {
        return new VideoEngine();
    }


    class VideoEngine extends Engine {

        private MediaPlayer mMediaPlayer;

        @Override
        public void onSurfaceCreated(SurfaceHolder holder) {
            Log.i("logs","VideoEngine#onSurfaceCreated ");
            super.onSurfaceCreated(holder);
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setSurface(holder.getSurface());
            try {
                AssetManager assetMg = getApplicationContext().getAssets();
                AssetFileDescriptor fileDescriptor = assetMg.openFd("video.mp4");
                mMediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(),
                        fileDescriptor.getStartOffset(), fileDescriptor.getLength());
                mMediaPlayer.setLooping(true);
                mMediaPlayer.setVolume(0, 0);
                mMediaPlayer.prepare();
                mMediaPlayer.start();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            Log.i("logs","VideoEngine#onVisibilityChanged visible = " + visible);
            if (visible) {
                mMediaPlayer.start();
            } else {
                mMediaPlayer.pause();
            }
        }

        @Override
        public void onSurfaceDestroyed(SurfaceHolder holder) {
            Log.i("logs","VideoEngine#onSurfaceDestroyed ");
            super.onSurfaceDestroyed(holder);
            mMediaPlayer.release();
            mMediaPlayer = null;

        }
    }
}
