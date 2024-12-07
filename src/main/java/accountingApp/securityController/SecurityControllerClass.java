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

import java.util.*;

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
        Role[] rolesArray = Role.values();
        List<String> rolesList = new ArrayList<>();
        for (Role r : rolesArray
        ) {
            rolesList.add(r.getAuthority());
        }

        List<String> isActiveList = new ArrayList<>();
        isActiveList.add("active");
        isActiveList.add("blocked");

        model.addAttribute("isActiveList", isActiveList);
        model.addAttribute("rolesList", rolesList);
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

        if (userName == null || userName.equals("") || userName.isEmpty() || userName.equals(" ")
                || userPass == null || userPass.equals("") || userPass.isEmpty() || userPass.equals(" ")
                || isActive == null || isActive.equals("") || isActive.isEmpty() || isActive.equals(" ")
                || roles == null || roles.equals("") || roles.isEmpty() || roles.equals(" ")
        ) {
            logger.warn("SecurityControllerClass.addNewUser():" +
                    " Attribute has a null value!");
            return getUsers(model);
        }

        String userNameWithoutSpaces = userName.trim();
        String userPassWithoutSpaces = userPass.trim();
        String isActiveWithoutSpaces = isActive.trim();
        String rolesWithoutSpaces = roles.trim();

        try {
            AppUser user;
            Set<Role> rolesSet = new HashSet<>();
            boolean isActiveUser;
            isActiveUser = isActiveWithoutSpaces.toLowerCase(Locale.ROOT).equals("active");

            if (rolesWithoutSpaces.toLowerCase(Locale.ROOT).equals("admin")) {
                rolesSet.add(Role.ADMIN);
            }
            rolesSet.add(Role.USER);

            user = new AppUser(userNameWithoutSpaces
                    , userPassWithoutSpaces
                    , isActiveUser
                    , rolesSet);
            service.createUser(user, userPassWithoutSpaces);
            return getUsers(model);

        } catch (Exception e) {
            logger.error("*** SecurityControllerClass.addNewUser():  WRONG DB VALUES " +
                    "OR EMPTY ATTRS *** " + e.getMessage() + " " + e.toString());
            e.printStackTrace();
            return getUsers(model);
        }
    }

    @PostMapping("/updateuser")
    public String updateAppUser(@RequestParam String id
            , @RequestParam String userName
            , @RequestParam String userPass
            , @RequestParam String isActive
            , @RequestParam String roles
            , Model model
    ) {
        if (id == null || id.equals("") || id.isEmpty() || id.equals(" ")
                ||userName == null || userName.equals("") || userName.isEmpty() || userName.equals(" ")
                || userPass == null || userPass.equals("") || userPass.isEmpty() || userPass.equals(" ")
                || isActive == null || isActive.equals("") || isActive.isEmpty() || isActive.equals(" ")
                || roles == null || roles.equals("") || roles.isEmpty() || roles.equals(" ")
        ) {
            logger.warn("SecurityControllerClass.updateAppUser():" +
                    " Attribute has a null value!");
            return getUsers(model);
        }

        String userIdWithoutSpaces = id.trim();
        String userNameWithoutSpaces = userName.trim();
        String userPassWithoutSpaces = userPass.trim();
        String isActiveWithoutSpaces = isActive.trim();
        String rolesWithoutSpaces = roles.trim();

        try {
            long idCheck = Long.parseLong(userIdWithoutSpaces);
            if (idCheck <= 0) {
                logger.warn("SecurityControllerClass.updateAppUser():" +
                        " WRONG ID FORMAT");
                return getUsers(model);
            } else if (!userNameWithoutSpaces.equals("")
                    && !userPassWithoutSpaces.equals("")
                    && !isActiveWithoutSpaces.equals("")
                    && !rolesWithoutSpaces.equals("")
            ) {

                AppUser user;
                Set<Role> rolesSet = new HashSet<>();
                boolean isActiveUser;
                isActiveUser = isActiveWithoutSpaces.toLowerCase(Locale.ROOT).equals("active");

                if (rolesWithoutSpaces.toLowerCase(Locale.ROOT).equals("admin")) {
                    rolesSet.add(Role.ADMIN);
                }
                rolesSet.add(Role.USER);

                user = new AppUser(idCheck
                        , userNameWithoutSpaces
                        , userPassWithoutSpaces
                        , isActiveUser
                        , rolesSet);
                service.updateUser(user, userPassWithoutSpaces);
            }
            return getUsers(model);

        } catch (Exception e) {
            logger.error("*** SecurityControllerClass.updateAppUser()::  WRONG DB VALUES " +
                    "OR EMPTY ATTRS *** " + e.getMessage() + " " + e.toString());
            e.printStackTrace();
            return getUsers(model);
        }
    }

    @PostMapping("/deleteuser")
    public String deleteAppUser(@RequestParam String id
            , Model model
    ) {
        if (id == null
                || id.equals("")
                || id.isEmpty()
                || id.equals(" ")
        ) {
            logger.warn("SecurityControllerClass.deleteAppUser():" +
                    " Attribute has a null value!");
            return getUsers(model);
        }

        String userIdWithoutSpaces = id.trim();

        try {
            long idCheck = Long.parseLong(userIdWithoutSpaces);
            if (idCheck <= 0) {
                logger.warn("SecurityControllerClass.deleteAppUser():" +
                        " WRONG ID FORMAT");
                return getUsers(model);
            }
            service.deleteUser(idCheck);
            return getUsers(model);
        } catch (Exception e) {
            logger.error("*** SecurityControllerClass.deleteAppUser():  WRONG DB VALUES " +
                    "OR EMPTY ATTRS *** " + e.getMessage() + " " + e.toString());
            e.printStackTrace();
            return getUsers(model);
        }
    }

    @PostMapping("/finduserbyname")
    public String findAppUser(@RequestParam String userName
            , Model model
    ) {
        if (userName == null
                || userName.equals("")
                || userName.isEmpty()
        ) {
            logger.warn("SecurityControllerClass.findAppUser():" +
                    " Attribute has a null value!");
            return getUsers(model);
        }

        String userIdWithoutSpaces = userName.trim();

        try {
            long idCheck = Long.parseLong(userIdWithoutSpaces);
            if (idCheck <= 0 || userIdWithoutSpaces.isEmpty()) {
                logger.warn("SecurityControllerClass.findAppUser():" +
                        " WRONG ID FORMAT");
                return getUsers(model);
            }
            List<AppUser> appUserList = service.findUserById(idCheck);
            model.addAttribute("appUserList", appUserList);
            return "users";
        } catch (Exception e) {
            try {
                Optional<AppUser> appUserOptional =
                        service.findUserByName(userIdWithoutSpaces);
                List<AppUser> appUserList = new ArrayList<>();
                appUserOptional.ifPresent(appUserList::add);
                model.addAttribute("appUserList", appUserList);
                logger.debug("SecurityControllerClass.findAppUser(): " +
                        "found an AppUser by fio  *** " + e.getMessage());
                return "users";
            } catch (Exception e1) {
                logger.error("*** SecurityControllerClass.findAppUser():  WRONG DB VALUES " +
                        "OR EMPTY ATTRS *** " + e.getMessage() + " " + e.toString());
                e1.printStackTrace();
                return getUsers(model);
            }
        }
    }
}
