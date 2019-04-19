package cz.dnesdan.kiwi.test.data.model;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Flight {
    private String flyTo;
    private String flyFrom;

    @SerializedName("flight_duration")
    private String flightDuration;

    private String distance;

    private String mapIdto;

    private String cityTo;

    private String cityFrom;

    private Country countryTo;

    private Country countryFrom;

    private Long aTime;

    private Long price;

    public String getMapIdto() {
        return mapIdto;
    }

    public String getFlyTo() {
        return flyTo;
    }

    public String getFlyFrom() {
        return flyFrom;
    }

    public String getFlightDuration() {
        return flightDuration;
    }

    public String getDistance() {
        return distance;
    }

    public String getCityTo() {
        return cityTo;
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public Country getCountryTo() {
        return countryTo;
    }

    public Country getCountryFrom() {
        return countryFrom;
    }

    public String getaTime() {
        Date date = new Date ();
        date.setTime(aTime*1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return simpleDateFormat.format(date);
    }

    public Long getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flyTo='" + flyTo + '\'' +
                ", flyFrom='" + flyFrom + '\'' +
                ", flightDuration='" + flightDuration + '\'' +
                ", distance='" + distance + '\'' +
                ", mapIdto='" + mapIdto + '\'' +
                ", cityTo='" + cityTo + '\'' +
                ", cityFrom='" + cityFrom + '\'' +
                ", countryTo=" + countryTo +
                ", countryFrom=" + countryFrom +
                ", aTime=" + aTime +
                ", price=" + price +
                '}';
    }
}
