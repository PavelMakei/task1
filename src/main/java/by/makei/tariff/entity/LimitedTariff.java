package by.makei.tariff.entity;

public class LimitedTariff extends AbstractTariff {
    private String limitedTariffParameters;

    public LimitedTariff() {
        super();
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
}
