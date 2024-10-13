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
        // Данные
        String deviceName = "TestDevice";
        Devices newDevice = new Devices(deviceName);
        when(devicesService.findAllDevices()).thenReturn(new ArrayList<>());

        // Действие
        String viewName = devicesController.addDevice(deviceName, model);

        // Проверка
        Assertions.assertEquals("devices", viewName);

        verify(devicesService).addNewDevice(any(Devices.class));
    }

    @Test
    void addDeviceEmptyNameNoDeviceAdded() {
        // Данные
        String deviceName = " ";

        // Действие
        String viewName = devicesController.addDevice(deviceName, model);

        // Проверка
        // Пример ожидаемого имени представления
        Assertions.assertEquals("devices", viewName);

        verify(devicesService, never()).addNewDevice(any(Devices.class));
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
    void updateProceduresAssignedSuccessfulUpdateShouldReturnView() {
        // Данные
        String id = "1";
        String name = "DeviceName";
        Devices devices = new Devices(1, name);
        List<Devices> devicesList = new ArrayList<>();
        devicesList.add(devices);

        when(devicesService.findAllDevices()).thenReturn(devicesList);

        // Действие
        String result = devicesController.updateProceduresAssigned(id, name, model);

        // Проверка
        Assertions.assertEquals("devices", result);

        verify(devicesService).updateDevice(any(Devices.class));
    }

    @Test
    void updateProceduresAssignedInvalidIdShouldReturnView() {
        // Данные
        String id = "-1";
        String name = "DeviceName";

        // Действие
        String result = devicesController.updateProceduresAssigned(id, name, model);

        // Проверка
        Assertions.assertEquals("devices", result);

        verify(devicesService, never()).updateDevice(any());  // метод не должен быть вызван
    }

    @Test
    void updateProceduresAssignedEmptyNameShouldReturnView() {
        // Данные
        String id = "1";
        String name = "  "; // после очистки от пробелов значение будет пустым

        // Действие
        String result = devicesController.updateProceduresAssigned(id, name, model);

        // Проверка
        Assertions.assertEquals("devices", result);

        verify(devicesService, never()).updateDevice(any());  // метод не должен быть вызван
    }

    @Test
    void updateProceduresAssignedExceptionThrownShouldReturnView() {
        // Данные
        String id = "1";
        String name = "DeviceName";

        //Симуляция выброса исключения
        doThrow(new RuntimeException()).when(devicesService).updateDevice(any());

        // Действие
        String result = devicesController.updateProceduresAssigned(id, name, model);

        // Проверка
        Assertions.assertEquals("devices", result);
    }

    @Test
    void testFindDevicesByIdValidId() {
        String name = "1";
        int id = Integer.parseInt(name);
        Devices device = new Devices();

        List<Devices> devicesList = Arrays.asList(device);

        when(devicesService.getDevicesById(1)).thenReturn(devicesList);

        String viewName = devicesController.findDevicesById(name, model);

        Assertions.assertEquals("devices", viewName);

        verify(model).addAttribute("devicesList", devicesList);

        verify(devicesService).getDevicesById(id);
    }

    @Test
    void testFindDevicesByIdSubZero() {
        String name = "0";

        List<Devices> devicesList = new ArrayList<>();

        when(devicesService.findAllDevices()).thenReturn(devicesList);

        String viewName = devicesController.findDevicesById(name, model);

        Assertions.assertEquals("devices", viewName);

        verify(model).addAttribute("devicesList", devicesList);
    }

    @Test
    void testFindDevicesByIdInvalidId() {
        String name = "invalid";
        Devices device = new Devices();
        List<Devices> devicesList = Arrays.asList(device);

        when(devicesService.getDevicesByName(name)).thenReturn(devicesList);

        String viewName = devicesController.findDevicesById(name, model);

        Assertions.assertEquals("devices", viewName);

        verify(model).addAttribute("devicesList", devicesList);
    }
}