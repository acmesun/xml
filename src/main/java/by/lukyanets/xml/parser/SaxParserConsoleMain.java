package by.lukyanets.xml.parser;

import by.lukyanets.xml.builder.VoucherSaxBuilder;
import by.lukyanets.xml.exception.VoucherErrorHandler;
import by.lukyanets.xml.handler.PrintDataHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class SaxParserConsoleMain {
    public static void main(String[] args) {
        /*try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader reader = parser.getXMLReader();
            reader.setContentHandler(new PrintDataHandler());
            reader.setErrorHandler(new VoucherErrorHandler());
            reader.parse("C:\\Users\\User\\epam\\xml\\src\\main\\resources\\voucher.xml");
        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }*/
        VoucherSaxBuilder saxBuilder = new VoucherSaxBuilder();
        saxBuilder.buildSetVouchers("C:\\Users\\User\\epam\\xml\\src\\main\\resources\\voucher.xml");
        System.out.println(saxBuilder.getVouchers());
    }
}
