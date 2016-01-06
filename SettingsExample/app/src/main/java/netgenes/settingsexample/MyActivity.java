package netgenes.settingsexample;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MyActivity extends AppCompatActivity {

    EditText notesEditText;
    Button btnSettings;
    private static final int SETTINGS_INFO = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        notesEditText = (EditText) findViewById(R.id.notesEditText);

        // Check if there is data in savedInstanceState
        if(savedInstanceState != null){
            String notes = savedInstanceState.getString("NOTES");

            notesEditText.setText(notes);
        }

        // Put what ever has been saved from when the app closed into sPNotes
        // if nothing then it will put EMPTY into sPNotes
        String sPNotes = getPreferences(Context.MODE_PRIVATE).getString("NOTES",
                "EMPTY");

        // If sPNotes is not EMPTY then there is something to retrieve
        // Will get data even if the app was force closed by the user
        if(!sPNotes.equals("EMPTY")){
            notesEditText.setText(sPNotes);
        }

        btnSettings = (Button) findViewById(R.id.btnSettings);

        btnSettings.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intentPreferences = new Intent(getApplicationContext(),
                        SettingsActivity.class);

                startActivityForResult(intentPreferences, SETTINGS_INFO);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode, data);

        // This is how we know what intent called it
        // TODO try creating a new button with a different request code
        if(requestCode == SETTINGS_INFO){
            updateNoteText();
        }
    }

    private void updateNoteText(){
        // All our key-value pairs are held inside here
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // check if bold checkbox was clicked on
        if(sharedPreferences.getBoolean("pref_text_bold", false)){
            notesEditText.setTypeface(null, Typeface.BOLD);
        } else {
            notesEditText.setTypeface(null, Typeface.NORMAL);
        }

        // Get text size as a string, define a default of 16
        String textSizeStr = sharedPreferences.getString("pref_text_size", "16");

        // Convert text size to a float so we can use it
        float textSizeFloat = Float.parseFloat(textSizeStr);

        // Change the text size
        notesEditText.setTextSize(textSizeFloat);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Put current note value in saved state
        outState.putString("NOTES",
                notesEditText.getText().toString());

        super.onSaveInstanceState(outState);
    }

    // Need to call this when the app is force closed by the user
    private void saveSettings(){

        SharedPreferences.Editor sPEditor = getPreferences(Context.MODE_PRIVATE).edit();

        sPEditor.putString("NOTES",
                notesEditText.getText().toString());
    }

    @Override
    // Called when the app is force closed by the user
    protected void onStop() {
        saveSettings();

        super.onStop();
    }
}
