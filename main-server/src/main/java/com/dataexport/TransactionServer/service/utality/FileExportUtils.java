package com.dataexport.TransactionServer.service.utality;

import java.io.File;

public class FileExportUtils {
    public static File getOrCreateFile(String folder, String filename) {
        File directory = new File("exported-files/" + folder);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        return new File(directory, filename);
    }
}
