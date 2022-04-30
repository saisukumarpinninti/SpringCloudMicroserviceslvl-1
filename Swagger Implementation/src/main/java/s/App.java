package s;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api")
public class App{

	@GetMapping("/Q1")
	public String Q1(){return "Hello World";}

    @GetMapping("/Q2/validate/{username}/{password}")

    public String validateUser(@PathVariable String  username,@PathVariable String password)
	{
        if(username.equals("Sukumar") && password.equals("Q2"))return "Valid User";else return "Invalid User"; }

	@GetMapping("/Q3/{Zipcode}")
	public ZipCode Q3(@PathVariable Integer Zipcode)   {
		ZipCode z = new ZipCode(532292,"Manikyapuram","Andhra Pradesh","India");
		if(Zipcode==z.getId())return z ; else return null ; 
}

}