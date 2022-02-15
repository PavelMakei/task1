package by.makei.tariff.builder;

import by.makei.tariff.exception.CustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StaxTariffBuilderTest {



    public StaxTariffBuilder staxTariffBuilder;
    public String fileName = "files/mobile_tariff.xml";
//    public String fileName = "/files/mobile_tariff.xml";

    @BeforeEach
    public void setUp() {
        staxTariffBuilder = new StaxTariffBuilder();
    }

    @Test
    void buildTariffs() throws CustomException {
        staxTariffBuilder.buildTariffs(fileName);
        int actual = staxTariffBuilder.tariffSet.size();
        int expected =7;
        assertEquals(expected, actual);
    }

}