package accountingApp.securityController;

import accountingApp.entity.AppUser;
import accountingApp.service.AppUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SecurityControllerClass {

    final Logger logger = LoggerFactory.getLogger(SecurityControllerClass.class);

    @Autowired
    AppUserService service;


    @GetMapping("/")
    @PreAuthorize("isAuthenticated()")
    public String getHome(){
        return "main";
    }

    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }


    @GetMapping("/users")
    public String getUsers(Model model){
        List<AppUser> appUserList = service.getAllAppUsers();
        model.addAttribute("appUserList", appUserList);
        return "users";
    }





}
