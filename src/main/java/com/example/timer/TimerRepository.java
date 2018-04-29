package com.example.timer;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TimerRepository extends MongoRepository<Timer, String> {
}
