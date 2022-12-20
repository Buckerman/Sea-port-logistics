import java.time.LocalDate;

public class Sender {
    public static int counter;

    private int id;
    private String name;
    private String surname;
    private String address;
    private String pesel;
    private int warningCounter = 0;

    public Sender(String name, String surname, String address, String pesel) {
        this.id = counter++;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.pesel = pesel;
    }

    public LocalDate birthDate() {
        int year;
        int month;
        int day;

        day = Integer.parseInt(pesel.substring(4, 6));
        month = Integer.parseInt(pesel.substring(2, 4));
        year = Integer.parseInt(pesel.substring(0, 2));

        //https://excelness.com/blog/data-urodzenia-z-pesel/
        if (month <= 12) year += 1900;
        else if (month <= 32) {
            year += 2000;
            month -= 20;
        } else if (month <= 52) {
            year += 2100;
            month -= 40;
        } else if (month <= 72) {
            year += 2200;
            month -= 60;
        } else if (month <= 92) {
            year += 1800;
            month -= 80;

        }
        return LocalDate.of(year, month, day);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = address;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public int getWarningCounter() {
        return warningCounter;
    }

    public void setWarningCounter(int warningCounter) {
        this.warningCounter = warningCounter;
    }

    @Override
    public String toString() {
        return
                id +
                        "|" + name +
                        "|" + surname +
                        "|" + address +
                        "|" + pesel +
                        "|" + this.birthDate();
    }

    public String print() {
        return
                "id=" + id +
                        "| imie='" + name + '\'' +
                        "| nazwisko='" + surname + '\'' +
                        "| adres='" + address + '\'' +
                        "| pesel='" + pesel + '\'' +
                        "| data urodzin='" + this.birthDate() + '\'';
    }
}

