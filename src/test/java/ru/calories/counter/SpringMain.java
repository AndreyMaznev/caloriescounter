package ru.calories.counter;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.calories.counter.model.Role;
import ru.calories.counter.model.User;
import ru.calories.counter.to.MealTo;
import ru.calories.counter.web.meal.MealRestController;
import ru.calories.counter.web.user.AdminRestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static ru.calories.counter.TestUtil.mockAuthorize;
import static ru.calories.counter.UserTestData.user;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 automatic resource management (ARM)
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/inmemory.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "email@mail.ru", "password", 2000, Role.ADMIN));
            System.out.println();

            mockAuthorize(user);

            MealRestController mealController = appCtx.getBean(MealRestController.class);
            List<MealTo> filteredMealsWithExcess =
                    mealController.getBetween(
                            LocalDate.of(2020, Month.JANUARY, 30), LocalTime.of(7, 0),
                            LocalDate.of(2020, Month.JANUARY, 31), LocalTime.of(11, 0));
            filteredMealsWithExcess.forEach(System.out::println);
            System.out.println();
            System.out.println(mealController.getBetween(null, null, null, null));
        }
    }
}
