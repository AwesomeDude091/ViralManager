package com.viralinnovation.viralmanager.Architectures;

import com.viralinnovation.viralmanager.Utils.ViralFileUtils;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Objects;

public final class JVM extends Architecture {

    private File jarFile;
    private final LinkedList<String> flags;
    private final int Xms;
    private final int Xmx;
    private final int javaVersion;
    private Process process;

    private static final Logger logger = LogManager.getLogger(JVM.class);

    public JVM(File activeDirectory, File jarFile, @Nullable LinkedList<String> flags, int Xms, int Xmx, int javaVersion) {
        super(activeDirectory);
        this.jarFile = jarFile;
        this.flags = flags;
        this.Xms = Xms;
        this.Xmx = Xmx;
        this.javaVersion = javaVersion;
    }

    @Override
    public void init(PrintStream streamToStreamTo) {
        setExecutable();
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(commandConstructor());
        processBuilder.directory(activeDirectory);
        try {
            process = processBuilder.start();
        } catch (IOException e) {
            logger.trace(e);
        }
        streamRedirector = new StreamRedirector(process.getInputStream(), process.getOutputStream(), streamToStreamTo);
        new Thread(streamRedirector).start();
    }

    @Override
    public void kill() {
        process.destroy();
        streamRedirector.killThread();
    }

    @Override
    public void runCommand(String command) {
        streamRedirector.insertConmmand(command);
    }

    @Override
    public void setExecutable() {
        if(!ViralFileUtils.isFilePresentEqualName(jarFile.getName(), jarFile)) {
            try {
                FileUtils.copyFileToDirectory(jarFile, activeDirectory);
            } catch (IOException e) {
                logger.trace(e);
            }
        }
    }

    @Override
    public void updateExecutable(Object file) {
        if(file instanceof File){
            File oldJar = new File(ViralFileUtils.generateOSBasedClassPath(activeDirectory.getAbsolutePath(), jarFile.getName()));
            try {
                FileUtils.delete(oldJar);
                this.jarFile = (File) file;
            } catch (IOException e) {
                logger.trace(e);
            }
        }
    }

    private @NotNull LinkedList<String> commandConstructor() {
        String path = ViralFileUtils.generateOSBasedClassPath(activeDirectory.getAbsolutePath(), jarFile.getName());
        LinkedList<String> command = new LinkedList<>();
        if(javaVersion == -1){
            command.add("java");
        } else {
            if(ViralFileUtils.findJavaDirectory(javaVersion) == null) {
                command.add("java");
                logger.error("Java Version: " + javaVersion + " could not be found reverting to System Default");
            } else {
                command.add(Objects.requireNonNull(ViralFileUtils.findJavaDirectory(javaVersion)).getAbsolutePath());
            }
        }
        command.add("-Xms" + Xms + "M");
        command.add("-Xmx" + Xmx + "M");
        command.add("-jar");
        command.add('"' + path + '"');
        if(flags != null){
            command.addAll(flags);
        }
        return command;
    }
}
