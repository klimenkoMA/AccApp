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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;


import java.util.*;

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

    private final List<AppUser> appUserList = new ArrayList<>();
    private String userName;
    private String userPass;
    private String isActive;
    private String roles;
    private Set<Role> roleSet;
    private AppUser user;
    private String viewName;


    {
        userName = "name";
        userPass = "password";
        isActive = "active";
        roles = "USER";
        roleSet = new HashSet<>();
        roleSet.add(Role.USER);
        user = new AppUser(userName
                , userPass, false, roleSet);
        appUserList.add(user);
    }

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


    @Test
    void getUsersShouldReturnAppUserList() {

        when(appUserService.getAllAppUsers()).thenReturn(appUserList);

        viewName = securityControllerClass.getUsers(model);

        Assertions.assertEquals("users", viewName);

        verify(model).addAttribute("appUserList", appUserList);

        verify(appUserService).getAllAppUsers();
    }

    @Test
    void validUserAttributesAdded() {

        when(appUserService.createUser(new AppUser(), "")).thenReturn(new AppUser());

        viewName = securityControllerClass.addNewUser(userName
                , userPass, isActive, roles, model);

        Assertions.assertEquals("users", viewName);

        verify(model, atMost(3)).addAttribute("appUserList", appUserList);

        verify(appUserService, atMost(2)).createUser(new AppUser(userName
                , userPass, false, roleSet), userPass);

    }

    @Test
    void noNameUserAttributesAdded() {

        userName = " ";
        user = new AppUser(userName, userPass, true, roleSet);

        when(appUserService.createUser(new AppUser(), "")).thenReturn(new AppUser());

        viewName = securityControllerClass.addNewUser(userName
                , userPass, isActive, roles, model);

        Assertions.assertEquals("users", viewName);

        verify(model, atMost(3)).addAttribute("appUserList", appUserList);

        verify(appUserService, never()).createUser(user, userPass);

    }

    @Test
    void exceptionWhenAppUserAdded() {

        doThrow(new RuntimeException("exceptionWhenAppUserAdded TEST"))
                .when(appUserService).createUser(user, userPass);

        verify(appUserService, never()).createUser(user, userPass);

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
