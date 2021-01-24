package pl.edu.pjwstk.jaz.entity;

import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.jaz.controller.LoginController;
import pl.edu.pjwstk.jaz.request.AuctionRequest;
import pl.edu.pjwstk.jaz.request.ParameterRequest;
import pl.edu.pjwstk.jaz.request.PhotoRequest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@Repository
public class AuctionService {
    private final EntityManager entityManager;
    private final SectionService sectionService;
    private final LoginController loginController;
    private final UserService userService;

    public AuctionService(EntityManager entityManager, SectionService sectionService, LoginController loginController, UserService userService) {
        this.entityManager = entityManager;
        this.sectionService = sectionService;
        this.loginController = loginController;
        this.userService = userService;
    }

    public void addAuction(AuctionRequest auctionRequest) {
        var auctionEntity = new AuctionEntity();
        auctionEntity.setDescription(auctionRequest.getDescription());
        auctionEntity.setPrice(auctionRequest.getPrice());
        auctionEntity.setTitle(auctionRequest.getTitle());
        auctionEntity.setVersion(1L);
        auctionEntity.setPhotoEntityList(addPhotoToAuction(auctionRequest.getPhoto(), auctionEntity));
        auctionEntity.setParameter_auction(addParameterToAuction(auctionRequest.getParameter(), auctionEntity));
        var categoryEntity = sectionService.findCategoryByTitle(auctionRequest.getCategory());
        auctionEntity.setCategoryEntity(categoryEntity);
//        UserEntity userEntity = (UserEntity)
//                SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        auctionEntity.setUserEntity(userEntity);
        var userEntity = new UserEntity();
        userEntity = userService.findUserByUsername(loginController.getController());
        auctionEntity.setUserEntity(userEntity);
        System.out.println(userEntity);
        entityManager.persist(auctionEntity);

    }

    @Transactional
    public void updateAuction(AuctionRequest auctionRequest, Long id) throws Exception {
        var auctionEntity = findAuctionByOwnerId(id);
        if (auctionEntity.getUserEntity().getUsername().equals(loginController.getController())) {
            try {
                auctionEntity.setDescription(auctionRequest.getDescription());
                auctionEntity.setPrice(auctionRequest.getPrice());
                auctionEntity.setTitle(auctionRequest.getTitle());
                auctionEntity.setVersion(auctionEntity.getVersion() + 1);
                var categoryEntity = sectionService.findCategoryByTitle(auctionRequest.getCategory());
                auctionEntity.setCategoryEntity(categoryEntity);
                var userEntity = new UserEntity();
                userEntity = userService.findUserByUsername(loginController.getController());
                auctionEntity.setUserEntity(userEntity);
                for (PhotoEntity x : addPhotoToAuction(auctionRequest.getPhoto(),auctionEntity)) {
                    entityManager.persist(x);
                }
                for(Auction_parameterEntity y : addParameterToAuction(auctionRequest.getParameter(),auctionEntity)) {
                    entityManager.persist(y);
                }
                entityManager.merge(auctionEntity);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } else
            System.out.println("Zly uzytkownik zalogowany");


    }

    @Transactional
    public AuctionRequest viewAuction(Long id) {
        var auctionEntity = findAuctionById(id);
        AuctionRequest auctionRequest = new AuctionRequest();
        auctionRequest.setDescription(auctionEntity.getDescription());
        auctionRequest.setPrice(auctionEntity.getPrice());
        var categoryEntity = sectionService.findCategoryByTitle(auctionEntity.getCategoryEntity().getTitle());
        auctionRequest.setCategory(categoryEntity.getTitle());
        auctionRequest.setVersion(auctionEntity.getVersion());
        auctionRequest.setTitle(auctionEntity.getTitle());
        List<PhotoRequest> photoRequestList = new ArrayList<>();
        for(PhotoEntity x : auctionEntity.getPhotoEntityList()) {
            var photoRequest = new PhotoRequest(x.getLink(),x.getOrder());
            photoRequestList.add(photoRequest);
        }
        auctionRequest.setPhoto(photoRequestList);
        List<ParameterRequest> parameterRequestList = new ArrayList<>();
        for(Auction_parameterEntity y : auctionEntity.getParameter_auction()) {
            var parameterRequest = new ParameterRequest(y.getValue(),y.getParameterEntity().getKey());
            parameterRequestList.add(parameterRequest);
        }
        auctionRequest.setParameter(parameterRequestList);
        auctionRequest.setOwner(auctionEntity.getUserEntity().getUsername());

        return auctionRequest;

    }

    @Transactional
    public List<AuctionRequest> viewAllAuctions(){

        var getAllAuction = getAllAuctions();
        List<AuctionRequest> AllAuctionsList = new ArrayList<>();
        for(AuctionEntity x : getAllAuction) {
            AllAuctionsList.add(viewAuction(x.getId()));
        }
        return AllAuctionsList;
    }

    public List<PhotoEntity> addPhotoToAuction(List<PhotoRequest> photo, AuctionEntity auctionEntity) {
        List<PhotoEntity> photoEntityList = new ArrayList<>();
        for (PhotoRequest b : photo) {
            var photoEntity = new PhotoEntity();
            photoEntity.setLink(b.getLink());
            photoEntity.setOrder(b.getOrder());
            photoEntity.setAuctionEntity(auctionEntity);
            photoEntityList.add(photoEntity);

        }
        return photoEntityList;
    }

    public Set<Auction_parameterEntity> addParameterToAuction(List<ParameterRequest> parameter, AuctionEntity auctionEntity) {
        Set<Auction_parameterEntity> parameterEntityList = new HashSet<>();
        for (ParameterRequest b : parameter) {
            var parameterEntity = new ParameterEntity();
            parameterEntity.setKey(b.getKey());
            var valueEntity = new Auction_parameterEntity();
            valueEntity.setValue(b.getValue());
            valueEntity.setParameterEntity(parameterEntity);
            valueEntity.setAuctionEntity(auctionEntity);
            parameterEntityList.add(valueEntity);

        }
        return parameterEntityList;
    }


    public AuctionEntity findAuctionById(Long id) {
        return entityManager.createQuery("select auction from AuctionEntity auction where auction.id = :id", AuctionEntity.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public AuctionEntity findAuctionByOwnerId(Long owner_id) {
        return entityManager.createQuery("select auction from AuctionEntity auction where auction.userEntity.id = :owner_id", AuctionEntity.class)
                .setParameter("owner_id", owner_id)
                .getSingleResult();
    }

    public List<AuctionEntity> getAllAuctions () {
        return entityManager.createQuery("select auction from AuctionEntity auction",AuctionEntity.class)
                .getResultList();
    }


}
