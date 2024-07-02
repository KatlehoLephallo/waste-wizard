package com.enviro.asessment.garde011.KatlehoLephallo;

import com.enviro.asessment.garde011.KatlehoLephallo.Categories.WasteCategory;
import com.enviro.asessment.garde011.KatlehoLephallo.Categories.WasteType;
import com.enviro.asessment.garde011.KatlehoLephallo.Guidelines.DisposalGuideline;
import com.enviro.asessment.garde011.KatlehoLephallo.Guidelines.DisposalGuidelineRepository;
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
public class DisposalGuidelineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DisposalGuidelineRepository repository;

    @Test
    public void testGetAllDisposalGuidelines() throws Exception {
        // Given
        WasteCategory plastic = new WasteCategory("Plastic", WasteType.RECYCLABLE);
        DisposalGuideline guideline1 = new DisposalGuideline("Recycle in the yellow bin", plastic);
        DisposalGuideline guideline2 = new DisposalGuideline("Remove lids from plastic bottles before recycling", plastic);
        List<DisposalGuideline> disposalGuidelines = Arrays.asList(guideline1, guideline2);

        given(repository.findAll()).willReturn(disposalGuidelines);

        // When & Then
        mockMvc.perform(get("/waste-wizard/disposal-guidelines")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.disposalGuidelineList", hasSize(2)))
                .andExpect(jsonPath("$._embedded.disposalGuidelineList[0].guideline", is(guideline1.getGuideline())))
                .andExpect(jsonPath("$._embedded.disposalGuidelineList[1].guideline", is(guideline2.getGuideline())));
    }


    @Test
    public void testGetDisposalGuideline() throws Exception {
        // Given
        WasteCategory plastic = new WasteCategory("Plastic", WasteType.RECYCLABLE);
        DisposalGuideline guideline = new DisposalGuideline("Recycle in the yellow bin", plastic);

        given(repository.findById(1L)).willReturn(Optional.of(guideline));

        // When & Then
        mockMvc.perform(get("/waste-wizard/disposal-guidelines/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.guideline", is(guideline.getGuideline())));
    }

    @Test
    public void testCreateDisposalGuideline() throws Exception {
        // Given
        WasteCategory plastic = new WasteCategory("Plastic", WasteType.RECYCLABLE);
        DisposalGuideline guideline = new DisposalGuideline("Recycle in the yellow bin", plastic);

        given(repository.save(any(DisposalGuideline.class))).willReturn(guideline);

        // When & Then
        mockMvc.perform(post("/waste-wizard/disposal-guidelines")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"guideline\":\"Recycle in the yellow bin\",\"category\":{\"id\":1,\"name\":\"Plastic\",\"type\":\"RECYCLABLE\"}}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.guideline", is(guideline.getGuideline())));
    }

    @Test
    public void testUpdateDisposalGuideline() throws Exception {
        // Given
        WasteCategory plastic = new WasteCategory("Plastic", WasteType.RECYCLABLE);
        DisposalGuideline guideline = new DisposalGuideline("Recycle in the yellow bin", plastic);

        given(repository.findById(1L)).willReturn(Optional.of(guideline));
        given(repository.save(any(DisposalGuideline.class))).willReturn(guideline);

        // When & Then
        mockMvc.perform(put("/waste-wizard/disposal-guidelines/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"guideline\":\"Dispose of in the grey bin\",\"category\":{\"id\":1,\"name\":\"Plastic\",\"type\":\"RECYCLABLE\"}}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.guideline", is("Dispose of in the grey bin")));
    }

    @Test
    public void testDeleteDisposalGuideline() throws Exception {
        // Given
        WasteCategory plastic = new WasteCategory("Plastic", WasteType.RECYCLABLE);
        DisposalGuideline guideline = new DisposalGuideline("Recycle in the yellow bin", plastic);

        given(repository.findById(1L)).willReturn(Optional.of(guideline));

        // When & Then
        mockMvc.perform(delete("/waste-wizard/disposal-guidelines/1"))
                .andExpect(status().isNoContent());

        verify(repository, times(1)).delete(guideline);
    }


}

