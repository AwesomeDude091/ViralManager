 /* Copyright © Viral Innovation - All Rights Reserved */

package com.viralinnovation.viralmanager.Configuration;

import org.jetbrains.annotations.Nullable;

import java.io.File;

public interface ConfigFunctions {
    @Nullable <T> T getObject(String key, File file, Class<T> returnDataType);
    void putObject(String key, Object value, File file);
}
