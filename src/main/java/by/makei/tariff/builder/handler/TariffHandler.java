package by.makei.tariff.builder.handler;

import by.makei.tariff.builder.TariffXmlTag;
import by.makei.tariff.entity.LimitedTariff;
import by.makei.tariff.entity.Tariff;
import by.makei.tariff.entity.UnlimTariff;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.Year;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import static by.makei.tariff.builder.TariffXmlTag.*;

public class TariffHandler extends DefaultHandler {
    private static final char UNDERSCORE = '_';
    private static final char HYPHEN = '-';

    private Set<Tariff> tariffs;
    private EnumSet<TariffXmlTag> enumTariffSet;
    private Tariff tariff;
    private TariffXmlTag tariffTag;


    public TariffHandler() {
        tariffs = new HashSet<>();
        enumTariffSet = EnumSet.range(NAME, LIMITED_TARIFF_PARAMETERS);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        String unlimitedTariffTag = UNLIMITED_TARIFF.getValue();
        String limitedTariffTag = LIMITED_TARIFF.getValue();
        if (unlimitedTariffTag.equals(qName) || limitedTariffTag.equals(qName)) {
            tariff = unlimitedTariffTag.equals(qName) ? new UnlimTariff() : new LimitedTariff();
            tariff.setTariffId(attributes.getValue(TARIFF_ID.getValue()));
            String optionalTitle = attributes.getValue(TITLE.getValue());
            tariff.setTitle(optionalTitle == null ? TITLE_BY_DEFAULT.getValue() : optionalTitle);
        } else {
            TariffXmlTag tempTag = valueOf(qName.toUpperCase().replace(HYPHEN, UNDERSCORE));
            if (enumTariffSet.contains(tempTag)) {
                tariffTag = tempTag;
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).trim();
        if (tariffTag != null) {
            switch (tariffTag) {
                case NAME -> tariff.setTariffName(data);
                case YEAR -> tariff.setYear(Year.parse(data));
                case OPERATOR_NAME -> tariff.setOperator(data);
                case PAYROLL -> tariff.setPayroll(Double.parseDouble(data));
                case INSIDE_NETWORK_CALLS -> tariff.getCallPrice().setInsideNetworkCall(Double.parseDouble(data));
                case OTHER_NETWORK_CALLS -> tariff.getCallPrice().setOutNetworkCall(Double.parseDouble(data));
                case WIRED_LINE_CALLS -> tariff.getCallPrice().setWiredCall(Double.parseDouble(data));
                case SMS_PRICE -> tariff.setSmsPrice(Double.parseDouble(data));
                case HAVE_A_FAVORITE_NUMBER -> tariff.getParameters().setFavoriteNumber(data);
                case TARIFFING -> tariff.getParameters().setTariffing(data);
                case TARIFF_CONNECTION_FEE -> tariff.getParameters().setConnectionPayment(data);
                case UNLIMITED_TARIFF_PARAMETERS -> {
                    UnlimTariff unlimitedTariff = (UnlimTariff) tariff;
                    unlimitedTariff.setUnlimitedTariffParameters(data);
                }
                case LIMITED_TARIFF_PARAMETERS -> {
                    LimitedTariff temporaryTariff = (LimitedTariff) tariff;
                    temporaryTariff.setLimitedTariffParameters(data);
                }
            }
            tariffTag = null;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        String unlimitedTariffTag = UNLIMITED_TARIFF.getValue();
        String limitedTariffTag = LIMITED_TARIFF.getValue();
        if (unlimitedTariffTag.equals(qName) || limitedTariffTag.equals(qName)) {
            tariffs.add(tariff);
        }
    }


    public Set<Tariff> getTariffs() {
        return tariffs;
    }

}
