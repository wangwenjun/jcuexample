import java.io.IOException;
import java.net.URL;
import java.security.CodeSource;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/***************************************
 * @author:Alex Wang
 * @Date:2017/9/16
 * QQ交流群:601980517，463962286
 ***************************************/
public class Test {
    public static void main(String[] args) throws IOException {
        CodeSource codeSource = Test.class.getProtectionDomain().getCodeSource();
        if (codeSource != null) {
            URL jar = codeSource.getLocation();
            ZipInputStream zip = new ZipInputStream(jar.openStream());
            while (true) {
                ZipEntry entry = zip.getNextEntry();
                if (entry == null) {
                    break;
                }
                System.out.println(entry.getName());
            }
        }
        else{
            System.out.println("no data.");
        }
    }
}
