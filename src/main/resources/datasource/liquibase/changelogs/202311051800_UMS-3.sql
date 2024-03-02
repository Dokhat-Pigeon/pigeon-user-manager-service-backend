--liquibase formatted sql

--changeset dmitry.krivenko:202311051800-1
CREATE TABLE IF NOT EXISTS public.user(
    id               BIGINT          NOT NULL,
    state            UUID            NOT NULL,
    name             VARCHAR         NOT NULL,
    email            VARCHAR         NOT NULL,
    login            VARCHAR         NOT NULL,
    password         VARCHAR         NOT NULL,
    image_name       VARCHAR,
    status           VARCHAR         NOT NULL,
    role             VARCHAR         NOT NULL,
    created_date     TIMESTAMPTZ     NOT NULL        DEFAULT now()::TIMESTAMPTZ,

    CONSTRAINT user_id              PRIMARY KEY (id),
    CONSTRAINT user_unique_email    UNIQUE      (email),
    CONSTRAINT user_unique_login    UNIQUE      (login)
);

CREATE SEQUENCE hibernate_user_sequence;

COMMENT ON TABLE  public.user                           IS 'Аккаунты пользователей';
COMMENT ON COLUMN public.user.id                        IS 'ID пользователя';
COMMENT ON COLUMN public.user.name                      IS 'Имя пользователя';
COMMENT ON COLUMN public.user.email                     IS 'Почта пользователя';
COMMENT ON COLUMN public.user.login                     IS 'Логин пользователя';
COMMENT ON COLUMN public.user.password                  IS 'Пароль пользователя';
COMMENT ON COLUMN public.user.image_name                IS 'Наименование файла изображения для аватарки пользователя';
COMMENT ON COLUMN public.user.status                    IS 'Статус пользователя';
COMMENT ON COLUMN public.user.created_date              IS 'Время создания записи';

--changeset dmitry.krivenko:202311051800-2
CREATE TABLE IF NOT EXISTS public.blacklist(
    id              BIGINT          NOT NULL,
    owner_id        BIGINT          NOT NULL,
    user_id         BIGINT          NOT NULL,
    created_date    TIMESTAMPTZ     NOT NULL        DEFAULT now()::TIMESTAMPTZ,

    CONSTRAINT  blacklist_id                 PRIMARY KEY(id),
    CONSTRAINT  blacklist_unique_fields      UNIQUE     (owner_id, user_id),
    FOREIGN KEY (owner_id)                   REFERENCES  public.user(id),
    FOREIGN KEY (user_id)                    REFERENCES  public.user(id)
);

CREATE SEQUENCE hibernate_blacklist_sequence;

COMMENT ON TABLE  public.blacklist                          IS 'Черный список пользователей';
COMMENT ON COLUMN public.blacklist.id                       IS 'ID черного списка';
COMMENT ON COLUMN public.blacklist.owner_id                 IS 'ID владельца черного списка';
COMMENT ON COLUMN public.blacklist.user_id                  IS 'ID пользователя в черном списке';
COMMENT ON COLUMN public.blacklist.created_date             IS 'Время создания записи';