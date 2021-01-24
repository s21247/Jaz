package pl.edu.pjwstk.jaz.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jaz.entity.SectionService;
import pl.edu.pjwstk.jaz.request.SectionRequest;

import javax.transaction.Transactional;

@RestController
public class SectionController {

    private final SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @PreAuthorize("hasAnyAuthority('Admin')")
    @PostMapping("/addSection")
    public void addSection(@RequestBody SectionRequest sectionRequest) {

        sectionService.saveSection(sectionRequest.getTitle());
    }

    @PreAuthorize("hasAnyAuthority('Admin')")
    @PostMapping("updateSection/{title}")
    public void updateSection(@RequestBody SectionRequest sectionRequest, @PathVariable(name = "title") String title) {

        sectionService.updateSection(title, sectionRequest.getTitle());
    }



}
