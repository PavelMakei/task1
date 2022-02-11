package by.makei.tariff.validator.impl;

import by.makei.tariff.builder.handler.TariffErrorHandler;
import by.makei.tariff.util.CustomFileUtil;
import by.makei.tariff.util.impl.CustomFileUtilImpl;
import by.makei.tariff.validator.XmlByXsdValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlByXsdValidatorImpl implements XmlByXsdValidator {
    private static final Logger logger = LogManager.getLogger();
    private static final XmlByXsdValidatorImpl instance = new XmlByXsdValidatorImpl();

    private XmlByXsdValidatorImpl (){}

    public static XmlByXsdValidatorImpl getInstance(){return instance;}

    @Override
    public boolean validateXml(String XmlFileName, String XsdFileName) {
        CustomFileUtil fileUtil = CustomFileUtilImpl.getInstance();
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File fileLocation = fileUtil.getFileFromStringForResourcesPackage(XmlFileName);
        File schemaLocation = fileUtil.getFileFromStringForResourcesPackage(XsdFileName);
        try {
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(fileLocation);
            validator.setErrorHandler(new TariffErrorHandler());
            validator.validate(source);
            return true;
        } catch (SAXException | IOException e) {
            logger.log(Level.INFO, "{} is not correct, or valid", XmlFileName, e);
            return false;
        }
    }
}
