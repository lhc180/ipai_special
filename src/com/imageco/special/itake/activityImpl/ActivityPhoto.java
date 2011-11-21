package com.imageco.special.itake.activityImpl;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.widget.TextView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.imageco.special.R;

import com.imageco.special.itake.activityImpl.special.ActivityGame;
import com.imageco.special.itake.activityImpl.special.GameGlo;
import com.imageco.special.itake.gloable.Constant;
import com.imageco.special.itake.gloable.Gloable;
import com.imageco.special.itake.gloable.SETTING;
import com.imageco.special.itake.viewcus.ViewfinderView;
import com.imageco.special.util.camera.CameraManager;
import com.imageco.special.util.decoding.CaptureActivityHandler;
import com.imageco.special.util.decoding.InactivityTimer;

import java.io.IOException;
import java.util.Vector;

/**
 * Class ActivityPhoto ...
 *
 * @author Administrator Created on 11-10-22
 */
public class ActivityPhoto extends Activity implements Callback
{
    /**
     * Field handler
     */
    private CaptureActivityHandler handler;

    /**
     * Field viewfinderView
     */
    private ViewfinderView viewfinderView;

    /**
     * Field hasSurface
     */
    private boolean hasSurface;

    /**
     * Field decodeFormats
     */
    private Vector<BarcodeFormat> decodeFormats;

    /**
     * Field characterSet
     */
    private String characterSet;

    /**
     * Field txtResult
     */
    private TextView txtResult;

    /**
     * Field inactivityTimer
     */
    private InactivityTimer inactivityTimer;

    /**
     * Field mediaPlayer
     */
    private MediaPlayer mediaPlayer;

    /**
     * Field playBeep
     */
    private boolean playBeep = false;

    /**
     * Field BEEP_VOLUME
     */
    private static final float BEEP_VOLUME = 0.10f;

    /**
     * Field vibrate
     */
    private boolean vibrate;

    /**
     * Field qrCode
     */
    private String qrCode;

    /**
     * Called when the activityImpl is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.capture);
        //初始化 CameraManager
        CameraManager.init(getApplication());
        Gloable.getInstance().setNewHistory(true);
        viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
        txtResult = (TextView) findViewById(R.id.txtResult);
        hasSurface = false;
        inactivityTimer = new InactivityTimer(this);
    }

    /**
     * Method onBackPressed ...
     */
    @Override
    public void onBackPressed()
    {

        Intent intent = new Intent();
        Bundle bundle = new Bundle();
//        intent.setClass(ActivityPhoto.this, ActivityResult.class);
        intent.setClass(ActivityPhoto.this, ActivityGame.class);
        bundle.putString("qrCode", "");
        intent.putExtras(bundle);
        setResult(Constant.PHOTO_RESULT, intent);
        finish();
    }

    /**
     * Method onResume ...
     */
    @Override
    protected void onResume()
    {
        super.onResume();
        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        if (hasSurface)
        {
            initCamera(surfaceHolder);
        }
        else
        {
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }

        decodeFormats = null;

        characterSet = null;

        playBeep = true;
        AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
        if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL)
        {
            playBeep = false;
        }
        initBeepSound();
        vibrate = true;
    }

    /**
     * Method onPause ...
     */
    @Override
    protected void onPause()
    {
        super.onPause();
        if (handler != null)
        {
            handler.quitSynchronously();
            handler = null;
        }
        CameraManager.get().closeDriver();
    }

    /**
     * Method onDestroy ...
     */
    @Override
    protected void onDestroy()
    {
        inactivityTimer.shutdown();
        super.onDestroy();
    }

    /**
     * Method initCamera ...
     *
     * @param surfaceHolder of type SurfaceHolder
     */
    private void initCamera(SurfaceHolder surfaceHolder)
    {
        try
        {
            CameraManager.get().openDriver(surfaceHolder);
        }
        catch (IOException ioe)
        {
            return;
        }
        catch (RuntimeException e)
        {
            return;
        }
        if (handler == null)
        {
            handler = new CaptureActivityHandler(this, decodeFormats,
                characterSet);
        }
    }

    /**
     * Method surfaceChanged ...
     *
     * @param holder of type SurfaceHolder
     * @param format of type int
     * @param width of type int
     * @param height of type int
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
        int height)
    {

    }

    /**
     * Method surfaceCreated ...
     *
     * @param holder of type SurfaceHolder
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        if (!hasSurface)
        {
            hasSurface = true;
            initCamera(holder);
        }
    }

    /**
     * Method surfaceDestroyed ...
     *
     * @param holder of type SurfaceHolder
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        hasSurface = false;
    }

    /**
     * Method getViewfinderView returns the viewfinderView of this ActivityPhoto object.
     *
     * @return the viewfinderView (type ViewfinderView) of this ActivityPhoto object.
     */
    public ViewfinderView getViewfinderView()
    {
        return viewfinderView;
    }

    /**
     * Method getHandler returns the handler of this ActivityPhoto object.
     *
     * @return the handler (type Handler) of this ActivityPhoto object.
     */
    public Handler getHandler()
    {
        return handler;
    }

    /**
     * Method drawViewfinder ...
     */
    public void drawViewfinder()
    {
        viewfinderView.drawViewfinder();
    }

    /**
     * Method handleDecode ...
     *
     * @param obj of type Result
     * @param barcode of type Bitmap
     */
    public void handleDecode(Result obj, Bitmap barcode)
    {
        inactivityTimer.onActivity();
        viewfinderView.drawResultBitmap(barcode);
        playBeepSoundAndVibrate();
        qrCode = obj.getText();
//        qrCode = CodeOperation.all2utf8(qrCode);
        //跳转页面
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        if (GameGlo.getInstance().getForResult())
        {
            intent.setClass(ActivityPhoto.this, ActivityGame.class);
            bundle.putString("qrCode", qrCode);
            intent.putExtras(bundle);
            setResult(Constant.PHOTO_RESULT, intent);
        }
        else
        {
            intent.setClass(ActivityPhoto.this, ActivityResult.class);
            bundle.putString("qrCode", qrCode);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        ActivityPhoto.this.finish();
    }

    /**
     * Method initBeepSound ...
     */
    private void initBeepSound()

    {
        Boolean testBoolean1;
        Boolean testBoolean2;
        MediaPlayer testBoolean3;
        testBoolean1 = SETTING.SOUND;
        testBoolean2 = playBeep;
        testBoolean3 = mediaPlayer;
        if (SETTING.SOUND && playBeep && (mediaPlayer == null))
        {
            // The volume on STREAM_SYSTEM is not adjustable, and users found it
            // too loud,
            // so we now play on the music stream.
            setVolumeControlStream(AudioManager.STREAM_MUSIC);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(beepListener);

            AssetFileDescriptor file = getResources().openRawResourceFd(
                R.raw.cameraclick);
            try
            {
                mediaPlayer.setDataSource(file.getFileDescriptor(),
                    file.getStartOffset(), file.getLength());
                file.close();
//				mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
                mediaPlayer.prepare();
            }
            catch (IOException e)
            {
                mediaPlayer = null;
            }
        }
    }

    /**
     * Field VIBRATE_DURATION
     */
    private static final long VIBRATE_DURATION = 200L;

    /**
     * Method playBeepSoundAndVibrate ...
     */
    private void playBeepSoundAndVibrate()
    {
        if (SETTING.SOUND && playBeep && mediaPlayer != null)
        {
            mediaPlayer.start();
        }
        if (SETTING.SHAKE && vibrate)
        {
            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(VIBRATE_DURATION);
        }
    }

    /**
     *
     */
    private final OnCompletionListener beepListener = new OnCompletionListener()
    {
        /**
         * Method onCompletion ...
         *
         * @param mediaPlayer of type MediaPlayer
         */
        public void onCompletion(MediaPlayer mediaPlayer)
        {
            mediaPlayer.seekTo(0);
        }
    };
}