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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;


class DevicesControllerTest {

    @InjectMocks
    private DevicesController devicesController;

    @Mock
    private DevicesService devicesService;

    @Mock
    private Model model;

    private final List<Devices> devicesList;

    {
        // Arrange: Подготовка данных для теста
        Devices device1 = new Devices();
        Devices device2 = new Devices();

        // Создаем список устройств
        devicesList = Arrays.asList(device1, device2);
    }

    /**
     * Инициализация моков перед каждым тестом
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getDevicesShouldReturnDevicesList() {

        // Настраиваем поведение мока
        when(devicesService.findAllDevices()).thenReturn(devicesList);

        // Act: Вызов тестируемого метода
        String viewName = devicesController.getDevices(model);

        // Assert: Проверяем результаты
        // Проверяем, что возвращаемое имя представления правильно
        Assertions.assertEquals("devices", viewName);

        // Убеждаемся, что список устройств добавлен в модель
        verify(model).addAttribute("devicesList", devicesList);

        // Проверяем, что сервис был вызван
        verify(devicesService).findAllDevices();

    }

    @Test
    void addDeviceValidNameDeviceAdded() {
        // Arrange
        String deviceName = "TestDevice";
        Devices newDevice = new Devices(deviceName);
        when(devicesService.findAllDevices()).thenReturn(new ArrayList<>());

        // Act
        String viewName = devicesController.addDevice(deviceName, model);

        // Assert
        verify(devicesService).addNewDevice(any(Devices.class));


        // Пример ожидаемого имени представления
        // assertEquals("expectedViewName", viewName);
    }

    @Test
    void addDeviceEmptyNameNoDeviceAdded() {
        // Arrange
        String deviceName = " ";

        // Act
        String viewName = devicesController.addDevice(deviceName, model);

        // Assert
        verify(devicesService, never()).addNewDevice(any(Devices.class));
        // Пример ожидаемого имени представления
        // assertEquals("expectedViewName", viewName);
    }

    @Test
    public void testDeleteDeviceSuccess() {
        String deviceId = "1";
        int idCheck = Integer.parseInt(deviceId);

        when(this.devicesService.findAllDevices()).thenReturn(Arrays.asList(new Devices(), new Devices()));

        String result = this.devicesController.deleteDevice(deviceId, model);

        verify(this.devicesService).deleteDeviceById(idCheck);

        Assertions.assertEquals("devices", result);
    }

    @Test
    public void testDeleteDeviceInvalidId() {
        String deviceId = "0";

        String result = devicesController.deleteDevice(deviceId, model);

        verify(devicesService, never()).deleteDeviceById(anyInt()); // метод не должен быть вызван
        Assertions.assertEquals("devices", result);
    }

    @Test
    public void testDeleteDeviceNonNumericId() {
        String deviceId = "abc";

        String result = devicesController.deleteDevice(deviceId, model);

        verify(devicesService, never()).deleteDeviceById(anyInt());
        Assertions.assertEquals("devices", result);
    }

    @Test
    public void testDeleteDeviceException() {
        String deviceId = "1";
        int idCheck = Integer.parseInt(deviceId);

        String result = devicesController.deleteDevice(deviceId, model);

        verify(devicesService).deleteDeviceById(idCheck);
        Assertions.assertEquals("devices", result);
    }

    @Test
    void updateProceduresAssigned() {
    }

    @Test
    void findDevicesById() {
    }
}