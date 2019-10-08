package pt.ipbeja.aula04.data;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.List;


/**
 * Dummy Contact Database
 */
public class ContactDataSource {

    private static final String[] FIRST_NAMES = new String[]{
            "Allison",
            "Arthur",
            "Ana",
            "Alex",
            "Arlene",
            "Alberto",
            "Barry",
            "Bertha",
            "Bill",
            "Bonnie",
            "Bret",
            "Beryl",
            "Chantal",
            "Cristobal",
            "Claudette",
            "Charley",
            "Cindy",
            "Chris",
            "Dean",
            "Dolly",
            "Danny",
            "Danielle",
            "Dennis",
            "Debby",
            "Erin",
            "Edouard"};


    private static final String[] LAST_NAMES = new String[]{
            "Abbott",
            "Acevedo",
            "Acosta",
            "Adams",
            "Adkins",
            "Aguilar",
            "Aguirre",
            "Albert",
            "Alexander",
            "Alford",
            "Allen",
            "Alston",
            "Alvarado",
            "Alvarez",
            "Anderson",
            "Andrews",
            "Anthony",
            "Armstrong",
            "Arnold",
            "Ashley",
            "Atkins",
            "Atkinson",
            "Austin",
            "Avery",
            "Avila",
            "Ayala",
            "Ayers",
            "Bailey",
            "Baird",
            "Baker",
            "Baldwin",
            "Ball",
            "Ballard"};


    private static final int INITIAL_CONTACTS = 50;
    private static List<Contact> contacts = new ArrayList<>();

    static { contacts.addAll(createContacts(INITIAL_CONTACTS)); }


    @SuppressLint("DefaultLocale")
    private static List<Contact> createContacts(int count) {
        ArrayList<Contact> contacts = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            long id = (long) (Math.random() * 10000 + 1);
            String firstName = randomFirstName();
            String lastName = randomLastName();
            Contact contact = new Contact(
                    id,
                    firstName,
                    lastName,
                    (int) (Math.random() * 50 + 18),
                    String.format("%s.%s.%d@dummy.pt", firstName.toLowerCase(), lastName.toLowerCase(), id)
            );
            contacts.add(contact);
        }

        return contacts;
    }

    private static String randomFirstName() {
        return FIRST_NAMES[(int) (Math.random() * FIRST_NAMES.length)];
    }

    private static String randomLastName() {
        return LAST_NAMES[(int) (Math.random() * LAST_NAMES.length)];
    }






    /**
     * Get all contacts in Database
     * @return List of all contacts
     */
    public static List<Contact> getContacts() {
        return contacts;
    }


    /**
     * Find a Contact by its ID
     * @param id the unique identifier
     * @return The contact if it exists, null if it doesn't
     */
    public static Contact getContactById(long id) {
        for (Contact contact : contacts) {
            if (contact.getId() == id) return contact;
        }
        return null;
    }

    /**
     * Inserts a contact in the database
     * @param contact The contact
     * @return True if operation was successful
     */
    public static boolean insertContact(Contact contact) {

        if (!contacts.contains(contact)) {
            contacts.add(contact);
            return true;
        }
        return false;
    }

    /**
     * Updates a contact in the database
     * @param contact The contact
     * @return True if operation was successful
     */
    public static boolean updateContact(Contact contact) {
        if (contacts.contains(contact)) {
            contacts.set(contacts.indexOf(contact), contact);
            return true;
        }

        return false;
    }

    /**
     * Deletes a contact from the database
     * @param contact The contact
     * @return True if operation was successful
     */
    public static boolean deleteContact(Contact contact) {
        return contacts.remove(contact);
    }


}