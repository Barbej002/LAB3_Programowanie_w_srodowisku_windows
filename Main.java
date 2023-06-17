import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
    private String nr_indeksu;
    private String imie;
    private String nazwisko;
    private int rok_st;
    private List<Double> oceny;

    public Student(String nr_indeksu, String imie, String nazwisko, int rok_st) {
        this.nr_indeksu = nr_indeksu;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.rok_st = rok_st;
        this.oceny = new ArrayList<>();
    }

    public String getNr_indeksu() {
        return nr_indeksu;
    }

    public void setNr_indeksu(String nr_indeksu) {
        this.nr_indeksu = nr_indeksu;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public int getRok_st() {
        return rok_st;
    }

    public void setRok_st(int rok_st) {
        this.rok_st = rok_st;
    }

    public List<Double> getOceny() {
        return oceny;
    }

    public void setOceny(List<Double> oceny) {
        this.oceny = oceny;
    }

    public void wyswietl() {
        System.out.println("Nr indeksu: " + nr_indeksu);
        System.out.println("Imię: " + imie);
        System.out.println("Nazwisko: " + nazwisko);
        System.out.println("Rok studiów: " + rok_st);
        System.out.println("Oceny: " + oceny);
    }
}

class Uni {
    private final List<Double> listaDopuszczalnychOcen;
    final List<Student> listaStudentow;

    public Uni() {
        listaDopuszczalnychOcen = List.of(2.0, 3.0, 3.5, 4.0, 4.5, 5.0);
        listaStudentow = new ArrayList<>();
    }

    public void dodajStudenta() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj nr indeksu studenta: ");
        String nr_indeksu = scanner.nextLine();
        System.out.print("Podaj imię studenta: ");
        String imie = scanner.nextLine();
        System.out.print("Podaj nazwisko studenta: ");
        String nazwisko = scanner.nextLine();
        System.out.print("Podaj rok studiów studenta: ");
        int rok_st = scanner.nextInt();

        Student student = new Student(nr_indeksu, imie, nazwisko, rok_st);
        listaStudentow.add(student);

        while (true) {
            try {
                System.out.print("Podaj ocenę dla studenta (2, 3, 3.5, 4, 4.5, 5): ");
                double ocena = scanner.nextDouble();
                if (!listaDopuszczalnychOcen.contains(ocena)) {
                    throw new IllegalArgumentException("Niepoprawna ocena!");
                }
                student.getOceny().add(ocena);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

            System.out.print("Czy chcesz dodać kolejną ocenę dla studenta? (T/N): ");
            String czy_kolejna_ocena = scanner.next();
            if (!czy_kolejna_ocena.equalsIgnoreCase("T")) {
                break;
            }
        }
    }

    public void usunStudenta() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj nr indeksu studenta do usunięcia: ");
        String nr_indeksu = scanner.nextLine();
        for (Student student : listaStudentow) {
            if (student.getNr_indeksu().equals(nr_indeksu)) {
                listaStudentow.remove(student);
                System.out.println("Student o nr indeksu " + nr_indeksu + " został usunięty.");
                return;
            }
        }
        System.out.println("Nie znaleziono studenta o podanym nr indeksu.");
    }

    public double obliczSrednia(Student student) {
        List<Double> oceny = student.getOceny();
        if (oceny.isEmpty()) {
            return 0.0;
        }
        double suma = 0.0;
        for (Double ocena : oceny) {
            suma += ocena;
        }
        return suma / oceny.size();
    }

    public double obliczSredniaAll() {
        double suma = 0.0;
        int liczbaOcen = 0;
        for (Student student : listaStudentow) {
            List<Double> oceny = student.getOceny();
            for (Double ocena : oceny) {
                suma += ocena;
                liczbaOcen++;
            }
        }
        if (liczbaOcen == 0) {
            return 0.0;
        }
        return suma / liczbaOcen;
    }

    public void wyswietlStudenta(String nr_indeksu) {
        for (Student student : listaStudentow) {
            if (student.getNr_indeksu().equals(nr_indeksu)) {
                student.wyswietl();
                return;
            }
        }
        System.out.println("Nie znaleziono studenta o podanym nr indeksu.");
    }
}

public class Main {
    public static void main(String[] args) {
        Uni uni = new Uni();
        Scanner scanner = new Scanner(System.in);
        int wybor = 0;

        while (wybor != 6) {
            System.out.println("Menu:");
            System.out.println("1. Dodaj studenta");
            System.out.println("2. Usuń studenta");
            System.out.println("3. Oblicz średnią dla konkretnego studenta");
            System.out.println("4. Oblicz średnią dla wszystkich studentów");
            System.out.println("5. Wyświetl studenta");
            System.out.println("6. Zakończ");

            System.out.print("Wybierz opcję: ");
            wybor = scanner.nextInt();
            scanner.nextLine(); // Pobierz znak nowej linii

            switch (wybor) {
                case 1:
                    uni.dodajStudenta();
                    break;
                case 2:
                    uni.usunStudenta();
                    break;
                case 3:
                    System.out.print("Podaj nr indeksu studenta: ");
                    String nr_indeksu = scanner.nextLine();
                    for (Student student : uni.listaStudentow) {
                        if (student.getNr_indeksu().equals(nr_indeksu)) {
                            double srednia = uni.obliczSrednia(student);
                            System.out.println("Średnia ocena dla studenta o nr indeksu " + nr_indeksu + ": " + srednia);
                            break;
                        }
                    }
                    break;
                case 4:
                    double sredniaAll = uni.obliczSredniaAll();
                    System.out.println("Średnia ocena dla wszystkich studentów: " + sredniaAll);
                    break;
                case 5:
                    System.out.print("Podaj nr indeksu studenta: ");
                    String indeks = scanner.nextLine();
                    uni.wyswietlStudenta(indeks);
                    break;
                case 6:
                    System.out.println("Koniec programu.");
                    break;
                default:
                    System.out.println("Niepoprawny wybór.");
                    break;
            }
        }
    }
}
