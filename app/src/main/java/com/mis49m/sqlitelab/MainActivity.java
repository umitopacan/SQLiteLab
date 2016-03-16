package com.mis49m.sqlitelab;

import android.media.MediaActionSound;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    TextView tvCount;
    EditText etID, etName, etPhone;
    Button btnAdd, btnUpdate, btnDelete;

    DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHandler = new DatabaseHandler(MainActivity.this);

        //-- read ui references
        tvCount = (TextView) findViewById(R.id.tv_count);
        etID = (EditText) findViewById(R.id.txt_id);
        etName = (EditText) findViewById(R.id.txt_name);
        etPhone = (EditText) findViewById(R.id.txt_phone);
        etPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnUpdate = (Button) findViewById(R.id.btn_update);
        btnDelete = (Button) findViewById(R.id.btn_delete);

        makeUpdateForm(false);
        showContactCounts();
    }

    public void delete(View view){
        String id = etID.getText().toString();

        clearValues();
    }

    public void update(View view){
        String id = etID.getText().toString();
        String name = etName.getText().toString();
        String phone = etPhone.getText().toString();

        clearValues();
    }

    public void getContact(View view){
        String id = etID.getText().toString();
        
        makeUpdateForm(true);
    }

    public void add(View view){
        String name = etName.getText().toString();
        String phone = etPhone.getText().toString();

        Contact contact = new Contact();
        contact.setName(name);
        contact.setPhoneNumber(phone);

        databaseHandler.addContact(contact);

        clearValues();
    }

    private void showContactCounts(){
        tvCount.setText("Contacts count : " );
    }

    private void clearValues(){
        etID.setText("");
        etName.setText("");
        etPhone.setText("");

        makeUpdateForm(false);
    }

    private void makeUpdateForm(boolean isUpdate){
        btnAdd.setEnabled(!isUpdate);
        btnUpdate.setEnabled(isUpdate);
        btnDelete.setEnabled(isUpdate);
    }

    private void showMessage(String value){
        Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
    }
}
