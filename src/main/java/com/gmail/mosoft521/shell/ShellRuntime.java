package com.gmail.mosoft521.shell;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class ShellRuntime {
    private static class StreamGobbler implements Runnable {
        private InputStream inputStream;
        private Consumer<String> consumer;

        public StreamGobbler(InputStream inputStream, Consumer<String> consumer) {
            this.inputStream = inputStream;
            this.consumer = consumer;
        }

        @Override
        public void run() {
            new BufferedReader(new InputStreamReader(inputStream)).lines().forEach(consumer);
        }
    }

    public static void main(String[] args) throws Exception {
        boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
        System.out.println("===ShellRuntime===");

        String homeDirectory = System.getProperty("user.home");
        Process process;
        if (isWindows) {
            process = Runtime.getRuntime().exec(String.format("cmd.exe /c dir %s", homeDirectory));
        } else {
            process = Runtime.getRuntime().exec(String.format("sh -c ls %s", homeDirectory));
        }
        StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), System.out::println);
        Executors.newSingleThreadExecutor().submit(streamGobbler);
        int exitCode = process.waitFor();
        assert exitCode == 0;
    }
}
