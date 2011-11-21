package com.imageco.special.itake.activityImpl;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.imageco.special.R;

import com.imageco.special.itake.gloable.Constant;
import com.imageco.special.itake.viewcus.MyEditText;
import com.imageco.special.util.decoding.Base64Encoder;
import com.imageco.special.util.net.Conn;
import org.json.JSONObject;

import static com.imageco.special.itake.userOption.CollectionOperation.getIMEI;

/**
 * Class ActivityMessage ...
 *
 * @author Administrator
 *         Created on 11-10-22
 */
@SuppressWarnings({"unchecked"})
public class ActivityMessage extends Activity {

    /**
     * Field confirmBtn
     */
    private Button mButton;
    private MyEditText mContent;
    private MyEditText mContact;
    private AlertDialog aldlg, aldg2;
    private String msg = "";
    private String toastMsg = "";

    /**
     * Called when the activityImpl is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.leavemessage);

        mButton = (Button) this.findViewById(R.id.mButton);
        mContent = (MyEditText) this.findViewById(R.id.mContent);
        mContact = (MyEditText) this.findViewById(R.id.mContact);

        mButton.getText();

        mButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String conStr;
                String contactStr;
                Boolean doit;
                conStr = mContent.getText().toString();//留言内容
                contactStr = mContact.getText().toString();//联系方式
                //留言或联系方式为空 false
                //留言超过140 false
                if (conStr.length() > 140) {
                    doit = Boolean.FALSE;
                    toastMsg = "留言内容需小于140个字";
                } else if (conStr.equals("") || contactStr.equals("")) {
                    doit = Boolean.FALSE;
                    toastMsg = "留言内容或者联系方式不能为空";
                } else {
                    doit = Boolean.TRUE;
                    toastMsg = "";
                }
                if (doit) {
                    sendUserAdvice(conStr, contactStr);
                } else {
                    Toast.makeText(ActivityMessage.this, toastMsg, Toast.LENGTH_SHORT).show();
                }
            }
        });


        /**
         * Method sendUserAdvice ...
         *
         * @param contant of type String
         * @param contact of type String
         */
    }

    private void sendUserAdvice(final String contant, final String contact) {
//        String pathStr = "msg=" + getMessage() + "&contact=" + getContact() + "&imei=" + getIMEI();
//        Conn.execute(Constant.URL_TRACK_BASE + Constant.URL_TRACK_ACTION + pathStr);

        new AsyncTask<Void, Void, Boolean>() {

            /**
             * Method doInBackground ...
             *
             * @param params of type Void...
             * @return Boolean
             */
            //Base64Encoder.encode(getMobileType(), "GBK")
            @Override
            protected Boolean doInBackground(Void... params) {
                Boolean result = false;
                try {
                    String pathStr;
//                    System.out.println(contant);

                    pathStr = "msg=" + Base64Encoder.encode(contant, "GBK") + "&contact=" + Base64Encoder.encode(contact, "GBK") + "&imei=" + getIMEI();

                    JSONObject json;
                    json = Conn.execute(Constant.URL_TRACK_BASE + Constant.URL_TRACK_MSG + pathStr);
                    if (!(json == null)) {//联网OK
//1成功
//{"result":"0000","msg":"发送成功"}
//2失败
//{“result”:”7777”,”msg”.”超过次数限制”}
//{"result":"8888","msg":"参数有误"}
//{"result":"9999","msg":"插入数据异常或重复提交数据"}
//如果是8888，9999，则再请求发送一次
                        String resultString = json.getString("result");
                        if (resultString.equalsIgnoreCase("0000")) {
                            msg = "发送成功";
                            result = Boolean.TRUE;
                        } else if (resultString.equals("7777")) {
                            msg = "超过次数限制";
                            result = Boolean.FALSE;
                        } else if (resultString.equals("8888")) {
                            msg = "参数有误";
                            result = Boolean.FALSE;
                        } else if (resultString.equals("9999")) {
                            result = Boolean.FALSE;
                            msg = "插入数据异常或重复提交数据";
                        }

                    } else {
                        msg = "网络不通";
                        result = Boolean.FALSE;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return result;
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                if (aBoolean) {
                    mContent.setText("");
                    mContact.setText("");
//                    Toast.makeText(ActivityMessage.this, "发送成功", Toast.LENGTH_SHORT);
                    aldlg = new AlertDialog.Builder(ActivityMessage.this)
                            .setTitle("温馨提示")
                            .setMessage("发送成功")
                            .setNeutralButton("确定",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            //点击"取消"按钮之后退出程序
                                            msg = "";
                                            aldlg.dismiss();
                                        }
                                    }
                            )
                            .create();//创建
                    aldlg.show();//显示

                } else {
                    aldg2 = new AlertDialog.Builder(ActivityMessage.this)
                            .setTitle("温馨提示")
                            .setMessage("发送失败: " + msg)
                            .setNeutralButton("确定",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            //点击"取消"按钮之后退出程序
                                            msg = "";
                                            aldg2.dismiss();

                                        }
                                    }
                            )
                            .create();//创建
                    aldg2.show();//显示
                }

            }
        }.execute();


    }


}
