package com.ssafy.tourmates.client.friend.repository;

import com.ssafy.tourmates.client.friend.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<Friend, Long> {
}
