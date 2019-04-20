package cz.dnesdan.kiwi.test.data.model;

import java.util.List;

public class Flights {
    private String currency;
    private List<Flight> data;

    public String getCurrency() {
        return currency;
    }

    public List<Flight> getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Flights{" +
                "currency='" + currency + '\'' +
                ", data=" + data +
                '}';
    }
}
