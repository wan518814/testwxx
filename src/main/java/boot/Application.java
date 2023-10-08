package boot;


//import org.bouncycastle.math.ec.custom.sec.SecP128R1Point;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@ServletComponentScan
public class Application{
   public  static  void main(String[] args){
       SpringApplication.run(Application.class,args);
   }

   @Bean
    public Redisson redisson(){
       Config config = new Config();
       config.useSingleServer().setAddress("redis://127.0.0.1:6379").setDatabase(0);
       return (Redisson) Redisson.create(config);
   }
}
