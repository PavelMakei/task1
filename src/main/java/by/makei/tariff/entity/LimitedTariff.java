package by.makei.tariff.entity;

import java.time.Year;

public class LimitedTariff extends AbstractTariff {
    private String limitedTariffParameters;

    public LimitedTariff() {
        super("limited");
    }

    public String getLimitedTariffParameters() {
        return limitedTariffParameters;
    }

    public void setLimitedTariffParameters(String limitedTariffParameters) {
        this.limitedTariffParameters = limitedTariffParameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LimitedTariff that = (LimitedTariff) o;
        return limitedTariffParameters.equals(that.limitedTariffParameters);
    }

    @Override
    public int hashCode() {
        return super.hashCode() + limitedTariffParameters.hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LimitedTariff{");
        sb.append(super.toString());
        sb.append("limitedTariffParameters='").append(limitedTariffParameters).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static LimitedTariff.Builder newBuilder() {
        return new LimitedTariff().new Builder();
    }

    public class Builder extends AbstractBuilder {

        private Builder() {
            // private constructor
        }

        public LimitedTariff.Builder setTariffId(String tariffId) {
            LimitedTariff.this.setTariffId(tariffId);
            return this;
        }

        public LimitedTariff.Builder setTitle(String title) {
            LimitedTariff.this.setTitle(title);
            return this;
        }

        public LimitedTariff.Builder setTariffName(String tariffName) {
            LimitedTariff.this.setTariffName(tariffName);
            return this;
        }

        public LimitedTariff.Builder setYear(Year year) {
            LimitedTariff.this.setYear(year);
            return this;
        }

        public LimitedTariff.Builder setOperator(String operator) {
            LimitedTariff.this.setOperator(operator);
            return this;
        }

        public LimitedTariff.Builder setPayroll(double payroll) {
            LimitedTariff.this.setPayroll(payroll);
            return this;
        }

        public LimitedTariff.Builder setParameters(Parameters parameters) {
            LimitedTariff.this.setParameters(parameters);
            return this;
        }

        public LimitedTariff.Builder setCallPrice(CallPrice callPrice) {
            LimitedTariff.this.setCallPrice(callPrice);
            return this;
        }

        public LimitedTariff.Builder setSmsPrice(double smsPrice) {
            LimitedTariff.this.setSmsPrice(smsPrice);
            return this;
        }


        public LimitedTariff build() {
            return LimitedTariff.this;
        }

    }

}
