package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Arrays;

@XmlRootElement(name = "Characteristic")
@XmlAccessorType(XmlAccessType.FIELD)
public class Characteristic implements Serializable {

    @XmlAttribute
    private boolean bool;

    @XmlAttribute
    private int i;

    @XmlElementWrapper(name = "chars")
    @XmlElement(name = "char")
    private char[] chars;
    private Contact contact;

    @XmlAttribute
    private String string;

    public Characteristic() {
    }

    public Characteristic(boolean bool, int i, char[] chars, Contact contact, String string) {
        this.bool = bool;
        this.i = i;
        this.chars = chars;
        this.contact = contact;
        this.string = string;
    }

    @Override
    public String toString() {
        return "Characteristic{"
                + "bool=" + bool
                + ", i=" + i
                + ", chars=" + Arrays.toString(chars)
                + ", contact=" + contact
                + ", string='" + string + '\''
                + '}';
    }

    public static void main(String[] args) {
        final Characteristic character = new Characteristic(
                true,
                473,
                new char[] {'3', '2', '1'},
                new Contact(5673, "+7-999-888-77-66"),
                "line");
        System.out.println(character);

        final Gson gson = new GsonBuilder().create();
        String jsonCharacter = gson.toJson(character);

        final Characteristic jsonCh = gson.fromJson(jsonCharacter, Characteristic.class);
        System.out.println(jsonCh);

    }
}
