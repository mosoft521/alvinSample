package com.gmail.mosoft521.runtime;

import java.io.IOException;

public class RuntimeTest {
    public static void main(String[] args) throws IOException {
//        Runtime.getRuntime().exec("cmd /c start d:/test.bat");
        Runtime.getRuntime().exec("java -version > java-version.txt");
    }
}
