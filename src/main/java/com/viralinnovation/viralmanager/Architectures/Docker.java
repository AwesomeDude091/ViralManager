package com.viralinnovation.viralmanager.Architectures;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;

public final class Docker extends Architecture {

    private final DockerClient dockerClient;
    private String repository;
    private File imageFile;
    private final static Logger logger = LogManager.getLogger(Docker.class);
    private long imageID;

    public Docker(@NotNull File activeDirectory, DefaultDockerClientConfig config, String repository, File imageFile) {
        super(activeDirectory);
        dockerClient = DockerClientBuilder.getInstance(config).build();
        this.repository = repository;
        this.imageFile = imageFile;
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

        List<Image> images = dockerClient.listImagesCmd().exec();

        for(Image image: images){
            if(image.getId().equals(this.repository)) {
                return;
            }
        }

        try {
            dockerClient.createImageCmd(repository, new FileInputStream(imageFile));
        } catch (FileNotFoundException e) {
            logger.trace(e);
        }
    }

    @Override
    public void updateExecutable(File object, @Nullable String repository) {
        if(repository != null){
            if(this.repository.equals(repository)) {
                dockerClient.removeImageCmd(this.repository).exec();
                this.repository = repository;
                this.imageFile = object;
            }
        }

    }

    private long findImageID(){
        List<Image> images = dockerClient.listImagesCmd().exec();
        for(Image image: images){
        }
        return 0L;
    }
}
