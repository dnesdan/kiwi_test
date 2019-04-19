package cz.dnesdan.kiwi.test.data;

import java.util.List;

import androidx.annotation.NonNull;
import cz.dnesdan.kiwi.test.data.model.Flight;

public interface  FlightsDataSource {

    interface LoadFlightsCallback {

        void onFlightsLoaded(List<Flight> flights);

        void onDataNotAvailable();
    }

    void getFlights(@NonNull LoadFlightsCallback callback);
}
