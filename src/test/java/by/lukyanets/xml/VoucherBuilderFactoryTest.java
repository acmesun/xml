package by.lukyanets.xml;

import by.lukyanets.xml.builder.AbstractVoucherBuilder;
import by.lukyanets.xml.entity.TouristVoucher;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toSet;
import static org.testng.Assert.assertEquals;

public class VoucherBuilderFactoryTest {


    @Test
    public void domParserTest() throws IOException {
        AbstractVoucherBuilder builder = VoucherBuilderFactory.createVoucherBuilder("DOM");
        builder.buildSetVouchers(getFullFilePath("voucher.xml"));
        Set<String> actual = builder.getVouchers().stream().map(TouristVoucher::toString).collect(toSet());
        Set<String> expected = Arrays.stream(getFileText("valid-vouchers.txt")
                .split("\r\n"))
                .filter(it -> !it.isEmpty())
                .collect(toSet());
        assertEquals(actual, expected);
    }

    private String getFullFilePath(String filename) {
        return VoucherBuilderFactoryTest.class.getClassLoader().getResource(filename).getPath();
    }

    private String getFileText(String filename) throws IOException {
        try (InputStream is = VoucherBuilderFactoryTest.class.getClassLoader().getResourceAsStream(filename)) {
            byte[] data = new byte[requireNonNull(is).available()];
            is.read(data);
            return new String(data);
        }
    }
}