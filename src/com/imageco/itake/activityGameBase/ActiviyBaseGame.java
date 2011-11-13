package com.imageco.itake.activityGameBase;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.imageco.R;
import com.imageco.itake.activityImpl.ActivityAboutUs;
import com.imageco.itake.activityImpl.ActivityHelp;
import com.imageco.itake.activityImpl.ActivityHistory;
import com.imageco.itake.activityImpl.ActivityPhoto;
import com.imageco.itake.activityImpl.special.ActivityGame;
import com.imageco.itake.activityImpl.special.ActivityPay;
import com.imageco.itake.activityImpl.special.ActivityRule;
import com.imageco.itake.activityImpl.special.ActivitySpecialMain;
import com.imageco.itake.gloable.Constant;
import org.json.JSONObject;

/**
 * Created by IntelliJ IDEA. User: OYQX Date: 11-11-12 Time: 上午11:23
 */
public class ActiviyBaseGame extends Activity
{
    private JSONObject mjson;

    private int times;

    private Context mActiviyBaseGame;

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
                break;
            case R.id.menu_item_aboutus:
                gotoAboutUs();
                break;
            case R.id.menu_item_help:
                gotoHelp();
                break;
            default:
                break;
        }
        return super.onMenuItemSelected(featureId,
            item);
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
     */
    protected void gotoPhotoForRsult()
    {

        Intent nextIntent = new Intent(this.getApplicationContext(), ActivityPhoto.class);
//        startActivity(nextIntent);
        startActivityForResult(nextIntent, Constant.PHOTO_RESULT);
//        finish();
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

                //发送地址1,是否联网-->2.次数是否够
                //获取mjson和times

                return Boolean.TRUE;
            }

            @Override protected void onPostExecute(Boolean aBoolean)
            {

                if (aBoolean)//doinbackground结束
                {
                    if (mjson != null)
                    {
                        if (times < 11)
                        {
                            Intent nextIntent =
                                new Intent(mActiviyBaseGame, ActivityGame.class);//需测试
                            startActivity(nextIntent);
                        }
                        else
                        {
                            //游戏次数超出通知
                        }
                    }
                    else
                    {
                        //联网通知
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
}