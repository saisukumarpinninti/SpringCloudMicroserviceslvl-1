package sukumar.movieinfoservice.Resources;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import sukumar.movieinfoservice.Movie;

@RestController
@RequestMapping("/movie")
public class MovieResource {

    @RequestMapping("/{movieid}")
    public Movie getMovieInfo(@PathVariable("movieid") String movieid){
        return new Movie(movieid,movieid);
    }
}
