package com.armiabotow.firstbot.service;

import com.armiabotow.firstbot.model.impl.Meeting;
import com.armiabotow.firstbot.repository.MeetingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MeetingService {
    private final MeetingRepository meetingRepository;

    public List<Meeting> getAllMeetingsNextWeek(LocalDateTime greaterThan, LocalDateTime lessThanEqual){
       return meetingRepository.findByDateTimeIsGreaterThanAndDateTimeIsLessThanEqualOrderByDateTimeAsc(greaterThan, lessThanEqual);
    }

    public void add(Meeting meeting){
        meetingRepository.save(meeting);
    }

    @Transactional
    public void deleteByName(String name) {
        meetingRepository.deleteByNameEquals(name);
    }
}
