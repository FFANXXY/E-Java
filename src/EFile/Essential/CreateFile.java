package EFile.Essential;

import ColorBack.error;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateFile {


    private static Path fullPath;
    /**
     * 创建指定路径下的文件并写入内容。
     * 如果文件所在的目录不存在，则会自动创建。
     *
     * @param fileName  文件名
     * @param filePath  文件所在路径
     * @param fileContent 文件内容
     *
     */
    public static void createFileAndWrite(String fileName, String filePath, String fileContent) {
        // 拼接完整的文件路径
        try {fullPath = Paths.get(filePath, fileName);}
        catch (Exception e) {
            error.sys("FileWrite<Essential>","Unknown error<Or check your codes>",e);
            error.sys("CreateFile","",e);
        }

        // 创建父目录（如果不存在）
        try {
            Files.createDirectories(fullPath.getParent());

            // 写入文件内容
            Files.write(fullPath, fileContent.getBytes());
        } catch (IOException e) {
            error.sys("FileWrite<Essential>","Unknown error<Or check your codes>",e);
        }
    }

}
