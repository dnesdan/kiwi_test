package cz.dnesdan.kiwi.test.flights;

import java.util.List;

import cz.dnesdan.kiwi.test.BasePresenter;
import cz.dnesdan.kiwi.test.BaseView;
import cz.dnesdan.kiwi.test.data.model.Flight;

public interface FlightsContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showFlights(List<Flight> flights);

        void showLoadingFlightsError();

        void showNoFlights();

        boolean isActive();
    }

    interface Presenter extends BasePresenter {

        void loadFlights();

        void kill();
    }
}
