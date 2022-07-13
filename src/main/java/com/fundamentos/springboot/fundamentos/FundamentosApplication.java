package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.bean.MyBean;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.springboot.fundamentos.component.ComponentDependency;
import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	//Usando la libreria commons de Apache (Log) podemos mostrar detalladamente en la consola los errores
	//Incluso manejando try - catch
	private final Log logger = LogFactory.getLog(this.getClass());

	//INYECTAR DEPENDENCIA
	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;

	private MyBeanWithProperties myBeanWithProperties;

	private UserPojo userPojo;

	private UserRepository userRepository;

	//La anotacion @Qualifier, nos permite especificar què dependencia es la que estamos inyectando
	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo, UserRepository userRepository){

		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;



	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	//Este metodo se trae de la implementacion de la clase CommandLineRunner, y sirve para estipular què metodos se ejecutaran
	//cuando se corra el proyecto
	@Override
	public void run(String... args) throws Exception {

		//ejemplosAnteriores();

		saveUsersInDataBase();
		getInfoJpqlFromUser();

	}

	private void getInfoJpqlFromUser(){
	//En la interfaz UserRepository, se usa el metodo de fundByEmail para encontrar usuario registrado de acuerdo al email
		logger.info("************Usuario encontrado con el metodo findByUserEmail  " +
				userRepository.findByUserEmail("mfposso0911@gmail.com")
				.orElseThrow(() -> new RuntimeException ("******No se encontro el Usuario"))	);
		//En la interfaz UserRepository, se usa el metodo de fundAndSort para encontrar, mediante un parametro
		// similar, un usuario registrado de acuerdo
		userRepository.findAndSort("Ma", Sort.by("id").ascending())
				.stream(). forEach(user -> logger.info("*************Usuario con metodo Sort" + user));

		userRepository.findByName("Manuel").stream()
				.forEach(user -> logger.info("*******************Usuario con query method" + user));


		logger.info("************Usuario encontrado con findByNameAndEmail "+ userRepository.findByNameAndEmail("Magdalena","magdamar@gmail.com")
				.orElseThrow(() -> new RuntimeException("***************Usuario no encontrado con metodo findByNameAndEmail")));
	}


	//Metodo para persistir la informacion de la DB
	private void saveUsersInDataBase(){

		User user1 = new User("Manuel","mfposso0911@gmail.com", LocalDate.of(2021,9,11));
		User user2 = new User("Marleny","marleny@gmail.com", LocalDate.of(2021,8,24));
		User user3 = new User("Magdalena","magdamar@gmail.com", LocalDate.of(2021,9,27));
		User user4 = new User("Cristina","cristina@gmail.com", LocalDate.of(2021,12,28));
		User user5 = new User("Katherine","katemape@gmail.com", LocalDate.of(2021,11,21));
		User user6 = new User("Eliana","aurora@gmail.com", LocalDate.of(2021,3,8));

		List<User> list = Arrays.asList(user1,user2,user3,user4,user5,user6);

		list.forEach(userRepository::save);

	}

	public void ejemplosAnteriores(){

		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		System.out.println(myBeanWithProperties.function());
		System.out.println("******  " + userPojo.getEmail() + " - " + userPojo.getPassword());

		try {

			int value = 10/10;

			logger.debug("#####Mi valor es: " + value);

		}catch (Exception ex){

			logger.error("######Esto es un error al dividir por 0" + ex.getStackTrace());

		}
	}
}
