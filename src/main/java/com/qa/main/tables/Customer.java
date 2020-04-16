package com.qa.main.tables;



public class Customer {
		
		private int id;
		private String firstName;
		private String surname;
		private String number;
		
		public Customer(int id,String firstName, String surname,String number) {
			this.id=id;
			this.firstName = firstName;
			this.surname=surname;
			this.number=number;
		}
		
		public Customer(int id) {
			this.id=id;
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
			
			return "id: " + id + " first name: " + firstName + " surname: " + surname;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((number == null) ? 0 : number.hashCode());
			result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
			result = prime * result + id;
			result = prime * result + ((surname == null) ? 0 : surname.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Customer other = (Customer) obj;
			if (number == null) {
				if (other.number != null)
					return false;
			} else if (!number.equals(other.number))
				return false;
			if (firstName == null) {
				if (other.firstName != null)
					return false;
			} else if (!firstName.equals(other.firstName))
				return false;
			if (id != other.id)
				return false;
			if (surname == null) {
				if (other.surname != null)
					return false;
			} else if (!surname.equals(other.surname))
				return false;
			return true;
		}

		public String getNumber() {
			return number;
		}

		public void setNumber(String number) {
			this.number = number;
		}

		
	}



