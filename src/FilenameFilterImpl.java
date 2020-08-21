import java.io.File;
import java.io.FilenameFilter;

public class FilenameFilterImpl implements FilenameFilter
{
    private final String e;


    /**
     * @param e : 파일 확장자명 ex) .txt
     */
    public FilenameFilterImpl( String e )
    {
        this.e = e;
    }


    @Override
    public boolean accept( File dir , String name )
    {
        return name.toLowerCase( ).endsWith( e );
    }
}
//[출처] java(자바) FilenameFilter를 이용한 특정 확장자 파일만 가져오기|작성자 세바니
