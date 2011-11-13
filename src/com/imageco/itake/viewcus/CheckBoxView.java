package com.imageco.itake.viewcus;

/**
 * Created by IntelliJ IDEA.
 * User: OYQX
 * Date: 11-10-21
 * Time: 下午12:41
 */

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.imageco.R;

/**
 * Class CheckBoxView ...
 *
 * @author Administrator
 *         Created on 11-10-22
 */
public class CheckBoxView extends RelativeLayout implements
        View.OnClickListener {
    /**
     * Field checkImage
     */
    private ImageView checkImage;
    /**
     * Field layout
     */
    private RelativeLayout layout;
    /**
     * Field flag
     */
    private boolean flag = false;
    /**
     * Field vcOnClickListener
     */
    private View.OnClickListener vcOnClickListener = null;

    /**
     * Constructor CheckBoxView creates a new CheckBoxView instance.
     *
     * @param context  of type Context
     * @param attrs    of type AttributeSet
     * @param defStyle of type int
     */
    public CheckBoxView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    /**
     * Constructor CheckBoxView creates a new CheckBoxView instance.
     *
     * @param context of type Context
     * @param attrs   of type AttributeSet
     */
    public CheckBoxView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    /**
     * Constructor CheckBoxView creates a new CheckBoxView instance.
     *
     * @param context of type Context
     */
    public CheckBoxView(Context context) {
        super(context);
        initView(context);
    }

    /**
     * Method initView ...
     *
     * @param context of type Context
     */
    private void initView(Context context) {
        layout = new RelativeLayout(context);
        layout.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.checkbox_bg));

        checkImage = new ImageView(context);
        checkImage.setImageDrawable(getResources().getDrawable(
                R.drawable.checkbox_swich));

        this.addView(layout);
        layout.addView(checkImage);

        setOnClickListener(this);
    }

    /**
     * Method setChecked sets the checked of this CheckBoxView object.
     *
     * @param checked the checked of this CheckBoxView object.
     */
    public void setChecked(boolean checked) {
        if (flag != checked) {
            flag = !checked;
            translate();
        }
    }

    /**
     * Method getChecked returns the checked of this CheckBoxView object.
     *
     * @return the checked (type boolean) of this CheckBoxView object.
     */
    public boolean getChecked() {
        return flag;
    }

    /**
     * Method translate ...
     */
    private void translate() {
        // (int fromXType, float fromXValue, int toXType, float toXValue, int
        // fromYType, float fromYValue, int toYType, float toYValue
        TranslateAnimation localTranslateAnimation = null;
        if (flag) {
            localTranslateAnimation = new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT, 0.5f,
                    Animation.RELATIVE_TO_PARENT, 0.0f,
                    Animation.RELATIVE_TO_PARENT, 0.0f,
                    Animation.RELATIVE_TO_PARENT, 0.0f);
            flag = false;
        } else {
            localTranslateAnimation = new TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT, 0.0f,
                    Animation.RELATIVE_TO_PARENT, 0.5f,
                    Animation.RELATIVE_TO_PARENT, 0.0f,
                    Animation.RELATIVE_TO_PARENT, 0.0f);
            flag = true;
        }

        localTranslateAnimation.setDuration(300);
        localTranslateAnimation.setFillAfter(true);
        AccelerateDecelerateInterpolator localAccelerateDecelerateInterpolator = new AccelerateDecelerateInterpolator();
        localTranslateAnimation
                .setInterpolator(localAccelerateDecelerateInterpolator);
        checkImage.startAnimation(localTranslateAnimation);

    }

    /**
     * Method onClick ...
     *
     * @param v of type View
     */
    @Override
    public void onClick(View v) {
        translate();
        vcOnClickListener.onClick(v);
    }

    /**
     * Method setClickListener sets the clickListener of this CheckBoxView object.
     *
     * @param paramOnClickListener the clickListener of this CheckBoxView object.
     */
    public void setClickListener(View.OnClickListener paramOnClickListener) {
        this.vcOnClickListener = paramOnClickListener;
    }
}
