package com.example.reddit.controller;

import com.example.reddit.domain.Comment;
import com.example.reddit.domain.Link;
import com.example.reddit.repository.CommentRepository;
import com.example.reddit.repository.LinkRepository;
import com.example.reddit.service.CommentService;
import com.example.reddit.service.LinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller

public class LinkController {



    private static final Logger logger = LoggerFactory.getLogger(LinkController.class);


    private LinkService linkService;
    private CommentService commentService ;


    public LinkController(LinkService linkService, CommentService commentService) {
        this.linkService = linkService;
        this.commentService = commentService;
    }

    @GetMapping("/")
    public String list(Model model){
        model.addAttribute("links",linkService.findAll());
        return"link/list";

   }
    @GetMapping("/link/{id}")
    public String read(@PathVariable Long id,Model model){
          Optional<Link> link = linkService.findById(id);
          if(link.isPresent()){
              Link currentLink = link.get();
              Comment comment = new Comment();
              comment.setLink(currentLink);
              model.addAttribute("comment",comment);
              model.addAttribute("link",link.get());
              model.addAttribute("success",model.containsAttribute("success"));
              return"link/view";
          }else{
              return "redirect:/";
          }


    }
    @GetMapping("/link/submit")
    public String newLinkForm(Model model) {
        model.addAttribute("link",new Link());
        return "link/submit";
    }

    @PostMapping("/link/submit")
    public String createLink(@Valid Link link, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()){
            logger.info("Validation errors were found while submitting a new link");
                    model.addAttribute("link",link);
                    return "link/submit";
        }else{
            linkService.save(link);
            logger.info(" New link was saved succesfully");
            redirectAttributes.addAttribute("id", link.getId()).addFlashAttribute("success",true);
            return "redirect:/link/{id}";

        }

    }
    @Secured({"ROLE_USER"})
    @PostMapping("link/comments")
    public String addComment(@Valid Comment comment , BindingResult bindingResult ){
        if(bindingResult.hasErrors()){
            logger.info("there was a problem adding a new comment");
        }
else{
            commentService.save(comment);
            logger.info(" new comment was saved successfully");
        }
        return "redirect:/link/"+comment.getLink().getId();
    }
}
