package com.imageco.itake.activityImpl.special;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.imageco.R;
import com.imageco.itake.activityGameBase.ActivityBaseGame;

/**
 * Class ActivitySpecialMain ...
 *
 * @author Administrator Created on 11-10-22
 */
public class ActivitySpecialMain extends ActivityBaseGame
{
    /**
     * Field btnPhoto 拍照
     */
    private Button btnPhoto;

    /**
     * Field btnGame 游戏
     */
    private Button btnGame;

    /**
     * Field btnHistory  历史
     */
    private Button btnHistory;

    /**
     * Field btnHelp 帮助
     */
    private Button btnHelp;

    /**
     * Method onCreate ...
     *
     * @param savedInstanceState of type Bundle
     */
    @Override public void onCreate(Bundle savedInstanceState)
    {
        setContentView(R.layout.aipai_main);//first
        super.onCreate(savedInstanceState);
    }

    /**
     * Method onBackPressed ...重写返回
     */
    @Override
    public void onBackPressed()
    {
        new AlertDialog.Builder(ActivitySpecialMain.this)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setTitle("温馨提示")
            .setMessage("确定退出程序吗?")
            .setPositiveButton("是",
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
            .setNegativeButton("否",
                new DialogInterface.OnClickListener()
                {
                    /**
                     * Method onClick ...
                     *
                     * @param dialog of type DialogInterface
                     * @param which of type int
                     */
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        return;
                    }
                })
            .show();
    }

    /**
     * Method initItem ...查找组件
     */
    @Override
    protected void initItem()
    {
        btnPhoto = (Button) this.findViewById(R.id.btnphoto);
        btnGame = (Button) this.findViewById(R.id.btngame);
        btnHistory = (Button) this.findViewById(R.id.btnhistory);
        btnHelp = (Button) this.findViewById(R.id.btnhelp);

    }

    /**
     * Method setOnListener ...添加监听
     */
    @Override
    protected void setOnListener()
    {
        View.OnClickListener listen_click = new View.OnClickListener()
        {
            /**
             * Method onClick ...
             *
             * @param v of type View
             */
            @Override public void onClick(View v)
            {
                switch (v.getId())
                {
                    case R.id.btnphoto:
                        gotoPhotoForRsult(false);
                        break;
                    case R.id.btngame:
                        gotoGame();
                        break;
                    case R.id.btnhistory:
                        gotoHistory();
                        break;
                    case R.id.btnhelp:
                        gotoHelp();
                        break;
                    default:
                        break;
                }
            }
        };
        btnPhoto.setOnClickListener(listen_click);
        btnGame.setOnClickListener(listen_click);
        btnHistory.setOnClickListener(listen_click);
        btnHelp.setOnClickListener(listen_click);
    }


}