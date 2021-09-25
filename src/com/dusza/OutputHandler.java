package com.dusza;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OutputHandler {
    private final Path inputPath;
    private final Path outputPath;

    public OutputHandler(Path inputPath, Path outputPath) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }

    public void writeHTML(List<Paragraph> paragraphList) {
        try(BufferedWriter writer = Files.newBufferedWriter(outputPath)) {
            writer.write("<html>\r\n");
            writer.write("\t<body>\r\n");
            for(Paragraph p : paragraphList) {
                String outputString = p.getHTML();
                writer.write("\t\t" + outputString + "\r\n");
            }
            writer.write("\t</body>\r\n");
            writer.write("</html>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Paragraph> readMarkdown() {
        List<Paragraph> result = new ArrayList<>();

        try(Scanner input = new Scanner(Files.newBufferedReader(inputPath))) {
            List<String> inputLines = new ArrayList<>();
            String line;
            while(input.hasNextLine()) {
                line = input.nextLine();
                if(line.trim().equals("")) {
                    Paragraph paragraph = new Paragraph();
                    paragraph.setFullText(String.join(" ", inputLines));
                    inputLines = new ArrayList<>();
                    result.add(paragraph);
                } else {
                    inputLines.add(line);
                }
            }

            Paragraph paragraph = new Paragraph();
            paragraph.setFullText(String.join("\n", inputLines));
            result.add(paragraph);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

}
