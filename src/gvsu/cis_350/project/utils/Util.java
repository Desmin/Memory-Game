package gvsu.cis_350.project.utils;

import gvsu.cis_350.project.core.Card;
import gvsu.cis_350.project.core.Card.CardType;
import gvsu.cis_350.project.core.game.difficulty.GameSessionSetting;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Util will house utility methods for various use throughout
 * the project.
 *
 * @author Desmin Little
 */
public final class Util {

    /**
     * Randomizes the order of the given cards, then returns a {@link List} containing the new order.
     *
     * @param cards A {@link LinkedList} of cards to be randomized.
     * @return A {@link List} with a randomized order of cards.
     */
    private static List<Card> randomize(List<Card> cards) {
        Collections.shuffle(cards, new SecureRandom());
        return cards;
    }

    /**
     * Fills a list with the needed amount of Card objects based on the given
     * game difficulty. This list is then used to build the UI.
     *
     * @param difficulty The difficulty of this game session.
     * @return An ArrayList<Card> holding the required amount of cards for the
     * given difficulty.
     */
    public static List<Card> fetchRandomizedList(GameSessionSetting difficulty) {
        int limit = difficulty.getNumberOfCards() / 2;
        List<Card> list = new LinkedList<>();
        for (CardType type : CardType.values()) {
            if ((type.ordinal() + 1) <= limit) {
                list.add(new Card(type));
                list.add(new Card(type));
            }
        }
        return randomize(list);
    }

    public static String toLowerFirstUC(String s) {
        s = s.toLowerCase();
        for (int i = 0; i < s.length(); i++) {
            if (i == 0) {
                s = String.format("%s%s", Character.toUpperCase(s.charAt(0)),
                        s.substring(1));
            }
            if (!Character.isLetterOrDigit(s.charAt(i))) {
                if (i + 1 < s.length()) {
                    s = String.format("%s%s%s", s.subSequence(0, i + 1),
                            Character.toUpperCase(s.charAt(i + 1)),
                            s.substring(i + 2));
                }
            }
        }
        return s.replace("_", " ").trim();
    }

    public static String[] formatUserSelection(String[] users) {
        if (Objects.isNull(users))
            return new String[]{};
        String[] formattedUsers = new String[users.length + 1];
        formattedUsers[0] = "";
        for (int i = 0; i < users.length; i++)
            formattedUsers[i + 1] = (String) users[i].subSequence(0, users[i].indexOf("."));
        return formattedUsers;
    }

}
