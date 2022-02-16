package by.makei.tariff.builder;


import by.makei.tariff.entity.AbstractTariff;
import by.makei.tariff.entity.LimitedTariff;
import by.makei.tariff.entity.UnlimTariff;
import by.makei.tariff.exception.CustomException;
import by.makei.tariff.util.CustomFileUtil;
import by.makei.tariff.util.impl.CustomFileUtilImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class JaxbTariffBuilder extends AbstractTariffBuilder {
    public static final Logger logger = LogManager.getLogger();
    //TODO make it works

    @Override
    public void buildTariffs(String fileName) throws CustomException, JAXBException {
        CustomFileUtil fileUtil = CustomFileUtilImpl.getInstance();
        File file = fileUtil.getFileFromStringForResourcesPackage(fileName);
        File schema = fileUtil.getFileFromStringForResourcesPackage(fileName); //TODO add validator by schema to all builders?

        //InputStream stream = getClass().getResourceAsStream(/files/mobile_tariff.xml); //variant to read file from resources package
        byte[] bytes = new byte[0];
        try (InputStream stream = new FileInputStream(file)) {
            bytes = stream.readAllBytes();
        } catch (IOException e) {
            //TODO logger?...
            e.printStackTrace();
        }

        ByteArrayInputStream xmlContentBytes = new ByteArrayInputStream(bytes);
        JAXBContext context = JAXBContext.newInstance(Tariffs.class, AbstractTariff.class, UnlimTariff.class, LimitedTariff.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
//note: setting schema to null will turn validator off
        unmarshaller.setSchema(null);
        Object unmarshal = unmarshaller.unmarshal(xmlContentBytes);
        Object xmlObject = Tariffs.class.cast(unmarshal);
        logger.log(Level.ERROR, xmlObject);
    }

    @XmlRootElement(namespace = "http://www.itacademy.by/makei/tariffs", name = "tariffs")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Tariffs {
        @XmlElements({
                @XmlElement(name = "unlimited-tariff", type = UnlimTariff.class, namespace = "http://www.itacademy.by/makei/tariffs"),
                @XmlElement(name = "limited-tariff", type = LimitedTariff.class, namespace = "http://www.itacademy.by/makei/tariffs")
        })
//        @XmlAnyElement(lax=true)
//        @XmlElementRef
        private List<AbstractTariff> list = new ArrayList<>();

        public List<AbstractTariff> getList() {
            return list;
        }
    }


}
