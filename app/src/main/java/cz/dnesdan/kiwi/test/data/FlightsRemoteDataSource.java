package cz.dnesdan.kiwi.test.data;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.Locale;

import cz.dnesdan.kiwi.test.BuildConfig;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/** Class for communication with the remote server **/
public class FlightsRemoteDataSource implements FlightsDataSource {

    private static final String LOG_TAG = FlightsRemoteDataSource.class.getSimpleName();

    private KiwiApiInterface apiInterface;

    private static FlightsRemoteDataSource INSTANCE = null;

    private CompositeDisposable disposable;

    public FlightsRemoteDataSource() {
        this.apiInterface = createApiInterface();
    }

    public static FlightsRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FlightsRemoteDataSource();
        }
        return INSTANCE;
    }

    public void destroyObservables() {
        if (INSTANCE != null) {
            if (disposable != null && !disposable.isDisposed()) {
                disposable.dispose();
                disposable = null;
            }
        }
    }

    public static KiwiApiInterface createApiInterface() {
        OkHttpClient okHttpClient = FlightsRemoteDataSource.createHttpClient();
        GsonConverterFactory gsonConverterFactory = FlightsRemoteDataSource.createGsonFactory();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(KiwiApiInterface.URL)
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();

        return retrofit.create(KiwiApiInterface.class);
    }

    public static OkHttpClient createHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor(message -> Log.d(LOG_TAG, message));
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }

        return builder.build();
    }

    public static GsonConverterFactory createGsonFactory() {
        return GsonConverterFactory.create();
    }

    @Override
    public void getFlights(@NonNull LoadFlightsCallback callback) {
        disposable = new CompositeDisposable();

        String country = Locale.getDefault().getCountry();

        Disposable flightsList = apiInterface.getPopularFlights(country)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(flights -> callback.onFlightsLoaded(flights.getData()),
                        throwable -> callback.onDataNotAvailable());

        disposable.add(flightsList);
    }
}
