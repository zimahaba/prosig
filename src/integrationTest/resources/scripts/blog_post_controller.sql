
INSERT INTO blog_post (id, title, content, created)
VALUES ('828409bd-4374-47b6-91d4-df1d163f5588', 'controller 1', 'controller 1', '2024-12-28 17:00:00');

INSERT INTO blog_comment (id, "content", blog_post_id, created)
VALUES('b04554b3-b3d3-4535-b72f-55dcd1522ba7', 'controller comment', '828409bd-4374-47b6-91d4-df1d163f5588', '2024-12-28 17:00:00');

INSERT INTO blog_comment (id, "content", blog_post_id, created)
VALUES('0e3137cf-0bd3-46b9-915b-eca706648744', 'controller comment 2', '828409bd-4374-47b6-91d4-df1d163f5588', '2024-12-28 17:00:01');