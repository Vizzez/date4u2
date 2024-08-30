package com.wizzey.date4u2.shell;

import com.wizzey.date4u2.core.FileSystem;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.util.unit.DataSize;

@ShellComponent
public class FSCommands {

    private final FileSystem fileSystem;

    public FSCommands(FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    @ShellMethod("Prints the free disk space.")
    public long freeDiskSpace() {
       return DataSize.ofBytes(fileSystem.getFreeDiskSpace()).toMegabytes();
    }

    @ShellMethod("Display required free disk space.")
    public long minimumFreeDiskSpace() {
        return 1_000_000;
    }
}
