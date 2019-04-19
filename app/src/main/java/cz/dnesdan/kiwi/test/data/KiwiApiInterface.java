package cz.dnesdan.kiwi.test.data;

import cz.dnesdan.kiwi.test.data.model.Flights;
import io.reactivex.Single;
import retrofit2.http.GET;

public interface KiwiApiInterface {
    String URL = "https://api.skypicker.com/";

    String IMAGE_BASE_URL = "https://images.kiwi.com/photos/600x600/";

    //https://api.skypicker.com/flights?
    // v=2&
    // sort=popularity&
    // asc=0&
    // locale=en&
    // daysInDestinationFrom=&
    // daysInDestinationTo=&
    // affilid=&
    // children=0&
    // infants=0&
    // flyFrom=49.2-16.61-250km&
    // to=anywhere&
    // featureName=aggregateResults&
    // dateFrom=06/03/2017&
    // dateTo=06/04/2017&
    // typeFlight=oneway&
    // returnFrom=&
    // returnTo=&
    // one_per_date=0&
    // oneforcity=1&
    // wait_for_refresh=0&
    // adults=1&
    // limit=45

    @GET("/flights?v=3&sort=popularity&limit=5&fly_from=49.2-16.61-250km")
    Single<Flights> getPopularFlights();
}
