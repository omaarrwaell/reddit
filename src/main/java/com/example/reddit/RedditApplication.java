package com.example.reddit;

import com.example.reddit.domain.Comment;
import com.example.reddit.domain.Link;
import com.example.reddit.repository.CommentRepository;
import com.example.reddit.repository.LinkRepository;
import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RedditApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedditApplication.class, args);
	}

    @Bean
	PrettyTime prettyTime(){
		return new PrettyTime();
}


	}


