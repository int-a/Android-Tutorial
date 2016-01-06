package com.netgenes.getcontacts;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContentResolverCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // give us access to the unique content provider from the other application in this application
    static final Uri CONTENT_URL = Uri.parse("content://com.netgenes.contactprovider.ContactProvider/cpcontacts");

    TextView contactsTextView = null;
    EditText deleteIDEditText, idLookupEditText, addNameEditText;

    ContentResolver resolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resolver = getContentResolver();

        contactsTextView = (TextView) findViewById(R.id.contactsTextView);
        deleteIDEditText = (EditText) findViewById(R.id.deleteIDEditText);
        idLookupEditText = (EditText) findViewById(R.id.idLookupEditText);
        addNameEditText = (EditText) findViewById(R.id.addNameEditText);

        getContacts();
    }

    public void getContacts(){

        String[] projectsion = new String[]{"id", "name"};

        // Since we want everything we do not need other arguments
        Cursor cursor = resolver.query(CONTENT_URL, projectsion, null, null, null);

        String contactList = "";

        if(cursor.moveToFirst()){
            do{
                String id = cursor.getString(cursor.getColumnIndex("id"));

                String name = cursor.getString(cursor.getColumnIndex("name"));

                contactList = contactList + id + " : " + name + "\n";
            }while(cursor.moveToNext());
        }

        contactsTextView.setText(contactList);
    }

    public void deleteContact(View view){

        String idToDelete = deleteIDEditText.getText().toString();

        // could put multiple items in String[] to delete multiple items at once
        // (maybe build the array ahead of time in that case?
        long idDeleted = resolver.delete(CONTENT_URL, "id = ? ", new String[]{idToDelete});

        // Update contact list in the text view
        getContacts();
    }

    public void lookupContact(View view){

        String idToFind = idLookupEditText.getText().toString();

        String[] projection = {"id", "name"};

        Cursor cursor = resolver.query(CONTENT_URL, projection, "id = ?", new String[]{idToFind}, null);

        String contact = "";

        if(cursor.moveToFirst()){
            String id = cursor.getString(cursor.getColumnIndex("id"));

            String name = cursor.getString(cursor.getColumnIndex("name"));

            contact = contact + id + " : " + name + "\n";
        }else{
            Toast.makeText(this, "Contact not found", Toast.LENGTH_SHORT).show();
        }

        contactsTextView.setText(contact);
    }

    public void addContact(View view){
        String nameToAdd = addNameEditText.getText().toString();

        ContentValues values = new ContentValues();
        values.put("name", nameToAdd);

        resolver.insert(CONTENT_URL, values);

        // Update contacts text view
        getContacts();
    }

    public void showContacts(View view){
        getContacts();
    }

}
