package by.makei.tariff.entity;

import by.makei.tariff.exception.CustomException;

import java.time.Year;
import java.util.Objects;

public class LimitedTariff extends Tariff{
    private String  limitedTariffParameters;

   public LimitedTariff(){super();}

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
        return Objects.equals(limitedTariffParameters, that.limitedTariffParameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), limitedTariffParameters.hashCode());
    }

    @Override
    public String toString() {
        return "LimitedTariff{"
                + super.toString()
                + "actionDiscount="
                + limitedTariffParameters
                +" %}";
    }
}
