package by.makei.tariff.builder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaxTariffBuilderTest {

    public SaxTariffBuilder saxTariffBuilder;
    public String fileName = "files/mobile_tariff.xml";

    @BeforeEach
    public void setUp() {
        saxTariffBuilder = new SaxTariffBuilder();
    }

    @Test
    void buildTariffs() {
        saxTariffBuilder.buildTariffs(fileName);
        int actual = saxTariffBuilder.tariffSet.size();
        int expected =7;
        assertTrue(expected==actual);
    }


}