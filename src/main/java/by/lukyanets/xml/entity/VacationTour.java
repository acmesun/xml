package by.lukyanets.xml.entity;

import java.time.LocalDate;

public class VacationTour extends TouristVoucher {
    private boolean visa;

    public boolean isVisa() {
        return visa;
    }

    public void setVisa(boolean visa) {
        this.visa = visa;
    }

    public VacationTour(int id, String averageRating, String city, LocalDate departureDate, int numberOfDays, String transport, Hotel hotel, Cost cost, boolean visa) {
        super(id, averageRating, city, departureDate, numberOfDays, transport, hotel, cost);
        this.visa = visa;
    }

    public VacationTour() {

    }



}
