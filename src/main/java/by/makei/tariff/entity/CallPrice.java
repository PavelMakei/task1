package by.makei.tariff.entity;


public class CallPrice {

    private double insideNetworkCall;
    private double outNetworkCall;
    private double wiredCall;


    public CallPrice( double insideNetworkCall, double outNetworkCall, double wiredCall) {
        this.insideNetworkCall = insideNetworkCall;
        this.outNetworkCall = outNetworkCall;
        this.wiredCall = wiredCall;
    }

    public CallPrice (){}

    public double getInsideNetworkCall() {
        return insideNetworkCall;
    }

    public double getOutNetworkCall() {
        return outNetworkCall;
    }

    public double getWiredCall() {
        return wiredCall;
    }

    public void setInsideNetworkCall(double insideNetworkCall) {
        this.insideNetworkCall = insideNetworkCall;
    }

    public void setOutNetworkCall(double outNetworkCall) {
        this.outNetworkCall = outNetworkCall;
    }

    public void setWiredCall(double wiredCall) {
        this.wiredCall = wiredCall;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CallPrice callPrice = (CallPrice) o;

        if (Double.compare(callPrice.insideNetworkCall, insideNetworkCall) != 0) return false;
        if (Double.compare(callPrice.outNetworkCall, outNetworkCall) != 0) return false;
        return Double.compare(callPrice.wiredCall, wiredCall) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(insideNetworkCall);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(outNetworkCall);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(wiredCall);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CallPrice{");
        sb.append("insideNetworkCall=").append(insideNetworkCall);
        sb.append(", outNetworkCall=").append(outNetworkCall);
        sb.append(", wiredCall=").append(wiredCall);
        sb.append('}');
        return sb.toString();
    }
}
