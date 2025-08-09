package com.rofix.fitspot.webservice.api.user.service;

import com.rofix.fitspot.webservice.api.user.entity.Clothing;
import com.rofix.fitspot.webservice.api.user.repository.ClothingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

@Service
@Transactional(readOnly = true) // 모든 메서드 읽기 전용 트랜잭션
public class ClothingService {

    private final ClothingRepository clothingRepository;

    public ClothingService(ClothingRepository clothingRepository) {
        this.clothingRepository = clothingRepository;
    }

    public Page<Clothing> searchClothes(
            String keyword,
            boolean searchInTitle,
            boolean searchInDescription,
            String category,
            Pageable pageable) {

        Specification<Clothing> spec = hasCategory(category)
                .and(keywordSpec(keyword, searchInTitle, searchInDescription));

        return clothingRepository.findAll(spec, pageable);
    }

    // Specification 안전화
    private Specification<Clothing> hasCategory(String category) {
        return (root, query, cb) -> {
            if (category == null || category.isBlank()) {
                return cb.conjunction(); // null 대신 무조건 true 조건
            }
            return cb.equal(root.get("category"), category);
        };
    }

    private Specification<Clothing> keywordSpec(String keyword, boolean inTitle, boolean inDesc) {
        return (root, query, cb) -> {
            if (keyword == null || keyword.isBlank() || (!inTitle && !inDesc)) {
                return cb.conjunction();
            }
            String pattern = "%" + keyword.toLowerCase() + "%";
            if (inTitle && inDesc) {
                return cb.or(
                        cb.like(cb.lower(root.get("title")), pattern),
                        cb.like(cb.lower(root.get("description")), pattern)
                );
            } else if (inTitle) {
                return cb.like(cb.lower(root.get("title")), pattern);
            } else { // inDesc
                return cb.like(cb.lower(root.get("description")), pattern);
            }
        };
    }
}
