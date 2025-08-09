package com.rofix.fitspot.webservice.api.user.repository;

import com.rofix.fitspot.webservice.api.user.entity.Clothing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClothingRepository extends JpaRepository<Clothing, Long>, JpaSpecificationExecutor<Clothing> {

//    // 특정 의상 id 목록에 대해, 각 의상의 '코디에 달린 좋아요 수' 집계
//    @Query("""
//            SELECT cc.cloth.clothingId, COUNT(1)
//            FROM CodyClothes cc
//            JOIN cc.cody c
//            JOIN c.likes 1
//            WHERE cc.cloth.clothingId IN :ids
//            GROUP BY cc.cloth.clothingId
//            """)
//    List<Object[]> countLikesByClothingIds(@Param("ids") List<Long> ids);
}
