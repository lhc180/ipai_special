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
import com.imageco.itake.activityGameBase.ActivityBaseGame;
import com.imageco.util.net.Conn;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA. User: OYQX Date: 11-11-12 Time: 下午1:55
 */
public class ActivityPay extends ActivityBaseGame
{
    private String phonenumber;

    private String backresult;

    private String backmsg;

    private JSONObject mjson;

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

    private AlertDialog alert_show;

    private AlertDialog alert_mustbePhone;

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
        String type = "";
        if (GameGlo.getInstance().getPayType().equals("a"))
        {
            type = "一等奖";
        }
        else if (GameGlo.getInstance().getPayType().equals("b"))
        {
            type = "二等奖";
        }
        text_result.setText(type);
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
                getNumber();
                sendPhoneNum();
            }
        });
    }

    /**
     * Method sendPhoneNum ...
     */
    private void sendPhoneNum()
    {

        postNum(phonenumber, GameGlo.getInstance().getPayType());
    }

    @Override protected void onResume()
    {
        GameGlo.getInstance().setDissmissAble(Boolean.TRUE);
        super
            .onResume();    //To change body of overridden methods use File | Settings | File Templates.
    }

    /**
     * Method postNum ...
     *
     * @param num of type String
     * @param type of type String
     */
    private void postNum(final String num, final String type)
    {
        //noinspection unchecked
        new AsyncTask<Void, Void, Boolean>()
        {
            @Override protected void onPostExecute(Boolean aBoolean)
            {

                if (aBoolean)
                {
                    if (backresult != null && backmsg != null)
                    {

                        alert_show = new AlertDialog.Builder(ActivityPay.this)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setTitle("提示")
                            .setMessage(backmsg)
                            .setPositiveButton("ok",
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

                                        alert_show.dismiss();
                                        System.exit(0);
                                    }
                                })
                            .show();
                    }
                }
                super.onPostExecute(
                    aBoolean);    //To change body of overridden methods use File | Settings | File Templates.
            }

            @Override protected Boolean doInBackground(Void... params)
            {
                if (phonenumber != null)
                {
                    String pathStr =
                        "http://222.44.51.34/aipai/interface/sendSms.php?mobile_number=" + num +
                            "&level=" + type;

                    mjson =
                        Conn.execute(pathStr);
                    if (mjson != null)
                    {
                        try
                        {
                            backresult = mjson.getString("result");
                            backmsg = mjson.getString("msg");
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }

                    return Boolean.TRUE;
                }
                else
                {
                    return Boolean.FALSE;
                }
            }
        }.execute();
    }

    private void getNumber()
    {
        String number;
        number = editText_phone.getText().toString();
        String regExp = "^((13[0-9])|(15[0-9])|(18[0,2-9]))\\d{8}$";

        Pattern p = Pattern.compile(regExp);

        Matcher m = p.matcher(number);
        if (m.matches())
        {
            phonenumber = number;
        }
        else
        {
            alert_mustbePhone = new AlertDialog.Builder(ActivityPay.this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("温馨提示")
                .setMessage("请输入正确的手机号")
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
                            editText_phone.setText("");
                            alert_mustbePhone.dismiss();
                        }
                    })

                .show();
        }
    }
}
