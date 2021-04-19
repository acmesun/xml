package by.lukyanets.xml.handler;

public enum VoucherXmlTag {
    TOURIST_VOUCHERS("tourist-vouchers"),
    WEEKEND_TOUR("weekend-tour"),
    VACATION_TOUR("vacation_tour"),
    ID("id"),
    AVERAGE_RATING("average-rating"),
    CITY("city"),
    DEPARTURE_DATE("departure-date"),
    NUMBER_DAYS("number-days"),
    TRANSPORT("transport"),
    HOTEL_CHARACTERISTICS("hotel-characteristics"),
    NUMBER_OF_STARS("number-of-stars"),
    TYPES_OF_ACCOMMODATION_AND_MEALS("types-of-accommodation-and-meals"),
    TYPE_OF_HOTEL_ROOM("type-of-hotel-room"),
    TV("tv"),
    AIR_CONDITIONING("air-conditioning"),
    BALCONY("balcony"),
    WI_FI("wi-fi"),
    COST("cost"),
    CURRENCY("currency"),
    VALUE("value"),
    BIKE_TOUR("bike-tour"),
    VISA("visa");

    private String value;

    VoucherXmlTag(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
