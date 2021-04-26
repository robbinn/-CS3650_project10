package com.siwen;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        File filename = new File("Main.jack");
        Analyzer.analyze(filename);
    }
}
