package meng.xing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableCaching //开启缓存
public class EndFontApplication {
	public static void main(String[] args) {
        SpringApplication.run(EndFontApplication.class, args);
	}
}
