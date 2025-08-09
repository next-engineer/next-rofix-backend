package com.rofix.fitspot.webservice.api.user.specification;

import com.rofix.fitspot.webservice.api.user.entity.Clothing;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

//동적 필터
public class ClothingSpecification {

    public static Specification<Clothing> hasCategory(String category) {
        return (root, query, criteriaBuilder) -> category == null || category.isBlank() ?
                criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("category"), category);
    }

    public static Specification<Clothing> keywordInTitleOrDescription(String keyword, boolean inTitle, boolean inDescription) {
        return (root, query, criteriaBuilder) -> {
            if(keyword == null || keyword.isBlank()) return criteriaBuilder.conjunction();
            
            String pattern = "%" + keyword.toLowerCase() + "%";
            List<Predicate> preds = new ArrayList<>();
            
            if(inTitle) {
                preds.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), pattern));
            }
            if(inDescription) {
                preds.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), pattern));
            }
            if(preds.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            
            return criteriaBuilder.or(preds.toArray(new Predicate[0]));
        };
    }

}
