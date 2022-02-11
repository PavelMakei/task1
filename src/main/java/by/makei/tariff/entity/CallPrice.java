package by.makei.tariff.entity;

import java.util.Objects;

public class CallPrice {

   // private final String callPriceId;
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
        return Double.compare(callPrice.insideNetworkCall, insideNetworkCall) == 0 && Double.compare(callPrice.outNetworkCall, outNetworkCall) == 0 && Double.compare(callPrice.wiredCall, wiredCall) == 0;}

    @Override
    public int hashCode() {
        return (int) (insideNetworkCall + outNetworkCall + wiredCall);
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
