package ru.calories.counter.service.datajpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.calories.counter.MealTestData;
import ru.calories.counter.model.Meal;
import ru.calories.counter.service.AbstractMealServiceTest;
import ru.calories.counter.util.exception.NotFoundException;

import static ru.calories.counter.MealTestData.*;
import static ru.calories.counter.Profiles.DATAJPA;
import static ru.calories.counter.UserTestData.*;

@ActiveProfiles(DATAJPA)
class DataJpaMealServiceTest extends AbstractMealServiceTest {
    @Test
    void getWithUser() {
        Meal adminMeal = service.getWithUser(ADMIN_MEAL_ID, ADMIN_ID);
        MEAL_MATCHER.assertMatch(adminMeal, adminMeal1);
        USER_MATCHER.assertMatch(adminMeal.getUser(), admin);
    }

    @Test
    void getWithUserNotFound() {
        Assertions.assertThrows(NotFoundException.class,
                () -> service.getWithUser(MealTestData.NOT_FOUND, ADMIN_ID));
    }
}
