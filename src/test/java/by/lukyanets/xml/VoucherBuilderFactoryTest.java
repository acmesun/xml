package by.lukyanets.xml;

import by.lukyanets.xml.builder.AbstractVoucherBuilder;
import by.lukyanets.xml.entity.TouristVoucher;
import by.lukyanets.xml.factory.VoucherBuilderFactory;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toSet;
import static org.testng.Assert.assertEquals;

public class VoucherBuilderFactoryTest {
    private static final String FILE_NAME = "voucher.xml";

    @Test
    public void domParserTest() throws IOException {
        voucherParsingTest("DOM", "valid-vouchers.txt");
    }

    @Test
    public void saxParserTest() throws IOException {
        voucherParsingTest("SAX", "expected.txt");
    }

    @Test
    public void staxParserTest() throws IOException {
        voucherParsingTest("STAX", "expected.txt");
    }

    private String getFullFilePath(String filename) {
        return VoucherBuilderFactoryTest.class.getClassLoader().getResource(filename).getPath();
    }

    private void voucherParsingTest(String type, String expectedFile) throws IOException {
        AbstractVoucherBuilder builder = VoucherBuilderFactory.createVoucherBuilder(type);
        builder.buildSetVouchers(getFullFilePath(FILE_NAME));
        Set<String> actual = builder.getVouchers().stream().map(TouristVoucher::toString).collect(toSet());
        Set<String> expected = Arrays.stream(getFileText(expectedFile)
                .split("\r\n"))
                .filter(it -> !it.isEmpty())
                .collect(toSet());
        assertEquals(actual, expected);
    }

    private String getFileText(String filename) throws IOException {
        try (InputStream is = VoucherBuilderFactoryTest.class.getClassLoader().getResourceAsStream(filename)) {
            byte[] data = new byte[requireNonNull(is).available()];
            is.read(data);
            return new String(data);
        }
    }
}