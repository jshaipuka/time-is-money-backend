package com.example.timer;

import org.springframework.data.repository.CrudRepository;

public interface TimerRepository extends CrudRepository<Timer, String> {
}
