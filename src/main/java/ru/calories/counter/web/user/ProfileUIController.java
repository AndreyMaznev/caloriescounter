package ru.calories.counter.web.user;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import ru.calories.counter.AuthorizedUser;
import ru.calories.counter.View;
import ru.calories.counter.to.UserTo;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
@RequestMapping("/profile")
public class ProfileUIController extends AbstractUserController {

    @GetMapping
    public String profile(ModelMap model, @AuthenticationPrincipal AuthorizedUser authUser) {
        model.addAttribute("userTo", authUser.getUserTo());
        return "profile";
    }

    @PostMapping
    public String updateProfile(@Validated(View.Web.class) UserTo userTo, BindingResult result, SessionStatus status, @AuthenticationPrincipal AuthorizedUser authUser) {
        if (result.hasErrors()) {
            return "profile";
        }
        super.update(userTo, authUser.getId());
        authUser.setTo(userTo);
        status.setComplete();
        return "redirect:/meals";
    }

    @GetMapping("/register")
    public String register(ModelMap model) {
        model.addAttribute("userTo", new UserTo());
        model.addAttribute("register", true);
        return "profile";
    }

    @PostMapping("/register")
    public String saveRegister(@Validated(View.Web.class) UserTo userTo, BindingResult result, SessionStatus status, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("register", true);
            return "profile";
        }
        super.create(userTo);
        status.setComplete();
        return "redirect:/login?message=app.registered&username=" + userTo.getEmail();
    }
}