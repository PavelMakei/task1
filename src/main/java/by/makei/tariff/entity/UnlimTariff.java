package by.makei.tariff.entity;


import by.makei.tariff.exception.CustomException;

import java.time.Year;
import java.util.Objects;

public class UnlimTariff extends Tariff {
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
        return Objects.hash(super.hashCode(), unlimitedTariffParameters.hashCode());
    }

    @Override
    public String toString() {
        return "LimitedTariff{"
                + super.toString()
                + "actionDiscount="
                + unlimitedTariffParameters
                +" %}";
    }
}
