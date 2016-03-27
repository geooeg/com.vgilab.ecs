package com.vgilab.ecs.rest;

import com.google.gson.Gson;
import com.vgilab.ecs.persistence.repositories.PositionInTimeRepository;
import com.vgilab.ecs.persistence.repositories.PositionRepository;
import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
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
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(locations = {"classpath:testApplicationContext.xml"})
public class PositionBatchUnitTest {

    private PositionBatchResource mockedPositionBatchResource;

    private MockMvc mockMvc;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private PositionInTimeRepository positionInTimeRepository;

    public PositionBatchUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new PositionController(positionRepository, positionInTimeRepository)).build();
        final List<PositionResource> positions = new LinkedList<>();
        for (int i = 1; i < 4; i++) {
            final PositionResource positionResource = new PositionResource();
            positionResource.setAltitude(10d * i);
            positionResource.setLongitude(9.7332200d);
            positionResource.setLatitude(52.3705200d);
            positionResource.setTrackedOn(1457989364000l);
            positions.add(positionResource);
        }
        mockedPositionBatchResource = new PositionBatchResource();
        mockedPositionBatchResource.setMaker("Apple");
        mockedPositionBatchResource.setModel("iPhone 5s");
        mockedPositionBatchResource.setSoftware("iOS 9.1");
        mockedPositionBatchResource.setPositions(positions);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void validatePostPostionBatch() throws Exception {
        final Gson gson = new Gson();
        final String toJson = gson.toJson(mockedPositionBatchResource);
        mockMvc.perform(post("/positions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
