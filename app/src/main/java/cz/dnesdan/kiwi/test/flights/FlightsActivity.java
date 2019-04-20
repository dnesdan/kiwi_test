package cz.dnesdan.kiwi.test.flights;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cz.dnesdan.kiwi.test.R;
import cz.dnesdan.kiwi.test.data.FlightsRemoteDataSource;
import cz.dnesdan.kiwi.test.data.model.Flight;

/** Activity which display 5 popuplar flights from Brno**/
public class FlightsActivity extends AppCompatActivity implements FlightsContract.View {

    private static final String LOG_TAG = FlightsActivity.class.getSimpleName();

    @BindView(R.id.rv_flights)
    RecyclerView recyclerView;
    @BindView(R.id.pb_flights)
    ProgressBar progressBar;

    private FlightsContract.Presenter presenter;

    private FlightsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flights);

        ButterKnife.bind(this);

        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

        FlightsRemoteDataSource source = FlightsRemoteDataSource.getInstance();

        FlightsPresenter flightsPresenter = new FlightsPresenter(source, this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.loadFlights();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.kill();
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        progressBar.setVisibility(active ? View.VISIBLE : View.GONE);
        recyclerView.setVisibility(active ? View.GONE : View.VISIBLE);
    }

    @Override
    public void showFlights(List<Flight> flights) {
        Log.d(LOG_TAG, flights.toString());
        adapter = new FlightsAdapter(flights);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showLoadingFlightsError() {
        showMessage("Error during loading");
    }

    @Override
    public void showNoFlights() {
        showMessage("Error - no flights");
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public void setPresenter(FlightsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    private void showMessage(String message) {
        Snackbar.make(getWindow().getDecorView().getRootView(), message, Snackbar.LENGTH_LONG).show();
    }
}
