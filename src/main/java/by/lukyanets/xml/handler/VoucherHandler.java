package by.lukyanets.xml.handler;

import by.lukyanets.xml.data.AccommodationAndMeals;
import by.lukyanets.xml.data.Currency;
import by.lukyanets.xml.data.HotelRoom;
import by.lukyanets.xml.entity.TouristVoucher;
import by.lukyanets.xml.entity.VacationTour;
import by.lukyanets.xml.entity.WeekendTour;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class VoucherHandler extends DefaultHandler {
    private Set<TouristVoucher> vouchers;
    private TouristVoucher currentVoucher;
    private VoucherXmlTag currentXmlTag;
    private EnumSet<VoucherXmlTag> withText;

    public VoucherHandler() {
        vouchers = new HashSet<>();
        withText = EnumSet.range(VoucherXmlTag.CITY, VoucherXmlTag.VALUE);
    }

    public Set<TouristVoucher> getVouchers() {
        return vouchers;
    }

    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if (qName.equals("weekend-tour")) {
            currentVoucher = new WeekendTour();
            currentVoucher.setId(attrs.getValue(0));
            currentVoucher.setAverageRating(attrs.getValue(1));
        } else if (qName.equals("vacation-tour")) {
            currentVoucher = new VacationTour();
            currentVoucher.setId(attrs.getValue(0));
            currentVoucher.setAverageRating(attrs.getValue(1));
        } else {
            VoucherXmlTag temp = VoucherXmlTag.valueOf(qName.toUpperCase().replace('-', '_'));
            if (withText.contains(temp)) {
                currentXmlTag = temp;
            }
        }
    }

    public void endElement(String uri, String localName, String qName) {
        if (qName.equals("weekend-tour") || qName.equals("vacation-tour")) {
            vouchers.add(currentVoucher);
        }
    }

    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length);
        if (currentXmlTag != null) {
            switch (currentXmlTag) {
                case CITY:
                    currentVoucher.setCity(data);
                    break;
                case DEPARTURE_DATE:
                    currentVoucher.setDepartureDate(LocalDate.parse(data));
                    break;
                case NUMBER_DAYS:
                    currentVoucher.setNumberOfDays(Integer.parseInt(data));
                    break;
                case TRANSPORT:
                    currentVoucher.setTransport(data);
                    break;
                case NUMBER_OF_STARS:
                    currentVoucher.getHotel().setNumberOfStars(Integer.parseInt(data));
                    break;
                case TYPES_OF_ACCOMMODATION_AND_MEALS:
                    currentVoucher.getHotel().setType(AccommodationAndMeals.valueOf(data));
                    break;
                case TYPE_OF_HOTEL_ROOM:
                    currentVoucher.getHotel().setHotelRoom(HotelRoom.valueOf(data));
                    break;
                case TV:
                    currentVoucher.getHotel().setTv(Boolean.parseBoolean(data));
                    break;
                case AIR_CONDITIONING:
                    currentVoucher.getHotel().setAirConditioning(Boolean.parseBoolean(data));
                    break;
                case BALCONY:
                    currentVoucher.getHotel().setBalcony(Boolean.parseBoolean(data));
                    break;
                case WI_FI:
                    currentVoucher.getHotel().setWi_fi(Boolean.parseBoolean(data));
                    break;
                case CURRENCY:
                    currentVoucher.getCost().setCurrency(Currency.valueOf(data));
                    break;
                case VALUE:
                    currentVoucher.getCost().setValue(Double.parseDouble(data));
                    break;
                case COST:
                case HOTEL_CHARACTERISTIC:
                    // Do nothing
                    break;
                default:
                    throw new EnumConstantNotPresentException(currentXmlTag.getDeclaringClass(), currentXmlTag.name());
            }
        }
        currentXmlTag = null;
    }
}
