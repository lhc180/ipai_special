package com.imageco.itake.activityImpl;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.imageco.R;
import com.imageco.itake.activityImpl.special.ActivitySpecialMain;
import com.imageco.itake.gloable.Constant;
import com.imageco.itake.gloable.Gloable;
import com.imageco.itake.gloable.SETTING;
import com.imageco.itake.userOption.CollectionOperation;
import com.imageco.util.decoding.Base64;
import com.imageco.util.net.Conn;
import org.json.JSONException;
import org.json.JSONObject;

import static com.imageco.itake.userOption.CollectionOperation.getChannel;
import static com.imageco.itake.userOption.CollectionOperation.getDisplay;
import static com.imageco.itake.userOption.CollectionOperation.getOs;
import static com.imageco.itake.userOption.CollectionOperation.getVersionCode;
import static com.imageco.itake.userOption.CollectionOperation.jsonObject;

/**
 * 用户行为分析 Created by IntelliJ IDEA. User: OYQX Date: 11-10-20 Time: 上午9:33
 */
@SuppressWarnings({"unchecked"})
public class ActivityRun extends Activity
{
    private JSONObject jsonAds;//广告返回

    /**
     * Field mProgressBar
     */
    private ProgressBar mProgressBar;

    /**
     * Field mTextView
     */
    private TextView mTextView;

    /**
     * Field a
     */
    private Activity a = this;

    /**
     * Field versionmsg
     */
    private String versionmsg = null;

    private String msg;

    /**
     * Method onResume ...
     */
    @Override
    protected void onResume()
    {
        super.onResume();
        loadData();
        initSetting();//初始化配置
        CollectionOperation.sendUserInfo();
//        downAds();
    }

    /**
     * Method initSetting ...
     */
    private void initSetting()
    {
//        SharedPreferences settings = getPreferences(Activity.MODE_PRIVATE);
        SharedPreferences settings = getSharedPreferences("setting", MODE_PRIVATE);
        SETTING.SOUND = settings.getBoolean("sound", false);
        SETTING.SHAKE = settings.getBoolean("shake", false);
        SETTING.PASTE = settings.getBoolean("paste", false);
        SETTING.FLASHLIGHT = settings.getBoolean("flashlight", false);
        Gloable.getInstance().setShow(settings.getBoolean("isshow", true));

//        System.out.println("sound======initSetting=============" + SETTING.SOUND);
//        System.out.println("shake=========initSetting==========" + SETTING.SHAKE);
//        System.out.println("paste==========initSetting=========" + SETTING.PASTE);
//        System.out.println("flashlight=====initSetting=========" + SETTING.FLASHLIGHT);
    }

    /**
     * Method onCreate ...
     *
     * @param savedInstanceState of type Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(
            savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        setContentView(R.layout.splash);
//        System.out.println(CollectionOperation.getVersionName());// 1.0.0
//        System.out.println(CollectionOperation.getSDKVersionNumber());//10
//        System.out.println(CollectionOperation.getVersionRelease());//2.3.4
//        String useragent=(new WebView(ActivityRun.this).getSettings()).getUserAgentString();
//        System.out.println("============="+useragent);
        mProgressBar = (ProgressBar) this.findViewById(R.id.progressBar);
        mProgressBar.setIndeterminate(false);
        mTextView = (TextView) findViewById(R.id.texttip);
    }

    /**
     * Method loadData ...
     */
    private void loadData()
    {
        new AsyncTask<Void, Void, Boolean>()
        {
            /**
             * 此方法在后台线程执行，完成任务的主要工作，通常需要较长的时间
             */
            @Override
            protected Boolean doInBackground(Void... arg0)
            {

                String pathStr =
                    "channel_id=" + getChannel() + "&display=" + getDisplay() + "&os=" + getOs();
                jsonObject =
                    Conn.execute(Constant.URL_TRACK_BASE + Constant.URL_TRACK_UPDATE + pathStr);
                publishProgress();
                for (int i = 1; i <= 100; i++)
                {
                    try
                    {
                        Thread.sleep(3);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    mProgressBar.setProgress(i);
                }
                return Boolean.TRUE;
            }

            /**
             * 此方法在主线程执行，用于显示任务执行的进度
             */
            @Override
            protected void onProgressUpdate(Void... values)
            {
//                System.out.println("onProgressUpdate");
                super.onProgressUpdate(values);
                mTextView.setText("loading...");
            }

            /**
             * 当任务执行之前开始调用此方法，可以在这里显示进度对话框
             */
            @Override
            protected void onPreExecute()
            {
//                System.out.println("onPreExecute");
                super.onPreExecute();
                mTextView.setText("正在检测版本");
            }

            /**
             * 此方法在主线程执行，任务执行的结果作为此方法的参数返回
             */
            protected void onPostExecute(Boolean result)
            {

                if (jsonObject != null)
                {
                    if (checkCode(jsonObject))
                    {
                        Gloable.getInstance().setNeedUpdate(Boolean.TRUE);
//                         path = jsonObject.getString("downurl");
                        try
                        {
                            Gloable.getInstance().setDownPath(jsonObject.getString("downurl"));
                            Gloable.getInstance().setApkRemark(jsonObject.getString("remark"));
                            Gloable.getInstance().setApkVersion(jsonObject.getString("version"));
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        Gloable.getInstance().setNeedUpdate(Boolean.FALSE);
                    }
                    gotoMainActivity();
                }
                else
                {
                    Gloable.getInstance().setNeedUpdate(Boolean.FALSE);
                    openDialog("当前网络不可用,无法检测版本,是否继续?");
                }
            }
        }.execute();
    }

    private boolean checkCode(JSONObject jsonObject)
    {
        if (jsonObject != null)
        {
            String versionStr;
            try
            {
                byte[] versionCode = Base64.decode(getVersionCode().toCharArray());
                versionStr = new String(versionCode);
//                System.out.println(Integer.parseInt(versionStr));
                if (!jsonObject.getString("version").equals(""))
                {
                    if (Integer.parseInt(jsonObject.getString("version")) >
                        Integer.parseInt(versionStr))
                    {
                        Gloable.getInstance().setNeedUpdate(true);
                    }
                    else
                    {
                        Gloable.getInstance().setNeedUpdate(false);
                    }
                }
                else
                {
                    Gloable.getInstance().setNeedUpdate(false);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            Gloable.getInstance().setNeedUpdate(false);
        }
        return Gloable.getInstance().getNeedUpdate();
    }

    /**
     * Method gotoMainActivity ...
     */
    //    启动主页
    private void gotoMainActivity()
    {
//        startService(new Intent(ActivityRun.this, AutoLoadAllStock.class));

        Intent intent = new Intent(ActivityRun.this, ActivitySpecialMain.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    /**
     * Method openDialog ...
     *
     * @param msg of type String
     */
    protected void openDialog(String msg)
    {
        if (a.hasWindowFocus())
        {
            new AlertDialog.Builder(ActivityRun.this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("系统提示")
                .setMessage(msg)
                .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener()
                {
                    /**
                     * Method onClick ...
                     *
                     * @param dialog of type DialogInterface
                     * @param whichButton of type int
                     */
                    public void onClick(DialogInterface dialog, int whichButton)
                    {
                        gotoMainActivity();
                    }
                })
                .setNegativeButton("取消",
                    new DialogInterface.OnClickListener()
                    {
                        /**
                         * Method onClick ...
                         *
                         * @param dialog of type DialogInterface
                         * @param whichButton of type int
                         */
                        public void onClick(DialogInterface dialog, int whichButton)
                        {
                            System.exit(0);
                        }
                    })
                .show();
        }
    }
}
