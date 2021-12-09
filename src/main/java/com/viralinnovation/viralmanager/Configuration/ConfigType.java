 /* Copyright © Viral Innovation - All Rights Reserved */

package com.viralinnovation.viralmanager.Configuration;

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public enum ConfigType implements ConfigFunctions {

    YAML("Yaml") {
        @Override
        public <T> T getObject(String key, File file, Class<T> returnDataType) {
            Yaml yaml = new Yaml();
            if(file.exists()){
                try {
                    Map<String, Object> dataMap = yaml.load(FileUtils.readFileToString(file, StandardCharsets.UTF_8));
                    return returnDataType.cast(dataMap.get(key));
                } catch (IOException e) {
                    logger.trace(e);
                }
            }
            return null;
        }

        @Override
        public void putObject(String key, Object value, File file) {
            Yaml yaml = new Yaml();
            Map<String, Object> dataMap = new HashMap<>();
            if(file.exists()){
                try {
                    dataMap = yaml.load(FileUtils.readFileToString(file, StandardCharsets.UTF_8));
                } catch (IOException e){
                    logger.trace(e);
                }
                if(dataMap.containsKey(key)) {
                    dataMap.replace(key, value);
                } else {
                    dataMap.put(key, value);
                }
            } else {
                dataMap.put(key, value);
                file.mkdirs();
            }

            PrintWriter writer = null;
            try {
                writer = new PrintWriter(file);
            } catch (FileNotFoundException e) {
                logger.trace(e);
            }
            yaml.dump(dataMap, writer);
        }
    },
    TOML("Toml") {
        @Override
        public <T> T getObject(String key, File file, Class<T> returnDataType) {
            Toml toml = new Toml().read(file);
            if(returnDataType == Double.class ){
                return returnDataType.cast(toml.getDouble(key));
            } else if (returnDataType == String.class) {
                return returnDataType.cast(toml.getString(key));
            } else if (returnDataType == Boolean.class) {
                return returnDataType.cast(toml.getBoolean(key));
            } else if (returnDataType == Date.class) {
                return returnDataType.cast(toml.getDate(key));
            } else if (returnDataType == List.class) {
                return returnDataType.cast(toml.getList(key));
            } else if (returnDataType == Long.class) {
                return returnDataType.cast(toml.getLong(key));
            } else if (returnDataType == Toml.class) {
                return returnDataType.cast(toml.getTable(key));
            }
            return null;
        }

        @Override
        public void putObject(String key, Object value, File file) {
            Toml toml;
            Map<String, Object> map;
            if(file.exists()){
                toml = new Toml().read(file);
                map = toml.toMap();
                if(map.containsKey(key)) {
                    map.replace(key, value);
                } else {
                    map.put(key, value);
                }
            } else {
                file.mkdirs();
                map = new HashMap<>();
                map.put(key, value);
            }
            TomlWriter tomlWriter = new TomlWriter();
            try {
                tomlWriter.write(map, file);
            } catch (IOException e) {
                logger.trace(e);
            }

        }
    },
    PROPERTIES("Properties") {
        @Override
        public <T> T getObject(String key, File file, Class<T> returnDataType) {
            Properties prop = new Properties();
            if(file.exists()) {
                try {
                    prop.load(new FileInputStream(file));
                    return returnDataType.cast(prop.getProperty(key));
                } catch (IOException e) {
                    logger.trace(e);
                }
            }
            return null;
        }

        @Override
        public void putObject(String key, Object value, File file) {
            Properties prop = new Properties();
            if(file.exists()) {
                try {
                    prop.load(new FileInputStream(file));
                    if(prop.getProperty(key) != null){
                        prop.replace(key, value);
                    } else {
                        prop.put(key, value);
                    }
                } catch (IOException e) {
                    logger.trace(e);
                }
            } else {
                file.mkdirs();
                prop.put(key, value);
            }
            PrintWriter writer = null;
            try {
                writer = new PrintWriter(file);
            } catch (FileNotFoundException e) {
                logger.trace(e);
            }
            try {
                prop.store(writer, "");
            } catch (IOException e) {
                logger.trace(e);
            }
        }
    };

    private final String name;
    private final static Logger logger = LogManager.getLogger(ConfigType.class);

    ConfigType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
