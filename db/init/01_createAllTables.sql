USE deview;

CREATE TABLE IF NOT EXISTS TECH_INTERVIEW
(
    TECH_INTERVIEW_ID  BIGINT       AUTO_INCREMENT PRIMARY KEY COMMENT '기술면접ID',
    HGH_QST_NUM        BIGINT       NULL                       COMMENT '상위질문번호',
    CATEGORY           VARCHAR(50)  NOT NULL                   COMMENT '카테고리',
    KEYWORD            VARCHAR(50)  NOT NULL                   COMMENT '키워드',
    QUESTION           VARCHAR(255) NOT NULL                   COMMENT '질문',
    ANSWER             TEXT         NOT NULL                   COMMENT '답변',
    HGH_QST_YN         VARCHAR(50)  NOT NULL                   COMMENT '상위질문사용가능'
    ) COMMENT '기술면접테이블';

CREATE TABLE IF NOT EXISTS STUDY_CONTENT
(
    STUDY_CONTENT_ID   BIGINT       AUTO_INCREMENT PRIMARY KEY COMMENT '학습컨텐츠ID',
    FIRST_CATEGORY     VARCHAR(50)  NOT NULL                   COMMENT '상위카테고리',
    SECOND_CATEGORY    VARCHAR(50)  NOT NULL                   COMMENT '하위카테고리',
    TITLE              VARCHAR(255) NOT NULL                   COMMENT '제목',
    BODY               TEXT         NOT NULL                   COMMENT '내용'
    ) COMMENT '학습컨텐츠테이블';
