package com.example.tktkb.registrationform;


import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_INPUT = 100;
    private Button createUser;
    String name;
    String imageUri;
    ImageView imageProfile;
    TextView anun;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createUser=findViewById(R.id.create);
        imageProfile=findViewById(R.id.image);
        anun=findViewById(R.id.anun);
        createUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivityForResult(intent,REQUEST_CODE_INPUT);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_INPUT){
            if(resultCode== RESULT_OK){
                if(data!=null){
                    name = data.getStringExtra("name");
                    imageUri = data.getStringExtra("nkar");
                    Uri profileUri = Uri.parse(imageUri);
                    imageProfile.setImageURI(profileUri);
                    anun.setText(name);
                }
            }
        }
    }
}
