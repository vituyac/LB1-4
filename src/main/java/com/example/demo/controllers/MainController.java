package com.example.demo.controllers;


import com.example.demo.models.*;
import com.example.demo.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    private CoinRepository coinRepository;
    @Autowired
    private ExchangerRepository exchangerRepository;
    @Autowired
    private DealRepository dealRepository;
    @Autowired
    private PortfolioRepository portfolioRepository;
    @Autowired
    private TrendsRepository trendsRepository;
    @GetMapping("/api/coins")
    @ResponseBody
    public Iterable<Coin> getCoins(){
        Iterable<Coin> coins = coinRepository.findAll();
        return coins;
    }
    @PostMapping("/api/coin/add")
    @ResponseBody
    public Coin postCoin(@RequestBody Coin Coin){
        Coin coin = new Coin(Coin.getCoin_name(), Coin.getCoin_code(), Coin.getPrice(), Coin.get_1h(), Coin.get_24h(), Coin.get_7d(), Coin.getMarket_cap(), Coin.getVolume(), Coin.getLast_price());
        coinRepository.save(coin);
        return coin;
    }
    @GetMapping("/api/exchangers")
    @ResponseBody
    public Iterable<Exchanger> getExchangers(){
        Iterable<Exchanger> exchangers = exchangerRepository.findAll();
        return exchangers;
    }
    @PostMapping("/api/exchangers/add")
    @ResponseBody
    public Exchanger postExchanger(@RequestBody Exchanger Exchanger){
        Exchanger exchanger = new Exchanger(Exchanger.getName(), Exchanger.getScore(), Exchanger.getVolume24h(), Exchanger.getMarkets(), Exchanger.getCoins(), Exchanger.getLast_volume());
        exchangerRepository.save(exchanger);
        return exchanger;
    }

    @ResponseBody
    @GetMapping("/api/portfolio/{id}")
    public Portfolio getPortfolio(@PathVariable Long id) {
        return portfolioRepository.findById(id).orElse(null);
    }
    @PostMapping("/api/portfolio/add")
    @ResponseBody
    public Portfolio addPortfolio(@RequestBody Portfolio Portfolio){
        Portfolio portfolio = new Portfolio(Portfolio.getCoins(), Portfolio.getDeals(),Portfolio.getProfile_volume_usd(), Portfolio.getProfile_volume_btc(), Portfolio.getCurrent_volume_usd(), Portfolio.getCurrent_volume_btc());
        portfolioRepository.save(portfolio);
        return portfolio;
    }

    @GetMapping("/api/trends")
    @ResponseBody
    public Iterable<Trends> getTrends(){
        Iterable<Trends> trends = trendsRepository.findAll();
        return trends;
    }
    @PostMapping("/api/trends/add")
    @ResponseBody
    public Trends addTrend(@RequestBody Coin Coin) {
        Optional<Coin> coinOptional = coinRepository.findById(Coin.getId());
        if (coinOptional.isPresent()) {
            Coin coin = coinOptional.get();
            Trends trend = new Trends(coin.getCoin_name(), coin.getCoin_code(), coin.get_24h());
            trendsRepository.save(trend);
            return trend;
        } else {
            return null;
        }
    }

    @PostMapping("/api/portfolio/{id}/add-deal")
    @ResponseBody
    public Deal addDeal(@PathVariable Long id, @RequestBody Deal Deal){
        Portfolio portfolio = portfolioRepository.findById(id).orElse(null);
        if (portfolio != null) {
            Deal deal = new Deal(Deal.getDate(), Deal.getType(), Deal.getPrice(), Deal.getVolume(), Deal.getCoin_name(), Deal.getCoin_code());
            portfolio.getDeals().add(deal);
            portfolioRepository.save(portfolio);
            return deal;
        } else {
            return null;
        }
    }
    @PostMapping("/api/portfolio/{id}/add-coin")
    @ResponseBody
    public Portfolio addCoin(@PathVariable Long id, @RequestBody Coin Coin){
        Portfolio portfolio = portfolioRepository.findById(id).orElse(null);
        if (portfolio != null) {
            Optional<Coin> coinOptional = coinRepository.findById(Coin.getId());
            if (coinOptional.isPresent()) {
                Coin coin = coinOptional.get();
                portfolio.addCoin(coin);
                portfolioRepository.save(portfolio);
                return portfolio;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

}
