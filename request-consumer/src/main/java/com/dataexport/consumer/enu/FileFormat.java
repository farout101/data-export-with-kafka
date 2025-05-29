package com.dataexport.consumer.enu;

import lombok.Getter;

@Getter
public enum FileFormat {
    CSV("csv"),
    JSON("json"),
    XLSX("xlsx");

    private final String format;

    FileFormat(String format) {
        this.format = format;
    }

    @Override
    public String toString() {
        return format;
    }
}
