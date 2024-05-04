package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Exchanger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Float score, volume24h;
    private Integer markets, coins;
    private List<Float> last_volume = new ArrayList<>();
    public Exchanger(String name, Float score, Float volume24h, Integer markets, Integer coins, List<Float> last_volume) {
        this.name = name;
        this.score = score;
        this.volume24h = volume24h;
        this.markets = markets;
        this.coins = coins;
        this.last_volume = last_volume;
    }
    public Exchanger() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Float getVolume24h() {
        return volume24h;
    }

    public void setVolume24h(Float volume24h) {
        this.volume24h = volume24h;
    }

    public Integer getMarkets() {
        return markets;
    }

    public void setMarkets(Integer markets) {
        this.markets = markets;
    }

    public Integer getCoins() {
        return coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }

    public List<Float> getLast_volume() {
        return last_volume;
    }

    public void setLast_volume(List<Float> last_volume) {
        this.last_volume = last_volume;
    }
}