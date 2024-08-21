package EFile.Essential;

import ColorBack.error;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class DelAllFile {
    public static void clearDirectory(String directoryPath) {
        Path directory = Paths.get(directoryPath);

        try (Stream<Path> files = Files.walk(directory)) {
            files
                    .filter(path -> !path.equals(directory)) // 确保不删除目录本身
                    .forEach(path -> {
                        try {
                            Files.delete(path);
                        } catch (IOException e) {
                            error.sys("<DelAllFile>删除文件时发生错误: " , e.getMessage());
                        }
                    });
        } catch (IOException e) {
            error.sys("<DelAllFile>遍历目录发送意外时: " , e.getMessage());
        }
    }
}
