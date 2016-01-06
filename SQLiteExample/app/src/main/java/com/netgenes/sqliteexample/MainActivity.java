package com.netgenes.sqliteexample;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase contactsDB = null;
    Button createDBButton, addContactButton, deleteContactButton, getContactsButton, deleteDBButton;
    EditText nameEditText, emailEditText, contactListEditText, idEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize all the buttons
        createDBButton = (Button) findViewById(R.id.createDBButton);
        addContactButton = (Button) findViewById(R.id.addContactButton);
        deleteContactButton = (Button) findViewById(R.id.deleteContactButton);
        getContactsButton = (Button) findViewById(R.id.getContactsButton);
        deleteDBButton = (Button) findViewById(R.id.deleteDBButton);

        // Initialize all the edittext fields
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        contactListEditText = (EditText) findViewById(R.id.contactListEditText);
        idEditText = (EditText) findViewById(R.id.idEditText);


    }

    public void createDatabase(View view) {

        try{
            // Creates or opens a database called MyContacts in mode private so only this app has access
            contactsDB = this.openOrCreateDatabase("MyContacts",
                    MODE_PRIVATE, null);

            contactsDB.execSQL("CREATE TABLE IF NOT EXISTS contacts " +
                "(id integer primary key, name VARCHAR, email VARCHAR);");

            // Verify that the database exists in the file system
            File database =
                    getApplicationContext().getDatabasePath("MyContacts.db");

            // Display toast message
            if(!database.exists()){
                Toast.makeText(this, "Database Created",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Database Missing!",
                        Toast.LENGTH_SHORT).show();
            }
        }
        catch(Exception e){
            Log.e("CONTACTS ERROR", "Error Creating Database");
        }

        // Make our buttons clickable now that there is a database
        addContactButton.setClickable(true);
        deleteContactButton.setClickable(true);
        getContactsButton.setClickable(true);
        deleteDBButton.setClickable(true);

    }

    public void addContact(View view) {

        // Get contact name and email entered
        String contactName = nameEditText.getText().toString();
        String contactEmail = emailEditText.getText().toString();

        contactsDB.execSQL("INSERT INTO contacts (name, email) VALUES('" +
            contactName + "','" + contactEmail + "');");

    }

    public void getContacts(View view){

        // Use rawQuery when using a SELECT
        Cursor cursor = contactsDB.rawQuery("SELECT * FROM contacts", null);

        // pass in column name
        int idColumn = cursor.getColumnIndex("id");
        int nameColumn = cursor.getColumnIndex("name");
        int emailColumn = cursor.getColumnIndex("email");

        cursor.moveToFirst();

        String contactList = "";

        if(cursor != null && (cursor.getCount() > 0)){
            do{
                // get values at given id in the columns
                String id = cursor.getString(idColumn);
                String name = cursor.getString(nameColumn);
                String email = cursor.getString(emailColumn);

                contactList = contactList + id + " : " + name + " : " + email + "\n";

            }while(cursor.moveToNext());

            // Set text of the edit text box to the contact list
            contactListEditText.setText(contactList);
        }else{
            Toast.makeText(this, "No results to show",
                    Toast.LENGTH_SHORT).show();

            contactListEditText.setText("");
        }
    }

    public void deleteContact(View view){
        // get id that was entered
        String id = idEditText.getText().toString();

        contactsDB.execSQL("DELETE FROM contacts WHERE id = " + id + ";");
    }

    public void deleteDatabase(View view) {
        this.deleteDatabase("MyContacts");
    }

    @Override
    protected void onDestroy() {
        contactsDB.close();

        super.onDestroy();
    }

    //TODO implement and onPasue() to save the DB whenever the activiy is pushed to the background
    //TODO implekemt onResume() to restore the database
}
