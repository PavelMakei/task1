package by.makei.tariff.builder.handler;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class TariffErrorHandler implements ErrorHandler {
    private static final Logger logger = LogManager.getLogger();

    public void warning(SAXParseException e) {
        logger.log(Level.WARN,"{} - {}",getLineColumnNumber(e), e.getMessage());
    }

    @Override
    public void error(SAXParseException e) {
        logger.log(Level.ERROR,"{} - {}",getLineColumnNumber(e), e.getMessage());
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        logger.log(Level.FATAL,"{} - {}",getLineColumnNumber(e), e.getMessage());
    }

    private String getLineColumnNumber(SAXParseException e) {
        // determine line and position of error
        return e.getLineNumber() + " : " + e.getColumnNumber();
    }
}
