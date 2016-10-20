abstract class Animal {
	String sound;
	String name;
	int legs;
	
	public void speak() {
		System.out.println("The "+this.getName()+" says: "+this.getSound());
	}
	
	public void walk() {
		System.out.println("The "+this.getName()+" walks on "+this.getLegs()+" legs");
	}
	
	public void setLegs(int legs) {
		this.legs = legs;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSound(String sound) {
		this.sound = sound;
	}
	
	public int getLegs() {
		return legs;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSound() {
		return sound;
	}
}


class Dog extends Animal {
	public Dog(){
		setName("Dog");
		setSound("Woof");
		setLegs(4);
	}
}

class Human extends Animal {
	public Human() {
		setName("Human");
		setSound("Let's take a selfie");
		setLegs(2);
	}
}

class Donkey extends Animal {
	public Donkey(){
		setName("Donkey");
		setSound("Hee haw");
		setLegs(4);
	}
}

class AnimalFactory {
	public Animal makeAnimal(int x){
		switch(x){
		case 1: return new Dog();
		case 2: return new Human();
		case 3: return new Donkey();
		default: return null;
		}
	}
}





public class FactoryPattern {
	public static void main(String[] args) {
		AnimalFactory animalFactory  = new AnimalFactory();
		
		for(int i=0;i<10;++i){
			Animal animal = animalFactory.makeAnimal(1 + (int)(Math.random() * 3));
			if(null!=animal){
				doSomething(animal);
			}
		}
	}
	
	public static void doSomething(Animal animal){
		animal.speak();
		animal.walk();
		System.out.println();
	}
}
