package by.makei.tariff.entity;

import java.time.Year;


public abstract class Tariff {
    private String tariffId;
    private String title;
    private String tariffName;
    private Year year;
    private String operator;
    private double payroll;
    private Parameters parameters;
    private CallPrice callPrice;
    private double smsPrice;


    public Tariff() {
            callPrice = new CallPrice();
            parameters = new Parameters();
    }

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
        Tariff tariff = (Tariff) o;
        return Double.compare(tariff.payroll, payroll) == 0
                && Double.compare(tariff.smsPrice, smsPrice) == 0
                && tariffId.equals( tariff.tariffId)
                && title.equals( tariff.title)
                && tariffName.equals( tariff.tariffName)
                && operator.equals( tariff.operator)
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
        return "tariffId='" + tariffId + '\'' +
                ", title='" + title + '\'' +
                ", tariffName='" + tariffName + '\'' +
                ", Year='" + year + '\'' +
                ", operator='" + operator + '\'' +
                ", payroll=" + payroll + " byn" +
                ", parameters=" + parameters +
                ", callPrice=" + callPrice +
                ", smsPrice=" + smsPrice + " byn" +
                '}';
    }
}
