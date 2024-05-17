package model;

/**
 * Represents a client in the system.
 */
public class Client {
    private int id;
    private String name;
    private String email;
    private int age;

    /**
     * Constructs a new client with default values.
     */
    public Client() {}

    /**
     * Constructs a new client with the specified name, email, and age.
     *
     * @param name  The name of the client.
     * @param email The email address of the client.
     * @param age   The age of the client.
     */
    public Client(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    /**
     * Constructs a new client with the specified id, name, email, and age.
     *
     * @param id    The unique identifier of the client.
     * @param name  The name of the client.
     * @param email The email address of the client.
     * @param age   The age of the client.
     */
    public Client(int id, String name, String email, int age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    /**
     * Retrieves the unique identifier of the client.
     *
     * @return The client's id.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the client.
     *
     * @param id The client's id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the client.
     *
     * @return The client's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the client.
     *
     * @param name The client's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the email address of the client.
     *
     * @return The client's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the client.
     *
     * @param email The client's email address.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the age of the client.
     *
     * @return The client's age.
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the client.
     *
     * @param age The client's age.
     */
    public void setAge(int age) {
        this.age = age;
    }
}
