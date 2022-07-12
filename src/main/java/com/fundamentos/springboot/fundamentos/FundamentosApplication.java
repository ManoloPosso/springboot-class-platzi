package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.bean.MyBean;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.springboot.fundamentos.component.ComponentDependency;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	//Usando la libreria commons de Apache (Log) podemos mostrar detalladamente en la consola los errores
	//Incluso manejando try - catch
	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);

	//INYECTAR DEPENDENCIA
	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;

	private MyBeanWithProperties myBeanWithProperties;

	private UserPojo userPojo;

	//La anotacion @Qualifier, nos permite especificar què dependencia es la que estamos inyectando
	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo){

		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;


	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	//Este metodo se trae de la implementacion de la clase CommandLineRunner, y sirve para estipular què metodos se ejecutaran
	//cuando se corra el proyecto
	@Override
	public void run(String... args) throws Exception {
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		System.out.println(myBeanWithProperties.function());
		System.out.println("******  " + userPojo.getEmail() + " - " + userPojo.getPassword());

		try {

			int value = 10/10;

			LOGGER.debug("#####Mi valor es: " + value);

		}catch (Exception ex){

			LOGGER.error("######Esto es un error al dividir por 0" + ex.getStackTrace());

		}

		;

	}
}
