package com.imageco.special.itake.activityImpl;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import com.imageco.special.R;

import com.imageco.special.itake.gloable.Constant;
import com.imageco.special.itake.gloable.Gloable;
import com.imageco.special.util.DataOperation;
import com.imageco.special.util.sql.DBLocalService;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static com.imageco.special.itake.gloable.Constant.FILENAME;

/**
 * Class ActivityResult ...
 *
 * @author Administrator Created on 11-10-22
 */
public class ActivityResult extends Activity
{
    /**
     * Called when the activityImpl is first created.
     */
    private Button sharemessageBut;

    /**
     * Field backmainBut
     */
    private Button backmainBut;

    /**
     * Field qrcodeTextView
     */
    private TextView qrcodeTextView;

    /**
     * Field db
     */
    private DBLocalService db;

    /**
     * Field qrCode
     */
    private String qrCode;

    private JSONObject mjson;

    private String addToFile;

    /**
     * Method onCreate ...
     *
     * @param savedInstanceState of type Bundle
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.photoresult);

        Bundle bundle = new Bundle();
        bundle = this.getIntent().getExtras();
        qrCode = bundle.getString("qrCode");

        qrcodeTextView = (TextView) findViewById(R.id.qrcode);
        qrcodeTextView.setTextColor(0xff000000);
        qrcodeTextView.setText(qrCode);

        if (Gloable.getInstance().getNewHistory())
        {
            addToFile = qrCode;
            db = new DBLocalService(this);
            ContentValues values = new ContentValues();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd  HH:mm:ss     ");
            Date curDate = new Date(System.currentTimeMillis());//获取当前时间
            String dateString = formatter.format(curDate);
            try
            {
                mjson = new JSONObject();
                mjson.put(Constant.FILECONTANT, addToFile);
                mjson.put(Constant.FILEDATE, dateString);
            }
            catch (JSONException e)
            {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                System.out.println("创建json失败");
            }
//        values.put("name", dateString);
//        values.put("tel", qrCode);
//
//        db.insert(db.TABLE_CONTACTS, null, values);
            ArrayList<JSONObject> arrayList = new ArrayList<JSONObject>();
            arrayList.add(mjson);
            int historyCount;
            historyCount = DataOperation.readFile(FILENAME).size();//8
//        System.out.println("================="+historyCount);
            if (historyCount < 20)
            {
                DataOperation.addArrayMap2File(FILENAME, arrayList, Activity.MODE_PRIVATE);
            }
            else
            {
                //大于20条时
                DataOperation.deleteFileItemInPos(Constant.FILENAME, 0);
                DataOperation.addArrayMap2File(FILENAME, arrayList, Activity.MODE_PRIVATE);
            }
        }
        sharemessageBut = (Button) findViewById(R.id.sharemessgebutton);
        sharemessageBut.setOnClickListener(new View.OnClickListener()
        {
            /**
             * Method onClick ...
             *
             * @param v of type View
             */
            public void onClick(View v)
            {
                Uri uri = Uri.parse("smsto:");
                Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                i.putExtra("sms_body", qrCode);
                startActivity(i);
            }
        });
        backmainBut = (Button) findViewById(R.id.backmainbutton);
        backmainBut.setOnClickListener(new View.OnClickListener()
        {
            /**
             * Method onClick ...
             *
             * @param v of type View
             */
            public void onClick(View v)
            {
//                Intent intent = new Intent();
//                intent.setClass(ActivityResult.this, ActivitySpecialMain.class);
//                startActivity(intent);
                ActivityResult.this.finish();
            }
        });
    }
}
