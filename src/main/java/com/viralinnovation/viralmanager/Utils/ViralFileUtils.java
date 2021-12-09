 /* Copyright © Viral Innovation - All Rights Reserved */

package com.viralinnovation.viralmanager.Utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;

public final class ViralFileUtils {

    public static boolean isFilePresentEqualName(String name, @NotNull File directory){
        File[] currentFiles = directory.listFiles();
        assert currentFiles != null;
        for (File file: currentFiles){
            if(file.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public static boolean isFilePresentContainName(String name, @NotNull File directory){
        File[] currentFiles = directory.listFiles();
        assert currentFiles != null;
        for (File file: currentFiles){
            if(file.getName().contains(name)){
                return true;
            }
        }
        return false;
    }

    @Contract(pure = true)
    public static @NotNull String generateOSBasedClassPath(@NotNull String currentPath, String toBeAdded){
        if(currentPath.contains("\\")) {
            return currentPath + "\\" + toBeAdded;
        } else {
            return currentPath + "/" + toBeAdded;
        }
    }
    
    public static @NotNull String generateOSBasedClassPath(@NotNull String currentPath, List<String> toBeAdded){
        // Adds OS Based divider into Path, iterating through List
        String returnString = currentPath;
        if(currentPath.contains("\\")) {
            for(String string: toBeAdded) {
                returnString = retrunString + "\\" + string;
            }
            return returnString;
        } else {
            for(String string: toBeAdded) {
                returnString = retrunString + "/" + string;
            }
            return returnString;
        }
    }

    public static @Nullable File findJavaDirectory(int javaVersion){
        File javaDirectory;
        String separator;
        if(System.getProperty("user.dir").contains("\\")){
            javaDirectory = new File("C:\\Program Files\\Java");
            separator = "_";
        } else {
            javaDirectory = new File("/usr/lib/jvm");
            separator = "-";
        }
        File[] javaFiles = javaDirectory.listFiles();
        assert javaFiles != null;
        for(File javaFolder: javaFiles){
            String folderName = javaFolder.getName();
            if(separator.equals("_")){
                folderName = folderName.split(separator)[0];
            } else {
                folderName = folderName.split(separator)[1];
            }
            if(folderName.contains(String.valueOf(javaVersion))) {
                File[] allJavaV = javaFolder.listFiles();
                assert allJavaV != null;
                for (File folders: allJavaV){
                    if(folders.getName().equals("bin") && folders.isDirectory()){
                        File[] folderContents = folders.listFiles();
                        assert folderContents != null;
                        for(File javaEXE: folderContents){
                            System.out.println(javaEXE.getName());
                            if(javaEXE.getName().equals("java.exe") || javaEXE.getName().equals("java")){
                                return javaEXE;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}
