package com.enviro.asessment.garde011.KatlehoLephallo;

import com.enviro.asessment.garde011.KatlehoLephallo.Categories.WasteCategory;
import com.enviro.asessment.garde011.KatlehoLephallo.Categories.WasteCategoryRepository;
import com.enviro.asessment.garde011.KatlehoLephallo.Categories.WasteType;
import com.enviro.asessment.garde011.KatlehoLephallo.Guidelines.DisposalGuideline;
import com.enviro.asessment.garde011.KatlehoLephallo.Guidelines.DisposalGuidelineRepository;
import com.enviro.asessment.garde011.KatlehoLephallo.Tips.RecyclingTip;
import com.enviro.asessment.garde011.KatlehoLephallo.Tips.RecyclingTipsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    private static final Logger log = LoggerFactory.getLogger(DataLoader.class);

    @Bean
    CommandLineRunner initDatabase(WasteCategoryRepository categoryRepository, DisposalGuidelineRepository guidelineRepository, RecyclingTipsRepository tipsRepository) {
        return args -> {
            // Preload waste categories
            WasteCategory plastic = categoryRepository.save(new WasteCategory("Plastic", WasteType.RECYCLABLE));
            WasteCategory glass = categoryRepository.save(new WasteCategory("Glass", WasteType.RECYCLABLE));
            WasteCategory food = categoryRepository.save(new WasteCategory("Food", WasteType.COMPOSTABLE));
            WasteCategory cement = categoryRepository.save(new WasteCategory("Cement", WasteType.NON_RECYCLABLE));

            log.info("Preloading " + plastic);
            log.info("Preloading " + glass);
            log.info("Preloading " + food);
            log.info("Preloading " + cement);

            // Preload disposal guidelines
            log.info("Preloading " + guidelineRepository.save(new DisposalGuideline("Recycle in the yellow bin", plastic)));
            log.info("Preloading " + guidelineRepository.save(new DisposalGuideline("Recycle in the green bin", glass)));
            log.info("Preloading " + guidelineRepository.save(new DisposalGuideline("Compost in the brown bin", food)));
            log.info("Preloading " + guidelineRepository.save(new DisposalGuideline("Dispose of in the grey bin", cement)));

            // Preload recycling tips
            log.info("Preloading " + tipsRepository.save(new RecyclingTip("Clean plastic containers before recycling", plastic)));
            log.info("Preloading " + tipsRepository.save(new RecyclingTip("Remove lids from glass bottles before recycling", glass)));
            log.info("Preloading " + tipsRepository.save(new RecyclingTip("Compost food waste at home", food)));
            log.info("Preloading " + tipsRepository.save(new RecyclingTip("Dispose of cement waste at a designated facility", cement)));
        };
    }

}

