package by.makei.tariff.builder;

public enum TariffXmlTag {

    TARIFFS("tariffs"),
    TARIFF_ID("tariff-id"),
    NAME("name"),
    YEAR("year"),
    OPERATOR_NAME("operator-name"),
    PAYROLL("payroll"),
    CALL_PRICES("call-prices"),
    INSIDE_NETWORK_CALLS("inside-network-calls"),
    OTHER_NETWORK_CALLS("other-network-calls"),
    WIRED_LINE_CALLS("wired-line-calls"),
    SMS_PRICE("sms-price"),
    PARAMETERS("parameters"),
    HAVE_A_FAVORITE_NUMBER("have-a-favorite-number"),
    TARIFFING("tariffing"),
    TARIFF_CONNECTION_FEE("tariff-connection-fee"),
    LIMITED_TARIFF("limited-tariff"),
    UNLIMITED_TARIFF("unlimited-tariff"),
    UNLIMITED_TARIFF_PARAMETERS("unlimited-tariff-parameters"),
    LIMITED_TARIFF_PARAMETERS("limited-tariff-parameters"),
    TITLE("title"),
    TITLE_BY_DEFAULT("title");

    private String value;

    TariffXmlTag(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
