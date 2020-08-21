import java.io.File;
import java.io.IOException;


public class FilenameFilterUtils {
    private FilenameFilterUtils() {
    }


    /**
     * 해당경로의 해당 확장자를 가지고 있는 파일 리스트를 리턴
     *
     * @param path : 경로
     * @param ex   : 확장자명 ex) .txt
     * @return
     * @throws IOException
     */
    public static File[] getfilenameFilterList(String path, String ex) throws IOException {
        File base = new File(path);
        if (base.exists() == false) {
            throw new IOException("Not exist or not a directory.");
        } else {
            if (isAvailable(base)) {
                FilenameFilterImpl filenameFilter = new FilenameFilterImpl(ex);
                File[] fileList = base.listFiles(filenameFilter);
                return fileList;
            }
            else{
                return null;
            }
        }
    }


    /**
     * Check available directory.
     *
     * @param directory
     * @return boolean result
     */
    private static boolean isAvailable(File directory) {
        boolean result = false;
        if (directory.exists() && directory.isDirectory()) {
            result = true;
        }
        return result;
    }
}
//[출처] java(자바) FilenameFilter를 이용한 특정 확장자 파일만 가져오기|작성자 세바니
