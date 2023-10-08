package boot.Text;

import org.springframework.beans.factory.annotation.Autowired;
import javax.sql.DataSource;
public class TestRun {

    @Autowired
    static DataSource d;

    public static void main(String[] args){
        System.out.println(d.getClass());
    }


}
