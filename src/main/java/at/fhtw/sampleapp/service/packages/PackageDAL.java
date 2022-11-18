package at.fhtw.sampleapp.service.packages;

import at.fhtw.sampleapp.model.Card;
import at.fhtw.sampleapp.service.repoCollection.RepoCard;
import at.fhtw.sampleapp.service.repoCollection.RepoPackages;
import at.fhtw.sampleapp.service.repoCollection.intermediateTables.RepoCardPackages;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class PackageDAL {

    public PackageDAL() {

    }

    public boolean addPackageDAL(List<Card> cardList){
        //add Cards to DB
        RepoCard cardRequest = new RepoCard();
        RepoPackages packageRequest = new RepoPackages();
        RepoCardPackages cardPackagesRequest = new RepoCardPackages();
        AtomicBoolean success = new AtomicBoolean(false); //automatisches update
        try {

            //ToDo: check package Count
            int package_count = packageRequest.getPackageCount();
            success.set(packageRequest.addPackage(package_count + 1));
            //ToDo: insert id into package table
            cardList.forEach(
                    card -> {
                        try {
                            //ToDo: check if card already exists
                            success.set(cardRequest.addCard(
                                    card.getCard_id(),
                                    card.getCard_name(),
                                    card.getCard_damage()));
                            //ToDo: insert package_id and card_id into intermediate table
                            success.set(cardPackagesRequest.addCardPackageId(card.getCard_id(), package_count + 1));
                        } catch (SQLException e) {
                            System.err.println("Fehler beim einfügen von Packages DAL");
                            throw new RuntimeException(e); //necessary because of AtomicBoolean
                        }
                    }

            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success.get();
    }
}
