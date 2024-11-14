package accountingApp.securityController;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityControllerClass {

    @GetMapping("/")
    @PreAuthorize("isAuthenticated()")
    public String getHome(){
        return "main";
    }

    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }


}
