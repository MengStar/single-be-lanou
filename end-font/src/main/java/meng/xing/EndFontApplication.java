package meng.xing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
//暂时关闭缓存
//@EnableCaching
public class EndFontApplication {

	public static void main(String[] args) {
        SpringApplication.run(EndFontApplication.class, args);
	}
}
