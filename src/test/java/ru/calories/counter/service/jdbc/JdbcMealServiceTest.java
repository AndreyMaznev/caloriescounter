package ru.calories.counter.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.calories.counter.service.AbstractMealServiceTest;

import static ru.calories.counter.Profiles.JDBC;

@ActiveProfiles(JDBC)
class JdbcMealServiceTest extends AbstractMealServiceTest {
}