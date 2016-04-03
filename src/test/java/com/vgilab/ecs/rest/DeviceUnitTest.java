package com.vgilab.ecs.rest;

import com.vgilab.ecs.persistence.repositories.AuthorizationCodeRepository;
import com.vgilab.ecs.persistence.repositories.DeviceRepository;
import com.vgilab.ecs.rest.resource.DeviceResource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
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
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(locations = {"classpath:testApplicationContext.xml"})
public class DeviceUnitTest {
    
    private DeviceResource mockedDeviceResource;

    private MockMvc mockMvc;

    @Autowired
    private DeviceRepository deviceRepository;
    
    @Autowired
    private AuthorizationCodeRepository authorizationCodeRepository;
    
    public DeviceUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new DeviceController(deviceRepository, authorizationCodeRepository)).build();
        mockedDeviceResource = new DeviceResource();
        mockedDeviceResource.setMaker("Apple");
        mockedDeviceResource.setModel("iPhone 5s");
        mockedDeviceResource.setSoftware("iOS 9.1");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testDeviceCreation() {
    
    
    }
}
