package by.lukyanets.xml.builder;

import by.lukyanets.xml.data.AccommodationAndMeals;
import by.lukyanets.xml.data.Currency;
import by.lukyanets.xml.data.HotelRoom;
import by.lukyanets.xml.entity.TouristVoucher;
import by.lukyanets.xml.entity.VacationTour;
import by.lukyanets.xml.entity.WeekendTour;
import by.lukyanets.xml.handler.VoucherXmlTag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static by.lukyanets.xml.handler.VoucherXmlTag.*;

public class VoucherStaxBuilder extends AbstractVoucherBuilder {
    private final static Logger logger = LogManager.getLogger(VoucherStaxBuilder.class);
    private XMLInputFactory inputFactory;

    public VoucherStaxBuilder() {
        this(new HashSet<>());
    }

    public VoucherStaxBuilder(Set<TouristVoucher> vouchers) {
        super(vouchers);
        inputFactory = XMLInputFactory.newInstance();
    }

    public void buildSetVouchers(String fileName) {
        XMLStreamReader reader;
        String name;
        logger.info("Trying to read file {}", fileName);
        try (FileInputStream input = new FileInputStream(fileName)) {
            reader = inputFactory.createXMLStreamReader(input);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (VACATION_TOUR == valueOf(prepareToEnum(name))
                            || WEEKEND_TOUR == valueOf(prepareToEnum(name))) {
                        TouristVoucher voucher = buildVoucher(reader);
                        getVouchers().add(voucher);
                    }
                }
            }
        } catch (IOException | XMLStreamException e) {
            logger.error("Exception {}.", e.toString());
        }
    }

    private TouristVoucher buildVoucher(XMLStreamReader reader) throws XMLStreamException {
        TouristVoucher voucher = null;
        String localName = reader.getLocalName();
        logger.info("Create new voucher");
        if (localName.equals(VACATION_TOUR.getValue())) {
            voucher = new VacationTour();
        } else if (localName.equals(WEEKEND_TOUR.getValue())) {
            voucher = new WeekendTour();
        }
        voucher.setId(reader.getAttributeValue(null, ID.getValue()));
        voucher.setAverageRating(reader.getAttributeValue(null, AVERAGE_RATING.getValue()));
        String name;
        logger.info("Parsing start.");
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (valueOf(prepareToEnum(name))) {
                        case CITY:
                            voucher.setCity(reader.getElementText());
                            break;
                        case DEPARTURE_DATE:
                            voucher.setDepartureDate(LocalDate.parse(reader.getElementText()));
                            break;
                        case NUMBER_DAYS:
                            voucher.setNumberOfDays(Integer.parseInt(reader.getElementText()));
                            break;
                        case TRANSPORT:
                            voucher.setTransport(reader.getElementText());
                            break;
                        case HOTEL_CHARACTERISTIC:
                            voucher.setHotel(getXMLHotelCharacteristic(reader, voucher));
                            break;
                        case COST:
                            voucher.setCost(getXMLCost(reader, voucher));
                            break;

                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (VACATION_TOUR == valueOf(prepareToEnum(name)) || WEEKEND_TOUR == valueOf(prepareToEnum(name))) {
                        return voucher;
                    }
            }
        }
        logger.error("Exception! Unknown element.");
        throw new XMLStreamException();
    }


    private TouristVoucher.Cost getXMLCost(XMLStreamReader reader, TouristVoucher voucher) throws XMLStreamException {
        TouristVoucher.Cost cost = null;
        if (voucher instanceof WeekendTour) {
            cost = new WeekendTour().new Cost();
        } else if (voucher instanceof VacationTour) {
            cost = new VacationTour().new Cost();
        }
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch ((valueOf(prepareToEnum(name)))) {
                        case CURRENCY:
                            cost.setCurrency(Currency.valueOf(reader.getElementText()));
                            break;
                        case VALUE:
                            cost.setValue(Double.parseDouble(reader.getElementText()));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (valueOf(name.toUpperCase()) == COST) {
                        return cost;
                    }
            }
        }
        logger.error("Exception! Unknown element in cost element.");
        throw new XMLStreamException();
    }

    private TouristVoucher.Hotel getXMLHotelCharacteristic(XMLStreamReader reader, TouristVoucher voucher) throws XMLStreamException {
        TouristVoucher.Hotel hotel = null;
        if (voucher instanceof WeekendTour) {
            hotel = new WeekendTour().new Hotel();
        } else if (voucher instanceof VacationTour) {
            hotel = new VacationTour().new Hotel();
        }
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (valueOf(prepareToEnum(name))) {
                        case NUMBER_OF_STARS:
                            hotel.setNumberOfStars(Integer.parseInt(getXMLText(reader)));
                            break;
                        case TYPES_OF_ACCOMMODATION_AND_MEALS:
                            hotel.setType(AccommodationAndMeals.valueOf(getXMLText(reader)));
                            break;
                        case TYPE_OF_HOTEL_ROOM:
                            hotel.setHotelRoom(HotelRoom.valueOf(getXMLText(reader)));
                            break;
                        case TV:
                            hotel.setTv(Boolean.parseBoolean(getXMLText(reader)));
                            break;
                        case AIR_CONDITIONING:
                            hotel.setAirConditioning(Boolean.parseBoolean(getXMLText(reader)));
                            break;
                        case BALCONY:
                            hotel.setBalcony(Boolean.parseBoolean(getXMLText(reader)));
                            break;
                        case WI_FI:
                            hotel.setWi_fi(Boolean.parseBoolean(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (valueOf(prepareToEnum(name)) == HOTEL_CHARACTERISTIC) {
                        return hotel;
                    }
            }
        }
        logger.error("Exception! Unknown element in hotel characteristics.");
        throw new XMLStreamException();
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        return reader.getElementText();
    }

    private String prepareToEnum(String name) {
        return name.toUpperCase().replace('-', '_');
    }
}
