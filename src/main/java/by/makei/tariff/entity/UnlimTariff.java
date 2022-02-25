package by.makei.tariff.entity;


import java.time.Year;
import java.util.Objects;

public class UnlimTariff extends AbstractTariff {
    private String unlimitedTariffParameters;

    public UnlimTariff() {
        super("unlimited");
    }

    public String getUnlimitedTariffParameters() {
        return unlimitedTariffParameters;
    }

    public void setUnlimitedTariffParameters(String unlimitedTariffParameters) {
        this.unlimitedTariffParameters = unlimitedTariffParameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UnlimTariff that = (UnlimTariff) o;
        return Objects.equals(unlimitedTariffParameters, that.unlimitedTariffParameters);
    }

    @Override
    public int hashCode() {
        return super.hashCode() + unlimitedTariffParameters.hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UnlimTariff{");
        sb.append(super.toString());
        sb.append("unlimitedTariffParameters='")
                .append(unlimitedTariffParameters).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static Builder newBuilder() {
        return new UnlimTariff().new Builder();
    }

    public class Builder extends AbstractBuilder{

        private Builder() {
            // private constructor
        }

        public UnlimTariff.Builder setTariffId(String tariffId) {
            UnlimTariff.this.setTariffId(tariffId);
            return this;
        }

        public UnlimTariff.Builder setTitle(String title) {
            UnlimTariff.this.setTitle(title);
            return this;
        }

        public UnlimTariff.Builder setTariffName(String tariffName) {
            UnlimTariff.this.setTariffName(tariffName);
            return this;
        }

        public UnlimTariff.Builder setYear(Year year) {
            UnlimTariff.this.setYear(year);
            return this;
        }

        public UnlimTariff.Builder setOperator(String operator) {
            UnlimTariff.this.setOperator(operator);
            return this;
        }

        public UnlimTariff.Builder setPayroll(double payroll) {
            UnlimTariff.this.setPayroll(payroll);
            return this;
        }

        public UnlimTariff.Builder setParameters(Parameters parameters) {
            UnlimTariff.this.setParameters(parameters);
            return this;
        }

        public UnlimTariff.Builder setCallPrice(CallPrice callPrice) {
            UnlimTariff.this.setCallPrice(callPrice);
            return this;
        }

        public UnlimTariff.Builder setSmsPrice(double smsPrice) {
            UnlimTariff.this.setSmsPrice(smsPrice);
            return this;
        }


        public UnlimTariff build() {
            return UnlimTariff.this;
        }

    }

}
