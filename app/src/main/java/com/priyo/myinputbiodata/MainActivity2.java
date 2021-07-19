package com.priyo.myinputbiodata;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    ArrayList<Mahasiswa> data_mhs;
    Mahasiswa data;
    RecyclerView recyclerView;
    AdapterMahasiswa adapterMahasiswa;

    EditText ed1, ed2, ed3, ed4, ed5;
    RadioButton rb1, rb2;
    RadioGroup rg1;
    Button back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        data_mhs = new ArrayList<>();
        Intent i = getIntent();
        data_mhs = i.getParcelableArrayListExtra("data_mhs");
        // tambah data dummy dulu
        data_mhs.add(new Mahasiswa(1,"2000101011","Adi","Denpasar", true, 190.1));
        data_mhs.add(new Mahasiswa(1,"2000101012","Adi 2","Denpasar", false, 190.1));
        data_mhs.add(new Mahasiswa(1,"2000101013","Adi 3","Denpasar", true, 190.1));
        data_mhs.add(new Mahasiswa(1,"2000101014","Adi 4","Denpasar", false, 190.1));
        data_mhs.add(new Mahasiswa(1,"2000101015","Adi 5","Denpasar", true, 190.1));

        recyclerView = findViewById(R.id.recyclerView);

        // Tambah Data
        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this, AddData.class));
            }
        });

        //setting recylerview
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        adapterMahasiswa = new AdapterMahasiswa(data_mhs);

        recyclerView.setAdapter(adapterMahasiswa);

        adapterMahasiswa.setOnItemClickListener(new AdapterMahasiswa.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Mahasiswa mhs = adapterMahasiswa.getItem(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
                builder.setTitle("Warning");
                builder.setIcon(R.drawable.ic_baseline_info_24);

                ViewGroup viewGroup = findViewById(android.R.id.content);
                View customView = LayoutInflater.from(MainActivity2.this).
                        inflate(R.layout.custom_alert, viewGroup, false);

                ed1 = customView.findViewById(R.id.editID);
                ed2 = customView.findViewById(R.id.editNIM);
                ed3 = customView.findViewById(R.id.editNama);
                ed4 = customView.findViewById(R.id.editAlamat);
                ed5 = customView.findViewById(R.id.editTinggiBdn);
                rg1 = customView.findViewById(R.id.radioJnsKel);
                rb1 = customView.findViewById(R.id.radioButton);
                rb2 = customView.findViewById(R.id.radioButton2);

                builder.setView(customView);

                ed1.setText(String.valueOf(mhs.getId()));
                ed2.setText(mhs.getNim());
                ed3.setText(mhs.getNama());
                ed4.setText(mhs.getAlamat());
                ed5.setText(String.valueOf(mhs.getTinggi_badan()));

                if (mhs.getJns_ke()) {
                    rb1.setChecked(true);
                } else {
                    rb2.setChecked(true);
                }


                builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        data_mhs.remove(position);

                        RadioButton rb = customView.findViewById(rg1.getCheckedRadioButtonId());
                        boolean jns_kel;
                        jns_kel = rb.getText().toString().equals("Laki-Laki");
                        data_mhs.add(new Mahasiswa(
                                Integer.parseInt(ed1.getText().toString()) ,
                                        ed2.getText().toString(),
                                        ed3.getText().toString(),
                                        ed4.getText().toString(),
                                        jns_kel,
                                        Double.parseDouble(ed5.getText().toString())
                        )
                        );
                        adapterMahasiswa.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("HAPUS", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        data_mhs.remove(position);
                        adapterMahasiswa.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

    }
}