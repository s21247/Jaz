package pl.edu.pjwstk.jaz.entity;

import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.jaz.request.CategoryRequest;
import pl.edu.pjwstk.jaz.request.SectionRequest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
public class SectionService {
    private final EntityManager entityManager;
    SectionRequest sectionRequest;

    public SectionService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void saveSection(String title) {
        var sectionEntity = new SectionEntity();
        sectionEntity.setTitle(title);
        entityManager.persist(sectionEntity);
    }

    @Transactional
    public void updateSection(String title, String newTitle) {
        var section = findSectionByTitle(title);
            section.setTitle(newTitle);
            entityManager.merge(section);
    }

    @Transactional
    public void saveCategory(CategoryRequest categoryRequest) {
        var section = findSectionByTitle(categoryRequest.getSectionTitle());
        var categoryEntity = new CategoryEntity();
        categoryEntity.setTitle(categoryRequest.getTitle());
        categoryEntity.setSectionEntity(section);
        entityManager.persist(categoryEntity);

    }



    public SectionEntity findSectionByTitle(String title) {
        return entityManager.createQuery("select section from SectionEntity section where section.title = :title", SectionEntity.class)
                .setParameter("title",title)
                .getSingleResult();
    }

    public SectionEntity findSectionById(Long id) {
        return entityManager.createQuery("select id from SectionEntity where id = :id", SectionEntity.class)
                .setParameter("id",id)
                .getSingleResult();
    }

    public CategoryEntity findCategoryByTitle(String title) {
        return entityManager.createQuery("select category from CategoryEntity category where category.title = :title", CategoryEntity.class)
                .setParameter("title",title)
                .getSingleResult();
    }
}
