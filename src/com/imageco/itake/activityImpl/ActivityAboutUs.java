package com.imageco.itake.activityImpl;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.imageco.R;
import com.imageco.util.decoding.Base64;

import static com.imageco.itake.userOption.CollectionOperation.getVersionCode;

/**
 * Created by IntelliJ IDEA.
 * User: OYQX
 * Date: 11-10-20
 * Time: 下午1:51
 */
public class ActivityAboutUs extends Activity {
    private TextView version;

    /**
     * Method onCreate ...
     *
     * @param savedInstanceState of type Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutus);
        version = (TextView) ActivityAboutUs.this.findViewById(R.id.version);
        byte[] versionCode;
        try {
            versionCode = Base64.decode(getVersionCode().toCharArray());
            String versionStr = new String(versionCode);
            version.setText("软件版本号: V1.0  ");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
