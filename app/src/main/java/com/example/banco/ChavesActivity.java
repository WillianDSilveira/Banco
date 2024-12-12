package com.example.banco;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class ChavesActivity extends AppCompatActivity {
    RepositorioPix repositorioPix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chaves);

        repositorioPix = new RepositorioPix(this);
        ListView listView = findViewById(R.id.editListChaves);

        List<Pix> chavesPix = repositorioPix.listarChaves();

        ArrayAdapter<Pix> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, chavesPix);

        listView.setAdapter(adapter);

    }
}