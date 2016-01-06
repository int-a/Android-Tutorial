package netgenes.translation;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;

public class MyActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

    }

    public void onTranslateClick(View view){

        EditText translateEditText = (EditText) findViewById(R.id.editText);

        if(!isEmpty(translateEditText)){
            Toast.makeText(this, "Getting Translations", Toast.LENGTH_LONG).show();

            new SaveTheFeed().execute();

        } else {
            Toast.makeText(this, "Enter words to translate", Toast.LENGTH_SHORT).show();
        }
    }

    protected boolean isEmpty(EditText editText){
        return editText.getText().toString().trim().length() == 0;
    }

    class SaveTheFeed extends AsyncTask<Void, Void, Void> {

        //String to contain json data
        String jsonString = "";

        //String to contain translations
        String result = "";

        @Override
        protected Void doInBackground(Void... params) {
            EditText translateEditText = (EditText) findViewById(R.id.editText);

            String wordsToTranslate = translateEditText.getText().toString();

            wordsToTranslate = wordsToTranslate.replace(" ", "+");

           HttpURLConnection httpClient = new HttpURLConnection() {
               @Override
               public void disconnect() {

               }

               @Override
               public boolean usingProxy() {
                   return false;
               }

               @Override
               public void connect() throws IOException {

               }
           }
        }
    }
}
