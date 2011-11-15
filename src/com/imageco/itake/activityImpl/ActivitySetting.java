package com.imageco.itake.activityImpl;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import com.imageco.R;
import com.imageco.itake.gloable.SETTING;
import com.imageco.itake.viewcus.CheckBoxView;

/**
 * Class ActivitySetting ...
 *
 * @author Administrator Created on 11-10-22
 */
public class ActivitySetting extends Activity
{
    /**
     * Field confirmBtn
     */
    private Button confirmBtn;

    /**
     * Field cancelBtn
     */
    private Button cancelBtn;

    /**
     * Field sound
     */
    public static boolean sound = SETTING.SOUND;

    /**
     * Field shake
     */
    public static boolean shake = SETTING.SHAKE;

    /**
     * Field paste
     */
    public static boolean paste = SETTING.PASTE;

    /**
     * Field flashlight
     */
    public static boolean flashlight = SETTING.FLASHLIGHT;

    /**
     * Field soundCheckBox
     */
    private CheckBox soundCheckBox;

    /**
     * Field shockCheckBox
     */
    private CheckBox shockCheckBox;

    /**
     * Field copyCheckBox
     */
    private CheckBox copyCheckBox;

    /**
     * Field flashlightcheckBox
     */
    private CheckBox flashlightcheckBox;

    /**
     * Field Tempsound
     */
    private boolean Tempsound = false;

    /**
     * Field Tempshock
     */
    private boolean Tempshock = false;

    /**
     * Field Tempcopy
     */
    private boolean Tempcopy = false;

    /**
     * Field Tempflashlight
     */
    private boolean Tempflashlight = false;

    /**
     * Field mContext
     */
    private Context mContext;

    /**
     * Field mCheckBoxView
     */
    private CheckBoxView mCheckBoxView;

    /**
     * Called when the activityImpl is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup);
        mContext = this.getApplicationContext();
        initCheck();
        initButton();
    }

    /**
     * Method initButton ...
     */
    private void initButton()
    {
        confirmBtn = (Button) findViewById(R.id.confirmbutton);
//        cancelBtn = (Button) findViewById(R.id.cancelbutton);

        confirmBtn.setOnClickListener(new Button.OnClickListener()
        {
            /**
             * Method onClick ...
             *
             * @param v of type View
             */
            public void onClick(View v)
            {
                System.out.println("confirm clicked");
//                System.out.println("sound===================" + sound);
//                System.out.println("shake===================" + shake);
//                System.out.println("paste===================" + paste);
//                System.out.println("flashlight==============" + flashlight);
                saveConfig(sound, shake, paste, flashlight);
                //重置全局变量
                SETTING.SOUND = sound;
                SETTING.SHAKE = shake;
                SETTING.PASTE = paste;
                SETTING.FLASHLIGHT = flashlight;
                ActivitySetting.this.finish();
            }
        });
    }

    /**
     * Method saveConfig ...
     *
     * @param sound of type Boolean
     * @param shake of type Boolean
     * @param paste of type Boolean
     * @param flashlight of type Boolean
     */
    //保存配置
    private void saveConfig(Boolean sound, Boolean shake, Boolean paste, Boolean flashlight)
    {
        SharedPreferences uiState = getSharedPreferences("setting", MODE_PRIVATE);
        SharedPreferences.Editor editor = uiState.edit();

        editor.putBoolean("sound", sound);
        editor.putBoolean("shake", shake);
        editor.putBoolean("paste", paste);
        editor.putBoolean("flashlight", flashlight);

        editor.commit();
    }

    /**
     * Method getSound returns the sound of this ActivitySetting object.
     *
     * @return the sound (type Boolean) of this ActivitySetting object.
     */
    private Boolean getSound()
    {
        Boolean status;
        SharedPreferences settings = getSharedPreferences("setting", MODE_PRIVATE);
        // 取得值.
        status = settings.getBoolean("sound", false);
        return status;
    }

    /**
     * Method getShake returns the shake of this ActivitySetting object.
     *
     * @return the shake (type Boolean) of this ActivitySetting object.
     */
    private Boolean getShake()
    {
        Boolean status;
        SharedPreferences settings = getSharedPreferences("setting", MODE_PRIVATE);
        // 取得值.
        status = settings.getBoolean("shake", false);
        return status;
    }

    /**
     * Method getPaste returns the paste of this ActivitySetting object.
     *
     * @return the paste (type Boolean) of this ActivitySetting object.
     */
    private Boolean getPaste()
    {
        Boolean status;
        SharedPreferences settings = getSharedPreferences("setting", MODE_PRIVATE);
        // 取得值.
        status = settings.getBoolean("paste", false);
        return status;
    }

    /**
     * Method getFlashlight returns the flashlight of this ActivitySetting object.
     *
     * @return the flashlight (type Boolean) of this ActivitySetting object.
     */
    private Boolean getFlashlight()
    {
        Boolean status;
        SharedPreferences settings = getSharedPreferences("setting", MODE_PRIVATE);
        // 取得值.
        status = settings.getBoolean("flashlight", false);
        return status;
    }

    /**
     * Method initCheck ...
     */
    private void initCheck()
    {
        soundCheckBox = (CheckBox) findViewById(R.id.soundCheckBox);
        shockCheckBox = (CheckBox) findViewById(R.id.shockCheckBox);
        copyCheckBox = (CheckBox) findViewById(R.id.copyCheckBox);
        flashlightcheckBox = (CheckBox) findViewById(R.id.flashlightcheckBox);
        mCheckBoxView = (CheckBoxView) findViewById(R.id.savepsd);
//        System.out.println(getPaste() + "" + getSound());
        mCheckBoxView.setClickListener(new View.OnClickListener()
        {
            /**
             * Method onClick ...
             *
             * @param v of type View
             */
            public void onClick(View v)
            {
                System.out.println("checkboxview checked");
            }
        });
        //声音
        if (SETTING.SOUND)
        {
            soundCheckBox.setChecked(true);
        }
        else
        {
            soundCheckBox.setChecked(false);
        }

        soundCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            /**
             * Method onCheckedChanged ...
             *
             * @param buttonView of type CompoundButton
             * @param isChecked of type boolean
             */
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                boolean isChecked)
            {
                sound = isChecked;
            }
        });
        //震动
        if (SETTING.SHAKE)
        {
            shockCheckBox.setChecked(true);
        }
        else
        {
            shockCheckBox.setChecked(false);
        }

        shockCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            /**
             * Method onCheckedChanged ...
             *
             * @param buttonView of type CompoundButton
             * @param isChecked of type boolean
             */
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                boolean isChecked)
            {
                shake = isChecked;
            }
        });

        //粘贴
        if (SETTING.PASTE)
        {
            copyCheckBox.setChecked(true);
        }
        else
        {
            copyCheckBox.setChecked(false);
        }
        copyCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            /**
             * Method onCheckedChanged ...
             *
             * @param buttonView of type CompoundButton
             * @param isChecked of type boolean
             */
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                boolean isChecked)
            {
                paste = isChecked;
            }
        });

        //闪光灯
        if (SETTING.FLASHLIGHT)
        {
            flashlightcheckBox.setChecked(true);
        }
        else
        {
            flashlightcheckBox.setChecked(false);
        }
        flashlightcheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            /**
             * Method onCheckedChanged ...
             *
             * @param buttonView of type CompoundButton
             * @param isChecked of type boolean
             */
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                boolean isChecked)
            {
                flashlight = isChecked;
            }
        });
    }
}
