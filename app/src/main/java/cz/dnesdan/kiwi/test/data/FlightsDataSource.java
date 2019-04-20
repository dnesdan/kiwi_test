package cz.dnesdan.kiwi.test.data;

import java.util.List;

import androidx.annotation.NonNull;
import cz.dnesdan.kiwi.test.data.model.Flight;

/** Interface for describing methods how to obtain data from Kiwi **/
public interface  FlightsDataSource {

    interface LoadFlightsCallback {

        void onFlightsLoaded(List<Flight> flights);

        void onDataNotAvailable();
    }

    void getFlights(@NonNull LoadFlightsCallback callback);
}
