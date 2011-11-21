package com.imageco.special.itake.adapterCus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.imageco.special.R;

import com.imageco.special.itake.activityImpl.ActivityResult;
import com.imageco.special.itake.gloable.Gloable;
import com.imageco.special.itake.gloable.HistoryListStatus;
import com.imageco.special.itake.viewcus.ListTextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class SettingAdapter ...
 *
 * @author Administrator Created on 11-10-22
 */
public class SettingAdapter extends BaseAdapter
{

//    private GestureDetector detector;

    /**
     * Field data
     */
    private ArrayList<HashMap<String, Object>> data;

    /**
     * Field context
     */
    private Context context;

    /**
     * Field positionList
     */
    private ArrayList<Integer> positionList;

//    private PhotoresultActivity nextcontext;

    private String text;

    private Class<ActivityResult> nextcontext;

    /**
     * Constructor SettingAdapter creates a new SettingAdapter instance.
     *
     * @param data of type ArrayList<HashMap<String, Object>>
     * @param context of type Context
     * @param positionList of type ArrayList<Integer>
     */
    public SettingAdapter(ArrayList<HashMap<String, Object>> data,
        Context context, ArrayList<Integer> positionList, Class<ActivityResult> nextcontext)
    {
        this.data = data;
        this.context = context;
        this.positionList = positionList;
        this.nextcontext = nextcontext;
    }

    /**
     * Method getCount returns the count of this SettingAdapter object.
     *
     * @return the count (type int) of this SettingAdapter object.
     */
    public int getCount()
    {
        return data.size();
    }

    /**
     * Method getItem ...
     *
     * @param position of type int
     *
     * @return Object
     */
    public Object getItem(int position)
    {
        return data.get(position);
    }

    /**
     * Method getItemId ...
     *
     * @param position of type int
     *
     * @return long
     */
    public long getItemId(int position)
    {
        return position;
    }

    /**
     * Method getView ...
     *
     * @param position of type int
     * @param convertView of type View
     * @param parent of type ViewGroup
     *
     * @return View
     */
    public View getView(int position, View convertView, ViewGroup parent)
    {

        LinearLayout layout;
        Holder holder;
        if (convertView != null)
        {
            layout = (LinearLayout) convertView;
            holder = (Holder) layout.getTag();
        }
        else
        {
            LayoutInflater inflater = LayoutInflater.from(context);
            layout = (LinearLayout) inflater.inflate(R.layout.list_item, null);
            holder = new Holder();
            holder.imageView = (ImageView) layout.getChildAt(3);
            holder.textView = (ListTextView) layout.getChildAt(0);
            holder.textView2 = (TextView) layout.getChildAt(1);
            layout.setTag(holder);
        }

        Integer resId = (Integer) data.get(position).get("itemselectedImage");// Integer
        holder.imageView.setImageResource(resId);
        text = (String) data.get(position).get("itemName");
        holder.textView.setText(text);
        holder.textView.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View v)
            {
//              Intent intent = new Intent();
                Bundle bundle = new Bundle();
                Intent intent = new Intent();
//                System.out.println("====================" + nextcontext);
                intent.setClass(context, nextcontext);
                bundle.putString("qrCode", text);
                intent.putExtras(bundle);
                Gloable.getInstance().setNewHistory(Boolean.FALSE);
                context.startActivity(intent);
            }
        });
        holder.textView2.setText((String) data.get(position).get("itemName2"));
        holder.imageView.setVisibility(View.INVISIBLE);
//        setOnTouchListener(holder.textView);

//        System.out.println("get viewImpl holder**********" + "position==" + position + "**statys is **" + HistoryListStatus.getInstance().getPositionStatus().get(position + "") + "*************************");
//
        if (HistoryListStatus.getInstance().getPositionStatus().get(position + "") &&
            HistoryListStatus.getInstance().getPositionStatus().get(position + "") != null)
        {
            holder.imageView.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.imageView.setVisibility(View.INVISIBLE);
        }

        return layout;
    }

    /**
     * Class Holder ...
     *
     * @author Administrator Created on 11-10-22
     */
    protected class Holder
    {
        /**
         * Field imageView
         */
        public ImageView imageView;

        /**
         * Field textView
         */
        public ListTextView textView;

        /**
         * Field textView2
         */
        public TextView textView2;
    }

//    private void setOnTouchListener(View v)
//    {
//        detector = new GestureDetector(new GestureCus(getInstance()));
//        v.setOnTouchListener(new View.OnTouchListener()
//        {
//            @Override public boolean onTouch(View v, MotionEvent event)
//            {
//                detector.onTouchEvent(event);
//
//                return false;
//            }
//        });
//    }
//
//    class GestureCus implements GestureDetector.OnGestureListener
//    {
//        public GestureCus(ApplicationContext instance)
//        {
//        }
//
//        @Override public boolean onDown(MotionEvent e)
//        {
//            return false;  //TODO
//        }
//
//        @Override public void onShowPress(MotionEvent e)
//        {
//            //TODO
//        }
//
//        @Override public boolean onSingleTapUp(MotionEvent e)
//        {
//            return false;  //TODO
//        }
//
//        @Override
//        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)
//        {
//            return false;  //TODO
//        }
//
//        @Override public void onLongPress(MotionEvent e)
//        {
//            //TODO
//        }
//
//        @Override
//        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
//        {
//            if (e1.getX() - e2.getX() > 50)
//            {
////           Toast.makeText(GestureCus.this, "左", LENGTH_SHORT).show();
//                Toast.makeText(ApplicationContext.getInstance(), "左", LENGTH_SHORT).show();
//                Gloable.getInstance().setDirection("左");
//
//                return true;
//            }
//            else if (e1.getX() - e2.getX() < -50)
//            {
//                Toast.makeText(ApplicationContext.getInstance(), "右", LENGTH_SHORT).show();
//                Gloable.getInstance().setDirection("右");
//
//                return true;
//            }
//            else if (e1.getY() - e2.getY() > 50)
//            {
//                Toast.makeText(ApplicationContext.getInstance(), "上", LENGTH_SHORT).show();
//                Gloable.getInstance().setDirection("上");
//
//                return true;
//            }
//            else if (e1.getY() - e2.getY() < -50)
//            {
//                Toast.makeText(ApplicationContext.getInstance(), "下", LENGTH_SHORT).show();
//                Gloable.getInstance().setDirection("下");
//                return true;
//            }
//            return false;
//        }
//    }
}
