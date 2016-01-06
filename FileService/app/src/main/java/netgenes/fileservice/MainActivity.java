package netgenes.fileservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    EditText downloadEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downloadEditText = (EditText) findViewById(R.id.downloadedEditText);

        IntentFilter intentFilter = new IntentFilter();

        // Sets up the intent to wait for this broadcast
        intentFilter.addAction(FileService.TRANSACTION_DONE);

        registerReceiver(downloadReceiver, intentFilter);

    }

    @Override
    protected void onStop(){
        super.onStop();

        unregisterReceiver(downloadReceiver);
    }

    public void startFileService(View view){

        Intent intent = new Intent(this, FileService.class);

        intent.putExtra("url", "http://www.newthinktank.com/wordpress/lotr.txt");

        // Start intent service
        this.startService(intent);
    }

    private BroadcastReceiver downloadReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("FileService", "Service Received");

            showFileContents();
        }
    };

    public void showFileContents(){
        StringBuilder sb;

        try{
            FileInputStream fis = this.openFileInput("myFile");

            // Create InputStreamReader to catch the data
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");

            // Create a BufferedReader to be able to read data in small bytes
            BufferedReader bufferedReader = new BufferedReader(isr);

            sb = new StringBuilder();

            String line;

            while ((line = bufferedReader.readLine()) != null){
                sb.append(line).append("\n");
            }

            downloadEditText.setText(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
