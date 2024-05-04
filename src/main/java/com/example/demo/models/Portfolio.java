package com.example.demo.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    private List<Deal> deals;
    @ManyToMany
    private List<Coin> coins;
    private List<Float> profile_volume_usd = new ArrayList<>();
    private List<Float> profile_volume_btc = new ArrayList<>();
    private Float current_volume_usd, current_volume_btc;
    public Portfolio(List<Coin> coins, List<Deal> deals, List<Float> profile_volume_usd, List<Float> profile_volume_btc, Float current_volume_usd, Float current_volume_btc) {
        this.coins = coins;
        this.deals = deals;
        this.profile_volume_usd = profile_volume_usd;
        this.profile_volume_btc = profile_volume_btc;
        this.current_volume_usd = current_volume_usd;
        this.current_volume_btc = current_volume_btc;
    }
    public Portfolio() {
    }
    public void addCoin(Coin coin)
    {
        this.coins.add(coin);
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Coin> getCoins() {
        return coins;
    }

    public void setCoins(List<Coin> coins) {
        this.coins = coins;
    }

    public List<Deal> getDeals() {
        return deals;
    }

    public void setDeals(List<Deal> deals) {
        this.deals = deals;
    }

    public List<Float> getProfile_volume_usd() {
        return profile_volume_usd;
    }

    public void setProfile_volume_usd(List<Float> profile_volume_usd) {
        this.profile_volume_usd = profile_volume_usd;
    }

    public List<Float> getProfile_volume_btc() {
        return profile_volume_btc;
    }

    public void setProfile_volume_btc(List<Float> profile_volume_btc) {
        this.profile_volume_btc = profile_volume_btc;
    }

    public Float getCurrent_volume_usd() {
        return current_volume_usd;
    }

    public void setCurrent_volume_usd(Float current_volume_usd) {
        this.current_volume_usd = current_volume_usd;
    }

    public Float getCurrent_volume_btc() {
        return current_volume_btc;
    }

    public void setCurrent_volume_btc(Float current_volume_btc) {
        this.current_volume_btc = current_volume_btc;
    }
}
