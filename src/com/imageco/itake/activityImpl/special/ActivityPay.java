package com.imageco.itake.activityImpl.special;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.imageco.R;
import com.imageco.itake.activityGameBase.ActiviyBaseGame;
import com.imageco.itake.gloable.Constant;
import com.imageco.util.net.Conn;
import org.json.JSONException;
import org.json.JSONObject;

import static com.imageco.itake.userOption.CollectionOperation.getChannel;
import static com.imageco.itake.userOption.CollectionOperation.getDisplay;
import static com.imageco.itake.userOption.CollectionOperation.getOs;

/**
 * Created by IntelliJ IDEA. User: OYQX Date: 11-11-12 Time: 下午1:55
 */
public class ActivityPay extends ActiviyBaseGame
{
    private String strType;

    private GameGlo mGameGlo = GameGlo.getInstance();

    private TextView text_result;

    private Button btn_sent;

    private EditText editText_phone;

    private ProgressDialog alertDia_wait;

    private AlertDialog alertDialog_selectType;

    private ProgressDialog sendBar;

    private AlertDialog alertDialog_selectType2;

    private JSONObject mJson;

    private String number;

    private String type;

    /**
     * Method onCreate ...
     *
     * @param savedInstanceState of type Bundle
     */
    @Override public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(
            savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        setContentView(R.layout.pay);
    }

    /**
     * Method getData ...
     */
    @Override protected void getData()
    {
        strType = mGameGlo.getPayType();
    }

    /**
     * Method initItem ...
     */
    @Override protected void initItem()
    {
        text_result = (TextView) this.findViewById(R.id.text_result);
        btn_sent = (Button) this.findViewById(R.id.btn_sent);
        editText_phone = (EditText) this.findViewById(R.id.editText_phone);
    }

    /**
     * Method setOnListener ...
     */
    @Override protected void setOnListener()
    {
        btn_sent.setOnClickListener(new View.OnClickListener()
        {
            /**
             * Method onClick ...
             *
             * @param v of type View
             */
            @Override public void onClick(View v)
            {
                sendPhoneNum();
            }
        });
    }

    /**
     * Method sendPhoneNum ...
     */
    private void sendPhoneNum()
    {

        //noinspection unchecked
        new AsyncTask<Void, Void, Boolean>()
        {
            /**
             * Method onPreExecute ...
             */
            @Override
            protected void onPreExecute()
            {
                alertDia_wait = ProgressDialog.show
                    (
                        ActivityPay.this,
                        "请等待...",
                        "正在检测版本" +
                            "...",
                        true
                    );
            }

            /**
             * Method onPostExecute ...
             *
             * @param aBoolean of type Boolean
             */
            @Override
            protected void onPostExecute(Boolean aBoolean)
            {
                alertDia_wait.dismiss();
                if (aBoolean && mJson != null)
                {
                    if (false)//返回a.b数量OK
                    {
                        alertDialog_selectType = new AlertDialog.Builder(ActivityPay.this)
                            .setTitle("温馨提示")
                            .setMessage("恭喜,A奖品尚有剩余.")
                            .setPositiveButton("领取",
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
                                        alertDialog_selectType.dismiss();
                                        sendBar = new ProgressDialog(ActivityPay.this);
                                        sendBar.setMessage("正在发送请求...");
                                        sendBar.setProgressStyle(
                                            ProgressDialog.STYLE_SPINNER);
                                        sendBar.show();
                                        postNum(number, type);//启线程发送message更新download
                                    }
                                })
                            .create();//创建
                        alertDialog_selectType.show();//显示
                    }
                    else
                    {//a不够显示b
                        alertDialog_selectType2 = new AlertDialog.Builder(ActivityPay.this)
                            .setTitle("温馨提示")
                            .setMessage("当天的A奖品已领完.")
                            .setPositiveButton("领取",
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
                                        alertDialog_selectType2.dismiss();
                                        sendBar = new ProgressDialog(ActivityPay.this);
                                        sendBar.setMessage("正在发送请求...");
                                        sendBar.setProgressStyle(
                                            ProgressDialog.STYLE_SPINNER);
                                        sendBar.show();
                                        postNum(number, type);//启线程发送message更新download
                                    }
                                })
                            .create();//创建
                        alertDialog_selectType2.show();//显示
                    }
                }
            }

            /**
             * Method doInBackground ...
             *
             * @param params of type Void...
             * @return Boolean
             */
            @Override
            protected Boolean doInBackground
            (Void... params)
            {
                String pathStr =
                    "channel_id=" + getChannel() + "&display=" + getDisplay() + "&os=" +
                        getOs();
                mJson =
                    Conn.execute(
                        Constant.URL_TRACK_BASE + Constant.URL_TRACK_UPDATE + pathStr);
                if (mJson != null)
                {
                    try
                    {
                        String path = mJson.getString("downurl");//
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
                return true;
            }
        }.execute();
    }

    /**
     * Method postNum ...
     *
     * @param num of type String
     * @param type of type String
     */
    private void postNum(String num, String type)
    {
    }
}
