package com.example.tktkb.registrationform;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

public class RegisterActivity extends Activity {
    private static final int RESULT_LOAD_IMAGE = 1;
    EditText name;
    EditText lastname;
    RadioButton male;
    RadioButton female;
    EditText email;
    EditText password;
    Button Save;
    CheckBox addphoto;
    String gender;
    Button add;
    public String nkarUri;
    Intent resultIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        resultIntent = getIntent();
        name = findViewById(R.id.name);
        lastname = findViewById(R.id.Lastname);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        email = findViewById(R.id.email);
        password = findViewById(R.id.txtPassword);
        addphoto = findViewById(R.id.addphoto);
        add = findViewById(R.id.add);
        add.setOnClickListener((View.OnClickListener) this);
        Save = findViewById(R.id.done);
        Save.setOnClickListener((View.OnClickListener) this);

    }

    public String getGender() {
        if (male.isChecked()) {
            gender = "male";
        }
        if (female.isChecked()) {
            gender = "female";
        }
        return gender;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add:
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, RESULT_LOAD_IMAGE);
                break;
            case R.id.done:
                getGender();
                User user = new User(name.getText().toString(),lastname.getText().toString(),gender,email.getText().toString(),password.getText().toString());
                setResult(RESULT_OK,resultIntent);
                finish();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK ){
            Uri imageUri = data.getData();
            nkarUri = imageUri.toString();
            resultIntent.putExtra("name",name.getText().toString());
            resultIntent.putExtra("nkar",imageUri);
            addphoto.isChecked();
            Save.setEnabled(true);
        }
    }
}
