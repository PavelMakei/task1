package by.makei.tariff.builder;

import by.makei.tariff.builder.handler.TariffErrorHandler;
import by.makei.tariff.builder.handler.TariffHandler;
import by.makei.tariff.util.CustomFileUtil;
import by.makei.tariff.util.impl.CustomFileUtilImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class SaxTariffBuilder extends TariffBuilder{
    private static final Logger logger = LogManager.getLogger();
    private XMLReader reader;
    private SAXParserFactory factory = SAXParserFactory.newInstance();
    private SAXParser saxParser;


    @Override
    public void buildTariffs(String filename) {
        CustomFileUtil fileUtil = CustomFileUtilImpl.getInstance();
        File file = fileUtil.getFileFromStringForResourcesPackage(filename);
        try {
            saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
            TariffHandler handler = new TariffHandler();
            reader.setContentHandler(handler);
            reader.setErrorHandler(new TariffErrorHandler());
            reader.parse(String.valueOf(file));
            tariffSet = handler.getTariffs();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
    }
}
