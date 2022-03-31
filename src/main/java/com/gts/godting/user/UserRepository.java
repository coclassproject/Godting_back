package com.gts.godting.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByOauth2Id(String oauthId);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);
}
