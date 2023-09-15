package com.wooyeon.yeon.profileChoice.repository;

import com.wooyeon.yeon.profileChoice.domain.UserMatch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<UserMatch, Long>, MatchRepositoryRecommandUserList {
    public List<UserMatch> findAllByUserLike1(Long userId);
}
