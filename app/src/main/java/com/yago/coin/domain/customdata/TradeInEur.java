package com.yago.coin.domain.customdata;

import java.io.Serializable;
import java.util.Objects;

public class TradeInEur implements Serializable {

    private String sku;

    private final Double amount;

    private final Double eurAmount;

    private final String currency;

    public TradeInEur(String sku, Double amount, Double eurAmount, String currency) {
        this.sku = sku;
        this.amount = amount;
        this.eurAmount = eurAmount;
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TradeInEur that = (TradeInEur) o;
        return Objects.equals(sku, that.sku) && Objects.equals(amount, that.amount) && Objects.equals(eurAmount, that.eurAmount) && Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku, amount, eurAmount, currency);
    }

    /**
     * GET and SET
     */

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Double getAmount() {
        return amount;
    }

    public Double getEurAmount() {
        return eurAmount;
    }

    public String getCurrency() {
        return currency;
    }

}