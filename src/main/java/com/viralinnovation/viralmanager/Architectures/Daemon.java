package com.viralinnovation.viralmanager.Architectures;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.PrintStream;

public final class Daemon extends Architecture {

    public Daemon(@NotNull File activeDirectory) {
        super(activeDirectory);
    }

    @Override
    public void init(PrintStream streamToStreamTo) {

    }

    @Override
    public void kill() {

    }

    @Override
    public void runCommand(String command) {

    }

    @Override
    public void setExecutable() {

    }

    @Override
    public void updateExecutable(File object, @Nullable String identifier) {

    }
}
