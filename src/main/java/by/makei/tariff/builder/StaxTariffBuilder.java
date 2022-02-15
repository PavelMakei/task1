package by.makei.tariff.builder;

import by.makei.tariff.entity.*;
import by.makei.tariff.exception.CustomException;
import by.makei.tariff.util.CustomFileUtil;
import by.makei.tariff.util.impl.CustomFileUtilImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.time.Year;
import java.util.Set;

public class StaxTariffBuilder extends AbstractTariffBuilder {
    private static final Logger logger = LogManager.getLogger();


    public Set<AbstractTariff> getTariffs() {
        return tariffSet;
    }


    @Override
    public void buildTariffs(String fileName) throws CustomException {
        CustomFileUtil fileUtil = CustomFileUtilImpl.getInstance();
        File file = fileUtil.getFileFromStringForResourcesPackage(fileName);
        String name;
        try (
                InputStream input = new FileInputStream(file);) {
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals(TariffXmlTag.UNLIMITED_TARIFF.getValue()) || name.equals(TariffXmlTag.LIMITED_TARIFF.getValue())) {
                        AbstractTariff tariff = buildTariff(reader);
                        tariffSet.add(tariff);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            logger.log(Level.ERROR, "File {} not found", file, e);
        } catch (IOException e) {
            logger.log(Level.ERROR, "File {} can't be read", file, e);
        } catch (XMLStreamException e) {
            logger.log(Level.ERROR, "File {} can't be parsed", file, e);
        }
    }


    private AbstractTariff buildTariff(XMLStreamReader reader) throws XMLStreamException, CustomException {
        AbstractTariff tariff = reader.getLocalName().equals(UNLIMITED_TARIFF) ? new UnlimTariff() : new LimitedTariff();
        tariff.setTariffId(reader.getAttributeValue(null, TARIFF_ID));
        // null check
        String optionalTitle;
        optionalTitle = (reader.getAttributeValue(null, TITLE));
        tariff.setTitle(optionalTitle == null ? TITLE_BY_DEFAULT : optionalTitle);
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> getStartElement(reader, tariff);
                case XMLStreamConstants.END_ELEMENT -> {
                    String name = reader.getLocalName();
                    if (name.equals(UNLIMITED_TARIFF)
                            || name.equals(LIMITED_TARIFF)) {
                        return tariff;
                    }
                }
                default -> logger.log(Level.WARN, "Can't be reached");
            }
        }
        logger.log(Level.ERROR, "Parsing error. Unknown element in xml file");
        throw new CustomException("Parsing error. Unknown element in xml file ");
    }

    private void getStartElement(XMLStreamReader reader, AbstractTariff tariff) throws XMLStreamException, CustomException {
        String name = reader.getLocalName();
        String data = getTextFromXmlReader(reader);
        // TariffXmlTag currentTag = TariffXmlTag.valueOf(name.toUpperCase().replace(HYPHEN, UNDERSCORE));
        switch (name) {
            case NAME -> tariff.setTariffName(data);
            case YEAR -> tariff.setYear(Year.parse(data));
            case OPERATOR_NAME -> tariff.setOperator(data);
            case PAYROLL -> tariff.setPayroll(Double.parseDouble(data));
            case CALL_PRICES -> getPricesFromXML(reader, tariff.getCallPrice());
            case SMS_PRICE -> tariff.setSmsPrice(Double.parseDouble(data));
            case PARAMETERS -> getParametersFromXML(reader, tariff.getParameters());
            case UNLIMITED_TARIFF_PARAMETERS -> {
                UnlimTariff unlimTariff = (UnlimTariff) tariff;
                unlimTariff.setUnlimitedTariffParameters(data);
            }
            case LIMITED_TARIFF_PARAMETERS -> {
                LimitedTariff limitedTariff = (LimitedTariff) tariff;
                limitedTariff.setLimitedTariffParameters(data);
            }
            default -> {
                logger.log(Level.ERROR, "Unsupported tag: {}", name);
                throw new CustomException("Unsupported tag: " + name);
            }
        }
    }

    private void getParametersFromXML(XMLStreamReader reader, Parameters parameters) throws XMLStreamException {
        while (reader.hasNext()) {
            int type = reader.next();
            String name = reader.getLocalName();
            String data = getTextFromXmlReader(reader);
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    // TariffXmlTag currentTag = TariffXmlTag.valueOf(name.toUpperCase().replace(HYPHEN, UNDERSCORE));
                    switch (name) {
                        case HAVE_A_FAVORITE_NUMBER -> parameters.setFavoriteNumber(data);
                        case TARIFFING -> parameters.setTariffing(data);
                        case TARIFF_CONNECTION_FEE -> parameters.setConnectionPayment(data);
                        default -> logger.log(Level.ERROR, "Unsupported tag: {}", name);
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    if (name.equals(TARIFF_CONNECTION_FEE)) {
                        return;
                    }
                }
                default -> logger.log(Level.ERROR, "Unsupported tag: {}", name);
            }
        }
    }

    private void getPricesFromXML(XMLStreamReader reader, CallPrice callPrice) throws XMLStreamException {
        while (reader.hasNext()) {
            int type = reader.next();
            String name = reader.getLocalName();
            String data = getTextFromXmlReader(reader);
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    // TariffXmlTag currentTag = TariffXmlTag.valueOf(name.toUpperCase().replace(HYPHEN, UNDERSCORE));
                    switch (name) {
                        case INSIDE_NETWORK_CALLS -> callPrice.setInsideNetworkCall(Double.parseDouble(data));
                        case OTHER_NETWORK_CALLS -> callPrice.setOutNetworkCall(Double.parseDouble(data));
                        case WIRED_NETWORK_CALLS -> callPrice.setWiredCall(Double.parseDouble(data));
                        default -> logger.log(Level.ERROR, "Unsupported tag: {}", name);
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    if (name.equals(WIRED_NETWORK_CALLS)) {
                        return;
                    }
                }
                default -> logger.log(Level.ERROR, "Unsupported tag: {}", name);
            }
        }
    }

    private String getTextFromXmlReader(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }


}
