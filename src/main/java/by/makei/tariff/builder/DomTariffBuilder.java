package by.makei.tariff.builder;

import by.makei.tariff.entity.*;
import by.makei.tariff.util.impl.CustomFileUtilImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.time.Year;


public class DomTariffBuilder extends AbstractTariffBuilder {

    private static final Logger logger = LogManager.getLogger();
    private DocumentBuilderFactory factory;
    private DocumentBuilder documentBuilder;


    @Override
    public void buildTariffs(String fileName) {
        customFileUtil = CustomFileUtilImpl.getInstance();
        File file = customFileUtil.getFileFromStringForResourcesPackage(fileName);

        try {
            factory = DocumentBuilderFactory.newInstance();
            documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            document.getDocumentElement().normalize();

            NodeList tariffList = document.getElementsByTagName(UNLIMITED_TARIFF);
            for (int i = 0; i < tariffList.getLength(); i++) {
                Node node = tariffList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element tariffElement = (Element) node;
                    AbstractTariff tariff = buildTariffs(tariffElement);
                    tariffSet.add(tariff);
                }
            }

            tariffList = document.getElementsByTagName(LIMITED_TARIFF);
            for (int i = 0; i < tariffList.getLength(); i++) {
                Element tariffElement = (Element) tariffList.item(i);
                AbstractTariff tariff = buildTariffs(tariffElement);
                tariffSet.add(tariff);
            }

            //TODO if correct catch
        } catch (SAXException exception) {
            logger.log(Level.ERROR, "File was not parsed!", exception);
        } catch (IOException exception) {
            logger.log(Level.ERROR, "File was not read!", exception);
        } catch (ParserConfigurationException exception) {
            logger.log(Level.ERROR, "File was not parsed!", exception);
            exception.printStackTrace();
        }
    }

    public AbstractTariff buildTariffs(Element element) {
        AbstractTariff.AbstractBuilder tariff;
        if(element.getTagName().equals(UNLIMITED_TARIFF)) {
            tariff = UnlimTariff.newBuilder();
        }else {
            tariff = LimitedTariff.newBuilder();
        }
        String data = element.getAttribute(TARIFF_ID);
        tariff.setTariffId(data);
        data = element.getAttribute(TITLE);
        tariff.setTitle(data.equals("") ? TITLE_BY_DEFAULT : data);
        data = getElementTextContent(element, NAME);
        tariff.setTariffName(data);
        data = getElementTextContent(element, YEAR);
        tariff.setYear(Year.parse(data));
        data = getElementTextContent(element, OPERATOR_NAME);
        tariff.setOperator(data);
        data = getElementTextContent(element, PAYROLL);
        tariff.setPayroll(Double.valueOf(data));

        CallPrice prices = tariff.build().getCallPrice();
        data = getElementTextContent(element, INSIDE_NETWORK_CALLS);
        prices.setInsideNetworkCall(Double.valueOf(data));
        data = getElementTextContent(element, OTHER_NETWORK_CALLS);
        prices.setOutNetworkCall(Double.valueOf(data));
        data = getElementTextContent(element, WIRED_NETWORK_CALLS);
        prices.setWiredCall(Double.valueOf(data));
        data = getElementTextContent(element, SMS_PRICE);
        tariff.setSmsPrice(Double.valueOf(data));

        Parameters parameters = tariff.build().getParameters();
        data = getElementTextContent(element, HAVE_A_FAVORITE_NUMBER);
        parameters.setFavoriteNumber(data);
        data = getElementTextContent(element, TARIFFING);
        parameters.setTariffing(data);
        data = getElementTextContent(element, TARIFF_CONNECTION_FEE);
        parameters.setConnectionPayment(data);

        if (tariff.build().getType().equals("unlimited")) {
            UnlimTariff unlimTariff = (UnlimTariff) tariff.build();
            data = getElementTextContent(element, UNLIMITED_TARIFF_PARAMETERS);
            unlimTariff.setUnlimitedTariffParameters(data);
        } else {
            data = getElementTextContent(element, LIMITED_TARIFF_PARAMETERS);
            LimitedTariff limitedTariff = (LimitedTariff) tariff.build();
            limitedTariff.setLimitedTariffParameters(data);
        }
        return tariff.build();
    }

    private String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }
}


