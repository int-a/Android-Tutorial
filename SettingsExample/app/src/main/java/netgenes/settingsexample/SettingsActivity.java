package netgenes.settingsexample;

import android.preference.PreferenceActivity;
import android.os.Bundle;

/**
 * Created by Anthony on 1/1/2016.
 */
public class SettingsActivity extends PreferenceActivity {

    @SuppressWarnings("deprication")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
    }
}
