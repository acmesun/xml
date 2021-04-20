package by.lukyanets.xmltask.factory;

import by.lukyanets.xmltask.builder.AbstractVoucherBuilder;
import by.lukyanets.xmltask.builder.VoucherDomBuilder;
import by.lukyanets.xmltask.builder.VoucherSaxBuilder;
import by.lukyanets.xmltask.builder.VoucherStaxBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VoucherBuilderFactory {

    private static final Logger logger = LogManager.getLogger(VoucherBuilderFactory.class);

    public static AbstractVoucherBuilder createVoucherBuilder(String typeOfParser) {

        TypeParser typeParser = TypeParser.valueOf(typeOfParser.toUpperCase());

        switch (typeParser) {
            case DOM:
                return new VoucherDomBuilder();
            case STAX:
                return new VoucherStaxBuilder();
            case SAX:
                return new VoucherSaxBuilder();
            default:
                logger.error("Exception from VoucherBuilderFactory.");
                throw new EnumConstantNotPresentException(typeParser.getDeclaringClass(), typeParser.name());
        }
    }

    private enum TypeParser {
        SAX,
        STAX,
        DOM
    }

    private VoucherBuilderFactory() {

    }
}
