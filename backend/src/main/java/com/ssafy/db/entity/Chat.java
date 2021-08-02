package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 채팅 기록
 */
@Entity
@Getter
@Setter
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chatId;
    private Timestamp chatDate;
    private String chatContent;
    private String chatFrom;

    @ManyToOne // 다대일 관계의 연관 관계
    @JoinColumn(name = "chatroom_id") // ChatRoom entity의 PK인 chatroom_id과 참조관계 mapping
    private ChatRoom chatRoom;
}