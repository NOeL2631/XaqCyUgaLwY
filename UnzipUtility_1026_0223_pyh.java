// 代码生成时间: 2025-10-26 02:23:45
import java.io.File;
import java.io.FileInputStream;
# 扩展功能模块
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
# FIXME: 处理边界情况

/**
 * Utility class to handle file uncompression.
# 优化算法效率
 * This class provides a method to unzip a file to a specified directory.
 */
public class UnzipUtility {
# NOTE: 重要实现细节
    
    /**
     * Unzips a zip file to the specified directory.
     * 
# 优化算法效率
     * @param zipFilePath Path to the zip file to be unzipped.
# 扩展功能模块
     * @param destDirectory Path to the directory where the files will be unzipped.
     * @throws IOException If an I/O error occurs.
     */
# 改进用户体验
    public void unzip(String zipFilePath, String destDirectory) throws IOException {
        File destDir = new File(destDirectory);
        destDir.mkdirs(); // Ensure directory is created.
        
        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
        ZipEntry entry = zipIn.getNextEntry();
        // Loop through the entry in the zip file
        while (entry != null) {
            String filePath = destDirectory + File.separator + entry.getName();
            if (!entry.isDirectory()) {
                // If the entry is a file, extract it
                extractFile(zipIn, filePath);
            } else {
                // If the entry is a directory, make the directory.
                File dir = new File(filePath);
                dir.mkdir();
            }
            zipIn.closeEntry();
            entry = zipIn.getNextEntry();
        }
        zipIn.close();
# TODO: 优化性能
    }
    
    /**
     * Extracts a file from the zip input stream to the specified path.
     * 
     * @param zipIn The zip input stream.
     * @param filePath The path to extract the file to.
# 增强安全性
     * @throws IOException If an I/O error occurs.
# NOTE: 重要实现细节
     */
    private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
# 增强安全性
        bufferedCopy(zipIn, new FileOutputStream(filePath));
        
        // Close the file input stream
        zipIn.closeEntry();
    }
    
    /**
     * Buffers the input and output streams for copying.
     * 
     * @param in The input stream.
# NOTE: 重要实现细节
     * @param out The output stream.
     * @throws IOException If an I/O error occurs.     */
    private void bufferedCopy(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
    }
}
