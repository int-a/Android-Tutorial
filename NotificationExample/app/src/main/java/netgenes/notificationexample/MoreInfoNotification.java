package netgenes.notificationexample;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Anthony on 1/2/2016.
 */
public class MoreInfoNotification extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        // Open up the correct layout file
        setContentView(R.layout.more_info_notific);
    }
}
