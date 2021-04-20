package by.lukyanets.xml.handler;

import by.lukyanets.xml.data.AccommodationAndMeals;
import by.lukyanets.xml.data.Currency;
import by.lukyanets.xml.data.HotelRoom;
import by.lukyanets.xml.entity.TouristVoucher;
import by.lukyanets.xml.entity.VacationTour;
import by.lukyanets.xml.entity.WeekendTour;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import static by.lukyanets.xml.handler.VoucherXmlTag.*;

public class VoucherHandler extends DefaultHandler {
    private Set<TouristVoucher> vouchers;
    private TouristVoucher currentVoucher;
    private VoucherXmlTag currentXmlTag;
    private EnumSet<VoucherXmlTag> withText;
    private static final Logger logger = LogManager.getLogger(VoucherHandler.class);

    public VoucherHandler() {
        vouchers = new HashSet<>();
        withText = EnumSet.range(CITY, VALUE);
    }

    public Set<TouristVoucher> getVouchers() {
        return vouchers;
    }

    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if (qName.equals(WEEKEND_TOUR.getValue())) {
            currentVoucher = new WeekendTour();
            setAttributes(attrs);
        } else if (qName.equals(VACATION_TOUR.getValue())) {
            currentVoucher = new VacationTour();
            setAttributes(attrs);
        } else {
            VoucherXmlTag temp = valueOf(qName.toUpperCase().replace('-', '_'));
            if (withText.contains(temp)) {
                currentXmlTag = temp;
            }
        }
    }

    private void setAttributes(Attributes attrs) {
        currentVoucher.setId(attrs.getValue(0));
        currentVoucher.setAverageRating(attrs.getValue(1));
    }

    public void endElement(String uri, String localName, String qName) {
        if (qName.equals(WEEKEND_TOUR.getValue()) || qName.equals(VACATION_TOUR.getValue())) {
            vouchers.add(currentVoucher);
        }
    }

    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length);
        logger.warn("Current tag can not be null.");
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
                    logger.info("Set cost and hotel characteristics.");
                    break;
                default:
                    logger.error("There is mo such constant in enum.");
                    throw new EnumConstantNotPresentException(currentXmlTag.getDeclaringClass(), currentXmlTag.name());
            }
        }
        currentXmlTag = null;
    }
}
