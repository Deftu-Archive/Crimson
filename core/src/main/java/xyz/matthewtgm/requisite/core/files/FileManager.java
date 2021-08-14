package xyz.matthewtgm.requisite.core.files;

import java.io.File;

public class FileManager {

    public File getConfigDirectory(File gameDirectory) {
       return checkExistence(new File(gameDirectory, "config"));
    }

    public File getTgmDevelopmentDirectory(File configDirectory) {
        return checkExistence(new File(configDirectory, "TGMDevelopment"));
    }

    public File getRequisiteDirectory(File tgmDevelopmentDirectory) {
        return checkExistence(new File(tgmDevelopmentDirectory, "@NAME@"));
    }

    private File checkExistence(File directory) {
        if (!directory.exists() && !directory.mkdirs())
            throw new IllegalStateException("Unable to create Requisite directories.");
        return directory;
    }

}