package com.imageco.special.itake.activityImpl;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.imageco.special.R;


/**
 * Class ActivityHelp ...
 *
 * @author Administrator
 *         Created on 11-10-22
 */
public class ActivityHelp extends Activity {
    /**
     * Field title
     */
    private String[] title;
    /**
     * Field content
     */
    private String[] content;
    /**
     * Field mLinearLayout
     */
    private LinearLayout mLinearLayout;


    /**
     * Method onCreate ...
     *
     * @param savedInstanceState of type Bundle
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.help);
        mLinearLayout = (LinearLayout) findViewById(R.id.layout);
        title = getResources().getStringArray(R.array.help_title);
        content = getResources().getStringArray(R.array.help_content);

//        添加视图
        for (int i = 0; i < title.length; i++) {

//            System.out.println(title[i]);
//            System.out.println(content[i]);

            TextView textView_title = new TextView(ActivityHelp.this);
            textView_title.setText(title[i]);
             textView_title.setLineSpacing(17, (float) 1.2);
            textView_title.setTextColor(Color.parseColor("#000000"));

            TextView textView_content = new TextView(ActivityHelp.this);
            textView_content.setText(content[i]);
            textView_content.setLineSpacing(17, (float) 1.2);
            textView_content.setTextColor(Color.parseColor("#000000"));
//            分割线
            View line = new View(ActivityHelp.this);
            line.setBackgroundColor(Color.parseColor("#000000"));
            line.setMinimumHeight(1);

            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.FILL_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT

            );

            mLinearLayout.addView(textView_title, p);
            mLinearLayout.addView(textView_content, p);
            mLinearLayout.addView(line, p);


        }
    }

}
