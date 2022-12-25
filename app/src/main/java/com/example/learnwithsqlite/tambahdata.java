package com.example.learnwithsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class tambahdata extends AppCompatActivity {
    biodataTBL biodataTBL;
        EditText nama,alamat;
        Button simpan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambahdata2);
        nama=findViewById(R.id.nama);
        alamat=findViewById(R.id.alamat);
        simpan=findViewById(R.id.simpan_data);

        biodataTBL=new biodataTBL(getApplicationContext());
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpan_data();
            }
        });

    }
    void simpan_data(){
        biodataTBL.simpan_data(
                nama.getText().toString(),
                alamat.getText().toString());

    }
}