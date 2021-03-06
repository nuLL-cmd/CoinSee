package com.automatodev.coinSee.controller.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CoinRangeEntityAlpha implements Serializable {
    private String dateRange;
    @SerializedName("1. open")
    private String open;
    @SerializedName("2. high")
    private String high;
    @SerializedName("3. low")
    private String low;
    @SerializedName("4. close")
    private String close;

    public CoinRangeEntityAlpha() {
    }

    public CoinRangeEntityAlpha(String dateRange,String open, String high, String low, String close) {
        this.open = open;
        this.dateRange = dateRange;
        this.high = high;
        this.low = low;
        this.close = close;
    }

    public String getDateRange() {
        return dateRange;
    }

    public String getOpen() {
        return open;
    }

    public String getHigh() {
        return high;
    }

    public String getLow() {
        return low;
    }

    public String getClose() {
        return close;
    }
}