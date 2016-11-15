package day8.training;

public class SuperClass {

	public static void showP() {
		System.out.println("Hello Super");
	}

	static class Parent {
		public void show11() {
			System.out.println("Hello Super");
		}
	}

	static class Child extends Parent {
//		;
		public static void show() {
			System.out.println("Hello Super");
		}
	}

}
