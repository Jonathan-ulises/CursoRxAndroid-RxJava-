package com.example.cursorxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RX03OperadoresActivity extends AppCompatActivity {

    private Disposable disposableJust;
    private Disposable disposableJustArray;
    private Disposable disposableFromArray;
    private Disposable disposableRange;
    private Disposable disposableRepeat;
    private Disposable disposableInterval;
    //TODO: OPTIMIZAR DISPOSABLE

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx03_operadores);

        //* OPERADORES QUE CREAN OBSERVABLES
        //probarJust();
        //probarJustArray();
        //probarFromArray();
        //probarRange();
        //probarRepeat();
        //probarInterval();
        //probarCreate();
        //probarCreateException();
        //probarCreateLargaDuracion();
        probarCreateLargaDuracionLambda();

    }

    private void probarJust() {
        Log.d("TAG1", "---------------- JUST -------------------");
        Observable.just("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<String>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                disposableJust = d;
                            }

                            @Override
                            public void onNext(@NonNull String s) {
                                Log.d("TAG1", "JUST -> onNext: " + s);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        }
                );
    }

    private void probarJustArray() {
        Log.d("TAG1", "---------------- JUSTARRAY -------------------");
        String[] numeros = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        Observable.just(numeros)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<String[]>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                disposableJustArray = d;
                            }

                            @Override
                            public void onNext(String @NonNull [] strings) {
                                Log.d("TAG1", "JUSTARRAY -> onNext: " + strings.length);

                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        }
                );
    }

    private void probarFromArray() {
        Log.d("TAG1", "---------------- FROMARRAY -------------------");
        String[] numeros = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        Observable.fromArray(numeros)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposableFromArray = d;
                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        Log.d("TAG1", "FROMARRAY -> onNext: " + s);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void probarRange() {
        Log.d("TAG1", "---------------- RANGE -------------------");
        Observable.range(7, 17)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposableRange = d;
                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        Log.d("TAG1", "RANGE -> onNext: " + integer);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void probarRepeat() {
        Log.d("TAG1", "---------------- REPEAT -------------------");
        Observable
                .range(10, 3)
                .repeat(4)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposableRepeat = d;
                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        Log.d("TAG1", "REPEAT -> onNext: " + integer);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void probarInterval() {
        Log.d("TAG1", "---------------- INTERVAL -------------------");
        Observable.interval(1, TimeUnit.SECONDS)
                .take(10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposableInterval = d;
                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {
                        Log.d("TAG1", "INTERVAL -> onNext: " + aLong);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void probarCreate() {
        Log.d("TAG1", "---------------- CREATE -------------------");
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                try {
                    Log.d("TAG1", "subscribe + hilo: " + Thread.currentThread().getName());
                    emitter.onNext("J");
                    emitter.onNext("O");
                    emitter.onNext("N");
                    emitter.onNext("A");
                    emitter.onNext("T");
                    emitter.onNext("H");
                    emitter.onNext("A");
                    emitter.onNext("N");
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        Log.d("TAG1", "CREATE -> onNext: " + s + " hilo: " + Thread.currentThread().getName());

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void probarCreateException() {
        Log.d("TAG1", "---------------- CREATE EXCEPTION-------------------");
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                try {
                    emitter.onNext(15/3);
                    emitter.onNext(3/0);
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Integer o) {
                        Log.d("TAG1", "CREATE EXCEPTION -> onNext: " + o);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("TAG1", "CREATE EXCEPTION -> onError: " + e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private String largaDuracion() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Terminado";
    }

    private void probarCreateLargaDuracion() {
        Log.d("TAG1", "---------------- CREATE LARGA DURACION-------------------");
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                try {
                    emitter.onNext(largaDuracion());
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<String>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@NonNull String s) {
                                Log.d("TAG1", "CREATE LARGA DURACION -> onNext: " + s);

                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                Log.d("TAG1", "CREATE LARGA DURACION -> onError: " + e.getMessage());

                            }

                            @Override
                            public void onComplete() {

                            }
                        }
                );
    }

    // USO INTERFACE NORMAL
    Sumar sumer = new Sumar() {
        @Override
        public int apply(int a, int b) {
            int resultado;
            resultado = a + b;
            return resultado;
        }
    };

    // USO INTERFACE LAMBDA
    Sumar sumarL = (a, b) -> a + b;
    Disposable disposableLmd;
    private void probarCreateLargaDuracionLambda() {
        Log.d("TAG1", "---------------- CREATE LARGA DURACION (LAMBDA)-------------------");
        Observable.create((ObservableOnSubscribe<String>) emitter -> {
            try {
                emitter.onNext(largaDuracion());
            } catch (Exception e) {
                emitter.onError(e);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        s -> Log.d("TAG1", "CREATE LARGA DURACION -> onNext: " + s),
                        e -> Log.d("TAG1", "CREATE LARGA DURACION -> onError: " + e.getMessage()),
                        () -> Log.d("TAG1", "onComplete")
                );
    }

    private void probarLambda() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposableJust.dispose();
        disposableJustArray.dispose();
        disposableFromArray.dispose();
        disposableRange.dispose();
        disposableRepeat.dispose();
        disposableRepeat.dispose();
    }
}