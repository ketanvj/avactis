package d_classDemo;

public class TestCylinderDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cylinder cy1 = new Cylinder(4,"Black",7);
		Cylinder cy2 = new Cylinder();
		
		System.out.println("Area of Cylinder1 is - " + cy1.calculateArea());
		System.out.println("Volume of Cylinder1 is - " + cy1.calculateVolume());
		System.out.println("Volume of Cylinder1 is - " + cy1);
		System.out.println("Volume of Cylinder1 is - " + cy1.toString());
	}

}
