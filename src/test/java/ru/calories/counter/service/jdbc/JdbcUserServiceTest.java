package ru.calories.counter.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.calories.counter.service.AbstractUserServiceTest;

import static ru.calories.counter.Profiles.JDBC;

@ActiveProfiles(JDBC)
class JdbcUserServiceTest extends AbstractUserServiceTest {
}