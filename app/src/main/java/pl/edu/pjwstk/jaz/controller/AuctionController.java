package pl.edu.pjwstk.jaz.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.jaz.entity.AuctionService;
import pl.edu.pjwstk.jaz.request.AuctionRequest;

import java.util.List;

@RestController
public class AuctionController {

    private final AuctionService auctionService;

    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @PreAuthorize("hasAnyAuthority('Admin,User,Default')")
    @PostMapping("/addAuction")
    public void addAuction(@RequestBody AuctionRequest auctionRequest) {

        auctionService.addAuction(auctionRequest);
    }

    @PreAuthorize("hasAnyAuthority('Admin,User')")
    @PostMapping("/updateAuction/{id}")
    public void updateAuction(@RequestBody AuctionRequest auctionRequest, @PathVariable(name = "id") Long id) throws Exception {
        auctionService.updateAuction(auctionRequest,id);
    }

    @PreAuthorize("hasAnyAuthority('Admin,User')")
    @GetMapping("/viewAuction/{id}")
    public AuctionRequest viewAuction(@PathVariable(name = "id") Long id) {

       return auctionService.viewAuction(id);
    }

    @PreAuthorize("hasAnyAuthority('Admin,User')")
    @GetMapping("/viewAllAuction")
    public List<AuctionRequest> viewAllAuction() {

        return auctionService.viewAllAuctions();
    }


}
