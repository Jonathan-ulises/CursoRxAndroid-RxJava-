package com.example.cursorxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RX01DisposableActivity extends AppCompatActivity {

    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx01_disposable);

        // EMISOR
        Observable<String> numeroObservable =
                Observable.just("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");

        //RECEPTOR (Oyente)
        Observer<String> numerosObserver = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable = d;
                Log.d("TAG1", "onSubscribe" + " HILO: " + Thread.currentThread().getName());
            }

            @Override
            public void onNext(@NonNull String numero) {
                Log.d("TAG1", "isDispose: " + disposable.isDisposed());
                Log.d("TAG1", "onNext: Numero:" + numero + " HILO: " + Thread.currentThread().getName());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("TAG1", "onError" + " HILO: " + Thread.currentThread().getName());

            }

            @Override
            public void onComplete() {
                Log.d("TAG1", "isDispose: " + disposable.isDisposed());
                Log.d("TAG1", "onComplete" + " HILO: " + Thread.currentThread().getName());

            }
        };

        numeroObservable
                .subscribeOn(Schedulers.io())   // Hilo donde se ejecuta el observable
                .observeOn(AndroidSchedulers.mainThread()) // Hilo donde se ejecuta el observer
                .subscribe(numerosObserver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TAG1", "isDispose: " + disposable.isDisposed());
        //Libera la subscripcion
        disposable.dispose();
        Log.d("TAG1", "isDispose: " + disposable.isDisposed());

        Log.d("TAG1", "onDestroy -> Desechamos la subscripcion" + " HILO: " + Thread.currentThread().getName());

    }
}