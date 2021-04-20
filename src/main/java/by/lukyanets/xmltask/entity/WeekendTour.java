package by.lukyanets.xmltask.entity;

import java.time.LocalDate;

public class WeekendTour extends TouristVoucher {
    private boolean bikeTour;

    public boolean isBikeTour() {
        return bikeTour;
    }

    public void setBikeTour(boolean bikeTour) {
        this.bikeTour = bikeTour;
    }

    public WeekendTour(String id, String averageRating, String city, LocalDate departureDate, int numberOfDays, String transport, Hotel hotel, Cost cost, boolean bikeTour) {
        super(id, averageRating, city, departureDate, numberOfDays, transport, hotel, cost);
        this.bikeTour = bikeTour;
    }

    public WeekendTour() {

    }

    @Override
    public String toString() {
        return "WeekendTour{" +
                "id='" + id + '\'' +
                ", averageRating='" + averageRating + '\'' +
                ", city='" + city + '\'' +
                ", departureDate=" + departureDate +
                ", numberOfDays=" + numberOfDays +
                ", transport='" + transport + '\'' +
                ", hotel=" + hotel +
                ", cost=" + cost +
                ", bikeTour=" + bikeTour +
                '}';
    }
}
