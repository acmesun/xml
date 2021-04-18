package by.lukyanets.xml.exception;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class VoucherErrorHandler implements ErrorHandler {
    @Override
    public void warning(SAXParseException e) throws SAXException {

    }

    @Override
    public void error(SAXParseException e) throws SAXException {

    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {

    }
}
