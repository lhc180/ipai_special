package com.imageco.itake.activityImpl.special;

import android.os.Bundle;
import com.imageco.R;
import com.imageco.itake.activityGameBase.ActivityBaseGame;

/**
 * Created by IntelliJ IDEA. User: OYQX Date: 11-11-12 Time: 下午1:54
 */
public class ActivityRule extends ActivityBaseGame

{
    @Override protected void onResume()
    {
        GameGlo.getInstance().setDissmissAble(Boolean.TRUE);
        super
            .onResume();    //To change body of overridden methods use File | Settings | File Templates.
    }

    /**
     * Method onCreate ...
     *
     * @param savedInstanceState of type Bundle
     */
    @Override public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(
            savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        setContentView(R.layout.rule);
    }
}
