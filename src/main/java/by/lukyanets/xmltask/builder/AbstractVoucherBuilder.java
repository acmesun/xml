package by.lukyanets.xmltask.builder;

import by.lukyanets.xmltask.entity.TouristVoucher;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractVoucherBuilder {

    private Set<TouristVoucher> vouchers;

    public AbstractVoucherBuilder(Set<TouristVoucher> vouchers) {
        this.vouchers = vouchers;
    }

    public AbstractVoucherBuilder() {
        this.vouchers = new HashSet<>();
    }

    public Set<TouristVoucher> getVouchers() {
        return vouchers;
    }

    protected void setVouchers(Set<TouristVoucher> vouchers) {
        this.vouchers = vouchers;
    }

    public abstract void buildSetVouchers(String fileName);
}
