package com.enviro.asessment.garde011.KatlehoLephallo;

import com.enviro.asessment.garde011.KatlehoLephallo.Categories.WasteCategory;
import com.enviro.asessment.garde011.KatlehoLephallo.Categories.WasteType;
import com.enviro.asessment.garde011.KatlehoLephallo.Tips.RecyclingTip;
import com.enviro.asessment.garde011.KatlehoLephallo.Tips.RecyclingTipsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
@AutoConfigureMockMvc
public class RecyclingTipControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecyclingTipsRepository repository;

    @Test
    public void testGetAllRecyclingTips() throws Exception {
        // Given
        WasteCategory plastic = new WasteCategory("Plastic", WasteType.RECYCLABLE);
        RecyclingTip tip1 = new RecyclingTip("Clean plastic containers before recycling", plastic);
        RecyclingTip tip2 = new RecyclingTip("Remove lids from plastic bottles before recycling", plastic);
        List<RecyclingTip> recyclingTips = Arrays.asList(tip1, tip2);

        given(repository.findAll()).willReturn(recyclingTips);

        // When & Then
        mockMvc.perform(get("/waste-wizard/recycling-tips")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.recyclingTipList", hasSize(2)))
                .andExpect(jsonPath("$._embedded.recyclingTipList[0].tip", is(tip1.getTip())))
                .andExpect(jsonPath("$._embedded.recyclingTipList[1].tip", is(tip2.getTip())));
    }

    @Test
    public void testGetRecyclingTip() throws Exception {
        // Given
        WasteCategory plastic = new WasteCategory("Plastic", WasteType.RECYCLABLE);
        RecyclingTip recyclingTip = new RecyclingTip("Clean plastic containers before recycling", plastic);

        given(repository.findById(1L)).willReturn(Optional.of(recyclingTip));

        // When & Then
        mockMvc.perform(get("/waste-wizard/recycling-tips/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tip", is(recyclingTip.getTip())));
    }

    @Test
    public void testCreateRecyclingTip() throws Exception {
        // Given
        WasteCategory plastic = new WasteCategory("Plastic", WasteType.RECYCLABLE);
        RecyclingTip recyclingTip = new RecyclingTip("Clean plastic containers before recycling", plastic);

        given(repository.save(any(RecyclingTip.class))).willReturn(recyclingTip);


        // When & Then
        mockMvc.perform(post("/waste-wizard/recycling-tips")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"tip\":\"Clean plastic containers before recycling\",\"category\":{\"id\":1,\"name\":\"Plastic\",\"type\":\"RECYCLABLE\"}}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.tip", is(recyclingTip.getTip())));
    }

    @Test
    public void testUpdateRecyclingTip() throws Exception {
        // Given
        WasteCategory plastic = new WasteCategory("Plastic", WasteType.RECYCLABLE);
        RecyclingTip recyclingTip = new RecyclingTip("Clean plastic containers before recycling", plastic);

        given(repository.findById(1L)).willReturn(Optional.of(recyclingTip));
        given(repository.save(any(RecyclingTip.class))).willReturn(recyclingTip);

        // When & Then
        mockMvc.perform(put("/waste-wizard/recycling-tips/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"tip\":\"Wash out plastic bottles before recycling\",\"category\":{\"id\":2,\"name\":\"Glass\",\"type\":\"RECYCLABLE\"}}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tip", is("Wash out plastic bottles before recycling")));
    }

    @Test
    public void testDeleteRecyclingTip() throws Exception {
        // Given
        WasteCategory plastic = new WasteCategory("Plastic", WasteType.RECYCLABLE);
        RecyclingTip recyclingTip = new RecyclingTip("Clean plastic containers before recycling", plastic);

        given(repository.findById(1L)).willReturn(Optional.of(recyclingTip));

        // When & Then
        mockMvc.perform(delete("/waste-wizard/recycling-tips/1"))
                .andExpect(status().isNoContent());

        verify(repository, times(1)).delete(recyclingTip);
    }


}

