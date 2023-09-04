package com.wooyeon.yeon.chat.controller;

import com.wooyeon.yeon.chat.dto.MatchRoomDto;
import com.wooyeon.yeon.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/chat")
public class ChatListController {
    ChatService chatService;

    @PostMapping("/rooms")
    public List<MatchRoomDto.MatchResponseDto> getMatchRoomList(@RequestBody MatchRoomDto.MatchRequestDto matchRequestDto) {
        return chatService.getMatchRoomList(matchRequestDto);
    }

    @PostMapping("/search-rooms")
    public List<MatchRoomDto.MatchResponseDto> searchMatchRoomList(@RequestBody MatchRoomDto.SearchRoomRequestDto SearchRoomRequest) {
        return chatService.searchMatchRoomList(SearchRoomRequest);
    }
}
