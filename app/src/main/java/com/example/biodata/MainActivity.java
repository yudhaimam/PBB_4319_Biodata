package com.example.biodata;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

public class MainActivity extends AppCompatActivity {

    private Button phone, alamat, email;
    private EditText edPhone, edAlamat, edEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phone = findViewById(R.id.btnPhone);
        alamat = findViewById(R.id.btnOpenLoc);
        email = findViewById(R.id.btnEmail);

        edPhone = findViewById(R.id.etPhone);
        edAlamat = findViewById(R.id.etLoc);
        edEmail = findViewById(R.id.etEmail);

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", numPhone, null));
//                startActivity(i);

                Uri uri = Uri.parse(edPhone.getText().toString());
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                intent.setData(Uri.fromParts("tel", String.valueOf(uri), null));

                if (intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
                }
            }
        });

        alamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri2 = Uri.parse("geo:0,0?q="+edAlamat.getText().toString());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, uri2);
                mapIntent.setPackage("com.google.android.apps.maps");

                if(mapIntent.resolveActivity(getPackageManager()) != null){
                    startActivity(mapIntent);
                }
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND );
                Uri uri = Uri.parse(edEmail.getText().toString());
                intent.putExtra(Intent.EXTRA_EMAIL, uri);
                intent.putExtra(Intent.EXTRA_SUBJECT , "");
                intent.putExtra(Intent.EXTRA_TEXT , "");
                intent.setType("message/rfc822");
                startActivity(Intent.createChooser(intent , "Choose Your Apps : "));
            }
        });
    }

    public void simpan(View view) {
//        CODE 3
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                //set icon
                .setIcon(android.R.drawable.ic_dialog_alert)
                //set title
                .setTitle("Message")
                //set message
                .setMessage("Anda Yakin Ingin Menyimpan Data?")
                //set positive button
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what would happen when positive button is clicked
                        Toast.makeText(getApplicationContext(),"Data Tersimpan",Toast.LENGTH_LONG).show();
                    }
                })
                //set negative button
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what should happen when negative button is clicked
                        Toast.makeText(getApplicationContext(),"Data Tidak Tersimpan",Toast.LENGTH_LONG).show();
                    }
                })
                .show();
    }

    public void cancel(View view) {
        finish();
        Toast.makeText(getApplicationContext(), "Halaman Utama", Toast.LENGTH_LONG).show();
    }
}