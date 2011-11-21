package com.imageco.special.itake.activityImpl.special;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.imageco.special.R;

import com.imageco.special.itake.activityGameBase.ActivityBaseGame;
import com.imageco.special.itake.gloable.Constant;
import com.imageco.special.util.net.Conn;
import org.json.JSONObject;

import static com.imageco.special.itake.userOption.CollectionOperation.getIMEI;

/**
 * Created by IntelliJ IDEA. User: OYQX Date: 11-11-11 Time: 下午3:05
 */
public class ActivityGame extends ActivityBaseGame
{
    private JSONObject mjson;

    /**
     * Field layoutDianshu 点数提示
     */
    private LinearLayout layout_text;

    /**
     * Field imaFirst 第一张
     */
    private ImageView imaFirst;

    /**
     * Field imaSecond
     */
    private ImageView imaSecond;

    /**
     * Field imaThird
     */
    private ImageView imaThird;

    /**
     * Field imafourth
     */
    private ImageView imaFourth;

    /**
     * Field imafifth
     */
    private ImageView imaFifth;

    /**
     * Field btn_continue 拍图
     */
    private Button btn_continue;

    /**
     * Field btn_pay 兑奖
     */
    private Button btn_pay;

    /**
     * Field btn_rule 规则
     */
    private Button btn_rule;

    /**
     * Field btn_quit 退出
     */
    private Button btn_quit;

    /**
     * Field text_result 点数
     */
    private TextView text_result;

    /**
     * Field text_starttip 开始提示
     */
    private TextView text_starttip;

    /**
     * Field listener  监听
     */
    private View.OnClickListener listen_click;

    /**
     * Field alert_assert
     */
    private AlertDialog alert_assert;

    private AlertDialog alert_equals;

    private AlertDialog alert_bigger;

    private AlertDialog alert_do_correct;

    /**
     * Field mGameGlo
     */
    private GameGlo mGameGlo = GameGlo.getInstance();

    /**
     * Field strResult
     */
    private String strResult = mGameGlo.getStrResult();

    /**
     * Field time
     */
    private int time = mGameGlo.getTimes();

    /**
     * Field resIdtemp1
     */
    private int resIdtemp1 = mGameGlo.getResIdtemp1();

    /**
     * Field resIdtemp2
     */
    private int resIdtemp2 = mGameGlo.getResIdtemp2();

    /**
     * Field resIdtemp3
     */
    private int resIdtemp3 = mGameGlo.getResIdtemp3();

    /**
     * Field resIdtemp4
     */
    private int resIdtemp4 = mGameGlo.getResIdtemp4();

    /**
     * Field resIdtemp5
     */
    private int resIdtemp5 = mGameGlo.getResIdtemp5();

    /**
     * Field intPos1
     */
    private int intPos1 = mGameGlo.getIntPos1();

    /**
     * Field intPos2
     */
    private int intPos2 = mGameGlo.getIntPos2();

    /**
     * Field intPos3
     */
    private int intPos3 = mGameGlo.getIntPos3();

    /**
     * Field intPos4
     */
    private int intPos4 = mGameGlo.getIntPos4();

    /**
     * Field intPos5
     */
    private int intPos5 = mGameGlo.getIntPos5();

    private TextView text_qianzui;

    private AlertDialog alert_quit;

    /**
     * Method onCreate ...
     *
     * @param savedInstanceState of type Bundle
     *
     * @see com.imageco.special.itake.activityGameBase.ActivityBaseGame#onCreate(Bundle)
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        setContentView(R.layout.game);
        super.onCreate(
            savedInstanceState);
    }

    /**
     * Method getData ...获取拍码数据
     *
     * @see com.imageco.special.itake.activityGameBase.ActivityBaseGame#getData()
     */
    protected void getData()
    {
        setData();
//        http://m.masafa.net
//        请访问以上网址下载正确软件版本。
//        BXING_10
    }

    /**
     * Method initItem ...查找组件
     */
    @Override protected void initItem()
    {
        layout_text = (LinearLayout) this.findViewById(R.id.layout_text);
        btn_continue = (Button) this.findViewById(R.id.btn_continue);
        btn_rule = (Button) this.findViewById(R.id.btn_rule);
        btn_quit = (Button) this.findViewById(R.id.btn_quit);
        btn_pay = (Button) this.findViewById(R.id.btn_pay);
        text_result = (TextView) this.findViewById(R.id.text_result);
        text_qianzui = (TextView) this.findViewById(R.id.text_qianzui);
        text_starttip = (TextView) this.findViewById(R.id.text_starttip);

        intPos1 = mGameGlo.getIntPos1();

        intPos2 = mGameGlo.getIntPos2();

        intPos3 = mGameGlo.getIntPos3();

        intPos4 = mGameGlo.getIntPos4();

        intPos5 = mGameGlo.getIntPos5();
        if (time > 0)
        {
            layout_text.setVisibility(View.VISIBLE);
            text_starttip.setVisibility(View.INVISIBLE);
            btn_continue.setText("继续拍码");
            int total = intPos1 + intPos2 + intPos3 + intPos4 + intPos5;
            //1:小于21点.2.正好21点.3:大于21点

            if (total == 21)
            {
                text_qianzui.setVisibility(View.VISIBLE);
                text_result.setText(total + "点");
                alert_equals = new AlertDialog.Builder(ActivityGame.this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("温馨提示")
                    .setMessage("恭喜你获得21点,获得一等奖")
                    .setPositiveButton("领奖去",
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
                                mGameGlo.setPayType("a");
                                gotoPay();
                            }
                        })
                    .show();
            }

            else if (total < 21)
            {
                text_qianzui.setVisibility(View.VISIBLE);
                text_result.setText(total + "点");
            }
            else if (total > 21)
            {
//                dismissAlert();
                text_qianzui.setVisibility(View.INVISIBLE);
                text_result.setText("你爆了!");
//                LinearLayout params;
//                params = new LinearLayout(this.getApplicationContext());
//                params.setHorizontalGravity(View.);
//                text_result.setLayoutParams(params);

                alert_bigger = new AlertDialog.Builder(ActivityGame.this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("温馨提示")
                    .setMessage("你的点数超出21.获得二等奖")
                    .setPositiveButton("好吧,给我奖品!",
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
                                gotoPay();
                            }
                        })
                    .setNeutralButton("重新开始",
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
                                resetData();
//                                refreshFinish();
                            }
//
                        }
                    )

                    .show();
            }
        }
        else
        {
//            text_starttip.setVisibility(View.VISIBLE);提示
        }
    }

    /**
     * Method setOnListener ...添加监听
     */
    @Override protected void setOnListener()
    {
        listen_click = new View.OnClickListener()
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
                    case R.id.btn_continue:
                        int total = intPos1 + intPos2 + intPos3 + intPos4 + intPos5;
                        int arrests = 21;
                        if (total > arrests)
                        {
                            new AlertDialog.Builder(ActivityGame.this)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .setTitle("温馨提示")
                                .setMessage("当前点数已经大于" + arrests + "点,确定需要继续吗?")
                                .setPositiveButton("我要继续拍!",
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
                                            gotoPhotoForRsult(true);
                                        }
                                    })
                                .setNegativeButton("不拍了,去领B奖品",
                                    new DialogInterface.OnClickListener()
                                    {
                                        /**
                                         * Method onClick ...
                                         *
                                         * @param dialog of type DialogInterface
                                         * @param which of type int
                                         */
                                        @Override
                                        public void onClick(DialogInterface dialog,
                                            int which)
                                        {
                                            gotoPay();
                                        }
                                    })
                                .show();
                        }
                        else
                        {
                            gotoPhotoForRsult(true);
                        }
                        break;
                    case R.id.btn_pay:
                        gotoPay();
                        break;
                    case R.id.btn_quit:
                        gotoQuit();
                        break;
                    case R.id.btn_rule:
                        gotoRule();
                        break;
                    default:
                        break;
                }
            }
        };

        btn_continue.setOnClickListener(listen_click);
        btn_pay.setOnClickListener(listen_click);
        btn_quit.setOnClickListener(listen_click);
        btn_rule.setOnClickListener(listen_click);
    }

    /**
     * Method gotoQuit ...退出
     */
    @Override protected void gotoQuit()
    {
        alert_assert = new AlertDialog.Builder(ActivityGame.this)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setTitle("温馨提示")
            .setMessage("确定强制退出程序吗,这样你不会获得任何奖励")
            .setPositiveButton("不要,退出",
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
                        alert_assert.dismiss();
                        resetData();
                    }
                })
            .setNegativeButton("领奖去.",
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
                        gotoPay();
                    }
                })
            .show();
    }

    /**
     * Method setData ...
     */
    private void setData()
    {
        imaFirst = (ImageView) this.findViewById(R.id.image_first);
        imaSecond = (ImageView) this.findViewById(R.id.image_second);
        imaThird = (ImageView) this.findViewById(R.id.image_third);
        imaFourth = (ImageView) this.findViewById(R.id.image_fourth);
        imaFifth = (ImageView) this.findViewById(R.id.image_fifth);
        String mDrawableName = strResult;
        if (strResult.length() > 2)
        {
            Integer intPos = Integer.parseInt(strResult.substring(strResult.lastIndexOf("_") + 1));
            int resID = getResources().getIdentifier(mDrawableName, "drawable", getPackageName());

//            System.out.println("intPos====================" + intPos);
//            System.out.println("intPos1====================" + intPos1);
//            System.out.println("intPos2====================" + intPos2);
//            System.out.println("intPos3====================" + intPos3);
//            System.out.println("intPos4====================" + intPos4);
//            System.out.println("intPos5====================" + intPos5);
            if (intPos == 11 || intPos == 12 || intPos == 13)
            {
                intPos = 10;//jqk=10点
            }

            switch (time)
            {
                case 0:
                    break;
                case 1:
                    mGameGlo.setResIdtemp1(resIdtemp1 = resID);
                    if (intPos == 1)
                    {
                        mGameGlo.setIntPos1(11);
                    }
                    else
                    {
                        mGameGlo.setIntPos1(intPos);
                    }
                    imaFirst.setImageResource(resIdtemp1);
                    break;
                case 2:
                    mGameGlo.setResIdtemp2(resIdtemp2 = resID);
                    if ((intPos1 + intPos) < 12)
                    {
                        if (intPos == 1)
                        {
                            mGameGlo.setIntPos2(11);
                        }
                        else
                        {
                            mGameGlo.setIntPos2(intPos);
                        }
                    }
                    else
                    {
                        mGameGlo.setIntPos2(intPos);
                    }
                    imaFirst.setImageResource(resIdtemp1);
                    imaSecond.setImageResource(resIdtemp2);
                    break;
                case 3:
                    mGameGlo.setResIdtemp3(resIdtemp3 = resID);
                    mGameGlo.setIntPos3(intPos);
                    if ((intPos1 + intPos2 + intPos) < 12)
                    {
                        if (intPos == 1)
                        {
                            mGameGlo.setIntPos3(11);
                        }
                        else
                        {
                            mGameGlo.setIntPos3(intPos);
                        }
                    }
                    else
                    {
                        mGameGlo.setIntPos3(intPos);
                    }
                    imaFirst.setImageResource(resIdtemp1);
                    imaSecond.setImageResource(resIdtemp2);
                    imaThird.setImageResource(resIdtemp3);
                    break;
                case 4:
                    mGameGlo.setResIdtemp4(resIdtemp4 = resID);
//                    mGameGlo.setIntPos4(intPos);
                    if ((intPos1 + intPos2 + intPos3 + intPos) < 12)
                    {
                        if (intPos == 1)
                        {
                            mGameGlo.setIntPos4(11);
                        }
                        else
                        {
                            mGameGlo.setIntPos4(intPos);
                        }
                    }
                    else
                    {
                        mGameGlo.setIntPos4(intPos);
                    }
                    imaFirst.setImageResource(resIdtemp1);
                    imaSecond.setImageResource(resIdtemp2);
                    imaThird.setImageResource(resIdtemp3);
                    imaFourth.setImageResource(resIdtemp4);
                    break;
                case 5:
                    mGameGlo.setResIdtemp5(resIdtemp5 = resID);
//                    mGameGlo.setIntPos5(intPos);
                    if ((intPos1 + intPos2 + intPos3 + intPos4 + intPos) < 12)
                    {
                        if (intPos == 1)
                        {
                            mGameGlo.setIntPos5(11);
                        }
                        else
                        {
                            mGameGlo.setIntPos5(intPos);
                        }
                    }
                    else
                    {
                        mGameGlo.setIntPos5(intPos);
                    }
                    imaFirst.setImageResource(resIdtemp1);
                    imaSecond.setImageResource(resIdtemp2);
                    imaThird.setImageResource(resIdtemp3);
                    imaFourth.setImageResource(resIdtemp4);
                    imaFifth.setImageResource(resIdtemp5);
                    break;
                default:
                    imaFirst.setImageResource(resIdtemp1);
                    imaSecond.setImageResource(resIdtemp2);
                    imaThird.setImageResource(resIdtemp3);
                    imaFourth.setImageResource(resIdtemp4);
                    imaFifth.setImageResource(resIdtemp5);
                    break;
            }
        }
    }

    /**
     * Method resetData ...数据重置
     */
    private void resetData()
    {

        alert_quit = new AlertDialog.Builder(ActivityGame.this)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setTitle("温馨提示")
            .setMessage("退出将消耗一次游戏数(每天10次)")
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

                        setTimes();
                    }
                })

            .show();
//        mGameGlo.setmGameGlo(null);
//        finish();
    }

    @Override protected void onResume()
    {
        if (GameGlo.getInstance().getDissmissAble())
        {
            dismissAlert();
            GameGlo.getInstance().setDissmissAble(Boolean.FALSE);
        }
        super
            .onResume();
    }

    private void dismissAlert()
    {
        if (alert_assert != null)
        {
            alert_assert.dismiss();
        }
        if (alert_equals != null)
        {
            alert_equals.dismiss();
        }
        if (alert_bigger != null)
        {
            alert_bigger.dismiss();
        }
        if (alert_do_correct != null)
        {
            alert_do_correct.dismiss();
        }
    }

    /**
     * Method onActivityResult ...
     *
     * @param requestCode of type int
     * @param resultCode of type int
     * @param data of type Intent
     */
    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == Constant.PHOTO_RESULT)
        {

            Bundle extras = data.getExtras();
            if (extras != null)
            {
                strResult = extras.getString("qrCode");

                if (!strResult.equals("") && strResult.contains("请访问以上网址下载正确软件版本") &&
                    strResult.contains("http://m.masafa.net"))
                {

                    strResult = strResult.substring(strResult.lastIndexOf("。") + 3);//排除\r\n
                    if (strResult.equals("14"))
                    {
                        strResult = "joker_14";
                    }
                    else if (strResult.equals("15"))
                    {
                        strResult = "joker_15";
                    }
                    else
                    {

                        strResult = strResult.toLowerCase();
                    }
                    mGameGlo.setTimes(++time);
                }
                else
                {
                    strResult = "";
                    alert_do_correct = new AlertDialog.Builder(ActivityGame.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("温馨提示")
                        .setMessage("请拍摄特制卡片以开始游戏")
                        .setPositiveButton("好,我拍",
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
                                    gotoPhotoForRsult(true);
                                }
//
                            })
                        .setNeutralButton("明白了,别烦我!",
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
                                    alert_do_correct.dismiss();
                                }
//
                            }
                        )
                        .show();
                }
                mGameGlo.setStrResult(strResult);
            }
            else
            {
                strResult = "";
            }
        }
    }

    private void setTimes()
    {
        //noinspection unchecked
        new AsyncTask<Void, Void, Boolean>()
        {
            @Override protected Boolean doInBackground(Void... params)
            {//http://222.44.51.34/aipai/interface/saveTimes.php?imei=3423532452345234
                String pathStr =
                    "http://222.44.51.34/aipai/interface/saveTimes.php?imei=" + getIMEI(false);

                mjson =
                    Conn.execute(pathStr);
                if (mjson != null)
                {

                    return Boolean.TRUE;
                }
                return Boolean.FALSE;
            }

            @Override protected void onPostExecute(Boolean aBoolean)
            {
                if (aBoolean)
                {
                    alert_quit.dismiss();
                    refreshFinish();
                }
            }
        }.execute();
    }
}
