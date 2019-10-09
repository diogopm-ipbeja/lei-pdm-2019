package pt.ipbeja.aula04.data;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * Dummy Contact Database
 */
@SuppressWarnings({"WeakerAccess", "unused"})
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
            "Edouard"
    };


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
            "Ballard"
    };


    private static final int INITIAL_CONTACTS = 50;
    private static final List<Contact> contacts = new ArrayList<>();

    static {
        contacts.addAll(createContacts());
    }

    private static List<Contact> createContacts() {
        ArrayList<Contact> contacts = new ArrayList<>();

        for (int i = 0; i < INITIAL_CONTACTS; i++) {
            Contact contact = createRandomContact();
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


    public static Contact createRandomContact() {
        long id = (long) (Math.random() * 10000 + 1);
        String firstName = randomFirstName();
        String lastName = randomLastName();
        String email = String.format(Locale.getDefault(), "%s.%s.%d@dummy.pt", firstName.toLowerCase(), lastName.toLowerCase(), id);  // "first.second.12345@dummy.pt"
        return new Contact(id, firstName, lastName, email);
    }


    // ---------------------------------------------------------------------------------------//





    /**
     * Get all contacts in Database
     *
     * @return List of all contacts
     */
    public static List<Contact> getContacts() {
        return contacts;
    }


    /**
     * Find a Contact by its ID
     *
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
     *
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
     *
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
     *
     * @param contact The contact
     * @return True if operation was successful
     */
    public static boolean deleteContact(Contact contact) {
        return contacts.remove(contact);
    }


}