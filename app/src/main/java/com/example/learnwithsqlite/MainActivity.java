package com.example.learnwithsqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listview;
    Button simpan;
    custome_adapter adapter;
    Cursor cursor;
    biodataTBL biodata;
    ArrayList<objek> list;

    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0,0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        simpan = findViewById(R.id.tambah_data);
        listview = findViewById(R.id.listview);
        biodata = new biodataTBL(getApplicationContext());
        getSupportActionBar().setTitle("Data Siswa ");
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), tmbhdata.class));
            }

        });

        ambil_data();
    }

    void ambil_data() {
        list = new ArrayList<objek>();
        cursor = biodata.tampil_data();
        if (cursor != null & cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(0);
                String nama = cursor.getString(1);
                String alamat = cursor.getString(2);
                list.add(new objek(id, nama, alamat));

            }
            adapter = new custome_adapter(getApplicationContext(), list, MainActivity.this);
            listview.setAdapter(adapter);

        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        ambil_data();
    }
    @Override
    public void onBackPressed(){
        finishAffinity();
        super.onBackPressed();
    }
}

 class
 custome_adapter extends BaseAdapter{
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<objek> model;
    TextView id,nama,alamat;
    Button edit,hapus;
    biodataTBL biodata;
    Activity activity;
    custome_adapter(Context context,ArrayList<objek> list,Activity activity){
        this.context=context;
            this.activity=activity;
            this.model=list;
            layoutInflater=layoutInflater.from(context);
            biodata=new biodataTBL(context);
        }
        @Override
        public int getCount() {
            return model.size();
        }

        @Override
        public Object getItem(int position) {
            return model.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            View view1=layoutInflater.inflate(R.layout.list_data,viewGroup,false);
            id=view1.findViewById(R.id.id);
            nama=view1.findViewById(R.id.nama);
            alamat=view1.findViewById(R.id.alamat);

            id.setText(model.get(position).getId());
            nama.setText(model.get(position).getNama());
            alamat.setText(model.get(position).getAlamat());
            edit=view1.findViewById(R.id.edit);
            hapus=view1.findViewById(R.id.hapus);

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, edit_data.class);
                    intent.putExtra("nama",model.get(position).getNama());
                    intent.putExtra("alamat",model.get(position).getAlamat());
                    intent.putExtra("id",model.get(position).getId());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
            hapus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder=new AlertDialog.Builder(activity);
                    builder.setTitle("Tanya");
                    builder.setMessage("Apakah anda ingin menghapus data ini?");
                    builder.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            biodata.delete(model.get(position).getId());

                            Intent intent=new Intent(context,MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);

                            ((Activity)context).finish();
                        }
                    });
                    builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                        }
                    });
                    AlertDialog alertDialog=builder.create();
                    alertDialog.show();

                }
            });
            return view1;
        }
}

