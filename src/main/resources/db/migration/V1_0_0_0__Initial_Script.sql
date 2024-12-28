
CREATE SCHEMA IF NOT EXISTS extensions;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA extensions;

CREATE TABLE blog_post (
    id      UUID      NOT NULL DEFAULT extensions.uuid_generate_v4(),
    title   TEXT      NOT NULL,
    content TEXT      NOT NULL,
    created TIMESTAMP NOT NULL
);

CREATE TABLE blog_comment (
    id           UUID      NOT NULL DEFAULT extensions.uuid_generate_v4(),
    content      TEXT      NOT NULL,
    blog_post_id UUID      NOT NULL,
    created      TIMESTAMP NOT NULL
);

ALTER TABLE blog_post    ADD CONSTRAINT blog_post_pk    PRIMARY KEY (id);
ALTER TABLE blog_comment ADD CONSTRAINT blog_comment_pk PRIMARY KEY (id);

ALTER TABLE blog_comment ADD CONSTRAINT blog_comment_blog_post_fk FOREIGN KEY (blog_post_id) REFERENCES blog_post (id);
