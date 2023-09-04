package com.wooyeon.yeon.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StompHandler implements ChannelInterceptor {
//    private final JwtUtils jwtUtils;

    // presend( ) 는 메시지가 실제로 채널로 전송되기 전에 호출
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {

        final StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        // websocket 연결시 헤더의 jwt token 유효성 검증
        // 유저 쪽에서 구현이 되면 추가할 예
        if (StompCommand.CONNECT == accessor.getCommand()) {
//            final String authorization = jwtUtil정);
        }
        return message;
    }
}
