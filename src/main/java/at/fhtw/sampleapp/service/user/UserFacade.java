package at.fhtw.sampleapp.service.user;

import at.fhtw.sampleapp.customExceptions.UnexpectedErrorException;
import at.fhtw.sampleapp.model.Card;
import at.fhtw.sampleapp.model.User;
import at.fhtw.sampleapp.service.repoCollection.RepoCard;
import at.fhtw.sampleapp.service.repoCollection.RepoDecks;
import at.fhtw.sampleapp.service.repoCollection.RepoStats;
import at.fhtw.sampleapp.service.repoCollection.RepoUser;
import at.fhtw.sampleapp.service.repoCollection.intermediateTables.RepoCardPackages;
import at.fhtw.sampleapp.service.repoCollection.intermediateTables.RepoUserCards;
import at.fhtw.sampleapp.service.repoCollection.intermediateTables.RepoUserPackages;

import java.util.ArrayList;
import java.util.List;

public class UserFacade {
    private List<User> userData;

    public UserFacade() { //addUser
        //remove sampleData
    }

    public List<User> getAllUsers() {
        List<User> userList = null; //ToDo: null entfernen, nicht die eleganteste Lösung
        RepoUser userRequest = new RepoUser();

        userList = userRequest.getAllUsers();
        return userList;
    }

    public User getUser(int user_id) {
        RepoUser userRequest = new RepoUser();
        return userRequest.getUser(user_id);
    }

    public User getUser(String user_name) {
        User user = null;
        RepoUser userRequest = new RepoUser();

        user = userRequest.getUser(user_name);
        return user;
    }

    public boolean addUser(String user_username, String user_password) {

        final int defaultMoney = 20;
        final boolean defaultDeck = true;
        //check if username is already in use
        RepoUser repoUser = new RepoUser();
        boolean success = false;
        if (repoUser.getUser(user_username) == null) { //user_name nicht vergeben
            success = repoUser.postUser(user_username, user_password, defaultMoney, defaultDeck);
        }

        if(!success) {
            //throw some Exception
        }

        //add into stats table default values
        User user = repoUser.getUser(user_username);
        RepoStats repoStats = new RepoStats();

        final int defaultElo = 1000;
        final int defaultWins = 0;
        final int defaultLosses = 0;
        final int defaultDraws = 0;

        String username;

        if(user.getUser_name() == null) {
            username = user.getUser_username();
        } else {
            username = user.getUser_name();
        }

        success = repoStats.addDefaultStats(defaultElo, defaultWins, defaultLosses, defaultDraws, username, user.getUser_id());


        return success;
    }

    public boolean updateUser(User user, int user_id) throws UnexpectedErrorException {
        //entries that aren't null will be updated

        RepoUser repoUser = new RepoUser();
        boolean success;

        if(user.getUser_name() != null) {
            success = repoUser.updateName(user_id, user.getUser_name());
            if(!success) {
                throw new UnexpectedErrorException("Unexpected Error. Update Failed");
            }
        }
        if(user.getUser_bio() != null) {
            success = repoUser.updateBio(user_id, user.getUser_bio());
            if(!success) {
                throw new UnexpectedErrorException("Unexpected Error. Update Failed");
            }
        }
        if(user.getUser_image() != null) {
            success = repoUser.updateImage(user_id, user.getUser_image());
            if(!success) {
                throw new UnexpectedErrorException("Unexpected Error. Update Failed");
            }
        }

        return true;
    }

    public void addUserCards(int user_id) {
        //get packages with user_id
        RepoUserCards repoUserCards = new RepoUserCards();
        RepoUserPackages repoUserPackages = new RepoUserPackages();
        List<Integer> packageIdList = repoUserPackages.getUserPackages(user_id);


        //get all card_ids with package_ids
        RepoCardPackages repoCardPackages = new RepoCardPackages();
        List<String> cardIdList = new ArrayList<>();

        packageIdList.forEach(package_id -> {
            List<String> queryCardIdList = repoCardPackages.getCardInPackage(package_id);
            //add each card_id to cardList
            queryCardIdList.forEach(card_id -> cardIdList.add(card_id));
        });

        cardIdList.forEach(
                cardId -> {
                    repoUserCards.addUserCard(user_id, cardId);
                }
        );

        //ToDo: get Cards
       /*
         RepoCard repoCard = new RepoCard();

        List<Card> cardList = new ArrayList<>();
        cardIdList.forEach(card_id -> cardList.add(repoCard.getCard(card_id)));

        */
    }
    public List<Card> getUserCardList(int user_id) { //USER DECK(4 KARTEN), nicht STACK

        RepoDecks repoDecks = new RepoDecks();
        RepoCard repoCard = new RepoCard();
        List<Card> cardList = new ArrayList<>();

        List<String> cardIdList = repoDecks.getDeck(user_id);

        cardIdList.forEach(
                cardId -> {
                    //get Card with Card ID
                    cardList.add(repoCard.getCard(cardId));
                }
        );
        return cardList;
    }
}
