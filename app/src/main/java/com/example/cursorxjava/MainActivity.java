package com.example.cursorxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.cursorxjava.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        clickEvent();
    }

    private void clickEvent() {
        bind.btnRX00Introduccion.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), RX00IntroActivity.class));
        });

        bind.btnRX01Disposable.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), RX01DisposableActivity.class));
        });

        bind.btnRX02CompositeDisposable.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), RX02CompositeDisposableActivity.class));
        });

        bind.btnRX02Operadores.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), RX03OperadoresActivity.class));
        });
    }
}