package netgenes.fragmentlayouts;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

/**
 * Created by Anthony on 12/29/2015.
 */
public class DetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // If in landscape mode, kill activity
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            finish();
            return;
        }

        // If activity was not killed...
        // If savedInstanceState is null we will need to create it
        if(savedInstanceState == null){
            DetailsFragment details = new DetailsFragment();

            // Get bundle of key value pairs (just 1)
            details.setArguments(getIntent().getExtras());

            getFragmentManager().beginTransaction().add(android.R.id.content, details).commit();
        }
    }
}
