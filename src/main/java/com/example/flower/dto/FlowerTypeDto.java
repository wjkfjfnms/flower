package com.example.flower.dto;

import com.example.flower.enums.flowerTypeEnum;

public class FlowerTypeDto {
    private final String displayName;

    public FlowerTypeDto(flowerTypeEnum flowerType) {
        this.displayName = flowerType.getDisplayName();
    }

    public String getDisplayName() {
        return displayName;
    }
}
