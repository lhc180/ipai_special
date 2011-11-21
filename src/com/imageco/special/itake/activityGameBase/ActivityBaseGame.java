package com.imageco.special.itake.activityGameBase;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.imageco.special.R;
import com.imageco.special.itake.activityImpl.ActivityAboutUs;
import com.imageco.special.itake.activityImpl.ActivityHelp;
import com.imageco.special.itake.activityImpl.ActivityHistory;
import com.imageco.special.itake.activityImpl.ActivityPhoto;
import com.imageco.special.itake.activityImpl.ActivitySetting;
import com.imageco.special.itake.activityImpl.special.ActivityGame;
import com.imageco.special.itake.activityImpl.special.ActivityPay;
import com.imageco.special.itake.activityImpl.special.ActivityRule;
import com.imageco.special.itake.activityImpl.special.ActivitySpecialMain;
import com.imageco.special.itake.activityImpl.special.GameGlo;
import com.imageco.special.itake.gloable.Constant;
import com.imageco.special.util.net.Conn;
import org.json.JSONException;
import org.json.JSONObject;

import static com.imageco.special.itake.userOption.CollectionOperation.getIMEI;

/**
 * Created by IntelliJ IDEA. User: OYQX Date: 11-11-12 Time: 上午11:23
 */
public class ActivityBaseGame extends Activity
{
    private JSONObject mjson;

    private int times;

    private Context mActiviyBaseGame;

    private AlertDialog alert_over;

    private AlertDialog alert_necessary;

    private String lefttimes;

    /**
     * Method onCreate ...
     *
     * @param savedInstanceState of type Bundle
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        mActiviyBaseGame = this.getApplicationContext();
        super.onCreate(savedInstanceState);
    }

    /**
     * Method onStart ...
     */
    @Override protected void onStart()
    {

        getData();
        initItem();
        setOnListener();
        handleData();
        super
            .onStart();    //To change body of overridden methods use File | Settings | File Templates.
    }

    /**
     * Method handleDate ...数据处理
     */
    protected void handleData()
    {

    }

    /**
     * Method onCreateOptionsMenu ...重写菜单
     *
     * @param menu of type Menu
     *
     * @return boolean
     */
    @Override public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return super.onCreateOptionsMenu(
            menu);    //To change body of overridden methods use File | Settings | File Templates.
    }

    /**
     * Method onMenuItemSelected ...
     *
     * @param featureId of type int
     * @param item of type MenuItem
     *
     * @return boolean
     */
    @Override public boolean onMenuItemSelected(int featureId, MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menu_item_home:
                gotoHome();
                break;
            case R.id.menu_item_photo:
//                gotoPhotoForRsult();//
                gotoPhotoForRsult(Boolean.FALSE);
                break;
            case R.id.menu_item_aboutus:
                gotoAboutUs();
                break;
            case R.id.menu_item_help:
                gotoSetting();
                break;
            default:
                break;
        }
        return super.onMenuItemSelected(featureId,
            item);
    }

    /**
     * Method gotoSetting ...设置
     */
    protected void gotoSetting()
    {
        Intent nextIntent = new Intent(this.getApplicationContext(), ActivitySetting.class);
        startActivity(nextIntent);
    }

    /**
     * Method gotoPhotoForRsult ...主页
     */
    protected void gotoHome()
    {
        Intent nextIntent = new Intent(this.getApplicationContext(), ActivitySpecialMain.class);
        startActivity(nextIntent);
    }

    /**
     * Method gotoPhotoForRsult ...拍照
     *
     * @param type 跳转方式
     */
    protected void gotoPhotoForRsult(boolean type)
    {
        Intent nextIntent = new Intent(this.getApplicationContext(), ActivityPhoto.class);
        if (type)
        {
            GameGlo.getInstance().setForResult(true);
            startActivityForResult(nextIntent, Constant.PHOTO_RESULT);
        }
        else
        {
            GameGlo.getInstance().setForResult(false);
            startActivity(nextIntent);
        }
    }

    /**
     * Method gotoGame ...游戏.先判断当天游戏次数是否有剩余
     */
    protected void gotoGame()
    {

        //noinspection unchecked
        new AsyncTask<Void, Void, Boolean>()
        {
            @Override protected Boolean doInBackground(Void... params)
            {
//http://222.44.51.34/aipai/interface/getTimes.php?imei=3423532452345234
                //发送地址1,是否联网-->2.次数是否够
                //获取mjson和times
//                String pathStr =
//                    "channel_id=" + getChannel() + "&display=" + getDisplay() + "&os=" + getOs();

                String pathStr =
                    "http://222.44.51.34/aipai/interface/getTimes.php?imei=" + getIMEI(false);

                mjson =
                    Conn.execute(pathStr);
                if (mjson != null)
                {
                    try
                    {
                        lefttimes = mjson.getString("times");
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }

                return Boolean.TRUE;
            }

            @Override protected void onPostExecute(Boolean aBoolean)
            {

                if (aBoolean)//doinbackground结束
                {
                    if (lefttimes != null)
                    {
                        GameGlo.getInstance().setLefttimes(Integer.parseInt(lefttimes));
                    }
                    if (mjson != null)
                    {
                        if (times > 0 || times == 0)
                        {
                            Intent nextIntent =
                                new Intent(mActiviyBaseGame, ActivityGame.class);//需测试
                            startActivity(nextIntent);
                        }
                        else
                        {
                            alert_over = new AlertDialog.Builder(ActivityBaseGame.this)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .setTitle("温馨提示")
                                .setMessage("该手机已经超出当天使用次数,无法游戏")

                                .setNeutralButton("OK",
                                    new DialogInterface.OnClickListener()
                                    {
                                        /**
                                         * Method onClick ...
                                         *
                                         * @param dialog of type DialogInterface
                                         * @param whichButton of type int
                                         */
                                        public void onClick(DialogInterface dialog,
                                            int whichButton)
                                        {
                                            alert_over.dismiss();
                                        }
//
                                    }
                                )

                                .show();
                        }
                    }
                    else
                    {
                        //联网通知
                        alert_necessary = new AlertDialog.Builder(ActivityBaseGame.this)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setTitle("温馨提示")
                            .setMessage("游戏必须联网以接收奖品")
                            .setPositiveButton("OK",
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
                                        alert_necessary.dismiss();
                                    }
                                })
                            .show();
                    }
                }
            }
        }.execute();
    }

    /**
     * Method gotoHistory ...历史
     */
    protected void gotoHistory()
    {
        Intent nextIntent = new Intent(this.getApplicationContext(), ActivityHistory.class);
        startActivity(nextIntent);
    }

    /**
     * Method gotoHelp ...帮助
     */
    protected void gotoHelp()
    {
        Intent nextIntent = new Intent(this.getApplicationContext(), ActivityHelp.class);
        startActivity(nextIntent);
    }

    /**
     * Method gotoAboutUs ...关于
     */
    protected void gotoAboutUs()
    {
        Intent nextIntent = new Intent(this.getApplicationContext(), ActivityAboutUs.class);
        startActivity(nextIntent);
    }

    /**
     * Method gotoPay ...兑奖
     */
    protected void gotoPay()
    {
        Intent nextIntent = new Intent(this.getApplicationContext(), ActivityPay.class);
        startActivity(nextIntent);
    }

    /**
     * Method gotoQuit ...退出
     */
    protected void gotoQuit()
    {

    }

    /**
     * Method gotoRule ...规则,说明
     */
    protected void gotoRule()
    {
        Intent nextIntent = new Intent(this.getApplicationContext(), ActivityRule.class);
        startActivity(nextIntent);
    }

    /**
     * Method initItem ...查找组件
     */
    protected void initItem()
    {
//        ActivityBaseGame.this.getCurrentFocus().setBackgroundResource(R.drawable.game_background);
    }

    /**
     * Method setOnListener ...添加监听
     */
    protected void setOnListener()
    {
    }

    /**
     * Method getData ...获取数据
     */
    protected void getData()
    {

    }

    /**
     * Method exitApp ...
     */
    protected void exitApp()
    {
        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        am.killBackgroundProcesses("com.imageco");
    }

    /**
     * Method refreshFinish ...
     */
    protected void refreshFinish()
    {
        GameGlo.resetInstance();
        finish();
    }
}