package by.makei.tariff.entity;

import java.time.Year;


public abstract class AbstractTariff {
    private String type;
    //@XmlAttribute(name = "tariff-id")
    private String tariffId;
    //@XmlAttribute(name = "title")
    private String title;
    //@XmlElement(name = "name")
    private String tariffName;
    private Year year;
    private String operator;
    private double payroll;
    private Parameters parameters;
    private CallPrice callPrice;
    private double smsPrice;


    public AbstractTariff(String type) {
        callPrice = new CallPrice();
        parameters = new Parameters();
        this.type = type;
    }

    public String getType() {return type;}

    public String getTariffId() {
        return tariffId;
    }

    public void setTariffId(String tariffId) {
        this.tariffId = tariffId;
    }

    public String getTariffName() {
        return tariffName;
    }

    public void setTariffName(String tariffName) {
        this.tariffName = tariffName;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public double getPayroll() {
        return payroll;
    }

    public void setPayroll(double payroll) {
        this.payroll = payroll;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = new Parameters(parameters.getFavoriteNumber(),
                parameters.getTariffing(), parameters.getConnectionPayment());
    }

    public CallPrice getCallPrice() {
        return new CallPrice();
    }

    public void setCallPrice(CallPrice callPrice) {
        this.callPrice = new CallPrice();
    }

    public double getSmsPrice() {
        return smsPrice;
    }

    public void setSmsPrice(double smsPrice) {
        this.smsPrice = smsPrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractTariff tariff = (AbstractTariff) o;
        return Double.compare(tariff.payroll, payroll) == 0
                && Double.compare(tariff.smsPrice, smsPrice) == 0
                && tariffId.equals(tariff.tariffId)
                && title.equals(tariff.title)
                && tariffName.equals(tariff.tariffName)
                && operator.equals(tariff.operator)
                && parameters.equals(tariff.parameters)
                && callPrice.equals(tariff.callPrice);
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += tariffId == null ? 1 : tariffId.hashCode();
        hash += tariffName == null ? 1 : tariffName.hashCode();
        hash += year == null ? 1 : year.hashCode();
        hash += operator == null ? 1 : operator.hashCode();
        hash += (int) payroll;
        hash += parameters == null ? 1 : parameters.hashCode();
        hash += callPrice == null ? 1 : callPrice.hashCode();
        hash += (int) smsPrice;

        return hash;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AbstractTariff{");
        sb.append("tariffId='").append(tariffId).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", tariffName='").append(tariffName).append('\'');
        sb.append(", year=").append(year);
        sb.append(", operator='").append(operator).append('\'');
        sb.append(", payroll=").append(payroll);
        sb.append(", parameters=").append(parameters);
        sb.append(", callPrice=").append(callPrice);
        sb.append(", smsPrice=").append(smsPrice);
        sb.append('}');
        return sb.toString();

    }

    public abstract class AbstractBuilder {

        AbstractBuilder() {
            // private constructor
        }

        public abstract AbstractTariff.AbstractBuilder setTariffId(String tariffId);

        public  abstract AbstractTariff.AbstractBuilder setTitle(String title);

        public  abstract AbstractTariff.AbstractBuilder setTariffName(String tariffName);

        public abstract AbstractTariff.AbstractBuilder setYear(Year year);

        public  abstract AbstractTariff.AbstractBuilder setOperator(String operator);

        public  abstract AbstractTariff.AbstractBuilder setPayroll(double payroll);

        public  abstract AbstractTariff.AbstractBuilder setParameters(Parameters parameters);

        public  abstract AbstractTariff.AbstractBuilder setCallPrice(CallPrice callPrice);

        public  abstract AbstractTariff.AbstractBuilder setSmsPrice(double smsPrice);


        public abstract AbstractTariff build();

    }


}
