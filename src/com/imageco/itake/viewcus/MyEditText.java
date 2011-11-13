package com.imageco.itake.viewcus;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Class MyEditText ...
 *
 * @author Administrator
 * Created on 11-10-25
 */
public class MyEditText extends EditText {

    /**
     * Constructor MyEditText creates a new MyEditText instance.
     *
     * @param context of type Context
     * @param attrs of type AttributeSet
     */
    public MyEditText(Context context, AttributeSet attrs) {

        super(context, attrs);

    }

    /**
     * Method onDraw ...
     *
     * @param canvas of type Canvas
     */
    @Override

    protected void onDraw(Canvas canvas) {


        Paint paint = new Paint();

        paint.setStyle(Style.STROKE);

        paint.setStrokeWidth(2);

        if (this.isFocused() == true)

            paint.setColor(Color.parseColor("#88b430"));

        else

//            paint.setColor(Color.rgb(0, 173, 173));
            paint.setColor(Color.parseColor("#87CEFA"));

        canvas.drawRoundRect(new RectF( 2+this.getScrollX(), 2 + this.getScrollY(), this.getWidth() - 3 + this.getScrollX(), this.getHeight() + this.getScrollY() - 1), 3, 3, paint);

        super.onDraw(canvas);

    }

}
