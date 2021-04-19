package by.lukyanets.xml.parser;

import by.lukyanets.xml.builder.VoucherSaxBuilder;

public class SaxParserConsoleMain {
    public static void main(String[] args) {
        VoucherSaxBuilder saxBuilder = new VoucherSaxBuilder();
        saxBuilder.buildSetVouchers("C:\\Users\\User\\epam\\xml\\src\\main\\resources\\voucher.xml");
        System.out.println(saxBuilder.getVouchers());
    }
}
