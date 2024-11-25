package accountingApp.securityController;

import accountingApp.entity.AppUser;
import accountingApp.entity.Role;
import accountingApp.service.AppUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@RequestMapping
@Controller
public class SecurityControllerClass {

    final Logger logger = LoggerFactory.getLogger(SecurityControllerClass.class);

    @Autowired(required = false)
    AppUserService service;

    @GetMapping("/")
    @PreAuthorize("isAuthenticated()")
    public String getHome() {
        return "main";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }


    @GetMapping("/users")
    public String getUsers(Model model) {
        List<AppUser> appUserList = service.getAllAppUsers();
        model.addAttribute("appUserList", appUserList);
        return "users";
    }

    @PostMapping("/adduser")
    public String addNewUser(@RequestParam String userName
            , @RequestParam String userPass
            , @RequestParam String isActive
            , @RequestParam String roles
            , Model model
    ) {

        String userNameWithoutSpaces = userName.trim();
        String userPassWithoutSpaces = userPass.trim();
        String isActiveWithoutSpaces = isActive.trim();
        String rolesWithoutSpaces = roles.trim();

        try {
            AppUser user;
            Set<Role> rolesSet = new HashSet<>();
            boolean isActiveUser;
            isActiveUser = isActiveWithoutSpaces.toLowerCase(Locale.ROOT).equals("да");

            if (rolesWithoutSpaces.toLowerCase(Locale.ROOT).matches("админ")) {
                rolesSet.add(Role.ADMIN);
            }
            rolesSet.add(Role.USER);


            user = new AppUser(userNameWithoutSpaces
                    , userPassWithoutSpaces
                    , isActiveUser
                    , rolesSet);
            service.createUser(user);
            return getUsers(model);

        } catch (Exception e) {
            logger.error("*** SecurityControllerClass.addNewUser():  WRONG DB VALUES " +
                    "OR EMPTY ATTRS *** " + e.getMessage() + " " + e.toString());
            e.printStackTrace();
            return getUsers(model);
        }
    }


}
