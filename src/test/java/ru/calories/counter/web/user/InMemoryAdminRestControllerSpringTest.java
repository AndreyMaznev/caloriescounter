package ru.calories.counter.web.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.calories.counter.repository.inmemory.InMemoryUserRepository;
import ru.calories.counter.util.exception.NotFoundException;

import static ru.calories.counter.UserTestData.NOT_FOUND;
import static ru.calories.counter.UserTestData.USER_ID;

@SpringJUnitConfig(locations = {"classpath:spring/inmemory.xml"})
class InMemoryAdminRestControllerSpringTest {

    @Autowired
    private AdminRestController controller;

    @Autowired
    private InMemoryUserRepository repository;

    @BeforeEach
    void setup() {
        repository.init();
    }

    @Test
    void delete() {
        controller.delete(USER_ID);
        Assertions.assertNull(repository.get(USER_ID));
    }

    @Test
    void deleteNotFound() {
        Assertions.assertThrows(NotFoundException.class, () -> controller.delete(NOT_FOUND));
    }
}