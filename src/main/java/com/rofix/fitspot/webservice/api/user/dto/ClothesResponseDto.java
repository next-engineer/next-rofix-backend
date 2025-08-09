package com.rofix.fitspot.webservice.api.user.dto;

import com.rofix.fitspot.webservice.api.user.entity.Clothing;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ClothesResponseDto {
    private Long clothingId;
    private String title;
    private String category;
    private String color;
    private String imageUrl;
    private String brand;
    private String season;
    private String description;
    private LocalDateTime createdAt;
    private Long likeCount;

    public static ClothesResponseDto fromEntity(Clothing clothing) {
        return new ClothesResponseDto(
                clothing.getClothingId(),
                clothing.getTitle(),
                clothing.getCategory(),
                clothing.getColor(),
                clothing.getImageUrl(),
                clothing.getBrand(),
                clothing.getSeason(),
                clothing.getDescription()
        );
    }
}
