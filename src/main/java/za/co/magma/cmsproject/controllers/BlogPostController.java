package za.co.magma.cmsproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import za.co.magma.cmsproject.domain.BlogPost;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class BlogPostController {
		
	@GetMapping("/addEditPost")
	public String addEditPost(Model model, @RequestParam("blogPostId") Optional<String> blogPostId) {
		setDefaultBlogPost(model);					
		return "admin/addEditPost";
	}
	

	@PostMapping("/addEditPost")
	public String addEditPostSubmit(Model model, BlogPost blogPost) {
	    System.out.println("Title is " + blogPost.getTitle());
	    System.out.println("Body is " + blogPost.getBody());
	    
		return "admin/addEditPost";
	}

	
	private void setDefaultBlogPost(Model model) {
		BlogPost blogPost = new BlogPost();		
		model.addAttribute("blogPost", blogPost);
	}

	@GetMapping("/blogposts")
	public String addBlogPost(Model model) {

		setDefaultBlogPost(model);
		return "admin/add_post";
	}

	@PostMapping("/blogposts")
	public String addPostBlogPost(Model model, BlogPost blogPost) {

		System.out.println(blogPost + " to be added...");
		return "admin/add_post";
	}
}