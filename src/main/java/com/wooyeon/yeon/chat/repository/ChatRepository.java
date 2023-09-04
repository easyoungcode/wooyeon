package com.wooyeon.yeon.chat.repository;

import com.wooyeon.yeon.chat.domain.Chat;
import com.wooyeon.yeon.chat.dto.MatchRoomDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
//    List<MatchRoomDto.MatchResponseDto>
}