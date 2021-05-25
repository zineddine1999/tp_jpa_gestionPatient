package ma.emsi.tpjpa;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import ma.emsi.tpjpa.entities.Patient;
import ma.emsi.tpjpa.repositories.PatientRepository;

@SpringBootApplication
public class TpJpaApplication implements CommandLineRunner{
	@Autowired
	private PatientRepository patientRepository;

	public static void main(String[] args) {
		SpringApplication.run(TpJpaApplication.class, args);
	}
	@Override
	public void run(String...args) throws Exception{
		patientRepository.save(new Patient(null,"hassan",new Date(),2300,false));
		patientRepository.save(new Patient(null,"Farah",new Date(),2400,false));
		patientRepository.save(new Patient(null,"Imane",new Date(),2500,false));
		patientRepository.save(new Patient(null,"Aymane",new Date(),2600,false));
		patientRepository.save(new Patient(null,"Yassine",new Date(),2300,true));
		System.out.println("********************");
		patientRepository.findAll().forEach(p->{
			System.out.println(p.toString());
		});
		System.out.println("************************");
		Patient patient =patientRepository.findById(4L).get();
		System.out.println(patient.getName());
		System.out.println("***********************");
		Page<Patient> patients=patientRepository.findByNameContains("a", PageRequest.of(0, 2));
		patients.getContent().forEach(p->{
			System.out.println(p.toString());
		});
		System.out.println("***********************");
		List<Patient> patients2=patientRepository.findByMalade(true);
		patients2.forEach(p->{
			System.out.println(p.toString());
		});
		System.out.println("***********************");
		List<Patient> patients3=patientRepository.findByNameContainsAndMalade("a",true);
		patients3.forEach(p->{
			System.out.println(p.toString());
		});
		// patientRepository.deleteById(5L);
		System.out.println("***********************");
		Page<Patient> pagePatients=patientRepository.findAll(PageRequest.of(1,2));
		System.out.println("nbr page :"+pagePatients.getTotalPages());
		List<Patient> listPatient=pagePatients.getContent();
		listPatient.forEach(p->{
			System.out.println(p.toString());
		});
	}

}
