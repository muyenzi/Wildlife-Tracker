public class Animal {

    private int id;
    private String name;

    public Animal(int id , String name){
        this.id=id;
        this.name=name;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object otherAnimal){
        if (!(otherAnimal instanceof Animal)) {
            return false;
        } else {
            Animal newAnimal = (Animal) otherAnimal;
            return this.getId().equals(newAnimal.getId()) &&
                    this.getName().equals(newAnimal.getName());
        }
    }
}
