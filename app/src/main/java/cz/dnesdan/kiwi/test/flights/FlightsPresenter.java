package cz.dnesdan.kiwi.test.flights;

import android.util.Log;

import java.util.List;

import cz.dnesdan.kiwi.test.data.FlightsDataSource;
import cz.dnesdan.kiwi.test.data.FlightsRemoteDataSource;
import cz.dnesdan.kiwi.test.data.model.Flight;

public class FlightsPresenter implements FlightsContract.Presenter {

    private static final String LOG_TAG = FlightsPresenter.class.getSimpleName();

    private FlightsContract.View flightsView;
    private FlightsRemoteDataSource source;

    public FlightsPresenter(FlightsRemoteDataSource source, FlightsContract.View flightsView) {
        this.flightsView = flightsView;
        flightsView.setPresenter(this);
        this.source = source;
    }

    @Override
    public void loadFlights() {
        flightsView.setLoadingIndicator(true);
        source.getFlights(new FlightsDataSource.LoadFlightsCallback() {
            @Override
            public void onFlightsLoaded(List<Flight> flights) {
                if (!flightsView.isActive()) {
                    return;
                }
                flightsView.setLoadingIndicator(false);
                flightsView.showFlights(flights);
            }

            @Override
            public void onDataNotAvailable() {
                if (!flightsView.isActive())
                    return;
                flightsView.setLoadingIndicator(false);
                flightsView.showNoFlights();
                Log.d(LOG_TAG, "Nothing loaded");
            }
        });
    }

    @Override
    public void kill() {
        source.destroyObservables();
    }

    @Override
    public void start() {
        loadFlights();
    }
}
