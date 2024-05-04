package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Coin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String coin_name, coin_code;
    private Float price, _1h, _24h, _7d, market_cap, volume;
    private List<Float> last_price = new ArrayList<>();
    public Coin() {
    }
    public Coin(String coin_name, String coin_code, Float price, Float _1h, Float _24h, Float _7d, Float market_cap, Float volume, List<Float> last_price) {
        this.coin_name = coin_name;
        this.coin_code = coin_code;
        this.price = price;
        this._1h = _1h;
        this._24h = _24h;
        this._7d = _7d;
        this.market_cap = market_cap;
        this.volume = volume;
        this.last_price = last_price;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCoin_name() {
        return coin_name;
    }

    public void setCoin_name(String coin_name) {
        this.coin_name = coin_name;
    }

    public String getCoin_code() {
        return coin_code;
    }

    public void setCoin_code(String coin_code) {
        this.coin_code = coin_code;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float get_1h() {
        return _1h;
    }

    public void set_1h(Float _1h) {
        this._1h = _1h;
    }

    public Float get_24h() {
        return _24h;
    }

    public void set_24h(Float _24h) {
        this._24h = _24h;
    }

    public Float get_7d() {
        return _7d;
    }

    public void set_7d(Float _7d) {
        this._7d = _7d;
    }

    public Float getMarket_cap() {
        return market_cap;
    }

    public void setMarket_cap(Float market_cap) {
        this.market_cap = market_cap;
    }

    public Float getVolume() {
        return volume;
    }

    public void setVolume(Float volume) {
        this.volume = volume;
    }

    public List<Float> getLast_price() {
        return last_price;
    }

    public void setLast_price(List<Float> last_price) {
        this.last_price = last_price;
    }
}
