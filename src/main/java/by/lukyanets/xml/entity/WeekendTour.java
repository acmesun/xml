package by.lukyanets.xml.entity;

import java.time.LocalDate;

public class WeekendTour extends TouristVoucher {
    private boolean bikeTour;

    public boolean isBikeTour() {
        return bikeTour;
    }

    public void setBikeTour(boolean bikeTour) {
        this.bikeTour = bikeTour;
    }

    public WeekendTour(int id, String averageRating, String city, LocalDate departureDate, int numberOfDays, String transport, Hotel hotel, Cost cost, boolean bikeTour) {
        super(id, averageRating, city, departureDate, numberOfDays, transport, hotel, cost);
        this.bikeTour = bikeTour;
    }

    public WeekendTour() {

    }
}
