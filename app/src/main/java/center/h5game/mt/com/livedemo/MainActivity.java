package center.h5game.mt.com.livedemo;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private final static int REQUEST_CODE_SET_WALLPAPER = 0x001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toSettingWallpaper(View view) {
/**
 * 跳转到系统设置壁纸界面
 *
 * @param context
 * @param paramActivity
 */
        try {
            Intent localIntent = new Intent();
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {//ICE_CREAM_SANDWICH_MR1  15
                localIntent.setAction(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);//android.service.wallpaper.CHANGE_LIVE_WALLPAPER
                //android.service.wallpaper.extra.LIVE_WALLPAPER_COMPONENT
                localIntent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT
                        , new ComponentName(MainActivity.this.getApplicationContext().getPackageName()
                                , SimpleDroidWallpaper.class.getCanonicalName()));
            } else {
                localIntent.setAction(WallpaperManager.ACTION_LIVE_WALLPAPER_CHOOSER);//android.service.wallpaper.LIVE_WALLPAPER_CHOOSER
            }
            MainActivity.this.startActivityForResult(localIntent, REQUEST_CODE_SET_WALLPAPER);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SET_WALLPAPER) {
            if (resultCode == RESULT_OK) {
                // TODO: 2017/3/13 设置动态壁纸成功
            } else {
                // TODO: 2017/3/13 取消设置动态壁纸
            }
        }
    }
}
