package EFile.Essential;

import ColorBack.error;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DeleteFile {
    /**
     * 删除指定路径下的文件。
     *
     * @param filePath 文件的完整路径
     */
    public static void deleteFile(String filePath) {
        // 创建 Path 对象
        Path path = Paths.get(filePath);

        // 尝试删除文件
        try {
            if (Files.exists(path) && Files.isRegularFile(path)) {
                Files.delete(path);
            } else {
                error.sys("DeleteFile<Essential>","Can't find this file:"+filePath);
            }
        } catch (IOException e) {
            error.sys("DeleteFile<Essential>","Unknown error<Maybe you should read the grammar of this>",e);
        }
    }
}
