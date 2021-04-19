package by.lukyanets.xml;

import by.lukyanets.xml.builder.AbstractVoucherBuilder;
import by.lukyanets.xml.builder.VoucherDomBuilder;
import by.lukyanets.xml.builder.VoucherSaxBuilder;
import by.lukyanets.xml.builder.VoucherStaxBuilder;

public class VoucherBuilderFactory {
    private enum TypeParser {
        SAX,
        STAX,
        DOM
    }

    private VoucherBuilderFactory() {

    }

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
                throw new EnumConstantNotPresentException(typeParser.getDeclaringClass(), typeParser.name());
        }
    }
}
