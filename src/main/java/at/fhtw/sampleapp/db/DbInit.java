package at.fhtw.sampleapp.db;

import at.fhtw.sampleapp.service.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbInit {
    private static Connection connection = DatabaseConnection.getDatabaseConnection();

    public DbInit() throws SQLException {
        initDB();
    }
    public static void initDB() throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DROP TABLE IF EXISTS card_details;\n" +
                            "DROP TABLE IF EXISTS card_deck_link;\n" +
                            "DROP TABLE IF EXISTS card_stack_link;\n" +
                            "DROP TABLE IF EXISTS cards;\n" +
                            "DROP TABLE IF EXISTS stacks;\n" +
                            "DROP TABLE IF EXISTS decks;\n" +
                            "DROP TABLE IF EXISTS players;\n" +
                            "\n" +
                            "CREATE TABLE players (\n" +
                            "    user_id SERIAL,\n" +
                            "    user_name varchar(255) NOT NULL,\n" +
                            "    user_password varchar(255) NOT NULL,\n" +
                            "    user_elo int NOT NULL,\n" +
                            "    PRIMARY KEY(user_id)\n" +
                            ");\n" +
                            "\n" +
                            "CREATE TABLE stacks (\n" +
                            "    stack_id int NULL,\n" +
                            "    user_id int NOT NULL,\n" +
                            "    PRIMARY KEY(stack_id),\n" +
                            "\n" +
                            "    CONSTRAINT fk_player\n" +
                            "        FOREIGN KEY(user_id)\n" +
                            "        REFERENCES players(user_id)\n" +
                            "        ON DELETE CASCADE\n" +
                            ");\n" +
                            "\n" +
                            "CREATE TABLE decks (\n" +
                            "   user_id int NOT NULL,\n" +
                            "   deck_id int NOT NULL,\n" +
                            "   PRIMARY KEY(deck_id),\n" +
                            "\n" +
                            "   CONSTRAINT fk_user_id\n" +
                            "       FOREIGN KEY(user_id)\n" +
                            "       REFERENCES players(user_id)\n" +
                            "       ON DELETE CASCADE\n" +
                            ");\n" +
                            "\n" +
                            "CREATE TABLE cards (\n" +
                            "   card_id int NOT NULL,\n" +
                            "   PRIMARY KEY(card_id)\n" +
                            "\n" +
                            ");\n" +
                            "-- TODO: many to many relationship for cards and decks\n" +
                            "-- TODO: DONE\n" +
                            "CREATE TABLE card_deck_link(\n" +
                            "    card_id int NOT NULL,\n" +
                            "    deck_id int NOT NULL,\n" +
                            "\n" +
                            "    CONSTRAINT fk_card_id\n" +
                            "        FOREIGN KEY(card_id)\n" +
                            "            REFERENCES cards(card_id)\n" +
                            "            ON DELETE CASCADE,\n" +
                            "\n" +
                            "    CONSTRAINT fk_deck_id\n" +
                            "        FOREIGN KEY(deck_id)\n" +
                            "            REFERENCES decks(deck_id)\n" +
                            "            ON DELETE CASCADE\n" +
                            ");\n" +
                            "\n" +
                            "CREATE TABLE card_stack_link(\n" +
                            "    card_id int NOT NULL,\n" +
                            "    stack_id int NOT NULL,\n" +
                            "\n" +
                            "    CONSTRAINT fk_card_id\n" +
                            "            FOREIGN KEY(card_id)\n" +
                            "            REFERENCES cards(card_id)\n" +
                            "            ON DELETE CASCADE,\n" +
                            "\n" +
                            "        CONSTRAINT fk_stack_id\n" +
                            "           FOREIGN KEY(stack_id)\n" +
                            "           REFERENCES stacks(stack_id)\n" +
                            "           ON DELETE CASCADE\n" +
                            ");\n" +
                            "\n" +
                            "CREATE TABLE card_details (\n" +
                            "    card_id int NOT NULL,\n" +
                            "    cd_id int NOT NULL,\n" +
                            "    cd_type varchar(255),\n" +
                            "    cd_element varchar(255),\n" +
                            "\n" +
                            "    CONSTRAINT fk_card_id\n" +
                            "        FOREIGN KEY(card_id)\n" +
                            "        REFERENCES cards(card_id)\n" +
                            "        ON DELETE CASCADE\n" +
                            ");\n" +
                            "\n" +
                            "\n" +
                            "INSERT INTO players VALUES (DEFAULT,'Glumanda','feuer',1000);\n" +
                            "INSERT INTO players VALUES (DEFAULT,'Glutexo','fuego',2000);\n" +
                            "INSERT INTO players VALUES (DEFAULT,'Glurak','elgato',500);"
            );
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error initializing DB");
        }

    }

}
