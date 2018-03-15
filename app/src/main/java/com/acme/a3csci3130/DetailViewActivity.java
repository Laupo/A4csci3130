package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailViewActivity extends Activity {

    private EditText businessNumberField, nameField, primaryBusinessField, addressField, provinceField;
    Contact receivedPersonInfo;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        appState = ((MyApplicationData) getApplicationContext());

        receivedPersonInfo = (Contact) getIntent().getSerializableExtra("Contact");

        businessNumberField = (EditText) findViewById(R.id.businessNumber);
        nameField = (EditText) findViewById(R.id.name);
        primaryBusinessField = (EditText) findViewById(R.id.primaryBusiness);
        addressField = (EditText) findViewById(R.id.address);
        provinceField = (EditText) findViewById(R.id.province);

        if(receivedPersonInfo != null){
            businessNumberField.setText(receivedPersonInfo.businessNumber);
            nameField.setText(receivedPersonInfo.name);
            primaryBusinessField.setText(receivedPersonInfo.primaryBusiness);
            addressField.setText(receivedPersonInfo.address);
            provinceField.setText(receivedPersonInfo.province);
        }
    }
    /*
     * This method update the contact on Firebase and displayed on the screen
     *
     * @param v
     *          View of the activity
     */
    public void updateContact(View v){
        //TODO: Update contact functionality

        // To get the ID of the contact
        String personID = receivedPersonInfo.personID;

        // Get updated info of the contact
        String businessNumber = businessNumberField.getText().toString();
        String name = nameField.getText().toString();
        String primaryBusiness = primaryBusinessField.getText().toString().toLowerCase();
        String address = addressField.getText().toString();
        String province = provinceField.getText().toString().toUpperCase();

        // Create a new object contact with the new attributes
        Contact person = new Contact(personID,businessNumber, name, primaryBusiness, address, province);

        // Update in Firebase the contact
        appState.firebaseReference.child(personID).setValue(person);

        finish();
    }
    /*
     * This method erase the contact on Firebase and  displayed on the screen
     * @param v
     *          View of the activity
     */
    public void eraseContact(View v)
    {
        //TODO: Erase contact functionality

        // To get the ID of the contact
        String personID = receivedPersonInfo.personID;

        // Remove the contact from Firebase
        appState.firebaseReference.child(personID).removeValue();

        finish();
    }
}
