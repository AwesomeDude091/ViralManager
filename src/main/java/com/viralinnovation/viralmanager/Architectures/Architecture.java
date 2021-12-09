/* Copyright © Viral Innovation - All Rights Reserved */

/* Copyright © Viral Innovation - All Rights Reserved */

package com.viralinnovation.viralmanager.Architectures;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.PrintStream;

public abstract class Architecture {

    protected final File activeDirectory;
    protected StreamRedirector streamRedirector;

    public Architecture(@NotNull File activeDirectory){
        if(!activeDirectory.exists()){
            activeDirectory.mkdirs();
        }
        this.activeDirectory = activeDirectory;
    }

    public abstract void init(PrintStream streamToStreamTo);
    public abstract void kill();
    public abstract void runCommand(String command);
    public abstract void setExecutable();
    public abstract void updateExecutable(Object object);

}
