package com.example.flower.enums;

import lombok.Getter;

/**
 * 分类类型枚举类
 */
@Getter
public enum flowerTypeEnum {
    ROSE("玫瑰"),
    LILY("百合"),
    TULIP("郁金香"),
    SUNFLOWER("向日葵"),
    ORCHID("兰花");

    private final String displayName;

    flowerTypeEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
