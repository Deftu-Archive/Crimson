package xyz.matthewtgm.requisite.core;

import java.io.File;

public interface IRequisite {

    void initialize(File gameDir);

    IRequisiteManager getManager();
    IRequisite getInstance();

}