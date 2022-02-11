package by.makei.tariff.entity;

public class Parameters {
    private String favoriteNumber;
    private String tariffing;
    private String connectionPayment;

    public Parameters(String favoriteNumber, String tariffing, String connectionPayment) {
        this.favoriteNumber = favoriteNumber;
        this.tariffing = tariffing;
        this.connectionPayment = connectionPayment;
    }

    public Parameters() {    }

    public String getFavoriteNumber() {
        return favoriteNumber;
    }

    public void setFavoriteNumber(String favoriteNumber) {
        this.favoriteNumber = favoriteNumber;
    }

    public String getTariffing() {
        return tariffing;
    }

    public void setTariffing(String tariffing) {
        this.tariffing = tariffing;
    }

    public String getConnectionPayment() {
        return connectionPayment;
    }

    public void setConnectionPayment(String connectionPayment) {
        this.connectionPayment = connectionPayment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parameters that = (Parameters) o;
        return favoriteNumber.equals(that.favoriteNumber) && tariffing.equals(that.tariffing) && connectionPayment.equals(that.connectionPayment);
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash += favoriteNumber == null ? ++hash : favoriteNumber.hashCode();
        hash += tariffing == null ? ++hash : tariffing.hashCode();
        hash += connectionPayment == null ? ++hash : connectionPayment.hashCode();
        return hash * 32;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Parameters{");
        sb.append("favoriteNumber='").append(favoriteNumber).append('\'');
        sb.append(", tariffing='").append(tariffing).append('\'');
        sb.append(", connectionPayment='").append(connectionPayment).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
