package com.example.timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@RestController
public class TimerController {
    private TimerRepository timerRepository;

    @Autowired
    TimerController(TimerRepository timerRepository) {
        this.timerRepository = timerRepository;
    }

    @GetMapping(value = "/{token}")
    @ResponseBody
    ResponseEntity<Timer> getTimer(final @PathVariable("token") String token) {
        return this.timerRepository
                .findById(token)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseBody
    ResponseEntity<Timer> createTimer(final @RequestBody Timer timer) throws UnsupportedEncodingException {
        ZoneId timeZone = timer.getTimeZone() == null ? ZoneId.systemDefault() : ZoneId.of(timer.getTimeZone());
        ZonedDateTime now = ZonedDateTime.now(timeZone);
        Long expiryTime = now.plus(1, ChronoUnit.MONTHS).toInstant().toEpochMilli();
        Long startTime = now.toInstant().toEpochMilli();

        String source = startTime.toString() + timeZone.toString() + timer.getSalary().toString();
        byte[] bytes = source.getBytes("UTF-8");
        UUID uuid = UUID.nameUUIDFromBytes(bytes);
        String token = uuid.toString();

        Timer newTimer = new Timer(token, startTime, timeZone.toString(), timer.getSalary(), expiryTime);

        this.timerRepository.save(newTimer);

        return ResponseEntity.ok(newTimer);
    }

    @PutMapping(value = "/{token}")
    @ResponseBody
    ResponseEntity<Timer> updateTimer(final @PathVariable("token") String token, final @RequestBody Timer timer) {

        return this.timerRepository
                .findById(token)
                .map(existingTimer -> {
                    this.timerRepository.save(timer);
                    return ResponseEntity.ok(timer);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/{token}")
    @ResponseBody
    ResponseEntity deleteTimer(final @PathVariable("token") String token, final @RequestBody Timer timer) {
        return this.timerRepository
                .findById(token)
                .map(existingTimer -> {
                    this.timerRepository.delete(timer);
                    return new ResponseEntity(HttpStatus.NO_CONTENT);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
