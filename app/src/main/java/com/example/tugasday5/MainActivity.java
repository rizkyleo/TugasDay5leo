package com.example.tugasday5;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText namaEditText;
    EditText kodeEditText;
    EditText jumlahEditText;
    RadioButton goldRadioButton;
    RadioButton silverRadioButton;
    RadioButton biasaRadioButton;
    Button processButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale(); // Load language preference
        setContentView(R.layout.activity_main);

        namaEditText = findViewById(R.id.nama);
        kodeEditText = findViewById(R.id.kode);
        jumlahEditText = findViewById(R.id.jumlah);
        goldRadioButton = findViewById(R.id.rgold);
        silverRadioButton = findViewById(R.id.rsilver);
        biasaRadioButton = findViewById(R.id.rbiasa);
        processButton = findViewById(R.id.process_button);

        processButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processInput();
            }
        });
    }

    // Method to process input data
    private void processInput() {
        // Retrieve data from EditText fields
        String nama = namaEditText.getText().toString();
        String kode = kodeEditText.getText().toString();
        int jumlah = Integer.parseInt(jumlahEditText.getText().toString());

        String namaBarang = "";
        double hargaBarang = 0;

        switch (kode) {
            case "OAS":
                namaBarang = "Oppo A5s";
                hargaBarang = 1989123;
                break;
            case "AAE":
                namaBarang = "Acer Aspire E14";
                hargaBarang = 8676981;
                break;
            case "AV4":
                namaBarang = "Asus Vivobook 14";
                hargaBarang = 9150999;
                break;
            default:
                // If kode is not recognized, show an error message and return
                Toast.makeText(MainActivity.this, "Kode barang tidak dikenali", Toast.LENGTH_SHORT).show();
                return;
        }

        double totalHarga = hargaBarang * jumlah;

        double diskon = 0;
        if (goldRadioButton.isChecked()) {
            diskon = 0.1; // 10% discount for Gold
        } else if (silverRadioButton.isChecked()) {
            diskon = 0.05; // 5% discount for Silver
        } else if (biasaRadioButton.isChecked()) {
            diskon = 0.02; // 2% discount for Biasa
        }

        if (totalHarga > 10000000) {
            totalHarga -= 100000;
        }

        totalHarga -= totalHarga * diskon;

        // Pass data to the next activity
        Intent intent = new Intent(MainActivity.this, Tampilan.class);
        intent.putExtra("totalHarga", totalHarga);
        intent.putExtra("nama", nama);
        intent.putExtra("tipePelanggan", getCustomerType());
        intent.putExtra("kode", kode);
        intent.putExtra("namaBarang", namaBarang);
        intent.putExtra("hargaBarang", hargaBarang);
        intent.putExtra("diskon", diskon);
        startActivity(intent);
    }

    // Method to determine customer type based on radio button selection
    private String getCustomerType() {
        if (goldRadioButton.isChecked()) {
            return "Gold";
        } else if (silverRadioButton.isChecked()) {
            return "Silver";
        } else {
            return "Biasa";
        }
    }

    // Method to set language preference
    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        // Save language preference
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }

    // Method to load language preference
    private void loadLocale() {
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "");
        setLocale(language);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.change) {
            showLanguageSelectionDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showLanguageSelectionDialog() {
        // Kode untuk menampilkan dialog pilihan bahasa
    }
}