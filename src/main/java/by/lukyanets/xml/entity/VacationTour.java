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

    public VacationTour(String id, String averageRating, String city, LocalDate departureDate, int numberOfDays, String transport, Hotel hotel, Cost cost, boolean visa) {
        super(id, averageRating, city, departureDate, numberOfDays, transport, hotel, cost);
        this.visa = visa;
    }

    public VacationTour() {

    }

    @Override
    public String toString() {
        return "VacationTour{" +
                "id='" + id + '\'' +
                ", averageRating='" + averageRating + '\'' +
                ", city='" + city + '\'' +
                ", departureDate=" + departureDate +
                ", numberOfDays=" + numberOfDays +
                ", transport='" + transport + '\'' +
                ", hotel=" + hotel +
                ", cost=" + cost +
                ", visa=" + visa +
                '}';
    }
}
