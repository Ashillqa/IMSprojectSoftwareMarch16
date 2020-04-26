# IMSprojectSoftwareMarch16
This project links a Google Cloud Platform MySQL instance to a Java application via JDBC, allowing us to manage the tables within the instance i.e. customers, items and orders. The project is a Maven project using the Eclipse IDE, with JUnit & Mockito for tests. Version control was done using GitHub allowing me to keep track of my code and commits. A CI Server was set up using Jenkins which created the build meaning that I could gain static analysis of my code from SonarQube and store the artifacts on Nexus via seperate build steps all in Jenkins.

## Setting up the environment
1) Clone the repository to the desired client
2) Open the project as a Maven project in your IDE such as Eclipse
3) Link it to your MySQL instance by replacing the hard-coded IP-address
4) You may now run it from your IDE as a Maven project and use it on your MySQL instance

## Prerequisites
In order to run the program from the command line, Maven must be used to create a jar containing the dependencies.
You also need a MySQL instance set up e.g. GCP. The IP addresses, which are hardcoded in the DBConfig class in the DB package need to be changed to match your instance.

## creating a development environment
You will need to install or create the following:
Maven, Java, Github(create), Jenkins, GCP(create instance), SonarQube.

1- Set up GCP MySQL instance. 
2- Whitelist your public IP address however this comes with its own risk * [ERD](https://github.com/Ashillqa/IMSprojectSoftwareMarch16/blob/master/PresentationDocs/RiskAssessment.docx)
3- Clone this repository to your client.
4- Set up a GitHub repository of your own
5- Open the project as a Maven Project
6- Change the hard-coded IP address in the src/main/java/com/qa/main/DB/DBConfig class.
7- Run the project from your IDE

Setting up the CI Pipeline:
1- As mentioned Jenkins should be downloaded as the build too, set up Jenkins and log in using the password provided in the setup
2- Next is to set up SonarQube in the following way:
```
create a VM instance on GCP
On the networks section modify firewall rules to configure the port tcp-9000 and have the source IP range as 0.0.0.0/0 (all) add the configured port to your network tags on your VM
```
Next is to Enter the following in the Ubuntu terminal/shell
```
curl https://gist.githubusercontent.com/christophperrins/760262e7308ceb8d9c51b4b984792a43/raw/00970ff2aa1857ab54f573f750c9f4f23d6c9578/installDocker.sh | sh
```
close the shell and re-open, entering the following up on the re-open:
```
curl https://gist.githubusercontent.com/christophperrins/fa5155359f8808a83fee7e34abb21769/raw/10f8cee4968fe76510b9e6a04cb6c679be92b466/installSonaqubeWithDocker.sh | sh
```
3- Now that you have SonarQube set up we go to Jenkins where step 1 already has us logged in and ready to build this project
4- In Jenkins click on create a new freestyle project and click configure whch will take you to the build set up
5- link it to your GitHub repository in the source management section and tick the following boxes underneath: 
```
poll scm box
Abort build when stuck
```
6- Begin the build for your project by firstly selecting 'Windows batch command' in the drop down. Now you can add the following as separate batch commands for clarity:
First is building your Maven project with the below command:
```
mvn clean package test
```
The next command is to upload it to SonarQube but replace with your SonarQube IP address and when you enter these yourself, seperate each instruction with a space not a new line:
```
mvn sonar:sonar
		--define sonar.host.url=http://<yourSonarqubeVirtualMachineIPAddress>:9000
		--define sonar.login.admin=admin
		--define sonar.password=admin
```
Next is to deploy an artifact to nexus again the new lines are just for clarity please do spaces:
```
mvn deploy:deploy-file
		--define generatePom=false 
		--define pomFile=pom.xml 
		--define url=<yourSonarqubeVirtualMachineIPAddress>:8081/repository/maven-snapshots 
		--define file=target/<artifactId>-<descriptorRef>.jar (the name of the fat jar in /target following build step)
		--define repositoryId=nexus
```
7- Save your configuration and click build now. This will now enable you to understand your code from a static analysis point of view on SonarQube. 

## Running the tests

SonarQube as mentioned is our static analysis tool. By seeing the suggestions and outcome of your project on SonarQube you can now assess what bugs to fix, which peices of code to improve and which sections of your code can be tested to provide better coverage.
The tests themselves were both JUnit tests and Mockito tests which as the name suggests will mock some of the classes and methods you wish to test the functionality of.
I attempted to test the Customer, items and order classes, the method classes for the respective tables and the actual Database classes which executed the necessary SQL statements for the respective tables.

### JUnit tests
This method executes the code under test. You use an assert method, provided by JUnit or another assert framework, to check an expected result versus the actual result. One of the reasons we use unit tests are that the classes you are testing do not require a server therefore this saves testing time and the Assert methods allow someone not related to the project to understand what is being looked for in the test and if functionality has been achieved for that particular test.
Example:
```
@Test
		public void createCustomerWithId() {
			assertEquals(1, customer.getId(), 0);
			assertEquals("NC", customer.getFirstName());
```
### Mockito testing
Mockito is a JAVA-based library that is another form of effective unit testing of JAVA applications. Mockito is used to mock interfaces so that a dummy functionality can be added to a mock interface that can be used in unit testing. This includes @Spy which when called, calls the actual method of the object you are mocking.
Example:
```
@Test
	public void CustomerUpdate() throws SQLException {
		dbc.updateCust(customer);
		Mockito.verify(dbc, Mockito.times(1)).updateCust(customer);
	}
```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [Jenkins](https://jenkins.io/) - CI Pipeline build tool
* [Eclipse](https://www.eclipse.org/downloads/) - IDE
* [SonarQube](https://www.sonarqube.org/) - Static analysis
* [Nexus](https://www.sonatype.com/product-nexus-repository/) - Artifact repository
* [Git](https://git-scm.com/) - version control system (vcs)
* [GitHub](https://github.com/) - Host of vcs
* [Trello](https://trello.com/) - Kanban board/user stories
* [MySQL](https://www.mysql.com/) - Database language and to check syntax throughout project
* [GCP](https://cloud.google.com/) - Database instance and the Virtual machine instance for setting up SonarQube 
* [JUnit](https://junit.org/junit5/) - Testing (see pom.xml)
* [Mockito](https://site.mockito.org/) - Testing (see pom.xml)

## Other

* [ERD](https://www.lucidchart.com) - Entity Relationship Diagram

## Authors

* **Ashill Pathak** - *Main* - [Ashill](https://github.com/Ashillqa)
* **Christopher Perrins** - *SonarQube shell commands* - [Chris](https://github.com/christophperrins)
* **Jordan Harrison** - *Databse Configuration* - [Jordan](https://github.com/JHarry444)









