package by.lukyanets.xml.entity;


import by.lukyanets.xml.data.Currency;
import by.lukyanets.xml.data.HotelRoom;
import by.lukyanets.xml.data.AccommodationAndMeals;

import java.time.LocalDate;


public abstract class TouristVoucher {
    private int id;
    private String averageRating;
    private String city;
    private LocalDate departureDate;
    private int numberOfDays;
    private String transport;
    private Hotel hotel = new Hotel();
    private Cost cost = new Cost();

    public TouristVoucher() {
    }

    public TouristVoucher(int id, String averageRating, String city, LocalDate departureDate, int numberOfDays, String transport, Hotel hotel, Cost cost) {
        this.id = id;
        this.averageRating = averageRating;
        this.city = city;
        this.departureDate = departureDate;
        this.numberOfDays = numberOfDays;
        this.transport = transport;
        this.hotel = hotel;
        this.cost = cost;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Cost getCost() {
        return cost;
    }

    public void setCost(Cost cost) {
        this.cost = cost;
    }

    public class Hotel {
        private int numberOfStars;
        private AccommodationAndMeals type;
        private HotelRoom hotelRoom;
        private boolean tv;
        private boolean airConditioning;
        private boolean balcony;
        private boolean wi_fi;

        public Hotel() {
        }

        public Hotel(int numberOfStars, AccommodationAndMeals type, HotelRoom hotelRoom, boolean tv, boolean airConditioning, boolean balcony, boolean wi_fi) {
            this.numberOfStars = numberOfStars;
            this.type = type;
            this.hotelRoom = hotelRoom;
            this.tv = tv;
            this.airConditioning = airConditioning;
            this.balcony = balcony;
            this.wi_fi = wi_fi;
        }

        public int getNumberOfStars() {
            return numberOfStars;
        }

        public void setNumberOfStars(int numberOfStars) {
            this.numberOfStars = numberOfStars;
        }

        public AccommodationAndMeals getType() {
            return type;
        }

        public void setType(AccommodationAndMeals type) {
            this.type = type;
        }

        public HotelRoom getHotelRoom() {
            return hotelRoom;
        }

        public void setHotelRoom(HotelRoom hotelRoom) {
            this.hotelRoom = hotelRoom;
        }

        public boolean isTv() {
            return tv;
        }

        public void setTv(boolean tv) {
            this.tv = tv;
        }

        public boolean isAirConditioning() {
            return airConditioning;
        }

        public void setAirConditioning(boolean airConditioning) {
            this.airConditioning = airConditioning;
        }

        public boolean isBalcony() {
            return balcony;
        }

        public void setBalcony(boolean balcony) {
            this.balcony = balcony;
        }

        public boolean isWi_fi() {
            return wi_fi;
        }

        public void setWi_fi(boolean wi_fi) {
            this.wi_fi = wi_fi;
        }
    }

    public class Cost {
        private Currency currency;
        private int value;

        public Cost() {
        }

        public Cost(Currency currency, int value) {
            this.currency = currency;
            this.value = value;
        }

        public Currency getCurrency() {
            return currency;
        }

        public void setCurrency(Currency currency) {
            this.currency = currency;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }


}
