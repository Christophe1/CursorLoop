package com.example.chris.cursorloop;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
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
    Cursor cur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//
//  Find contact based on name.
//
        ContentResolver cr = getContentResolver();


//        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null,
//                null, null, null);
        if (cur != null) {
            cur.moveToFirst();}
        // this query only return contacts with phone number and is not duplicated
         cur = getContentResolver().query(
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


        if (cur.getCount() > 0) {
            cur.moveToFirst();
            while (cur.moveToNext()) {
                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                Log.e("cursor total", "" + cur.getCount());
                Log.i("id", id);
                Log.i("Name", name);

//                if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0)
//                {
                    // Query phone here. Covered next
//                    phones.moveToFirst();
                    Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id,null, null);
                    while (phones.moveToNext()) {
                        String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        Log.i("Number", phoneNumber);
                    }
                    phones.close();
//                }

            }
        }
//
////        phones.moveToFirst();
//        phones = getContentResolver().query(
////                the table to query
//                ContactsContract.Contacts.CONTENT_URI,
////                the columns to return
//                null,
////               selection criteria :
//// we only want contacts that have a name and a phone number. If they have a phone number, the value is 1 (if not, it is 0)
//                ContactsContract.Contacts.IN_VISIBLE_GROUP + " = '" + ("1") + "'" + " AND " + ContactsContract.Contacts.HAS_PHONE_NUMBER + "=1",
////               selection criteria
//                null,
////                display in ascending order
//                ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC");
//        Log.e("phones", "" + phones.getCount());
//
////        phones.close();
////
////
//        phones.moveToFirst();
//        String phoneContactId = phones.getString(phones.getColumnIndexOrThrow(BaseColumns._ID));
//        Log.e("phonecontactid", "" + phoneContactId);
//
//        phones.close();
//
////        if (phonestwo.isBeforeFirst()) {
////        phonestwo.moveToFirst();}
////        while (phonestwo.moveToNext()) {
////        if(phonestwo != null &&  phonestwo.moveToFirst()){
////        phonestwo.moveToFirst();
//        if(phonestwo!=null && phonestwo.getCount()>0){
//        phonestwo.moveToFirst();}
//        phonestwo = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
//                null,
//                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
//                new String[]{phoneContactId},
//                null);
//        Log.e("phonestwo", "" + phonestwo.getCount());
//
//        while (phonestwo.moveToNext()) {
//            int phoneType = phonestwo.getInt(phonestwo.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.TYPE));
//            String phoneNumber = phonestwo.getString(phonestwo.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
//            Log.e("phonestwo", "" + phoneNumber);
//
//        }
//
//
//
//        phonestwo.close();
//
////***********************
////
////        if(cursor!=null && cursor.getCount()>0){
////            cursor.moveToFirst();}
////        Uri uri = ContactsContract.Contacts.CONTENT_URI;
////        String[] PROJECTION = {
////                ContactsContract.Contacts._ID,
////                ContactsContract.Contacts.DISPLAY_NAME,
////                ContactsContract.Contacts.HAS_PHONE_NUMBER
////        };
////        String selection = "(" + ContactsContract.Contacts.IN_VISIBLE_GROUP +
////                " = '1' AND (" + ContactsContract.Contacts.HAS_PHONE_NUMBER + " != 0 ))";
////        String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";
////
////
////         cursor = getContentResolver().query(uri, PROJECTION, selection, null, sortOrder);
////
////
////        Log.e("cursor", "" + cursor.getCount());
////        cursor.close();
//
////        **************************
    }
//
//
//        Log.e("phonestwo", "" + phonestwo.getCount());
//
//    }
//*****************************
//         people = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
//        if (people != null && people.getCount() > 0) {
//            people.moveToFirst();
//        }
//        while(people.moveToNext()) {
//
//
//
//                int nameFieldColumnIndex = people.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);
//                String contact = people.getString(nameFieldColumnIndex);
//                int numberFieldColumnIndex = people.getColumnIndex(ContactsContract.PhoneLookup.NUMBER);
//                String number = people.getString(numberFieldColumnIndex);
//            }
//
//        Log.e("people", "" + people.getCount());
//        people.close();
//        *******************************************
//         phonestwo = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
//
//        while (phonestwo.moveToNext())
//        {
//            String NameofPerson=phonestwo.getString(phonestwo.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
//            String NumberofPerson=phonestwo.getString(phonestwo.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//        }
//        Log.e("Name", "" + ());
//    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
