package com.imageco.itake.viewcus;

/**
 * Created by IntelliJ IDEA.
 * User: OYQX
 * Date: 11-10-21
 * Time: 下午9:20
 */

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 单行文本跑马灯控件
 *
 * @author admin
 */

public class ListTextView extends TextView {

    /**
     * Constructor ListTextView creates a new ListTextView instance.
     *
     * @param context of type Context
     */
    public ListTextView(Context context) {
        super(context);
// TODO Auto-generated constructor stub
    }

    /**
     * Constructor ListTextView creates a new ListTextView instance.
     *
     * @param context of type Context
     * @param attrs   of type AttributeSet
     */
    public ListTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Constructor ListTextView creates a new ListTextView instance.
     *
     * @param context  of type Context
     * @param attrs    of type AttributeSet
     * @param defStyle of type int
     */
    public ListTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * Method isFocused returns the focused of this ListTextView object.
     *
     * @return the focused (type boolean) of this ListTextView object.
     */
    @Override
    public boolean isFocused() {
        return true;
    }

}