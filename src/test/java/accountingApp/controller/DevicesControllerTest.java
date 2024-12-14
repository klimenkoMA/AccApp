package accountingApp.controller;

import accountingApp.entity.Devices;
import accountingApp.service.DevicesService;
import accountingApp.usefulmethods.Checker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;


class DevicesControllerTest {

    @InjectMocks
    private DevicesController devicesController;

    @Mock
    private DevicesService devicesService;

    @Mock
    private Model model;

    @Mock
    private Checker checker;

    private List<Devices> devicesList;
    private String deviceName;
    private String deviceId;
    private String viewName;
    private Devices device;

    {
        // Arrange: Подготовка данных для теста
        deviceName = "Hiper";
        deviceId = "1";
        device = new Devices(deviceName);
        Devices device2 = new Devices();

        // Создаем список устройств
        devicesList = new ArrayList<>();
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
        viewName = devicesController.getDevices(model);

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
        // Данные
        deviceName = "TestDevice";

        when(devicesService.findAllDevices()).thenReturn(new ArrayList<>());

        // Действие
        viewName = devicesController.addDevice(deviceName, model);

        // Проверка
        Assertions.assertEquals("devices", viewName);

        verify(devicesService, atMost(1)).addNewDevice(any(Devices.class));
    }

    @Test
    void addDeviceEmptyNameNoDeviceAdded() {
        // Данные
        deviceName = " ";

        // Действие
        viewName = devicesController.addDevice(deviceName, model);

        // Проверка
        // Пример ожидаемого имени представления
        Assertions.assertEquals("devices", viewName);

        verify(devicesService, never()).addNewDevice(new Devices(deviceName));
    }

    @Test
    public void testDeleteDeviceSuccess() {
        deviceId = "1";
        int idCheck = Integer.parseInt(deviceId);

        when(devicesService.findAllDevices()).thenReturn(Arrays.asList(new Devices(),
                new Devices()));

        viewName = devicesController.deleteDevice(deviceId, model);

        verify(devicesService).deleteDeviceById(idCheck);

        Assertions.assertEquals("devices", viewName);
    }

    @Test
    public void testDeleteDeviceInvalidId() {
        deviceId = "0";

        String result = devicesController.deleteDevice(deviceId, model);

        verify(devicesService, never()).deleteDeviceById(anyInt()); // метод не должен быть вызван
        Assertions.assertEquals("devices", result);
    }

    @Test
    public void testDeleteDeviceNonNumericId() {
        deviceId = "abc";

        viewName = devicesController.deleteDevice(deviceId, model);

        verify(devicesService, never()).deleteDeviceById(anyInt());
        Assertions.assertEquals("devices", viewName);
    }

    @Test
    public void testDeleteDeviceException() {
        deviceId = "1";
        int idCheck = Integer.parseInt(deviceId);

        doThrow(new RuntimeException()).when(devicesService).deleteDeviceById(anyInt());

        verify(devicesService, never()).deleteDeviceById(idCheck);
    }

    @Test
    void updateProceduresAssignedSuccessfulUpdateShouldReturnView() {
        // Данные
        deviceId = "1";
        deviceName = "DeviceName";
        device = new Devices(deviceName);
        devicesList.add(device);

        when(devicesService.findAllDevices()).thenReturn(devicesList);

        // Действие
        viewName = devicesController.updateDevice(deviceId, deviceName, model);

        // Проверка
        Assertions.assertEquals("devices", viewName);

        verify(devicesService).updateDevice(any(Devices.class));
    }

    @Test
    void updateProceduresAssignedInvalidIdShouldReturnView() {
        // Данные
        deviceId = "-1";
        deviceName = "DeviceName";

        // Действие
        viewName = devicesController.updateDevice(deviceId, deviceName, model);

        // Проверка
        Assertions.assertEquals("devices", viewName);

        verify(devicesService, never()).updateDevice(any());  // метод не должен быть вызван
    }

    @Test
    void updateProceduresAssignedEmptyNameShouldReturnView() {
        // Данные
        deviceId = "1";
        deviceName = "  "; // после очистки от пробелов значение будет пустым

        // Действие
        viewName = devicesController.updateDevice(deviceId, deviceName, model);

        // Проверка
        Assertions.assertEquals("devices", viewName);

        verify(devicesService, never()).updateDevice(new Devices(deviceName));  // метод не должен быть вызван
    }

    @Test
    void updateProceduresAssignedExceptionThrownShouldReturnView() {
        // Данные
        deviceId = "1";
        deviceName = "DeviceName";

        //Симуляция выброса исключения
        doThrow(new RuntimeException()).when(devicesService).updateDevice(any());

        // Действие
        viewName = devicesController.updateDevice(deviceId, deviceName, model);

        // Проверка
        Assertions.assertEquals("devices", viewName);

    }

    @Test
    void testFindDevicesByIdValidId() {
        deviceName = "1";
        int id = Integer.parseInt(deviceName);
        device = new Devices();

        devicesList = Collections.singletonList(device);

        when(devicesService.getDevicesById(1)).thenReturn(devicesList);

        viewName = devicesController.findDevicesById(deviceName, model);

        Assertions.assertEquals("devices", viewName);

        verify(model).addAttribute("devicesList", devicesList);

        verify(devicesService).getDevicesById(id);
    }

    @Test
    void testFindDevicesByIdSubZero() {
        deviceName = "0";
        int idCheck = Integer.parseInt(deviceName);

        devicesList = new ArrayList<>();

        when(devicesService.findAllDevices()).thenReturn(devicesList);

        viewName = devicesController.findDevicesById(deviceName, model);

        Assertions.assertEquals("devices", viewName);

        verify(model).addAttribute("devicesList", devicesList);

        verify(devicesService, never()).getDevicesById(idCheck);
    }

    @Test
    void testFindDevicesThrowsException() {
        deviceName = "invalid";

        device = new Devices(deviceName);
        devicesList = Collections.singletonList(device);

        doThrow(new RuntimeException()).when(devicesService).getDevicesByName(deviceName);

        verify(model, never()).addAttribute("devicesList", devicesList);
    }
}