package com.dataexport.consumer.service.utality;

import java.io.File;

public class FileExportUtils {
    public static File getOrCreateFile(String folder, String filename) {
        File directory = new File("ExportFiles/" + folder);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        return new File(directory, filename);
    }
}
