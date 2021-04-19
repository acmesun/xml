package by.lukyanets.xml.builder;

import by.lukyanets.xml.entity.TouristVoucher;
import by.lukyanets.xml.handler.VoucherHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class VoucherSaxBuilder extends AbstractVoucherBuilder{
    private VoucherHandler handler = new VoucherHandler();
    private XMLReader reader;

    public VoucherSaxBuilder() {
        this(new HashSet<>());
    }

    public VoucherSaxBuilder(Set<TouristVoucher> vouchers) {
        super(vouchers);
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
        } catch (SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        reader.setContentHandler(handler);
    }

    @Override
    public void buildSetVouchers(String fileName) {
        try {
            reader.parse(fileName);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        setVouchers(handler.getVouchers());
    }
}

