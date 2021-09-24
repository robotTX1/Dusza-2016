package com.dusza;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Path inputPath = FileSystems.getDefault().getPath("input.md");
        Path outputPath = FileSystems.getDefault().getPath("output.html");

        OutputHandler outputHandler = new OutputHandler(inputPath, outputPath);
        List<Paragraph> paragraphList = outputHandler.readMarkdown();

        HTML html = new HTML(paragraphList);

        for(Paragraph p : paragraphList) {
//            System.out.println(p.getFullText());
        }

        System.out.println(CharacterChain.getCharacters("\\*Hello\\*"));

    }
}
