package by.makei.tariff.entity;


import java.util.Objects;

public class UnlimTariff extends AbstractTariff {
    private String unlimitedTariffParameters;

    public UnlimTariff() {}

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
}
