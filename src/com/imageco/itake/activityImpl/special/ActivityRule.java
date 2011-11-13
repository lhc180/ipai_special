package com.imageco.itake.activityImpl.special;

import android.os.Bundle;
import com.imageco.R;
import com.imageco.itake.activityGameBase.ActiviyBaseGame;

/**
 * Created by IntelliJ IDEA. User: OYQX Date: 11-11-12 Time: 下午1:54
 */
public class ActivityRule extends ActiviyBaseGame

{
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
