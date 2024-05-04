package com.example.demo.repo;
import com.example.demo.models.Coin;
import org.springframework.data.repository.CrudRepository;
public interface CoinRepository extends CrudRepository<Coin, Long> {
}



