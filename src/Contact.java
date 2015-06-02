
public class Contact
{


    String name;
    String phone;
    String email;

    public Contact() {}

    public Contact(String newName, String newPhone, String newEmail)
    {
        name = newName;
        phone = newPhone;
        email = newEmail;
    }

    public void print ()
    {
        System.out.println(name + " " + phone + " " + email);
    }

}