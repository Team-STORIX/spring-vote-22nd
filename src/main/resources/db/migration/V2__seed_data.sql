-- 1. 파트장 후보 (FE) : ID 1 ~ 10
INSERT INTO candidate (id, team, name, part, category, vote_count, created_at, updated_at) VALUES
    (1, 'STORIX',    '김윤성', 'FE', 'PART_LEADER', 0, NOW(), NOW()),
    (2, 'STORIX',    '이채연', 'FE', 'PART_LEADER', 0, NOW(), NOW()),
    (3, 'DiggIndie', '백승선', 'FE', 'PART_LEADER', 0, NOW(), NOW()),
    (4, 'DiggIndie', '조성아', 'FE', 'PART_LEADER', 0, NOW(), NOW()),
    (5, 'CatchUp',   '장자윤', 'FE', 'PART_LEADER', 0, NOW(), NOW()),
    (6, 'CatchUp',   '정성훈', 'FE', 'PART_LEADER', 0, NOW(), NOW()),
    (7, 'Modelly',   '손주완', 'FE', 'PART_LEADER', 0, NOW(), NOW()),
    (8, 'Modelly',   '정윤지', 'FE', 'PART_LEADER', 0, NOW(), NOW()),
    (9, 'Menual',    '신용섭', 'FE', 'PART_LEADER', 0, NOW(), NOW()),
    (10, 'Menual',   '최무현', 'FE', 'PART_LEADER', 0, NOW(), NOW());

-- 2. 파트장 후보 (BE) : ID 11 ~ 20
INSERT INTO candidate (id, team, name, part, category, vote_count, created_at, updated_at) VALUES
    (11, 'STORIX',    '서가영', 'BE', 'PART_LEADER', 0, NOW(), NOW()),
    (12, 'STORIX',    '이수아', 'BE', 'PART_LEADER', 0, NOW(), NOW()),
    (13, 'DiggIndie', '변호영', 'BE', 'PART_LEADER', 0, NOW(), NOW()),
    (14, 'DiggIndie', '이윤지', 'BE', 'PART_LEADER', 0, NOW(), NOW()),
    (15, 'CatchUp',   '배승식', 'BE', 'PART_LEADER', 0, NOW(), NOW()),
    (16, 'CatchUp',   '신혁',   'BE', 'PART_LEADER', 0, NOW(), NOW()),
    (17, 'Modelly',   '이연호', 'BE', 'PART_LEADER', 0, NOW(), NOW()),
    (18, 'Modelly',   '이준영', 'BE', 'PART_LEADER', 0, NOW(), NOW()),
    (19, 'Menual',    '이지원', 'BE', 'PART_LEADER', 0, NOW(), NOW()),
    (20, 'Menual',    '변하영', 'BE', 'PART_LEADER', 0, NOW(), NOW());

-- 3. 데모데이 팀 후보 : ID 101 ~ 105 (프론트 요청 반영)
INSERT INTO candidate (id, team, name, part, category, vote_count, created_at, updated_at) VALUES
    (101, 'STORIX',    'STORIX',    NULL, 'DEMO_DAY', 0, NOW(), NOW()),
    (102, 'DiggIndie', 'DiggIndie', NULL, 'DEMO_DAY', 0, NOW(), NOW()),
    (103, 'CatchUp',   'CatchUp',   NULL, 'DEMO_DAY', 0, NOW(), NOW()),
    (104, 'Modelly',   'Modelly',   NULL, 'DEMO_DAY', 0, NOW(), NOW()),
    (105, 'Menual',    'Menual',    NULL, 'DEMO_DAY', 0, NOW(), NOW());