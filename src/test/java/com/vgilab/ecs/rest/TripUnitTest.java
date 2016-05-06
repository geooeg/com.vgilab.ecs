package com.vgilab.ecs.rest;

import com.google.gson.Gson;
import com.vgilab.ecs.persistence.repositories.DeviceRepository;
import com.vgilab.ecs.persistence.repositories.TripRepository;
import com.vgilab.ecs.rest.resource.CreateTripResource;
import com.vgilab.ecs.rest.resource.DeviceResource;
import com.vgilab.ecs.rest.resource.StopTripResource;
import java.util.Calendar;
import java.util.UUID;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 *
 * @author smuellner
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(locations = {"classpath:testApplicationContext.xml"})
public class TripUnitTest {

    private CreateTripResource mockedCreateTripResource;

    private StopTripResource mockedStopTripResource;

    private MockMvc mockMvc;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private TripRepository tripRepository;

    public TripUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new TripController(tripRepository, deviceRepository)).build();
        mockedCreateTripResource = mock(CreateTripResource.class);
        mockedCreateTripResource.setDeviceId(UUID.randomUUID().toString());
        mockedCreateTripResource.setStartTime(Calendar.getInstance());  
        mockedStopTripResource = mock(StopTripResource.class);
        mockedStopTripResource.setTripId(UUID.randomUUID().toString());
        mockedStopTripResource.setEndTime(Calendar.getInstance()); 
    }

    @After
    public void tearDown() {
    }

    @Test
    public void validatePostCreateTrip() throws Exception {
        final Gson gson = new Gson();
        final String toJson = gson.toJson(mockedCreateTripResource);
        mockMvc.perform(post("/create_trip")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void validatePostStopTrip() throws Exception {
        final Gson gson = new Gson();
        final String toJson = gson.toJson(mockedStopTripResource);
        mockMvc.perform(post("/stop_trip")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
