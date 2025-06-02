package com.dataexport.TransactionServer.service.utality;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.util.concurrent.*;

@Component
public class FileCleaner {

    Logger log = LoggerFactory.getLogger(FileCleaner.class);

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public void scheduleFileDeletion(Path filePath, long delayMinutes) {
        log.info("Scheduling deletion of file: {} in {} minutes.", filePath, delayMinutes); // Log the scheduling info
        scheduler.schedule(() -> {
            try {
                Files.deleteIfExists(filePath);
                log.info("Deleted: {}", filePath);
            } catch (IOException e) {
                log.error("Failed to delete file: {}", filePath, e);
            }
        }, delayMinutes, TimeUnit.MINUTES);
    }

    public Path getFilePath(String fileName) {
        Path projectRoot = Paths.get(System.getProperty("user.dir"));
        return projectRoot.resolve("exported-files").resolve(fileName).normalize();
    }

    @SuppressWarnings("unused")
    public void scheduleDeletionBasedOnCreation(Path filePath, long lifetimeMinutes) {
        try {
            BasicFileAttributes attrs = Files.readAttributes(filePath, BasicFileAttributes.class);
            Instant creationTime = attrs.creationTime().toInstant();
            long delay = creationTime.plusSeconds(lifetimeMinutes * 60).toEpochMilli()
                    - Instant.now().toEpochMilli();

            if (delay > 0) {
                scheduler.schedule(() -> {
                    try {
                        Files.deleteIfExists(filePath);
                    } catch (IOException e) {
                        log.error("Failed to delete file: {}", filePath, e);
                    }
                }, delay, TimeUnit.MILLISECONDS);
            } else {
                // If time has already passed, delete immediately
                Files.deleteIfExists(filePath);
                log.info("Deleted (based on creation time): {}", filePath);
            }
        } catch (IOException e) {
            log.error("Failed to get file attributes for: {}", filePath, e);
        }
    }
}
