package com.ssafy.tourmates.notice.repository;

import com.ssafy.tourmates.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
