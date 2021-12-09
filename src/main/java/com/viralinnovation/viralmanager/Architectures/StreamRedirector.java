 /* Copyright © Viral Innovation - All Rights Reserved */

package com.viralinnovation.viralmanager.Architectures;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

public final class StreamRedirector implements Runnable {

    private final InputStream in;
    private final PrintStream redirectTarget;
    private final OutputStream out;
    private boolean runNow = true;
    private static final Logger logger = LogManager.getLogger(StreamRedirector.class);

    public StreamRedirector(InputStream in, OutputStream out, PrintStream redirectTarget) {
        this.in = in;
        this.redirectTarget = redirectTarget;
        this.out = out;
    }

    @Override
    public void run() {
        if(!runNow){
            return;
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        try {
            String line;
            while ((line = br.readLine()) != null) {
                // don't spam the console with useless stuff
                if (line.equals("") || line.equals(">")) continue;
                redirectTarget.println(line);
            }
        } catch (IOException ex) {
            logger.trace(ex);
        }
    }

    public void insertConmmand(String command) {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(out));
        try {
            bufferedWriter.write(command);
            bufferedWriter.write("\n");
            bufferedWriter.flush();
        } catch (IOException e) {
            logger.trace(e);
        }
    }

    public void killThread() {
        runNow = false;
    }
}
