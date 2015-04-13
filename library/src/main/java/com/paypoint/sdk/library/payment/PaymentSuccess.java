package com.paypoint.sdk.library.payment;

/**
 * Who:  Pete
 * When: 09/04/2015
 * What:
 */
public class PaymentSuccess {

    private String transactionId;

    private String merchantReference;

    private String lastFour;

    private float amount;

    private String currency;

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setMerchantReference(String merchantReference) {
        this.merchantReference = merchantReference;
    }

    public void setLastFour(String lastFour) {
        this.lastFour = lastFour;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getMerchantReference() {
        return merchantReference;
    }

    public String getLastFour() {
        return lastFour;
    }

    public float getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }
}
