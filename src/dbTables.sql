DROP TABLE IF EXISTS player_package_link;
DROP TABLE IF EXISTS card_deck_link;
DROP TABLE IF EXISTS card_stack_link;
DROP TABLE IF EXISTS card_package_link;
DROP TABLE IF EXISTS packages;
DROP TABLE IF EXISTS cards;
DROP TABLE IF EXISTS stacks;
DROP TABLE IF EXISTS decks;
DROP TABLE IF EXISTS stats;
DROP TABLE IF EXISTS players;

CREATE TABLE players
(
    user_id          SERIAL,
    user_username    varchar(255) NOT NULL,
    user_password    varchar(255) NOT NULL,
    user_money       int          NOT NULL,
    user_defaultDeck boolean      NOT NULL,
    user_bio         varchar(255),
    user_image       varchar(255),
    user_name        varchar(255),

    PRIMARY KEY (user_id)
);

CREATE TABLE stacks
(
    stack_id int NULL,
    user_id  int NOT NULL,
    PRIMARY KEY (stack_id),

    CONSTRAINT fk_player
        FOREIGN KEY (user_id)
            REFERENCES players (user_id)
            ON DELETE CASCADE
);

CREATE TABLE decks
(
    deck_id   SERIAL,
    user_id   int NOT NULL,
    card_1_id varchar(255),
    card_2_id varchar(255),
    card_3_id varchar(255),
    card_4_id varchar(255),

    PRIMARY KEY (deck_id),

    CONSTRAINT fk_player
        FOREIGN KEY (user_id)
            REFERENCES players (user_id)
            ON DELETE CASCADE
);

CREATE TABLE cards
(
    card_id     varchar(255) NOT NULL,
    card_name   varchar(255) NOT NULL,
    card_damage int          NOT NULL,
    PRIMARY KEY (card_id)

);

CREATE TABLE packages
(
    package_id        int     NOT NULL,
    --jedes Package existiert nur einmail
    package_available boolean NOT NULL,
    PRIMARY KEY (package_id)
);

-- ToDo: card n:m deck
CREATE TABLE card_deck_link
(
    card_id varchar(255) NOT NULL,
    deck_id int          NOT NULL,

    CONSTRAINT fk_card_id
        FOREIGN KEY (card_id)
            REFERENCES cards (card_id)
            ON DELETE CASCADE,

    CONSTRAINT fk_deck_id
        FOREIGN KEY (deck_id)
            REFERENCES decks (deck_id)
            ON DELETE CASCADE
);

CREATE TABLE card_stack_link
(
    card_id  varchar(255) NOT NULL,
    stack_id int          NOT NULL,

    CONSTRAINT fk_card_id
        FOREIGN KEY (card_id)
            REFERENCES cards (card_id)
            ON DELETE CASCADE,

    CONSTRAINT fk_stack_id
        FOREIGN KEY (stack_id)
            REFERENCES stacks (stack_id)
            ON DELETE CASCADE
);


CREATE TABLE card_package_link
(
    card_id    varchar(255) NOT NULL,
    package_id int          NOT NULL,

    CONSTRAINT fk_card_id
        FOREIGN KEY (card_id)
            REFERENCES cards (card_id)
            ON DELETE CASCADE,
    CONSTRAINT fk_package_id
        FOREIGN KEY (package_id)
            REFERENCES packages (package_id)
            ON DELETE CASCADE
);

CREATE TABLE player_package_link
(
    user_id    int NOT NULL,
    package_id int NOT NULL,

    CONSTRAINT fk_user_id
        FOREIGN KEY (user_id)
            REFERENCES players (user_id)
            ON DELETE CASCADE,
    CONSTRAINT fk_package_id
        FOREIGN KEY (package_id)
            REFERENCES packages (package_id)
            ON DELETE CASCADE
);

CREATE TABLE stats
(
    stat_id SERIAL,
    user_id int NOT NULL,
    user_name varchar(255),
    user_elo int NOT NULL,
    user_wins int NOT NULL,
    user_losses int NOT NULL
);
