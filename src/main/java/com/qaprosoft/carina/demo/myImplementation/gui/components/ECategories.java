package com.qaprosoft.carina.demo.myImplementation.gui.components;

public enum ECategories {
    CELLPHONES_TABLETS("CELULARES Y TABLETS"),
    COMPUTING("COMPUTACION"),
    ELECTRONIC_CLEANING("LIMPIEZA ELECTRONICA"),
    HARDWARE("COMPONENTES DE HW"),
    TV_AUDIO_VIDEO("TV, AUDIO Y VIDEO"),
    PERIPHERAL("PERIFERICOS Y ACCESORIOS");

    private final String value;

    ECategories(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
