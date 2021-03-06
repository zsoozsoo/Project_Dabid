package com.ssafy.db.repository;

import com.ssafy.db.entity.Live;
import com.ssafy.db.entity.LiveStatus;
import com.ssafy.db.entity.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
/**
 * Live 관련 DB 쿼리 메소드 이름을 자동 분석해서 JPQL을 생성 및 실행을 위한 Spring Data JPA 인터페이스 정의.
 */
@Repository
//JpaRepository<>안에는 엔티티 클래스 이름과 ID 필드 타입이 지정된다. 기본형의 경우 wrapper class
public interface LiveRepository extends JpaRepository<Live,Integer> {
    Optional<Live> findByPrdId(int prdId);
    Optional<List<Live>> findTop2ByLiveStatusOrderByLiveDateAsc(LiveStatus liveStatus);

    Optional<List<Live>> findByLiveTitleContainingOrPrdNameContaining(
            String liveTitle, String prdName);
    Optional<List<Live>> findByProductCategoryIn(List<ProductCategory> prdCategory);
    Optional<List<Live>> findByLiveStatusIn(List<LiveStatus> liveStatuses);

    Page<Live> findAll(Pageable pageable);
}
