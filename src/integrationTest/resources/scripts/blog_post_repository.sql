
INSERT INTO blog_post (id, title, content, created)
VALUES ('4c659044-0600-42e9-a614-508269c2990e', 'test title 1', 'test content 1', '2024-12-28 17:00:00');

INSERT INTO blog_comment (id, "content", blog_post_id, created)
VALUES('785c687d-d8bb-4704-baef-d8ab95d6af23', 'test comment 1', '4c659044-0600-42e9-a614-508269c2990e', '2024-12-28 17:00:00');

INSERT INTO blog_comment (id, "content", blog_post_id, created)
VALUES('1b461657-ebb2-4d47-8877-8e1b477c5f31', 'test comment 2', '4c659044-0600-42e9-a614-508269c2990e', '2024-12-28 17:00:01');

INSERT INTO blog_comment (id, "content", blog_post_id, created)
VALUES('bcf53424-93d1-46b1-a9f0-068eeb69bee5', 'test comment 3', '4c659044-0600-42e9-a614-508269c2990e', '2024-12-28 17:00:02');

INSERT INTO blog_comment (id, "content", blog_post_id, created)
VALUES('2fba3cca-73b4-47fb-a4e2-6f2b8cda2bad', 'test comment 4', '4c659044-0600-42e9-a614-508269c2990e', '2024-12-28 17:00:03');
