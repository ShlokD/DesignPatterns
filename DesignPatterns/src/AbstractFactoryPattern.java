interface VehicleEngine {
	public String toString();
}

interface VehicleTires {
	public String toString();
}


class BikeEngine implements VehicleEngine {
	public String toString() {
		return "55 mph";
	}
}


class CarEngine implements VehicleEngine {
	public String toString() {
		return "145 mph";
	}
}

class BikeTires implements VehicleTires {
	public String toString() {
		return "2 tires";
	}
}

class CarTires implements VehicleTires {
	public String toString(){
		return "4 tires";
	}
}


abstract class Vehicle {
	String name;
	VehicleEngine vEngine;
	VehicleTires vTires;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void driveVehicle() {
		System.out.println("The "+name+" is driving on the road at a speed of "+vEngine);
	}
	
	public void stopVehicle() {
		System.out.println("The "+vTires+" of the "+name+" screeched to a halt");
	}
	
	public String toString() {
		return "The vehicle of type "+name+" has a top speed of "+vEngine+" and runs on "+vTires;
	}
	
	public abstract void makeVehicle();
}

class Car extends Vehicle {
	VehicleFactory vehicleFactory;
	
	Car(VehicleFactory vehicleFactory){
		this.vehicleFactory = vehicleFactory;
	}
	
	public void makeVehicle(){
		setName("Car");
		vEngine = vehicleFactory.addEngine();
		vTires = vehicleFactory.addTires();
		System.out.println(this);
	}
}

class Bike extends Vehicle {
	
VehicleFactory vehicleFactory;
	
	Bike(VehicleFactory vehicleFactory){
		this.vehicleFactory = vehicleFactory;
	}
	
	public void makeVehicle(){
		setName("Bike");
		this.vEngine = vehicleFactory.addEngine();
		this.vTires =  vehicleFactory.addTires();
		System.out.println(this);
	}
	
}

interface VehicleFactory {
	public VehicleEngine addEngine();
	public VehicleTires addTires();
}

class CarFactory implements VehicleFactory {
	@Override
	public VehicleEngine addEngine() {
		// TODO Auto-generated method stub
		return new CarEngine();
	}
	
	@Override
	public VehicleTires addTires() {
		// TODO Auto-generated method stub
		return new CarTires();
	}
}


class BikeFactory implements VehicleFactory {
	@Override
	public VehicleEngine addEngine() {
		// TODO Auto-generated method stub
		return new BikeEngine();
	}
	
	@Override
	public VehicleTires addTires() {
		// TODO Auto-generated method stub
		return new BikeTires();
	}
}

abstract class VehicleManufacturer {
	public abstract Vehicle orderVehicle(String type);
	
	public Vehicle manufactureVehicle(String type) {
		Vehicle vehicle = orderVehicle(type);
		vehicle.makeVehicle();
		vehicle.driveVehicle();
		vehicle.stopVehicle();
		return vehicle;
	}
}

class CarAndBikeManufacturer extends VehicleManufacturer {
	public Vehicle orderVehicle(String type){
		Vehicle vehicle = null;
		if(type == "CAR"){
			VehicleFactory carFactory = new CarFactory();
			vehicle = new Car(carFactory);
		}
		else if(type == "BIKE"){
			VehicleFactory bikeFactory = new BikeFactory();
			vehicle = new Bike(bikeFactory);
		}
		return vehicle;
	}
}
public class AbstractFactoryPattern {

	public static void main(String[] args){
		CarAndBikeManufacturer carAndBikeManufacturer = new CarAndBikeManufacturer();
		Vehicle car = carAndBikeManufacturer.orderVehicle("CAR");
		if(null != car){
			car.makeVehicle();
			car.driveVehicle();
			car.stopVehicle();
		}
		
		System.out.println();
		Vehicle bike = carAndBikeManufacturer.orderVehicle("BIKE");
		if(null != bike){
			bike.makeVehicle();
			bike.driveVehicle();
			bike.stopVehicle();
		}
	}
}
