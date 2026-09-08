package Property.Property.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Property.Property.entity.Property;
import Property.Property.service.PropertyService;
import Property.Property.service.UserService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PropertyService serviceIMPL;

    @GetMapping("/user/home")
    public String getAllProperties(Model model) {
        // Fetch all properties from the database
    	List<Property> properties = serviceIMPL.getAllProperties();
        System.out.println("Fetched properties: " + properties);
        model.addAttribute("propertyList", serviceIMPL.getAllProperties());
        return "user/index"; 
    }

    @GetMapping("/user/search")
    public String searchProperties(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("propertyList", userService.searchProperties(keyword));
        model.addAttribute("keyword", keyword);
        return "user/index";
    }

    @GetMapping("/user/filter")
    public String filterProperties(@RequestParam("type") String type, Model model) {
        if ("all".equalsIgnoreCase(type)) {
            model.addAttribute("propertyList", serviceIMPL.getAllProperties()); 
        } else {
            model.addAttribute("propertyList", userService.filterPropertiesByType(type));
        }
    	
        return "user/index";
    }
    
    
    @GetMapping("/user/home/about")
    public String about() {
        return "user/aboutpage"; 
    }
    
    @GetMapping("/user/home/contact")
    public String showContactPage() {
        return "user/contact";  // This should match your `contact.html` file name
    }
}
