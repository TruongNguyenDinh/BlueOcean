/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package BlueOceanScene.Utils;

import MainForm.Models.User;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

/**
 *
 * @author truon
 */
public class WriterQuickLogin  {
    public static boolean writer(String ql) {
        try {
            String[] parts = ql.split("\\.");
            if (parts.length < 2) return false;
            try{
                String result = parts[1].trim();
                String fileName = User.getId() + "." + result + ".quicklogin";
                Path dirPath = Paths.get("src/Local");
                if (!Files.exists(dirPath)) {
                    Files.createDirectories(dirPath);  // Tạo thư mục nếu chưa có
                }

                // Duyệt và xóa file cũ có user_id trùng
                try (DirectoryStream<Path> stream = Files.newDirectoryStream(dirPath, User.getId() + ".*.quicklogin")) {
                    for (Path entry : stream) {
                        Files.delete(entry);  // Xóa file trùng user_id
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Tạo file mới
                Path path = dirPath.resolve(fileName);
                String content = User.getUsername() + "," + User.getPassword();
                try {
                    Files.write(path, content.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
            catch(IOException e){
                
            }
        }
        catch(ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
