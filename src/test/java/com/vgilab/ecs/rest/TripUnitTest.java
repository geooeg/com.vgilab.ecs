/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vgilab.ecs.rest;

import com.vgilab.ecs.persistence.repositories.DeviceRepository;
import com.vgilab.ecs.persistence.repositories.TripRepository;
import com.vgilab.ecs.rest.resource.CreateTripResource;
import com.vgilab.ecs.rest.resource.DeviceResource;
import java.util.UUID;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
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
    private DeviceResource mockedDeviceResource;

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
        mockedDeviceResource = new DeviceResource();
        mockedCreateTripResource = new CreateTripResource();
        mockedCreateTripResource.setDeviceId(UUID.randomUUID().toString());
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
