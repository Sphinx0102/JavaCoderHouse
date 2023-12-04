import java.util.*;

public class Main {
    public static class Person {
        private String name;
        private String lastname;

        public String getName(){
            return name;
        }
        public void setName(String name){
            this.name = name;
        }
        public String getLastname(){
            return lastname;
        }
        public void setLastname(String lastname){
            this.lastname = lastname;
        }

    }


    public static void main(String[] args) {
        List<Person> peopleList = new ArrayList<>();
        for(int i = 1; i <= 5; i++){
            Person person = new Person();
            person.setName(namesProvider(i));
            person.setLastname(lastNameProvider(i));
            peopleList.add(person);
        }
        //ordenar por nombre
        Collections.sort(peopleList, Comparator.comparing(Person::getName));
        System.out.println("Personas Ordenadas por Nombre:");
        for(Person person: peopleList){
            System.out.println(person.getName() + " " + person.getLastname());
        }
        //ordenar por apellido
        Collections.sort(peopleList, Comparator.comparing(Person::getLastname));
        System.out.println("Personas Ordenadas por Apellido:");
        for (Person person : peopleList) {
            System.out.println(person.getName() + " " + person.getLastname());
        }
        //ordenar inversamente por apellido
        Collections.sort(peopleList, Comparator.comparing(Person::getLastname).reversed());
        System.out.println("Personas Ordenadas Inversamente por Apellido:");
        for (Person person : peopleList) {
            System.out.println(person.getName() + " " + person.getLastname());
        }
    }
    private static String namesProvider(int index){
        String[] names = {"Juan", "Ese", "Franco", "Leandro", "Alejandro"};
        return names[index-1];
    }
    private static String lastNameProvider(int index){
        String[] lastNames= {"Araya", "Brancateli", "Lopez", "Amaya", "Molina"};
        return lastNames[index-1];
    }
}

