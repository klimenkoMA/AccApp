package accountingApp.controller.securityController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import accountingApp.entity.AppUser;
import accountingApp.entity.Role;
import accountingApp.securityController.SecurityControllerClass;
import accountingApp.service.AppUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.verification.VerificationMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.mockito.Mockito.*;

@WebMvcTest(SecurityControllerClass.class)
class SecurityControllerClassTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AppUserService appUserService;

    @InjectMocks
    private SecurityControllerClass securityControllerClass;

    @Mock
    private Model model;

    private final List<AppUser> appUserList;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @WithMockUser
        // Создаем пользователя, который будет аутентифицирован
    void testGetHome_WithAuthenticatedUser() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("main"));
    }

    @Test
    void testGetHome_WithoutAuthenticatedUser() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void testGetLogin() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    {
        AppUser u1 = new AppUser();
        AppUser u2 = new AppUser();
        appUserList = Arrays.asList(u1, u2);
    }

    @Test
    void getUsersShouldReturnAppUserList() {

        when(appUserService.getAllAppUsers()).thenReturn(appUserList);

        String viewName = securityControllerClass.getUsers(model);

        Assertions.assertEquals("users", viewName);

        verify(model).addAttribute("appUserList", appUserList);

        verify(appUserService).getAllAppUsers();
    }

    @Test
    void validUserAttributesAdded() {

        String userName = "name";
        String userPass = "password";
        String isActive = "active";
        String roles = "USER";

        when(appUserService.createUser(new AppUser(), "")).thenReturn(new AppUser());

        String viewName = securityControllerClass.addNewUser(userName
                , userPass, isActive, roles, model);

        Assertions.assertEquals("users", viewName);

//        verify(model).addAttribute("appUserList",appUserList);

//        verify(appUserService).createUser(new AppUser(userName
//                , userPass, false, new Role[]{Role.USER}), userPass);
    }

    @Test
    void updateAppUser() {
    }

    @Test
    void deleteAppUser() {
    }

    @Test
    void findAppUser() {
    }
}
