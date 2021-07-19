package com.priyo.myinputbiodata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class AddData extends AppCompatActivity {
    EditText edId, edNim, edNama, edAlamat, edTinggi;
    RadioGroup rgJnsKel;
    Button btnSimpan, btnLihat;

    Mahasiswa data;
    ArrayList<Mahasiswa> daftarNama;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        daftarNama = new ArrayList<>();

        edId = (EditText)findViewById(R.id.editID);
        edNim = (EditText)findViewById(R.id.editNIM);
        edNama = (EditText)findViewById(R.id.editNama);
        edAlamat = (EditText)findViewById(R.id.editAlamat);
        edTinggi = (EditText)findViewById(R.id.editTinggiBdn);

        rgJnsKel = (RadioGroup)findViewById(R.id.radioJnsKel);

        btnSimpan = (Button)findViewById(R.id.buttonSimpan);

        btnLihat = findViewById(R.id.button2);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // mulai simpan di sini
                int id = Integer.parseInt(edId.getText().toString());
                String nim = edNim.getText().toString();
                String nama = edNama.getText().toString();
                String alamat = edAlamat.getText().toString();
                Double tinggi = Double.parseDouble(edTinggi.getText().toString());

                boolean jnskel = true;
                RadioButton rb = (RadioButton) findViewById(rgJnsKel.getCheckedRadioButtonId());
                if(rb != null) {
                    if(rb.getText().toString().equals("Laki-Laki")) {
                        jnskel = true;
                    } else {
                        jnskel = false;
                    }
                }

                data = new Mahasiswa(
                        id,
                        nim,
                        nama,
                        alamat,
                        jnskel,
                        tinggi
                );

                daftarNama.add(data);
                click();
//              Toast.makeText(AddData.this, data.nama +" " + data.alamat,Toast.LENGTH_SHORT).show();
            }
        });

        btnLihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click();
            }
        });
    }

    private void click() {
        Intent goToViewData = new Intent(AddData.this, MainActivity2.class);
        goToViewData.putParcelableArrayListExtra("data_mhs", daftarNama);
        startActivity(goToViewData);
    }
}