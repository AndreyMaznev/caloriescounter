package ru.calories.counter.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.calories.counter.service.AbstractMealServiceTest;

import static ru.calories.counter.Profiles.JPA;

@ActiveProfiles(JPA)
class JpaMealServiceTest extends AbstractMealServiceTest {
}