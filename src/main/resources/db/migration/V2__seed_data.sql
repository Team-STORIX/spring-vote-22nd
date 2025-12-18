-- 데모데이 후보
INSERT INTO candidate (name, category, team, vote_count, created_at, updated_at) VALUES
    ('DiggIndie', 'DEMO_DAY', 'DIGGINDIE', 0, NOW(), NOW()),
    ('STORIX',    'DEMO_DAY', 'STORIX',    0, NOW(), NOW()),
    ('Catch Up',  'DEMO_DAY', 'CATCHUP',   0, NOW(), NOW()),
    ('GroomEasy', 'DEMO_DAY', 'GROOMEASY', 0, NOW(), NOW()),
    ('Modelly',   'DEMO_DAY', 'MODELLY',   0, NOW(), NOW());

-- 파트장 후보 (BE)
INSERT INTO candidate (name, category, part, team, vote_count, created_at, updated_at) VALUES
    ('서가영', 'PART_LEADER', 'BE', 'DIGGINDIE', 0, NOW(), NOW()),
    ('이수아', 'PART_LEADER', 'BE', 'STORIX', 0, NOW(), NOW());

-- 파트장 후보 (FE)
INSERT INTO candidate (name, category, part, team, vote_count, created_at, updated_at) VALUES
    ('김윤성', 'PART_LEADER', 'FE', 'CATCHUP', 0, NOW(), NOW()),
    ('이채연', 'PART_LEADER', 'FE', 'GROOMEASY', 0, NOW(), NOW());