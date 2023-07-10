package com.armiabotow.firstbot.repository;

import com.armiabotow.firstbot.model.impl.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    long deleteByNameEquals(@NonNull String name);
    List<Meeting> findByDateTimeIsGreaterThanAndDateTimeIsLessThanEqualOrderByDateTimeAsc(@NonNull LocalDateTime greaterThan, @NonNull LocalDateTime lessThanEqual);
}
