package com.wizzey.date4u2.core;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileSystem {

    /**
     * The root directory of the file system.
     */
    private final Path root = Paths.get(System.getProperty("user.home")).resolve("fs").toAbsolutePath().normalize();


    public FileSystem() {
        if (!Files.isDirectory(root)) {
            try {
                Files.createDirectory(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Returns the free disk space in bytes.
     *
     * @return the free disk space in bytes
     */
    public long getFreeDiskSpace() {
        return root.toFile().getFreeSpace();
    }

    /**
     * Loads the bytes of the file with the given filename.
     *
     * @param filename the filename
     * @return the bytes of the file
     */
    public byte[] load(String filename) {
        try {
            Path path = resolve(filename);
            return Files.readAllBytes(path);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * Stores the given bytes in a file with the given filename.
     *
     * @param filename the filename
     * @param bytes    the bytes to store
     */
    public void store(String filename, byte[] bytes) {
        try {
            Files.write(resolve(filename), bytes);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }


    private Path resolve(String filename) {
        Path path = root.resolve(filename).toAbsolutePath().normalize();
        if (!path.startsWith(root))
            throw new SecurityException("Access to " + path + " denied");
        return path;
    }

}
