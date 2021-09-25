package com.dusza;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Path inputPath = FileSystems.getDefault().getPath("input.md");
        Path outputPath = FileSystems.getDefault().getPath("output.html");

        IOHandler IOHandler = new IOHandler(inputPath, outputPath);
        List<Paragraph> paragraphList = IOHandler.readMarkdown();

        IOHandler.writeHTML(paragraphList);
    }
}
