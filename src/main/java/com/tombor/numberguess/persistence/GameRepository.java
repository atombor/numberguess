package com.tombor.numberguess.persistence;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GameRepository extends CrudRepository<Game, Long> {

    Optional<Game> findById(long playerid);

    @Override
    Game save(Game entity);

}
