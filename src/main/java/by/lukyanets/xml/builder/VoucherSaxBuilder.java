package by.lukyanets.xml.builder;

import by.lukyanets.xml.entity.TouristVoucher;
import by.lukyanets.xml.handler.VoucherHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Set;

public class VoucherSaxBuilder {
    private Set<TouristVoucher> vouchers;
    private VoucherHandler handler = new VoucherHandler();
    private XMLReader reader;

    public VoucherSaxBuilder() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
        } catch (SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        reader.setContentHandler(handler);
    }

    public Set<TouristVoucher> getVouchers() {
        return vouchers;
    }

    public void buildSetVouchers(String filename) {
        try {
            reader.parse(filename);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }


}
