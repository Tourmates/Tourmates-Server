package com.ssafy.tourmates.admin.notice.repository;

import com.ssafy.tourmates.admin.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
