package com.rofix.fitspot.webservice.api.user.controller;

import com.rofix.fitspot.webservice.api.user.dto.ClothesResponseDto;
import com.rofix.fitspot.webservice.api.user.entity.Clothing;
import com.rofix.fitspot.webservice.api.user.service.ClothingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clothes")
public class ClothingController {

    private final ClothingService clothingService;

    public ClothingController(ClothingService clothingService) {
        this.clothingService = clothingService;
    }

    @GetMapping("/search")
    public Page<ClothesResponseDto> searchClothes(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "true") boolean searchInTitle,
            @RequestParam(defaultValue = "false") boolean searchInDescription,
            @RequestParam(required = false) String category,
            Pageable pageable
    ) {
        Page<Clothing> clothes = clothingService.searchClothes(keyword, searchInTitle, searchInDescription, category, pageable);

        return clothes.map(ClothesResponseDto::fromEntity);
    }
}
