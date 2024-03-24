import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;;

public class CodeGenerator {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("請輸入mermaid檔案名稱");
        } else {
            // get input
            String fileName = args[0];
            String mermaidCode = "";

            FileReader mermaidCodeReader = new FileReader();
            mermaidCode = mermaidCodeReader.read(fileName);
            List<String> classNameList = Parser.splitByClass(mermaidCode);
            for (String className : classNameList) {
                List<String> classContentList = Parser.getClassContent(mermaidCode, className);
                String writeInContent = "public class " + className + " {\n";
                for (String classContent : classContentList) {
                    if (classContent.contains("()")) {
                        if (classContent.startsWith("+")) {
                            classContent = classContent.substring(1);

                        } else if (classContent.startsWith("-")) {
                        }
                    }
                }
            }
        }
    }
}

class FileReader {
    public String read(String fileName) {
        try {
            return Files.readString(Paths.get(fileName));
        } catch (IOException e) {
            System.err.println("讀取檔案時發生錯誤：" + e.getMessage());
            return "";
        }
    }
}

class Parser {
    public static List<String> splitByClass(String mermaidText) {
        List<String> classList = new ArrayList<>();
        String[] lines = mermaidText.split("\n");
        for (String line : lines) {
            if (line.startsWith("    class")) {
                String className = extractClassName(line);
                if (!classList.contains(className)) {
                    classList.add(className);
                }
            }
        }
        return classList;
    }

    public static String extractClassName(String classLine) {
        Pattern pattern = Pattern.compile("\\bclass\\s+(\\w+)\\b");
        Matcher matcher = pattern.matcher(classLine);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }

    public static List<String> getClassContent(String mermaidText, String className) {
        String[] lines = mermaidText.split("\n");
        List<String> classContent = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {
            String classNameWithColon = String.format(" %s ", className);
            String classNameWithBraces = String.format("%s {", className);
            if (lines[i].contains(classNameWithColon)) {
                Pattern pattern = Pattern.compile("\\s*(\\+|\\-).*");
                Matcher matcher = pattern.matcher(lines[i]);
                if (matcher.find()) {
                    classContent.add(matcher.group(0).trim());
                }
            }
            if (lines[i].contains(classNameWithBraces)) {
                // System.out.println(line);
                // System.out.println(findIndex(lines, line));
                // int currentIndex = findIndex(lines, lines[i]);
                Boolean foundClosingBraces = false;
                while (!foundClosingBraces) {
                    String nextLine = lines[i + 1].trim();
                    if (nextLine.contains("}")) {
                        foundClosingBraces = true;
                    } else {
                        if (!classContent.contains(nextLine)) {
                            classContent.add(nextLine);
                            i++;
                        } else {
                            i++;
                        }

                    }
                }
            }

        }
        return classContent;
    }

    public static int findIndex(String arr[], String target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }
}