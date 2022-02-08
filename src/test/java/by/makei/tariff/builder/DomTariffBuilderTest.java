package by.makei.tariff.builder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DomTariffBuilderTest {
    public DomTariffBuilder domTariffBuilder;
    public String fileName = "files/mobile_tariff.xml";

    @BeforeEach
    public void setUp() {
        domTariffBuilder = new DomTariffBuilder();
    }

    @Test
    void buildTariffs() {
        domTariffBuilder.buildTariffs(fileName);
        int actual = domTariffBuilder.tariffSet.size();
        int expected =7;
        assertTrue(expected==actual);
    }
}