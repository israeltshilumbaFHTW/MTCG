package at.fhtw.sampleapp.service.packages;

import at.fhtw.sampleapp.model.Card;
import at.fhtw.sampleapp.service.repoCollection.RepoCard;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class PackageDAL {

    public PackageDAL(){

    }

    public boolean addPackageDAL(List<Card> cardList) {
        //add Cards to DB
        RepoCard cardRequest = new RepoCard();
        AtomicBoolean success = new AtomicBoolean(false); //automatisches update
        cardList.forEach(
                card -> {
                    try {
                        success.set(cardRequest.addCard(
                                card.getCard_id(),
                                card.getCard_name(),
                                card.getCard_damage()));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
        );

        //ToDo:add DeckId to DB

        //ToDo:add Entries to card_package_link
        return success.get();
    }
}
