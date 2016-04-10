package com.example.chris.cursorloop;

import android.app.Activity;
import android.database.Cursor;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;



public class MainActivity extends Activity {

    Cursor phones;
    Cursor phonestwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        phones.moveToFirst();
        phones = getContentResolver().query(
//                the table to query
                ContactsContract.Contacts.CONTENT_URI,
//                the columns to return
                null,
//               selection criteria :
// we only want contacts that have a name and a phone number. If they have a phone number, the value is 1 (if not, it is 0)
                ContactsContract.Contacts.IN_VISIBLE_GROUP + " = '" + ("1") + "'" + " AND " + ContactsContract.Contacts.HAS_PHONE_NUMBER + "=1",
//               selection criteria
                null,
//                display in ascending order
                ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC");


Log.e("phones", "" + phones.getCount());


        String phoneContactId = phones.getString(phones.getColumnIndexOrThrow(BaseColumns._ID));
//        if (phonestwo.isBeforeFirst()) {
//        phonestwo.moveToFirst();}
//        while (phonestwo.moveToNext()) {
//        if(phonestwo != null &&  phonestwo.moveToFirst()){
//        phonestwo.moveToFirst();
        if(phonestwo!=null && phonestwo.getCount()>0){
            phonestwo.moveToFirst();
            phonestwo = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                    new String[]{phoneContactId},
                    null);
            phonestwo.close();
        }

        Log.e("phonestwo", "" + phonestwo.getCount());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
