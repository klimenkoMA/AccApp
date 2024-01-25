package accountingApp.repository;

import accountingApp.controller.DevicesController;
import accountingApp.entity.Devices;
import accountingApp.service.DevicesService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DevicesController.class)
public class DevicesControllerTest {

    //test getDevices
    //test addDevice
    //test deleteDevice
    //test updateDevice
    //test findDeviceByID

    @Autowired
    MockMvc mvc;

    @Autowired
    DevicesService service;

    @Autowired
    DevicesController controller;

    @Autowired
    DevicesRepository repository;


    private List<Devices> getDevices() {
        Devices dev1 = new Devices( "Kyocera");
        Devices dev2 = new Devices( "Acer");
        Devices dev3 = new Devices("Canon");

        List<Devices> devices = new ArrayList<>();
        devices.add(dev1);
        devices.add(dev2);
        devices.add(dev3);

        return devices;
    }


    @Test //getDevices
    void mustReturnDevicesList() throws Exception {

        Mockito.when(this.service.findAllDevices()).thenReturn(getDevices());

        mvc.perform(get("devices"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3));
    }

    @Test
    void mustAddDeviceToBD() throws Exception {

        Devices dev1 = new Devices(4,"HP");
//        Mockito.when(this.service.addNewDevice()).thenReturn(getDevices());
    }

}
