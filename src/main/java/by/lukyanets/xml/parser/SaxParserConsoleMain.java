package by.lukyanets.xml.parser;

import by.lukyanets.xml.VoucherBuilderFactory;
import by.lukyanets.xml.builder.AbstractVoucherBuilder;
import by.lukyanets.xml.builder.VoucherDomBuilder;
import by.lukyanets.xml.builder.VoucherSaxBuilder;
import by.lukyanets.xml.builder.VoucherStaxBuilder;

public class SaxParserConsoleMain {
    public static void main(String[] args) {


        AbstractVoucherBuilder builder = VoucherBuilderFactory.createVoucherBuilder("STAX");
        builder.buildSetVouchers("C:\\Users\\User\\epam\\xml\\src\\main\\resources\\voucher.xml");
        System.out.println(builder.getVouchers());

        /*VoucherDomBuilder domBuilder = new VoucherDomBuilder();
        domBuilder.buildSetVouchers("C:\\Users\\User\\epam\\xml\\src\\main\\resources\\voucher.xml");
        System.out.println(domBuilder.getVouchers());

        VoucherStaxBuilder staxBuilder = new VoucherStaxBuilder();
        staxBuilder.buildSetVouchers("C:\\Users\\User\\epam\\xml\\src\\main\\resources\\voucher.xml");
        System.out.println(staxBuilder.getVouchers());*/
    }
}
