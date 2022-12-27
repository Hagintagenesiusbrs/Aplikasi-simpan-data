package com.example.learnwithsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class edit_data extends AppCompatActivity {
    EditText nama,alamat;
    Button update;
    biodataTBL biodata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data);
        nama=findViewById(R.id.nama);
        alamat=findViewById(R.id.alamat);
        update=findViewById(R.id.update);
        nama.setText(getIntent().getStringExtra("nama"));
        alamat.setText(getIntent().getStringExtra("alamat"));

        biodata=new biodataTBL(getApplicationContext());
        getSupportActionBar().setTitle("Edit Data"+getIntent().getStringExtra("nama"));
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                biodata.update_data(getIntent().getStringExtra("id"),
                        nama.getText().toString(),
                        alamat.getText().toString());
                finish();

            }
        });
    }
}