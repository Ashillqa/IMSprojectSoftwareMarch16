package main;



public class Customer {
		
		private int id;
		private String firstName;
		private String surname;
		
		public Customer(String firstName, String surname) {
			this.firstName = firstName;
			this.surname=surname;
		}
		
		public Customer( String firstName, String surname,int id) {
			this.id=id;
			this.firstName = firstName;
			this.surname=surname;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getSurname() {
			return surname;
		}

		public void setSurname(String surname) {
			this.surname = surname;
		}

		
			
		

		
		@Override
		public String toString() {
			
			return "id:" + id + " first name:" + firstName + " surname:" + surname;
		}
	}



