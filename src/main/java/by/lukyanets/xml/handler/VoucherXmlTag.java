package by.lukyanets.xml.handler;

public enum VoucherXmlTag {
    VOUCHERS("tourist-vouchers"),
    CITY("city"),
    DEPARTURE_DATE("departure-date"),
    DAYS_NUMBER("number-days"),
    TRANSPORT("transport"),
    HOTEL_CHARACTERISTIC("hotel-characteristic"),
    COST("cost");

    private String value;

    VoucherXmlTag(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
