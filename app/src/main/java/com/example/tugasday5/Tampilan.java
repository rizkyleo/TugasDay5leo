package com.example.tugasday5;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Tampilan extends AppCompatActivity {

    TextView namaTextView;
    TextView tipePelangganTextView;
    TextView kodeBarangTextView;
    TextView namaBarangTextView;
    TextView hargaBarangTextView;
    TextView diskonTextView;
    TextView totalHargaTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampilan);

        namaTextView = findViewById(R.id.nama);
        tipePelangganTextView = findViewById(R.id.tipe_pelanggan);
        kodeBarangTextView = findViewById(R.id.kode_barang_text_view);
        namaBarangTextView = findViewById(R.id.nama_barang_text_view);
        hargaBarangTextView = findViewById(R.id.harga_barang_text_view);
        diskonTextView = findViewById(R.id.diskon_text_view);
        totalHargaTextView = findViewById(R.id.total_harga_text_view);

        Intent intent = getIntent();
        String nama = intent.getStringExtra("nama");
        String tipePelanggan = intent.getStringExtra("tipePelanggan");
        String kodeBarang = intent.getStringExtra("kode");
        String namaBarang = intent.getStringExtra("namaBarang");
        double hargaBarang = intent.getDoubleExtra("hargaBarang", 0);
        double diskon = intent.getDoubleExtra("diskon", 0);
        double totalHarga = intent.getDoubleExtra("totalHarga", 0);

        namaTextView.setText("Selamat Datang, " + nama + "!");
        tipePelangganTextView.setText("Tipe Pelanggan: " + tipePelanggan);
        kodeBarangTextView.setText("Kode Barang: " + kodeBarang);
        namaBarangTextView.setText("Nama Barang: " + namaBarang);
        hargaBarangTextView.setText("Harga Barang: Rp " + hargaBarang);
        diskonTextView.setText("Diskon: " + (diskon * 100) + "%");
        totalHargaTextView.setText("Total Harga: Rp " + totalHarga);

        Button shareButton = findViewById(R.id.share_button);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareData();
            }
        });
    }

    private void shareData() {
        String shareText = "Nama: " + namaTextView.getText().toString() + "\n" +
                "Tipe Pelanggan: " + tipePelangganTextView.getText().toString() + "\n" +
                "Kode Barang: " + kodeBarangTextView.getText().toString() + "\n" +
                "Nama Barang: " + namaBarangTextView.getText().toString() + "\n" +
                "Harga Barang: " + hargaBarangTextView.getText().toString() + "\n" +
                "Diskon: " + diskonTextView.getText().toString() + "\n" +
                "Total Harga: " + totalHargaTextView.getText().toString();

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
        startActivity(Intent.createChooser(shareIntent, "Bagikan melalui"));

    }
}
