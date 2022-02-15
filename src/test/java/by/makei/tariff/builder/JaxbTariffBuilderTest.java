package by.makei.tariff.builder;

import by.makei.tariff.exception.CustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class JaxbTariffBuilderTest {

    public JaxbTariffBuilder jaxbTariffBuilder;
    public String fileName = "files/mobile_tariff.xml";
    //public String fileName = "/files/mobile_tariff.xml";

    @BeforeEach
    public void setUp() {
        jaxbTariffBuilder = new JaxbTariffBuilder();
    }

    @Test
    void buildTariffs() throws JAXBException, CustomException, FileNotFoundException {
        jaxbTariffBuilder.buildTariffs(fileName);
        int actual = jaxbTariffBuilder.tariffSet.size();
        int expected =7;
        assertEquals(expected,actual);
    }

}