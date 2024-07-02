package com.enviro.asessment.garde011.KatlehoLephallo;

import com.enviro.asessment.garde011.KatlehoLephallo.Categories.WasteCategory;
import com.enviro.asessment.garde011.KatlehoLephallo.Categories.WasteCategoryRepository;
import com.enviro.asessment.garde011.KatlehoLephallo.Categories.WasteType;
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
public class WasteCategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WasteCategoryRepository repository;

    @Test
    public void testGetAllWasteCategories() throws Exception {
        // Given
        WasteCategory plastic = new WasteCategory("Plastic", WasteType.RECYCLABLE);
        WasteCategory glass = new WasteCategory("Glass", WasteType.RECYCLABLE);
        List<WasteCategory> wasteCategories = Arrays.asList(plastic, glass);

        given(repository.findAll()).willReturn(wasteCategories);

        // When & Then
        mockMvc.perform(get("/waste-wizard/waste-categories")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.wasteCategoryList", hasSize(2)))
                .andExpect(jsonPath("$._embedded.wasteCategoryList[0].name", is(plastic.getName())))
                .andExpect(jsonPath("$._embedded.wasteCategoryList[1].name", is(glass.getName())));
    }

    @Test
    public void testGetWasteCategory() throws Exception {
        // Given
        WasteCategory plastic = new WasteCategory("Plastic", WasteType.RECYCLABLE);

        given(repository.findById(1L)).willReturn(Optional.of(plastic));

        // When & Then
        mockMvc.perform(get("/waste-wizard/waste-categories/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(plastic.getName())));
    }

    @Test
    public void testCreateWasteCategory() throws Exception {
        // Given
        WasteCategory plastic = new WasteCategory("Plastic", WasteType.RECYCLABLE);

        given(repository.save(any(WasteCategory.class))).willReturn(plastic);

        // When & Then
        mockMvc.perform(post("/waste-wizard/waste-categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Plastic\",\"type\":\"RECYCLABLE\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(plastic.getName())));
    }

    @Test
    public void testUpdateWasteCategory() throws Exception {
        // Given
        WasteCategory plastic = new WasteCategory("Plastic", WasteType.RECYCLABLE);

        given(repository.findById(1L)).willReturn(Optional.of(plastic));
        given(repository.save(any(WasteCategory.class))).willReturn(plastic);

        // When & Then
        mockMvc.perform(put("/waste-wizard/waste-categories/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Plastic\",\"type\":\"RECYCLABLE\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(plastic.getName())));
    }

    @Test
    public void testDeleteWasteCategory() throws Exception {
        // Given
        WasteCategory plastic = new WasteCategory("Plastic", WasteType.RECYCLABLE);

        given(repository.findById(1L)).willReturn(Optional.of(plastic));

        // When & Then
        mockMvc.perform(delete("/waste-wizard/waste-categories/1"))
                .andExpect(status().isNoContent());

        verify(repository, times(1)).delete(plastic);
    }


}
