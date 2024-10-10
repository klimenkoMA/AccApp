package accountingApp.controller;

import accountingApp.entity.Devices;
import accountingApp.service.DevicesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DevicesControllerTest {

    @InjectMocks
    private DevicesController devicesController;

    @Mock
    private DevicesService devicesService;

    @Mock
    private Model model;

    /**
     * Инициализация моков перед каждым тестом
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getDevicesShouldReturnDevicesList() {

        // Arrange: Подготовка данных для теста
        Devices device1 = new Devices();
        Devices device2 = new Devices();

        // Создаем список устройств
        List<Devices> mockDevicesList = Arrays.asList(device1, device2);

        // Настраиваем поведение мока
        when(devicesService.findAllDevices()).thenReturn(mockDevicesList);

        // Act: Вызов тестируемого метода
        String viewName = devicesController.getDevices(model);

        // Assert: Проверяем результаты
        // Проверяем, что возвращаемое имя представления правильно
        Assertions.assertEquals("devices", viewName);

        // Убеждаемся, что список устройств добавлен в модель
        verify(model).addAttribute("devicesList", mockDevicesList);

        // Проверяем, что сервис был вызван
        verify(devicesService).findAllDevices();

    }
}