package in.ac.dei.edrp.client;

import com.google.gwt.user.client.rpc.IsSerializable;


public class CM_passwordPolicyGetter implements IsSerializable {
    int minimum_length;
    int maximum_length;
    int expiry_period;

    public int getMinimum_length() {
        return minimum_length;
    }

    public void setMinimum_length(int minimum_length) {
        this.minimum_length = minimum_length;
    }

    public int getMaximum_length() {
        return maximum_length;
    }

    public void setMaximum_length(int maximum_length) {
        this.maximum_length = maximum_length;
    }

    public int getExpiry_period() {
        return expiry_period;
    }

    public void setExpiry_period(int expiry_period) {
        this.expiry_period = expiry_period;
    }
}
