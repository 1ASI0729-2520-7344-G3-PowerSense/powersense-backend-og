package com.powersense.inventory.domain.model.valueobjects;

/**
 * Device Category
 * Enum representing different types of devices
 */
public enum DeviceCategory {
    LIGHT("Light", "💡"),
    AC("Air Conditioner", "❄️"),
    TV("Television", "📺"),
    REFRIGERATOR("Refrigerator", "🧊"),
    HEATING("Heating", "🔥"),
    COMPUTER("Computer", "💻"),
    FAN("Fan", "🌀"),
    WASHING_MACHINE("Washing Machine", "🧺"),
    MICROWAVE("Microwave", "📟"),
    CONSOLE("Gaming Console", "🎮"),
    PRINTER("Printer", "🖨️"),
    DRYER("Dryer", "🌡️"),
    OVEN("Oven", "🍳"),
    COFFEE_MAKER("Coffee Maker", "☕"),
    WATER_HEATER("Water Heater", "🚿"),
    SOUND_SYSTEM("Sound System", "🔊"),
    MOTOR("Motor", "⚙️"),
    GENERIC_POWER("Generic Power Device", "🔌");

    private final String displayName;
    private final String icon;

    DeviceCategory(String displayName, String icon) {
        this.displayName = displayName;
        this.icon = icon;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getIcon() {
        return icon;
    }
}