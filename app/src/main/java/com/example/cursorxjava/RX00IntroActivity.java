package com.example.cursorxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RX00IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx00_intro);

        // EMISOR
        Observable<String> numeroObservable =
                Observable.just("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");

        //RECEPTOR (Oyente)
        Observer<String> numerosObserver = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d("TAG1", "onSubscribe" + " HILO: " + Thread.currentThread().getName());
            }

            @Override
            public void onNext(@NonNull String numero) {
                Log.d("TAG1", "onNext: Numero:" + numero + " HILO: " + Thread.currentThread().getName());

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("TAG1", "onError" + " HILO: " + Thread.currentThread().getName());

            }

            @Override
            public void onComplete() {
                Log.d("TAG1", "onComplete" + " HILO: " + Thread.currentThread().getName());

            }
        };

        numeroObservable
                .subscribeOn(Schedulers.io())   // Hilo donde se ejecuta el observable
                .observeOn(AndroidSchedulers.mainThread()) // Hilo donde se ejecuta el observer
                .subscribe(numerosObserver);
    }
}