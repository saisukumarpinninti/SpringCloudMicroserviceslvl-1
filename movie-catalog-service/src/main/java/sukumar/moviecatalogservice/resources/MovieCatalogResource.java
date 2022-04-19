package sukumar.moviecatalogservice.resources;


import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import sukumar.moviecatalogservice.CatalogItem;
import sukumar.moviecatalogservice.Movie;
import sukumar.moviecatalogservice.Rating;
import sukumar.moviecatalogservice.UserRating;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping("/{userid}")
    public List<CatalogItem> getCatalog(@PathVariable("userid") String userId) {
        UserRating ratings = restTemplate.getForObject("http://RATING-DATA-SERVICE/ratingsdata/users/" + userId, UserRating.class);

        return ratings.getUserRating().stream().map(rating -> {
            Movie m = restTemplate.getForObject("http://MOVIE-INFO-SERVICE/movie/" + rating.getMovieid(), Movie.class);

            return new CatalogItem(m.getName(), "hello", rating.getRating());
        }).collect(Collectors.toList());

    }

}
/*
    Movie m =  webClientBuilder.build()
            .get()
            .uri("http://localhost:8082/movie/"+rating.getMovieid())
            .retrieve()
            .bodyToMono(Movie.class)
            .block();
*/