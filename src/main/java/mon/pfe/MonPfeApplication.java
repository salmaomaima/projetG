package mon.pfe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import mon.pfe.entity.Employer;

@SpringBootApplication
public class MonPfeApplication {

	@SuppressWarnings({ "unused", "deprecation" })
	public static void main(String[] args) {
		SpringApplication.run(MonPfeApplication.class, args);
		
		
	//Long i=(long) 1;
	//BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
	//String h=encoder.encode("hedi");
	//	String h1=encoder.encode("houissa");
	//String h2=encoder.encode("hedi");
	
	//dao.save(new Employer(h,"mot_de_passe","adresse","11253","grade","nom","prenom",i,1,1));
	//dao.save(new Employer(h1,"mot_de_passe","adresse","11253","grade","nom","prenom",i,1,1));
	//dao.save(new Employer(h2,"mot_de_passe","adresse","11253","grade","nom","prenom",i,1,1));

	//Md5PasswordEncoder encoderr = new Md5PasswordEncoder();
//	String h="hediabirhouissa@lai3bhediisima";
	//System.out.println(encoder.encodePassword(h,16));
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat month = new SimpleDateFormat("MM");
		
		String dd="2016-05-19";
		String df="2016-05-26";
		Date dated=null;
		Date datef=null;
		
		
		try {
			dated=formatter.parse(dd);
			datef=formatter.parse(df);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date date = null;
		try {
			date = formatter.parse(formatter.format(new Date()));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		long mil=1000*60*60;
		long delta =datef.getTime()-date.getTime();
		System.out.println("deffirece : "+delta/mil);
		
		
		System.out.println("date debeut = "+formatter.format(dated));
		Date d=dated;
		d.setDate(dated.getDate()+30);
		String s=formatter.format(dated);
		try {
			dated=formatter.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("datef "+date);
			System.out.println("month : "+date.getMonth());
			System.out.println("dat "+date.getDate());
			System.out.println("day "+date.getDay());
			System.out.println("year "+date.getYear());
			System.out.println("hours "+32/31);
	
	}
}