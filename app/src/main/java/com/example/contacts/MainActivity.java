package com.example.contacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int RESULT_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) !=
                PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_CONTACTS))
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(this).
                        setMessage("we neeed permission for futher acess").
                        setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, RESULT_CODE);

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                builder.show();

            }

        }
        else {
            fetchContact();
        }
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == RESULT_CODE)
        {
            if(permissions.length>0)
            {
             if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
             {
                 fetchContact();
             }
            }
        }
    }

    private void fetchContact() {
        ArrayList<String>  contacts = new ArrayList<>();
            Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;


          String[] projection = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER};
          String selection = null;
          String[] selectionArgs = null;
          String sortOrder = null;
         ContentResolver resolver = getContentResolver();
         Cursor cursor = resolver.query(uri,projection,selection,selectionArgs,sortOrder);
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            contacts.add(name+"\n"+number);
        }
        ((ListView)findViewById(R.id.list)).setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,contacts));
    }
}
