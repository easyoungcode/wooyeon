package com.wooyeon.yeon.chat.controller;

import com.wooyeon.yeon.chat.dto.MatchRoomDto;
import com.wooyeon.yeon.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;

@RestController
@RequiredArgsConstructor
public class ChatController {
    ChatService chatService;

    public MatchRoomDto.MatchResponseDto getMatchRoomList(
            @RequestBody MatchRoomDto.MatchRequestDto matchRequestDto) {
        return chatService.getMatchRoomList(matchRequestDto);
    }


}
