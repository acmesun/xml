package by.lukyanets.xml.handler;

import by.lukyanets.xml.entity.Voucher;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class VoucherHandler extends DefaultHandler {
    private Set<Voucher> vouchers;
    private Voucher current;
    private VoucherXmlTag currentXmlTag;
    private EnumSet<VoucherXmlTag> withText;
    private static final String ELEMENT_VOUCHER = "voucher";

    public VoucherHandler() {
        vouchers = new HashSet<>();
        withText = EnumSet.range(VoucherXmlTag.VOUCHERS, VoucherXmlTag.COST);
    }

    public Set<Voucher> getVouchers() {
        return vouchers;
    }

    public void startElement(String uri, String localName, String qName, Attributes attrs) {

    }

}
