package com.example.demo.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Trends {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String coin_name, coin_code;
    private Float _24h;
    public Trends(String coin_name, String coin_code, Float _24h) {
        this.coin_name = coin_name;
        this.coin_code = coin_code;
        this._24h = _24h;
    }
    public Trends() {
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

    public Float get_24h() {
        return _24h;
    }

    public void set_24h(Float _24h) {
        this._24h = _24h;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
