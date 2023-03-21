package com.awards.films;

import com.awards.films.domain.Awards;
import com.awards.films.repository.AwardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@SpringBootApplication
public class GoldenRaspberryAwardsApplication {

	@Autowired
	private ResourceLoader resourceLoader;

	public static void main(String[] args) {
		SpringApplication.run(GoldenRaspberryAwardsApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(AwardsRepository repository) {
		return args -> {

			Resource resource = resourceLoader.getResource("classpath:/static/movielist.csv");

			try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
				String line;
				while ((line = br.readLine()) != null) {
					String[] attr = line.split(";");
					if (!attr[0].equals("year")) {
						int year = Integer.parseInt(attr[0]);
						String title = attr[1];
						String studios = attr[2];
						String producers = attr[3];
						boolean winner = attr.length == 5;

						repository.save(new Awards(null, year, title, studios, producers, winner));
					}
				}
			}

		};
	}

}
