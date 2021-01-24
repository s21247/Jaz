package pl.edu.pjwstk.jaz.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.entity.AuctionEntity;
import pl.edu.pjwstk.jaz.entity.AuctionService;
import pl.edu.pjwstk.jaz.entity.SectionService;
import pl.edu.pjwstk.jaz.request.CategoryRequest;
import pl.edu.pjwstk.jaz.request.SectionRequest;


@RestController
public class CategoryController {

    private final SectionService sectionService;

    public CategoryController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @PreAuthorize("hasAnyAuthority('Admin')")
    @PostMapping("/addCategory")
    public void addCategory(@RequestBody CategoryRequest categoryRequest) {
        sectionService.saveCategory(categoryRequest);
    }
}
