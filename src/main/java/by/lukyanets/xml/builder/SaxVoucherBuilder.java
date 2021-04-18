package by.lukyanets.xml.builder;

import by.lukyanets.xml.entity.Voucher;
import by.lukyanets.xml.exception.VoucherErrorHandler;
import by.lukyanets.xml.handler.VoucherHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Set;

public class SaxVoucherBuilder {
    private Set<Voucher> vouchers;
    private VoucherHandler handler = new VoucherHandler();
    private XMLReader reader;

    public SaxVoucherBuilder() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
        } catch (SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        reader.setErrorHandler(new VoucherErrorHandler());
        reader.setContentHandler(handler);
    }

    public Set<Voucher> getVouchers() {
        return vouchers;
    }

    public void buildSetVouchers(String fileName) {
        try {
            reader.parse(fileName);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }

    }
}
