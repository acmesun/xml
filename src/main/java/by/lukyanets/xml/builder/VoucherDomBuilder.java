package by.lukyanets.xml.builder;

import by.lukyanets.xml.data.AccommodationAndMeals;
import by.lukyanets.xml.data.Currency;
import by.lukyanets.xml.data.HotelRoom;
import by.lukyanets.xml.entity.TouristVoucher;
import by.lukyanets.xml.entity.VacationTour;
import by.lukyanets.xml.entity.WeekendTour;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static by.lukyanets.xml.handler.VoucherXmlTag.*;

public class VoucherDomBuilder extends AbstractVoucherBuilder {

    private DocumentBuilder docBuilder;
    private static final Logger logger = LogManager.getLogger(VoucherDomBuilder.class);

    public VoucherDomBuilder() {
        this(new HashSet<>());
    }

    public VoucherDomBuilder(Set<TouristVoucher> vouchers) {
        super(vouchers);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.error("Exception {}! Can not be parse.", e.toString());
        }
    }

    @Override
    public void buildSetVouchers(String fileName) {
        Document doc;
        try {
            doc = docBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            NodeList vouchersWeekendList = root.getChildNodes();
            for (int i = 0; i < vouchersWeekendList.getLength(); i++) {
                if (!(vouchersWeekendList.item(i) instanceof Element)) {
                    continue;
                }
                Element voucherElement = (Element) vouchersWeekendList.item(i);
                TouristVoucher voucher = buildVoucher(voucherElement);
                getVouchers().add(voucher);
            }
        } catch (SAXException | IOException e) {
            logger.error("Exception {}. Try again.", e.toString());
            e.printStackTrace();
        }
    }

    private TouristVoucher buildVoucher(Element voucherElement) {
        logger.error(" Check and Creation new voucher.");
        TouristVoucher voucher = null;
        if (voucherElement.getTagName().equals(WEEKEND_TOUR.getValue())) {
            voucher = new WeekendTour();
        } else if (voucherElement.getTagName().equals(VACATION_TOUR.getValue())) {
            voucher = new VacationTour();
        }
        logger.info("Parsing start.");
        voucher.setId(voucherElement.getAttribute(ID.getValue()));
        voucher.setAverageRating(voucherElement.getAttribute(AVERAGE_RATING.getValue()));
        voucher.setCity(voucherElement.getElementsByTagName(CITY.getValue()).item(0).getTextContent());
        voucher.setDepartureDate(LocalDate.parse(voucherElement.getElementsByTagName(DEPARTURE_DATE.getValue()).item(0).getTextContent()));
        voucher.setNumberOfDays(Integer.parseInt(voucherElement.getElementsByTagName(NUMBER_DAYS.getValue()).item(0).getTextContent()));
        voucher.setTransport(voucherElement.getElementsByTagName(TRANSPORT.getValue()).item(0).getTextContent());
        TouristVoucher.Hotel hotel = voucher.getHotel();
        Element hotelElement = (Element) voucherElement.getElementsByTagName(HOTEL_CHARACTERISTIC.getValue()).item(0);
        hotel.setNumberOfStars(Integer.parseInt(getElementTextContext(hotelElement, NUMBER_OF_STARS.getValue())));
        hotel.setType(AccommodationAndMeals.valueOf(getElementTextContext(hotelElement, TYPES_OF_ACCOMMODATION_AND_MEALS.getValue())));
        hotel.setHotelRoom(HotelRoom.valueOf(getElementTextContext(hotelElement, TYPE_OF_HOTEL_ROOM.getValue())));
        hotel.setTv(Boolean.parseBoolean(getElementTextContext(hotelElement, TV.getValue())));
        hotel.setAirConditioning(Boolean.parseBoolean(getElementTextContext(hotelElement, AIR_CONDITIONING.getValue())));
        hotel.setBalcony(Boolean.parseBoolean(getElementTextContext(hotelElement, BALCONY.getValue())));
        hotel.setWi_fi(Boolean.parseBoolean(getElementTextContext(hotelElement, WI_FI.getValue())));
        TouristVoucher.Cost cost = voucher.getCost();
        Element costElement = (Element) voucherElement.getElementsByTagName(COST.getValue()).item(0);
        cost.setCurrency(Currency.valueOf(getElementTextContext(costElement, CURRENCY.getValue())));
        cost.setValue(Double.parseDouble(getElementTextContext(costElement, VALUE.getValue())));
        logger.info("Parsing end.");
        return voucher;
    }

    private static String getElementTextContext(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }
}
