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
            String temp = Parser.splitByClass(mermaidCode);
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
    public static String splitByClass(String input) {
        List<String> classList = new ArrayList<>();
        String[] lines = input.split("\n");

    }
}