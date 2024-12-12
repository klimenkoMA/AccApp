package accountingApp.controller.securityController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import accountingApp.entity.AppUser;
import accountingApp.entity.Role;
import accountingApp.securityController.SecurityControllerClass;
import accountingApp.service.AppUserService;
import accountingApp.usefulmethods.Checker;
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

    @Mock
    private Checker checker;

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
    private long userId;


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

        verify(model, atMost(1)).addAttribute("appUserList", appUserList);

        verify(appUserService, atMost(1)).createUser(new AppUser(userName
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
    void validUpdatingAppUser() {

        userId = 1;

        when(appUserService.updateUser(new AppUser(), "222")).thenReturn(new AppUser());

        viewName = securityControllerClass.updateAppUser(userId + "", userName
                , userPass, isActive, roles, model);

        Assertions.assertEquals("users", viewName);

        verify(model, atMost(1)).addAttribute("appUserList", appUserList);

        verify(appUserService, atMost(1)).updateUser(new AppUser(userId, userName
                , userPass, false, roleSet), userPass);
    }

    @Test
    void emptyNameUpdatingAppUser() {
        userName = " ";

        when(appUserService.updateUser(new AppUser(), "222")).thenThrow(new RuntimeException());

        verify(model, atMost(1)).addAttribute("appUserList", appUserList);

        verify(appUserService, never()).updateUser(new AppUser(userId, userName
                , userPass, false, roleSet), userPass);
    }

    @Test
    void exceptionDuringUpdatingAppUser() {

        doThrow(new RuntimeException("exceptionDuringUpdatingAppUser TEST"))
                .when(appUserService).updateUser(user, userPass);

        verify(appUserService, never()).updateUser(new AppUser(userId, userName
                , userPass, false, roleSet), userPass);
    }

    @Test
    void validDeletingAppUser() {
        userId = 1;

        viewName = securityControllerClass.deleteAppUser(userId + "", model);

        Assertions.assertEquals("users", viewName);

        verify(model, atMost(1)).addAttribute("appUserList", appUserList);

        verify(appUserService, atMost(1)).deleteUser(userId);
    }

    @Test
    void wrongIdDeletingAppUser() {
        userId = -1;

        verify(model, atMost(1)).addAttribute("appUserList", appUserList);

        verify(appUserService, never()).deleteUser(userId);
    }

    @Test
    void exceptionDuringDeletingAppUser() {

        doThrow(new RuntimeException("exceptionDuringDeletingAppUser TEST"))
                .when(appUserService).deleteUser(userId);

        verify(appUserService, never()).deleteUser(userId);
    }

    @Test
    void validFindingAppUser() {
        userName = "appuser";

        viewName = securityControllerClass.findAppUser(userName, model);

        Assertions.assertEquals("users", viewName);

        verify(model, atMost(1)).addAttribute("appUserList", appUserList);

        verify(appUserService, atMost(1)).findUserByName(userName);
    }

    @Test
    void wrongIdFindingAppUser() {
        userId = -1;

        verify(model, atMost(1)).addAttribute("appUserList", appUserList);

        verify(appUserService, never()).findUserById(userId);
    }

    @Test
    void exceptionDuringFindingAppUser() {

        doThrow(new RuntimeException("exceptionDuringDeletingAppUser TEST"))
                .when(appUserService).findUserById(userId);

        verify(appUserService, never()).findUserById(userId);
    }
}
