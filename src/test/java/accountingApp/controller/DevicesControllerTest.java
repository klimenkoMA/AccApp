package accountingApp.controller;

import accountingApp.entity.Devices;
import accountingApp.entity.Employee;
import accountingApp.entity.Room;
import accountingApp.service.DevicesService;
import accountingApp.service.EmployeeService;
import accountingApp.service.RoomService;
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
    private RoomService roomService;
    @Mock
    private EmployeeService employeeService;
    @Mock
    private Model model;
    @Mock
    private Checker checker;

    private List<Devices> devicesList;
    private String deviceName;
    private String deviceId;
    private String viewName;
    private String description;
    private long inventory;
    private Devices device;
    private Room room;
    private Employee employee;

    {
        // Arrange: Подготовка данных для теста
        deviceName = "Hiper";
        deviceId = "1";
        description = "Нет-топ для сотрудников IT-отдела";
        inventory = 111111L;
        room = new Room();
        employee = new Employee();
        device = new Devices(deviceName
                , description
                , inventory
                , room
                , employee);
        Devices device2 = new Devices();

        // Создаем список устройств
        devicesList = new ArrayList<>();
        devicesList.add(device);
        devicesList.add(device2);
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
        viewName = devicesController.addDevice(deviceName
                , description
                , inventory + ""
                , room
                , employee
                , model);

        // Проверка
        Assertions.assertEquals("devices", viewName);

        verify(devicesService, atMost(1)).addNewDevice(new Devices(deviceName
                , description
                , inventory
                , room
                , employee));
    }

    @Test
    void addDeviceEmptyNameNoDeviceAdded() {
        // Данные
        deviceName = " ";
        description = " ";

        // Действие
        viewName = devicesController.addDevice(deviceName
                , description
                , " "
                , room
                , employee
                , model);

        // Проверка
        // Пример ожидаемого имени представления
        Assertions.assertEquals("devices", viewName);

        verify(devicesService, never()).addNewDevice(new Devices(deviceName
                , description
                , -1L
                , room
                , employee));
    }

    @Test
    public void testDeleteDeviceSuccess() {
        deviceId = "1";
        int idCheck = Integer.parseInt(deviceId);

        when(devicesService.findAllDevices()).thenReturn(Arrays.asList(new Devices(),
                new Devices()));

        viewName = devicesController.deleteDevice(deviceId, model);

        verify(devicesService, never()).deleteDeviceById(idCheck);

        Assertions.assertEquals("devices", viewName);
    }

    @Test
    public void testDeleteDeviceInvalidId() {
        deviceId = "0";

        String result = devicesController.deleteDevice(deviceId, model);

        verify(devicesService, never()).deleteDeviceById(0); // метод не должен быть вызван
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

        doThrow(new RuntimeException()).when(devicesService).deleteDeviceById(1);

        verify(devicesService, never()).deleteDeviceById(idCheck);
    }

    @Test
    void updateProceduresAssignedSuccessfulUpdateShouldReturnView() {
        // Данные
        deviceId = "1";
        deviceName = "DeviceName";
        device = new Devices(1, deviceName
                , description
                , inventory
                , room
                , employee);
        devicesList.add(device);

        when(devicesService.findAllDevices()).thenReturn(devicesList);

        // Действие
        viewName = devicesController.updateDevice(deviceId
                , deviceName
                , description
                , inventory + ""
                , room
                , employee
                , model);

        // Проверка
        Assertions.assertEquals("devices", viewName);

        verify(devicesService, atMost(1)).updateDevice(device);
    }

    @Test
    void updateProceduresAssignedInvalidIdShouldReturnView() {
        // Данные
        deviceId = "-1";
        deviceName = "DeviceName";

        // Действие
        viewName = devicesController.updateDevice(deviceId
                , deviceName
                , description
                , " "
                , room
                , employee
                , model);

        // Проверка
        Assertions.assertEquals("devices", viewName);

        verify(devicesService, never()).updateDevice(new Devices(-1
                , deviceName
                , description
                , -1L
                , room
                , employee));  // метод не должен быть вызван
    }

    @Test
    void updateProceduresAssignedEmptyNameShouldReturnView() {
        // Данные
        deviceId = "1";
        deviceName = "  "; // после очистки от пробелов значение будет пустым
        description = " ";

        // Действие
        viewName = devicesController.updateDevice(deviceId
                , deviceName
                , description
                , " "
                , room
                , employee
                , model);

        // Проверка
        Assertions.assertEquals("devices", viewName);

        verify(devicesService, never()).updateDevice(new Devices(1
                , deviceName
                , description
                , -1L
                , room
                , employee));  // метод не должен быть вызван
    }

    @Test
    void updateProceduresAssignedExceptionThrownShouldReturnView() {
        // Данные
        deviceId = "1";
        deviceName = "DeviceName";

        //Симуляция выброса исключения
        doThrow(new RuntimeException()).when(devicesService).updateDevice(any());

        verify(devicesService, never()).updateDevice(new Devices(1
                , deviceName
                , description
                , -1L
                , room
                , employee));  // метод не должен быть вызван

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

        device = new Devices(deviceName
                , description
                , inventory
                , room
                , employee);
        devicesList = Collections.singletonList(device);

        doThrow(new RuntimeException()).when(devicesService).getDevicesByName(deviceName);

        verify(model, never()).addAttribute("devicesList", devicesList);
    }
}