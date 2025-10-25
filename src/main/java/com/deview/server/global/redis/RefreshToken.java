package com.deview.server.global.redis;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@NoArgsConstructor
@RedisHash(value = "refreshToken", timeToLive = 60*60*24*14) //리프레시 토큰의 생명 주기(14일)
/* Redis에 저장해서 RefreshToken이 유효한지 검증 */
public class RefreshToken {
    @Id
    @Indexed
    private String username;

    @Indexed
    private String jwtRefreshToken;

    @TimeToLive
    private Long ttl;

    @Builder
    public RefreshToken(String jwtRefreshToken, String username) {
        this.jwtRefreshToken = jwtRefreshToken;
        this.username = username;
        this.ttl = 60*60*24*14L;
    }
}
