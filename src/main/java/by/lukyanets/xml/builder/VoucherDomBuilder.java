package by.lukyanets.xml.builder;

import by.lukyanets.xml.data.AccommodationAndMeals;
import by.lukyanets.xml.data.Currency;
import by.lukyanets.xml.data.HotelRoom;
import by.lukyanets.xml.entity.TouristVoucher;
import by.lukyanets.xml.entity.VacationTour;
import by.lukyanets.xml.entity.WeekendTour;
import by.lukyanets.xml.handler.VoucherXmlTag;
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

public class VoucherDomBuilder {
    private Set<TouristVoucher> vouchers;
    private DocumentBuilder docBuilder;

    public VoucherDomBuilder() {
        vouchers = new HashSet<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public Set<TouristVoucher> getVouchers() {
        return vouchers;
    }

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
                vouchers.add(voucher);
            }
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    private TouristVoucher buildVoucher(Element voucherElement) {
        TouristVoucher voucher = null;
        if (voucherElement.getTagName().equals("weekend-tour")) {
            voucher = new WeekendTour();
        } else if (voucherElement.getTagName().equals("vacation-tour")) {
            voucher = new VacationTour();
        }
        voucher.setId(voucherElement.getAttribute("id"));
        voucher.setAverageRating(voucherElement.getAttribute("average-rating"));
        voucher.setCity(voucherElement.getElementsByTagName("city").item(0).getTextContent());
        voucher.setDepartureDate(LocalDate.parse(voucherElement.getElementsByTagName("departure-date").item(0).getTextContent()));
        voucher.setNumberOfDays(Integer.parseInt(voucherElement.getElementsByTagName("number-days").item(0).getTextContent()));
        voucher.setTransport(voucherElement.getElementsByTagName("transport").item(0).getTextContent());
        TouristVoucher.Hotel hotel = voucher.getHotel();
        Element hotelElement = (Element) voucherElement.getElementsByTagName("hotel-characteristic").item(0);
        hotel.setNumberOfStars(Integer.parseInt(getElementTextContext(hotelElement, "number-of-stars")));
        hotel.setType(AccommodationAndMeals.valueOf(getElementTextContext(hotelElement, VoucherXmlTag.TYPES_OF_ACCOMMODATION_AND_MEALS.getValue())));
        hotel.setHotelRoom(HotelRoom.valueOf(getElementTextContext(hotelElement, "type-of-hotel-room")));
        hotel.setTv(Boolean.parseBoolean(getElementTextContext(hotelElement, "tv")));
        hotel.setAirConditioning(Boolean.parseBoolean(getElementTextContext(hotelElement, "air-conditioning")));
        hotel.setBalcony(Boolean.parseBoolean(getElementTextContext(hotelElement, "balcony")));
        hotel.setWi_fi(Boolean.parseBoolean(getElementTextContext(hotelElement, "wi-fi")));
        TouristVoucher.Cost cost = voucher.getCost();
        Element costElement = (Element) voucherElement.getElementsByTagName("cost").item(0);
        cost.setCurrency(Currency.valueOf(getElementTextContext(costElement, "currency")));
        cost.setValue(Double.parseDouble(getElementTextContext(costElement, "value")));
        return voucher;
    }


    private static String getElementTextContext(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }
}
