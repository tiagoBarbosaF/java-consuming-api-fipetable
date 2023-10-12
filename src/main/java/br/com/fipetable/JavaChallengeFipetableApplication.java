package br.com.fipetable;

import br.com.fipetable.main.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaChallengeFipetableApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(JavaChallengeFipetableApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main();
		main.showMenu();
	}
}
