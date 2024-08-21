package Values;

import ColorBack.error;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class WriteValue {

    //写下新变量
    public static void write(String content, String value) {
        boolean isHaveValue;
        isHaveValue = check(content, value);
        if(!isHaveValue) {
            String wrs = content + "(" + value + ")";
            BufferedWriter out = null;
            try {
                out = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream("src/Value/value.txt", true)));
                out.write(wrs + "\r\n");
            } catch (Exception e) {
                e.fillInStackTrace();
                error.sys("Filer.WriteValue.write<close.BufferedWriter>","There's sth wrong with write file",e);
            } finally {
                try {
                    if (out != null) {
                        out.close();
                    }else{
                        error.sys("Filer.WriteValue.write<close.BufferedWriter.EMPTY>","There's nothing with the Reader");
                    }
                } catch (IOException e) {
                    error.sys("Filer.WriteValue.write<close.BufferedWriter>","There's sth wrong with close Reader",e);
                    e.fillInStackTrace();
                }
            }

        }
    }

    //检查是否存在变量
    public static boolean check(String content, String value) {
        try (Scanner sc = new Scanner(new FileReader("src/Value/value.txt"))) {
            int reading = 0;
            while (sc.hasNextLine()) {
                reading++;
                String line = sc.nextLine();
                if (line.startsWith(content)) {
                modifyLine(reading, content + "(" + value + ")");
                return true;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    //更改某一行
    public static void modifyLine(int lineNumber, String newText) throws IOException {
        String filePath = "src/Value/value.txt";
        List<String> lines = readFileIntoList(filePath);

        if (lines.size() < lineNumber) {
            throw new IndexOutOfBoundsException("Line number " + lineNumber + " is out of bounds for file " + filePath);
        }

        // 修改指定行的内容
        lines.set(lineNumber - 1, newText);

        // 写回文件
        writeListToFile(lines, filePath);
    }

    private static List<String> readFileIntoList(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    private static void writeListToFile(List<String> lines, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    //获取指定变量位置
    public static Optional<String> readValue(String content) {
        String filePath = "src/Value/value.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(content)) {
                    String value = line.substring(line.indexOf("(") + 1, line.indexOf(")"));
                    return Optional.of(value);
                }
            }
        } catch (IOException e) {
            // 报错
            System.err.println("PackageError " + e.getMessage());
        }
        return Optional.empty(); //返回空

}
    //清空
    public static void voidFile() throws Exception{
        File f = new File ("src/Value/value.txt");
        FileWriter fw = new FileWriter (f);
        fw.write("");
        fw.flush();
        fw.close();
    }
}

