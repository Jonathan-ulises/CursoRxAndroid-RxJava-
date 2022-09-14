package com.example.cursorxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.cursorxjava.databinding.ActivityRx03OperadoresBinding;
import com.jakewharton.rxbinding4.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.observables.GroupedObservable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RX03OperadoresActivity extends AppCompatActivity {

    ActivityRx03OperadoresBinding bind;

    private Disposable disposableJust;
    private Disposable disposableJustArray;
    private Disposable disposableFromArray;
    private Disposable disposableRange;
    private Disposable disposableRepeat;
    private Disposable disposableInterval;
    private Disposable disposableLDL;
    //TODO: OPTIMIZAR DISPOSABLE

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityRx03OperadoresBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

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
        //probarCreateLargaDuracionLambda();
        //probarBuffer();
        //probarMap();
        //probarFlatMap();
        //probarGroupBy();
        //probarScan();
        //probarWindow();

        //* OPERADORES QUE SELECTIVAMENTE EMITEN ITEM DE UN OBSERVABLE
        //probarDebounce();
        //probarDistinc();
        //probarElementAt();
        //probarFilter();
        probarFirst();
    }

    /*
    Just emite una lista de valores, unicamente puede emitir 10 valores.
     */
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

    /*
    Just pero con la opcion de array de primitivos. La lista cuenta como un elemento del just
     */
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

    /*
    FromArray permite emitir listas de valores
     */
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

    /*
    Establece un rango de valores a enviar, con un punto inicial y la cantidad de rangos
     */
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

    /*
    Repeat repite un rango establecido
     */
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

    /*
    Interva establece un perido de tiempo de ejecucion al observable.
    para que se detenga, se establece un take para tomar una cantidad de items
    generados por el interval.
     */
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

    /*
    Create crea un observable con emiciones personalizadas y controladas
     */
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
    private void probarCreateLargaDuracionLambda() {
        Log.d("TAG1", "---------------- CREATE LARGA DURACION (LAMBDA)-------------------");
        disposableLDL =  Observable.create((ObservableOnSubscribe<String>) emitter -> {
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

    /*
    Buffer agrupa items dependiendo a un valor
     */
    private void probarBuffer() {
        Log.d("TAG1", "---------------- BUFFER -------------------");
        Observable<Integer> integerObservable = Observable.just(1,2,3,4,5,6,7,8,9);
        integerObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .buffer(3)
                .subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<Integer> integers) {
                        Log.d("TAG1", "BUFFER -> onNext: ");
                        for (Integer integer : integers) {
                            Log.d("TAG1", "BUFFER ITEM -> " + integer);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /*
    El operador map transforma los items que se le pasan en otra cosa.
     */
    private void probarMap() {
        Log.d("TAG1", "---------------- MAP -------------------");
        List<Empleado> empleados = Empleado.setUpEmpleados();
        Observable.fromArray(empleados)
                .map(empleados1 -> {
                    List<String> nombres = new ArrayList<>();
                    for (Empleado e : empleados1) {
                        nombres.add(e.getNombre());
                    }
                    return nombres;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        s -> Log.d("TAG1", "MAP -> onNext: " + s),
                        e -> Log.d("TAG1", "MAP -> onError: " + e.getMessage()),
                        () -> Log.d("TAG1", "MAP -> onComplete")
                );
    }

    /*
    FlatMap transforma los items en observables y salen como singles observables
     */
    private void probarFlatMap() {
        Log.d("TAG1", "---------------- FLATMAP -------------------");
        Observable
                .just("item2")
                .flatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Throwable {
                        Log.d("TAG1", "INSIDE FLATMAP: " + s);
                        return Observable.just(s + " 1 ", s + " 2 ", s + " 3 ");
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        Log.d("TAG1", "FLATMAP -> onNext: " + s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /*
    Agrupa elementos en observables por medio de clave - valor.
    estos grupos son nuevos observables a los cuales se puede subscribir.
     */
    private void probarGroupBy() {
        Log.d("TAG1", "---------------- GROUP BY -------------------");
//        Observable<Integer> numeroObservable = Observable.just(1,2,3,4,5,6,7,8,9);
//        Observable<GroupedObservable<String, Integer>> groupedObservableObservable =
//                numeroObservable
//                        .groupBy(new Function<Integer, String>() {
//                            @Override
//                            public String apply(Integer integer) throws Throwable {
//                                return integer % 2 == 0 ? "PAR" : "IMPAR";
//                            }
//                        });
//
//        groupedObservableObservable
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<GroupedObservable<String, Integer>>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(@NonNull GroupedObservable<String, Integer> stringIntegerGroupedObservable) {
//                        stringIntegerGroupedObservable.subscribe(new Observer<Integer>() {
//                            @Override
//                            public void onSubscribe(@NonNull Disposable d) {
//
//                            }
//
//                            @Override
//                            public void onNext(@NonNull Integer integer) {
//                                if (stringIntegerGroupedObservable.getKey().equals("PAR")) {
//                                    Log.d("TAG1", "GROUP BY (PAR) -> onNext: " + integer);
//                                } else {
//                                    Log.d("TAG1", "GROUP BY (IMPAR) -> onNext: " + integer);
//                                }
//                            }
//
//                            @Override
//                            public void onError(@NonNull Throwable e) {
//
//                            }
//
//                            @Override
//                            public void onComplete() {
//
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

        //LAMBDA
        Observable<Integer> numeroObservable = Observable.just(1,2,3,4,5,6,7,8,9);
        Observable<GroupedObservable<String, Integer>> groupedObservableObservable =
                numeroObservable
                        .groupBy(integer -> integer % 2 == 0 ? "PAR" : "IMPAR");

        groupedObservableObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        stringIntegerGroupedObservable ->
                                stringIntegerGroupedObservable.subscribe(
                                        integer -> {
                                            if (stringIntegerGroupedObservable.getKey().equals("PAR")) {
                                                Log.d("TAG1", "GROUP BY (PAR) -> onNext: " + integer);
                                            } else {
                                                Log.d("TAG1", "GROUP BY (IMPAR) -> onNext: " + integer);
                                            }
                                        },
                                        e -> e.getMessage(),
                                        () -> {}
                                ),
                        e -> e.getMessage(),
                        () -> {}
                );
    }

    /*
    Transforma un item en otro item, pero tambien puedes usar elementos que ya han sido
    transformados.
     */
    private void probarScan() {
        Log.d("TAG1", "---------------- SCAN -------------------");
        Observable.just(1,2,3,4,5,6,7)
                .scan(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Throwable {
                        return integer + integer2;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        next -> Log.d("TAG1", "SCAN -> onNext: " + next)
                );
    }

    /*
    Subdivide los item de un observable en una venta (observable) que pueden emitir los items.
     */
    private void probarWindow() {
        Log.d("TAG1", "---------------- WINDOW -------------------");
        Observable<Observable<Integer>> observableObservable = Observable
                .range(1, 150)
                .window(3);
        observableObservable
                .subscribe(
                        e -> {
                            Log.d("TAG1", "siguiente Ventana");
                            e.subscribe(
                                            n -> Log.d("TAG1", "item en ventana: " + n)
                                    );
                        }
                );
    }

//    private void probarDebounce() {
//        Log.d("TAG1", "---------------- WINDOW -------------------");
//        Observable<String> observable = (Observable<String>) RxTextView.textChanges(bind.etQuery)
//                .debounce(500, TimeUnit.MILLISECONDS)
//                .map(e -> e.toString())
//                .subscribe(
//                        e -> {
//                            Log.d("TAG1", "onNext -> String de busqueda: " + e);
//                            bind.tvQuery.setText("Query: " + e);
//                        }
//                );
//    }

    /*
    Emite los valores eliminando items repetidos
     */
    private void probarDistinc() {
        Log.d("TAG1", "---------------- DISTINCT -------------------");
        Observable<Integer> numeroObservable = Observable.just(1,2,3,4,2,2,2,2,3,1);
        numeroObservable
                .distinct()
                .subscribe(
                        e -> Log.d("TAG1", "DISTINCT -> onNext: " + e)
                );
    }

    private void probarElementAt() {
        Log.d("TAG1", "---------------- ELEMENTAT -------------------");
        Observable<Integer> numeroObservable = Observable.just(1,2,3,4,2,2,2,2,3,1);
        numeroObservable.elementAt(7)
                .subscribe(
                        e -> Log.d("TAG1", "ELEMENTAT -> onNext: " + e)
                );
    }

    private void probarFilter() {
        Log.d("TAG1", "---------------- FILTER -------------------");
        Observable<Integer> numeroObservable = Observable.just(1,2,3,4,2,2,2,2,3,1);
//        numeroObservable
//                .filter(new Predicate<Integer>() {
//                    @Override
//                    public boolean test(Integer integer) throws Throwable {
//                        return integer % 2 == 0;
//                    }
//                })
//                .subscribe(
//                        e -> Log.d("TAG1", "FILTER -> onNext: " + e)
//                );

        numeroObservable
                .filter(integer -> integer % 2 == 0)
                .subscribe(
                        e -> Log.d("TAG1", "FILTER -> onNext: " + e)
                );
    }

    private void probarFirst() {
        Log.d("TAG1", "---------------- FIRST -------------------");
        Observable<Integer> numeroObservable = Observable.just(1,2,3,4,2,2,2,2,3,1);
        numeroObservable
                .first(0)
                .subscribe(
                        e -> Log.d("TAG1", "FIRST -> onNext: " + e)
                );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposableJust.dispose();
        disposableJustArray.dispose();
        disposableFromArray.dispose();
        disposableRange.dispose();
        disposableRepeat.dispose();
        disposableInterval.dispose();
        disposableLDL.dispose();
    }
}