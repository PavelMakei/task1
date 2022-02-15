package by.makei.tariff.builder;

import by.makei.tariff.entity.AbstractTariff;
import by.makei.tariff.exception.CustomException;
import by.makei.tariff.util.CustomFileUtil;


import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractTariffBuilder {
    public static final String TARIFFS = "tariffs";
    public static final String TARIFF_ID = "tariff-id";
    public static final String NAME = "name";
    public static final String YEAR = "year";
    public static final String OPERATOR_NAME = "operator-name";
    public static final String PAYROLL = "payroll";
    public static final String CALL_PRICES = "call-prices";
    public static final String INSIDE_NETWORK_CALLS = "inside-network-calls";
    public static final String OTHER_NETWORK_CALLS = "other-network-calls";
    public static final String WIRED_NETWORK_CALLS = "wired-line-calls";
    public static final String SMS_PRICE = "sms-price";
    public static final String PARAMETERS = "parameters";
    public static final String HAVE_A_FAVORITE_NUMBER = "have-a-favorite-number";
    public static final String TARIFFING = "tariffing";
    public static final String TARIFF_CONNECTION_FEE = "tariff-connection-fee";
    public static final String LIMITED_TARIFF = "limited-tariff";
    public static final String UNLIMITED_TARIFF = "unlimited-tariff";
    public static final String UNLIMITED_TARIFF_PARAMETERS = "unlimited-tariff-parameters";
    public static final String LIMITED_TARIFF_PARAMETERS = "limited-tariff-parameters";
    public static final String TITLE = "title";
    public static final String TITLE_BY_DEFAULT = "title";


    protected Set<AbstractTariff> tariffSet;
    protected CustomFileUtil customFileUtil;

    protected AbstractTariffBuilder() {
        tariffSet = new HashSet<>();
    }

    protected AbstractTariffBuilder(Set<AbstractTariff> tariffs) {
        this.tariffSet = new HashSet<>(tariffs);
    }

    public Set<AbstractTariff> getTariffs() {
        return new HashSet<>(tariffSet);
    }

    public abstract void buildTariffs(String fileName) throws CustomException, JAXBException, FileNotFoundException;
}

