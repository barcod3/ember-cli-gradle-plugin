package com.kiefer.gradle;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Program {
    public static boolean onPath(String program) {

        String[] paths = System.getenv("PATH").split(Pattern.quote(File.pathSeparator));
        String pathExtEnv = System.getenv("PATHEXT");
        String[] extensions = pathExtEnv != null ? pathExtEnv.split(Pattern.quote(File.pathSeparator)) : new String[0];

        for (String path : paths) {
            if (new File(path, program).exists()) {
                return true;
            }

            for (String extension : extensions) {
                File file = new File(path, program + extension);
                if (file.exists()) {
                    return true;
                }
            }

        }
        return false;
    }

    private Program () {}
}
