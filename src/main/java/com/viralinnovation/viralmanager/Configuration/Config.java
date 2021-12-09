 /* Copyright © Viral Innovation - All Rights Reserved */

package com.viralinnovation.viralmanager.Configuration;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.*;

public final class Config {

    private final Map<File, ConfigType> fileConfigTypeMap;
    private final Set<File> fileList;
    private final String name;
    private String version;
    private final UUID uuid;

    public Config(@NotNull Map<File, ConfigType> fileConfigTypeMap, String name, String version, UUID uuid) {
        this.fileConfigTypeMap = fileConfigTypeMap;
        this.fileList = fileConfigTypeMap.keySet();
        this.name = name;
        this.version = version;
        this.uuid = uuid;
    }

    public Config(@NotNull Map<File, ConfigType> fileConfigTypeMap, String name, String version) {
        this.fileConfigTypeMap = fileConfigTypeMap;
        this.fileList = fileConfigTypeMap.keySet();
        this.name = name;
        this.version = version;
        this.uuid = UUID.randomUUID();
    }

    public <T> @Nullable T getObject(String key, File file, Class<T> returnDataType) {
        if(fileList.contains(file)){
            return fileConfigTypeMap.get(file).getObject(key, file, returnDataType);
        }
        return null;
    }

    public void putObject(String key, Object value, File file) {
        if(fileList.contains(file)){
            fileConfigTypeMap.get(file).putObject(key, value, file);
        }
    }

    public String getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setVersion(String value){
        this.version = value;
    }

    public void addFile(File file, ConfigType configType){
        if(!fileList.contains(file)){
            fileList.add(file);
            fileConfigTypeMap.put(file, configType);
        }
    }

    public void removeFile(File file, boolean clean){
        if(fileList.contains(file)){
            fileList.remove(file);
            fileConfigTypeMap.remove(file);
            if(clean) file.delete();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Config config = (Config) o;
        return Objects.equals(fileConfigTypeMap, config.fileConfigTypeMap) && Objects.equals(fileList, config.fileList) && Objects.equals(name, config.name) && Objects.equals(version, config.version) && Objects.equals(uuid, config.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileConfigTypeMap, fileList, name, version, uuid);
    }

    @Contract(pure = true)
    @Override
    public @NotNull String toString() {
        return "Config{" +
                "fileConfigTypeMap=" + fileConfigTypeMap +
                ", fileList=" + fileList +
                ", name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", uuid=" + uuid +
                '}';
    }
}