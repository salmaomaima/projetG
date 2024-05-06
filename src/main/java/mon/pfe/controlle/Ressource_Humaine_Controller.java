package mon.pfe.controlle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import mon.pfe.entity.Archive;
import mon.pfe.entity.Demande;
import mon.pfe.entity.Departement;
import mon.pfe.entity.Employer;
import mon.pfe.entity.Notification_Reprise;
import mon.pfe.entity.Roles;
import mon.pfe.entity.Solde;
import mon.pfe.entity.TypeCongee;
import mon.pfe.entity.Validation;
import mon.pfe.repository.ArchiveRepository;
import mon.pfe.repository.DemandeRepository;
import mon.pfe.repository.DepartementRepository;
import mon.pfe.repository.EmployerRepository;
import mon.pfe.repository.NotificationRepriseRepository;
import mon.pfe.repository.RolesRepository;
import mon.pfe.repository.SoldeRepository;
import mon.pfe.repository.TypeCongeeRepository;
import mon.pfe.repository.ValidationRepository;

@Controller
@Secured({"ROLE_RH","ROLE_PR"})
public class Ressource_Humaine_Controller {

	@Autowired
	public EmployerRepository employer;	
	@Autowired
	public DepartementRepository departement;
	@Autowired
	public TypeCongeeRepository typecongee;
	@Autowired
	public DemandeRepository demande;
	@Autowired
	public LoginController log;
	@Autowired
	public SoldeRepository solde;
	@Autowired
	public RolesRepository role;
	@Autowired
	public NotificationRepriseRepository notification;
	@Autowired
	public ValidationRepository validation ;
	@Autowired
	public ArchiveRepository archive;
	
	@Secured({"ROLE_RH", "ROLE_PR"})
	@RequestMapping(value="/ajouter_employer" , method=RequestMethod.GET)
		public String ajouter_employer(String nom_utilisateur, String mot_de_passe, String adresse, String telephone, String grade, String nom, String prenom,
				Long idresponsable,int id_departement,int id_role, Model model, HttpServletRequest httpServletRequest){
//		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
//		String pswd="hediabir"+mot_de_passe+"@lai3b"+nom_utilisateur+"isima";
//		encoder.encodePassword(pswd,16);
		employer.save(new Employer(nom_utilisateur,mot_de_passe,adresse,telephone,grade,nom,prenom,idresponsable,id_departement,id_role));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		Date dt=new Date();
		String s=formatter.format(dt);
		int annee= Integer.parseInt(s);
			
			solde.save(new Solde(0,60,6,28,(annee-1),employer.get_id(nom_utilisateur),dt));
			solde.save(new Solde(0,60,6,28,annee,employer.get_id(nom_utilisateur),dt));
		
			String username=log.getLogedUser(httpServletRequest);
			model.addAttribute("username",username);
			List<Employer> list = employer.findAll();
		model.addAttribute("emp",list);
		List<Employer> ep=employer.find_by_nom_utilisateur(username);
		Employer emp=ep.get(0);
		List<Validation> noti=validation.find_by_id_rd(emp.getId_employer(),'p');
		model.addAttribute("notif",noti);
		List<Validation>valid=validation.find_by_id_pr(emp.getId_employer(),'p');
		model.addAttribute("valid",valid);
		List<Validation> arch= validation.find_to_archive('p'); 
		model.addAttribute("arch",arch);
			return "list_employer";
			}	
	@Secured({"ROLE_RH", "ROLE_PR"})
	@RequestMapping(value="/mise_a_jour_employer" , method=RequestMethod.GET)
	public String mise_a_jour_employer(Long id_employer, String nom_utilisateur, String mot_de_passe, String adresse, String telephone, String grade, String nom, String prenom,
			Long idresponsable,int id_departement,int id_role, Model model, HttpServletRequest httpServletRequest){
//	Md5PasswordEncoder encoder = new Md5PasswordEncoder();
//	String pswd="hediabir"+mot_de_passe+"@lai3b"+nom_utilisateur+"isima";
	//encoder.encodePassword(pswd,16)
	employer.save(new Employer(id_employer, nom_utilisateur, mot_de_passe, adresse, telephone, grade, nom, prenom, idresponsable, id_departement, id_role));
		String username=log.getLogedUser(httpServletRequest);
		model.addAttribute("username",username);
		List<Employer> list = employer.findAll();
		model.addAttribute("emp",list);
		List<Employer> ep=employer.find_by_nom_utilisateur(username);
		Employer emp=ep.get(0);
		List<Validation> noti=validation.find_by_id_rd(emp.getId_employer(),'p');
		model.addAttribute("notif",noti);
		List<Validation>valid=validation.find_by_id_pr(emp.getId_employer(),'p');
		model.addAttribute("valid",valid);
		List<Validation> arch= validation.find_to_archive('p'); 
		model.addAttribute("arch",arch);
		return "list_employer";
		}
	@Secured({"ROLE_RH", "ROLE_PR"})
	@RequestMapping(value="/modifier_ressource_humaine", method=RequestMethod.GET)
	public String modifier_ressource_humaine(Long id_employer, String nom_utilisateur, String mot_de_passe, String adresse, String telephone, String grade, String nom, String prenom,
			Long idresponsable,int id_departement,int id_role, Model model, HttpServletRequest httpServletRequest){

	employer.save(new Employer(id_employer, nom_utilisateur, mot_de_passe, adresse, telephone, grade, nom, prenom, idresponsable, id_departement, id_role));
		String username=log.getLogedUser(httpServletRequest);
		model.addAttribute("username",username);
		List<Employer> list = employer.findAll();
		model.addAttribute("emp",list);
		List<Employer> ep=employer.find_by_nom_utilisateur(username);
		Employer emp=ep.get(0);
		List<Validation> noti=validation.find_by_id_rd(emp.getId_employer(),'p');
		model.addAttribute("notif",noti);
		List<Validation>valid=validation.find_by_id_pr(emp.getId_employer(),'p');
		model.addAttribute("valid",valid);
		List<Validation> arch= validation.find_to_archive('p'); 
		model.addAttribute("arch",arch);
		return "modif_profil_rh";
	}
	@Secured({"ROLE_RH", "ROLE_PR"})
	@RequestMapping(value="/modifier_mot_de_passe_rh")
	public String profil_ressource(Model model, HttpServletRequest httpServletRequest,String nouveaux){
		String username=log.getLogedUser(httpServletRequest);
		model.addAttribute("username",username);
		List<Employer> ep=employer.find_by_nom_utilisateur(username);
		Employer emp=ep.get(0);
		employer.save(new Employer(emp.getId_employer(), emp.getNom_utilisateur(), nouveaux, emp.getAdresse(), emp.getTelephone(), emp.getGrade(),
				emp.getNom(), emp.getPrenom(), emp.getIdresponsable(), emp.getId_departement(), emp.getId_role()));
		List<Validation> noti=validation.find_by_id_rd(emp.getId_employer(),'p');
		model.addAttribute("notif",noti);
		List<Validation>valid=validation.find_by_id_pr(emp.getId_employer(),'p');
		model.addAttribute("valid",valid);
		List<Validation> arch= validation.find_to_archive('p'); 
		model.addAttribute("arch",arch);
	 return "modif_profil_rh";
 }
	@Secured({"ROLE_RH", "ROLE_PR"})
	@RequestMapping(value="/ajouter_departement" , method=RequestMethod.GET)
	public String ajouter_departement(Long id_chef, String nom_departement, Model model, HttpServletRequest httpServletRequest){
		departement.save(new Departement(id_chef,nom_departement));
		String username=log.getLogedUser(httpServletRequest);
		model.addAttribute("username",username);
		List<Departement> list = departement.findAll();
	model.addAttribute("dep",list);
	List<Employer> ep=employer.find_by_nom_utilisateur(username);
	Employer emp=ep.get(0);
	List<Validation> noti=validation.find_by_id_rd(emp.getId_employer(),'p');
	model.addAttribute("notif",noti);
	List<Validation>valid=validation.find_by_id_pr(emp.getId_employer(),'p');
	model.addAttribute("valid",valid);
	List<Validation> arch= validation.find_to_archive('p'); 
	model.addAttribute("arch",arch);
		return "list_departement";
	}
	@Secured({"ROLE_RH", "ROLE_PR"})
	@RequestMapping(value="/modifier_departement" , method=RequestMethod.GET)
	public String modifier_departement(int id_departement, Long id_chef, String nom_departement, Model model, HttpServletRequest httpServletRequest){
		departement.save(new Departement(id_departement,id_chef,nom_departement));
		String username=log.getLogedUser(httpServletRequest);
		model.addAttribute("username",username);
		List<Departement> list = departement.findAll();
	model.addAttribute("dep",list);
	List<Employer> list1 = employer.findAll();
	model.addAttribute("emp",list1);
	List<Employer> ep=employer.find_by_nom_utilisateur(username);
	Employer emp=ep.get(0);
	List<Validation> noti=validation.find_by_id_rd(emp.getId_employer(),'p');
	model.addAttribute("notif",noti);
	List<Validation>valid=validation.find_by_id_pr(emp.getId_employer(),'p');
	model.addAttribute("valid",valid);
	List<Validation> arch= validation.find_to_archive('p'); 
	model.addAttribute("arch",arch);
	return "list_departement";
	}
	@Secured({"ROLE_RH", "ROLE_PR"})
	@RequestMapping (value="/ajouter_type_congee" , method=RequestMethod.GET)
	public String ajouter_type_congee( String nom_type, int nmbre_jours, Model model, HttpServletRequest httpServletRequest, String code_type){
		typecongee.save(new TypeCongee(nom_type,nmbre_jours,code_type));
		String username=log.getLogedUser(httpServletRequest);
		model.addAttribute("username",username);
			List<TypeCongee> list = typecongee.findAll();
		model.addAttribute("type",list);
		List<Employer> list1 = employer.findAll();
		model.addAttribute("emp",list1);
		List<Employer> ep=employer.find_by_nom_utilisateur(username);
		Employer emp=ep.get(0);
		List<Validation> noti=validation.find_by_id_rd(emp.getId_employer(),'p');
		model.addAttribute("notif",noti);
		List<Validation>valid=validation.find_by_id_pr(emp.getId_employer(),'p');
		model.addAttribute("valid",valid);
		List<Validation> arch= validation.find_to_archive('p'); 
		model.addAttribute("arch",arch);
		return "list_type_congee";
	}
	@Secured({"ROLE_RH", "ROLE_PR"})
	@RequestMapping (value="/modifier_type_congee" , method=RequestMethod.GET)
	public String modifier_type_congee(int id_type, String nom_type, int nmbre_jours, Model model, HttpServletRequest httpServletRequest, String code_type){
		typecongee.save(new TypeCongee(id_type,nom_type,nmbre_jours,code_type));
		String username=log.getLogedUser(httpServletRequest);
		model.addAttribute("username",username);
			List<TypeCongee> list = typecongee.findAll();
		model.addAttribute("type",list);
		List<Employer> list1 = employer.findAll();
		model.addAttribute("emp",list1);
		List<Employer> ep=employer.find_by_nom_utilisateur(username);
		Employer emp=ep.get(0);
		
		List<Validation> noti=validation.find_by_id_rd(emp.getId_employer(),'p');
		model.addAttribute("notif",noti);
		List<Validation>valid=validation.find_by_id_pr(emp.getId_employer(),'p');
		model.addAttribute("valid",valid);
		List<Validation> arch= validation.find_to_archive('p'); 
		model.addAttribute("arch",arch);
		
		return "list_type_congee";
	}
	@Secured({"ROLE_RH", "ROLE_PR"})
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/remplir_demande_r" , method=RequestMethod.GET)
	public String remplir_demande(String date_envoit, String date_debut, int periode, Long id_employer,
			int id_type, Model model, HttpServletRequest httpServletRequest){
		
int d=demande.count_demande_en_cours(id_employer, 'p');
		
		String username=log.getLogedUser(httpServletRequest);
		model.addAttribute("username",username);
		List<Employer> ep=employer.find_by_nom_utilisateur(username);
		Employer emp=ep.get(0);
		List<Validation> noti=validation.find_by_id_rd(emp.getId_employer(),'p');
		model.addAttribute("notif",noti);
		List<Validation>valid=validation.find_by_id_pr(emp.getId_employer(),'p');
		model.addAttribute("valid",valid);
		
		TypeCongee ty=typecongee.findOne(id_type);
		
		if(d<1){
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy");
			int annee= Integer.parseInt(format.format(new Date()));
			
			Solde s1= solde.find_by_id_employer_anne(emp.getId_employer(), annee);
			Solde s2= solde.find_by_id_employer_anne(emp.getId_employer(), (annee-1));
			long i = s1.getTotal_solde()+s2.getTotal_solde();
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			
		
		
			
			Date de = null;
			Date dd = null;
			try {
				 de = formatter.parse(date_envoit);
				 dd=formatter.parse(date_debut);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if((i<periode)&&(ty.getCode_type().equals("1111"))){
				
				String chaine ="votre solde annuelle inssfisonte vous ne pouvez pas passer cette demande ";
				model.addAttribute("chaine",chaine);
			}
			
			else {
				
				
				
				if(periode>ty.getNmbre_jours())
				{
					String chaine ="periode de conger demander depasse le max de cette type de conge ";
					model.addAttribute("chaine",chaine);
				}
				else{
			
				Date df=dd;
				 df.setDate(df.getDate()+periode);	
					demande.save(new Demande(de,dd,df,periode,id_employer,id_type,'p'));
					
					Demande dem = demande.demande_en_cours(id_employer, 'p');
					
//					Date date_validation_rd, Long id_demande, Long id_employer, Long id_resposable, char action_rd
					
					try {
						validation.save(new Validation(formatter.parse(formatter.format(new Date())),dem.getId_demande(),id_employer,emp.getIdresponsable(),'p'));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					String chaine ="votre demande est en cours d'execution ...";
					model.addAttribute("chaine",chaine);
				}
			}
			
			
		}
		
		else {
			String chaine ="vous avez une demande en cours d'execution vous pouvez pas affecter autre";
			model.addAttribute("chaine",chaine);
		}
		
		
		List<Validation> arch= validation.find_to_archive('p'); 
		model.addAttribute("arch",arch);
		return "etat_demande_rh";
	}
	

	@Secured({"ROLE_RH", "ROLE_PR"})
	@RequestMapping(value="/annuler_demande_rh")
	public String annuler_demande_rh(HttpServletRequest httpServletRequest, Model model,Long id_demande){
		String username=log.getLogedUser(httpServletRequest);
	model.addAttribute("username",username);
	List<Employer> ep=employer.find_by_nom_utilisateur(username);
	Employer emp=ep.get(0);
	
	Demande d=demande.findOne(id_demande);
	demande.save(new Demande(d.getId_demande(),d.getDate_envoit(),d.getDate_debut(),d.getDate_fin(),d.getPeriode(),d.getId_employer(),d.getId_type(),'a'));
	
	TypeCongee type= typecongee.findOne(d.getId_type());
	
	SimpleDateFormat year = new SimpleDateFormat("yyyy");
	int annee= Integer.parseInt(year.format(new Date()));
	Solde s1= solde.find_by_id_employer_anne(emp.getId_employer(), annee);
	
	if(type.getCode_type()=="011100"){
		solde.save(new Solde(s1.getId_Solde(), s1.getTotal_solde(), s1.getSolde_materniter(), s1.getSolde_specifique(), d.getPeriode()+s1.getSolde_maladie() , s1.getAnnee_solde(), s1.getId_employer(), s1.getMise_a_jours()));
	}
	
	if(type.getCode_type()=="000110"){
		solde.save(new Solde(s1.getId_Solde(), s1.getTotal_solde(), s1.getSolde_materniter(), s1.getSolde_specifique()+d.getPeriode(), s1.getSolde_maladie() , s1.getAnnee_solde(), s1.getId_employer(), s1.getMise_a_jours()));
	}
	
	if(type.getCode_type()=="111100"){
		solde.save(new Solde(s1.getId_Solde(), s1.getTotal_solde(), s1.getSolde_materniter()+d.getPeriode(), s1.getSolde_specifique(), s1.getSolde_maladie() , s1.getAnnee_solde(), s1.getId_employer(), s1.getMise_a_jours()));
	}
	
	if(type.getCode_type()=="011110"){
		
		Solde s2= solde.find_by_id_employer_anne(emp.getId_employer(), (annee-1));
			if(s2.getTotal_solde()==0){
				solde.save(new Solde(s1.getId_Solde(), s1.getTotal_solde()+d.getPeriode(), s1.getSolde_materniter(), s1.getSolde_specifique(), s1.getSolde_maladie() , s1.getAnnee_solde(), s1.getId_employer(), s1.getMise_a_jours()));
			}
			else{
				solde.save(new Solde(s2.getId_Solde(), s2.getTotal_solde()+d.getPeriode(), s2.getSolde_materniter(), s2.getSolde_specifique(), s2.getSolde_maladie() , s2.getAnnee_solde(), s2.getId_employer(), s2.getMise_a_jours()));
			}
	}
	
	List<Demande>dem_to_annuler=demande.find_to_annule(emp.getId_employer(),new Date(),'o');		
	model.addAttribute("dem_to_annuler",dem_to_annuler);
	
	List<Demande>dem_cant_annuler=demande.cant_annul(emp.getId_employer(),new Date(),'a');		
	model.addAttribute("dem_cant_annuler",dem_cant_annuler);

	List<Validation> noti=validation.find_by_id_rd(emp.getId_employer(),'p');
	model.addAttribute("notif",noti);
	List<Validation>valid=validation.find_by_id_pr(emp.getId_employer(),'p');
	model.addAttribute("valid",valid);
	List<Validation> arch= validation.find_to_archive('p'); 
	model.addAttribute("arch",arch);
	 return "list_demande_rh";
 }
		
	@Secured({"ROLE_RH", "ROLE_PR"})
	  @RequestMapping(value="/del_mod_emp", method=RequestMethod.GET)
	  public String del_mod_emp(@RequestParam(required=true, value="action")String bt ,Long id_employer,Model model, HttpServletRequest httpServletRequest ) {
		 
		  String username=log.getLogedUser(httpServletRequest);
		  List<Employer> epp=employer.find_by_nom_utilisateur(username);
			Employer emp=epp.get(0);
			
		  List<Validation> arch= validation.find_to_archive('p');
		  List<Validation>valid=validation.find_by_id_pr(emp.getId_employer(),'p');
		  List<Validation> noti=validation.find_by_id_rd(emp.getId_employer(),'p');
		  
		  if (bt.equals("supprimer")){
				
				List<Solde>sold=solde.find_by_id_employer(id_employer);
				sold.removeAll(sold);
//				for(int i=0;i<sold.size();i++){
//					Solde s=sold.get(0);
//					System.out.println(s);
//					solde.delete(s.getId_Solde());
//				}
				employer.delete(employer.findOne(id_employer));
				
				model.addAttribute("username",username);
				List<Employer> list = employer.findAll();
			model.addAttribute("emp",list);
			
			
			return "list_employer";}
		  else{
			  
			  model.addAttribute("username",username);
			  
			  Employer ep= employer.findOne(id_employer);
			  model.addAttribute("emp", ep);
			  
			  Employer res=employer.findOne(ep.getIdresponsable());
				model.addAttribute("res", res);
				
				Departement dep1=departement.findOne(ep.getId_departement());
				model.addAttribute("dep1", dep1);
				
				Roles r=role.findOne(ep.getId_role());
				model.addAttribute("ro", r);
				
				List<Employer> list = employer.findAll();
				model.addAttribute("rs",list);
				
				List<Departement> list1 = departement.findAll();
				model.addAttribute("dep",list1);
				
				List<Roles> list2 = role.findAll();
				model.addAttribute("rol",list2);
				
				
				model.addAttribute("notif",noti);
				
				
				model.addAttribute("valid",valid);
				
				
				model.addAttribute("arch",arch);
			  return "modifier_employer";
		  }
	    }
	@Secured({"ROLE_RH", "ROLE_PR"})
	  @RequestMapping(value="/del_mod_typ", method=RequestMethod.GET)
	  public String del_mod_typ(@RequestParam(required=true, value="action")String bt ,int id_type ,Model model, HttpServletRequest httpServletRequest ) {
		
		  String username=log.getLogedUser(httpServletRequest);
		  List<Employer> ep=employer.find_by_nom_utilisateur(username);
			Employer emp=ep.get(0);
			List<Validation> arch= validation.find_to_archive('p'); 
			List<Validation>valid=validation.find_by_id_pr(emp.getId_employer(),'p');
			List<Validation> noti=validation.find_by_id_rd(emp.getId_employer(),'p');
			
			
		  if (bt.equals("supprimer")){
				typecongee.delete(typecongee.findOne(id_type));
				
				model.addAttribute("username",username);
				
				List<TypeCongee> list = typecongee.findAll();
				model.addAttribute("type",list);
				
				
				model.addAttribute("notif",noti);
				
				model.addAttribute("valid",valid);
				
				model.addAttribute("arch",arch);
				
			return "list_type_congee";}
		
		  else{
			 
			  model.addAttribute("username",username);
			  
			  TypeCongee type= typecongee.findOne(id_type);
				model.addAttribute("type", type);
				
				List<Employer> list = employer.findAll();
				model.addAttribute("emp",list);
				
			
				model.addAttribute("notif",noti);
			
				model.addAttribute("valid",valid);
			
				model.addAttribute("arch",arch);
			  return "modifier_type_congee";
		  }
	    }
	@Secured({"ROLE_RH", "ROLE_PR"})
	  @RequestMapping(value="/del_mod_dep", method=RequestMethod.GET)
	  public String del_mod_dep(@RequestParam(required=true, value="action")String bt ,int id_departement,Model model, HttpServletRequest httpServletRequest ) {
		  if (bt.equals("supprimer")){
				departement.delete(departement.findOne(id_departement));
				String username=log.getLogedUser(httpServletRequest);
				model.addAttribute("username",username);
				
				List<Departement> list = departement.findAll();
				model.addAttribute("dep",list);
				
				List<Employer> list1 = employer.findAll();
				model.addAttribute("emp",list1);
			return "list_departement";}
		  else{
			  String username=log.getLogedUser(httpServletRequest);
			  model.addAttribute("username",username);
			
			  Departement dp= departement.findOne(id_departement);
				model.addAttribute("dep", dp);
				
				Employer ep=employer.findOne(dp.getId_chef());
				model.addAttribute("emp1",ep);
				List<Employer> list = employer.findAll();
				model.addAttribute("emp",list);
				
				List<Employer> epl=employer.find_by_nom_utilisateur(username);
				Employer emp=epl.get(0);
				List<Validation> noti=validation.find_by_id_rd(emp.getId_employer(),'p');
				model.addAttribute("notif",noti);
				List<Validation>valid=validation.find_by_id_pr(emp.getId_employer(),'p');
				model.addAttribute("valid",valid);
				List<Validation> arch= validation.find_to_archive('p'); 
				model.addAttribute("arch",arch);
			  return "modifier_departement";
		  }
	    }
	@Secured({"ROLE_RH", "ROLE_PR"})
	  @RequestMapping(value="/demande_N1_rh" , method=RequestMethod.GET)
		public String demande_n1(Long ntf, Model model, HttpServletRequest httpServletRequest){
			
			String username=log.getLogedUser(httpServletRequest);
			model.addAttribute("username",username);
			
			
			List<Employer> ep=employer.find_by_nom_utilisateur(username);
			Employer emp=ep.get(0);
			List<Employer> list = employer.findAll();
			model.addAttribute("emp",list);
			List<Validation> noti=validation.find_by_id_rd(emp.getId_employer(),'p');
			model.addAttribute("notif",noti);
			List<Validation>valid=validation.find_by_id_pr(emp.getId_employer(),'p');
			model.addAttribute("valid",valid);
			List<Validation> arch= validation.find_to_archive('p'); 
			model.addAttribute("arch",arch);
			
			Validation notif=validation.findOne(ntf);
			Employer emp_env=employer.findOne(notif.getId_employer());
			Employer rd=employer.findOne(notif.getId_responsable());
			Demande dem=demande.findOne(notif.getId_demande());
			TypeCongee type=typecongee.findOne(dem.getId_type());
			Departement dp=departement.findOne(emp_env.getId_departement());
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date dt=new Date();
			model.addAttribute("date",formatter.format(dt));
			model.addAttribute("dp",dp);
			model.addAttribute("type",type);
			model.addAttribute("dem",dem);
			model.addAttribute("emp_env",emp_env);
			model.addAttribute("rd",rd);
			model.addAttribute("v",notif);
			return "demande_N1_rh";
		}
	@Secured({"ROLE_RH","ROLE_PR","ROLE_RD"})
		@RequestMapping(value="/validation_N1_rh" , method=RequestMethod.GET)
		public String validation_N1(@RequestParam(required=true, value="action")String bt,Long id_validation,Long id_employer, Long id_demande,Long id_responsable, String avis_rd,
					Long id_remplacant,String date_validation_rd, Model model, HttpServletRequest httpServletRequest){		
			
			Employer emp =employer.findOne(id_employer);
			Departement dep =departement.findOne(emp.getId_departement());
		
			Validation vali=validation.findOne(id_validation);
			 if(vali.getAction_rd()=='p'){
			
			 if (bt.equals("accepter")){
				 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					Date dvrd = null;
					try {
						 dvrd = formatter.parse(date_validation_rd);
					
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
				 validation.save(new Validation(id_validation,dvrd,id_demande,id_employer,id_remplacant,id_responsable,dep.getId_chef(),'o','p',avis_rd));
				
				 String chaine =" votre acceptation a ete bien saisi";
				 model.addAttribute("chaine",chaine);
				 
			 }
			 
			else{
				
				
				 
				 
				 Demande dem = demande.findOne(id_demande);
				
					demande.save(new Demande(dem.getId_demande(),dem.getDate_envoit(),dem.getDate_debut(),dem.getDate_fin(),dem.getPeriode(),dem.getId_employer(),dem.getId_type(),'n'));
					String chaine =" votre refus a ete bien saisi";
					 model.addAttribute("chaine",chaine);
					 
				
			}
			 
			 }
			  
			  else{
				  
					String chaine =" vous avez deja donne votre avis pour cette demande ";
					 model.addAttribute("chaine",chaine);
				  
			  }
			 
			 
			 List<Validation> noti=validation.find_by_id_rd(emp.getId_employer(),'p');
				model.addAttribute("notif",noti);
				List<Validation>valid=validation.find_by_id_pr(emp.getId_employer(),'p');
				model.addAttribute("valid",valid);
				List<Validation> arch= validation.find_to_archive('p'); 
				model.addAttribute("arch",arch);
				
			 return "validation_N1_rh";
		}
	@Secured({"ROLE_RH", "ROLE_PR","ROLE_RD"})
		@RequestMapping(value="/demande_N2_rh" , method=RequestMethod.GET)
		public String demande_n2(Long vld, Model model, HttpServletRequest httpServletRequest){
			
			String username=log.getLogedUser(httpServletRequest);
			model.addAttribute("username",username);
			
			List<Employer> list1 = employer.findAll();
			model.addAttribute("emp",list1);
			List<Employer> ep=employer.find_by_nom_utilisateur(username);
			Employer emp=ep.get(0);
			
			List<Validation> arch= validation.find_to_archive('p'); 
			model.addAttribute("arch",arch);
			
			Validation valid=validation.findOne(vld);
			Employer emp_env=employer.findOne(valid.getId_employer());
			Employer rd=employer.findOne(valid.getId_responsable());
			Employer pr=employer.findOne(valid.getId_premier_responsable());
			Employer rp=employer.findOne(valid.getId_remplaçant());
			Demande dem=demande.findOne(valid.getId_demande());
			TypeCongee type=typecongee.findOne(dem.getId_type());
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date dt=new Date();
			model.addAttribute("date",formatter.format(dt));
			model.addAttribute("validation",valid);
			model.addAttribute("type",type);
			model.addAttribute("dem",dem);
			model.addAttribute("emp_env",emp_env);
			model.addAttribute("rd",rd);
			model.addAttribute("pr",pr);
			model.addAttribute("rp",rp);
			
			List<Validation> noti=validation.find_by_id_rd(emp.getId_employer(),'p');
			model.addAttribute("notif",noti);
			List<Validation>valide=validation.find_by_id_pr(emp.getId_employer(),'p');
			model.addAttribute("valid",valide);
		
			return "demande_N2_rh";
		}
	@Secured({"ROLE_RH", "ROLE_PR","ROLE_RD"})
		@RequestMapping(value="/validation_N2_rh" , method=RequestMethod.GET)
		public String validation_N2(@RequestParam(required=true, value="action")String bt,Long id_validation, String date_validation_pr, String avis_pr, Model model, HttpServletRequest httpServletRequest){
		
		
			
			String username=log.getLogedUser(httpServletRequest);
			model.addAttribute("username",username);
			
		List<Employer> empl=employer.find_by_nom_utilisateur(username);
		Employer emp=empl.get(0);
		
			Validation valid=validation.findOne(id_validation);
			 if(valid.getAction_pr()=='p'){
			
			Demande dem = demande.findOne(valid.getId_demande());
			if (bt.equals("accepter")){
				 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					Date dvrd = null;
					try {
						 dvrd = formatter.parse(date_validation_pr);
					
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
				 validation.save(new Validation(id_validation,valid.getDate_validation_rd(),dvrd,valid.getId_demande(),valid.getId_employer(),valid.getId_remplaçant(),
						 valid.getId_responsable(),valid.getId_premier_responsable(),'o','o','p',valid.getAvis_rd(),avis_pr));
				  
				 String chaine =" votre acceptation a ete bien saisi";
				 model.addAttribute("chaine",chaine);
				 
				
			 }
			 
			else{
				
				 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					Date dvrd = null;
					try {
						 dvrd = formatter.parse(date_validation_pr);
					
					} catch (ParseException e) {
						e.printStackTrace();
					}
				validation.save(new Validation(id_validation,valid.getDate_validation_rd(),dvrd,valid.getId_demande(),valid.getId_employer(),valid.getId_remplaçant(),
						 valid.getId_responsable(),valid.getId_premier_responsable(),'o','n','n',valid.getAvis_rd(),avis_pr));
				demande.save(new Demande(dem.getId_demande(),dem.getDate_envoit(),dem.getDate_debut(),dem.getDate_fin(),dem.getPeriode(),dem.getId_employer(),dem.getId_type(),'n'));
				
				String chaine =" votre refus a ete bien saisi";
				 model.addAttribute("chaine",chaine);
				 
				
			}
			
			 }
			  
			  else{
				  
					String chaine =" vous avez deja donne votre avis pour cette demande ";
					 model.addAttribute("chaine",chaine);
				  
			  }
			
			List<Validation> noti=validation.find_by_id_rd(emp.getId_employer(),'p');
			model.addAttribute("notif",noti);
			List<Validation>valide=validation.find_by_id_pr(emp.getId_employer(),'p');
			model.addAttribute("valid",valide);
			List<Validation> arch= validation.find_to_archive('p'); 
			model.addAttribute("arch",arch);
			return "validation_N2_rh";
			
		}
	@Secured({"ROLE_RH","ROLE_PR"})
	  @RequestMapping(value="/demande_N3" , method=RequestMethod.GET)
		public String demande_n3(Long vld, Model model, HttpServletRequest httpServletRequest){
			
			String username=log.getLogedUser(httpServletRequest);
			model.addAttribute("username",username);			
			
			List<Employer> ep=employer.find_by_nom_utilisateur(username);
			Employer emp=ep.get(0);
			
			model.addAttribute("rh",emp); 
			
			List<Validation> noti=validation.find_by_id_rd(emp.getId_employer(),'p');
			model.addAttribute("notif",noti);
			
			Validation valid=validation.findOne(vld);
			model.addAttribute("validation",valid);
			
			Employer emp_env=employer.findOne(valid.getId_employer());
			model.addAttribute("emp_env",emp_env);
			
			Employer rd=employer.findOne(valid.getId_responsable());
			model.addAttribute("rd",rd);
			
			Employer pr=employer.findOne(valid.getId_premier_responsable());
			model.addAttribute("pr",pr);
			
			Employer rp=employer.findOne(valid.getId_remplaçant());
			model.addAttribute("rp",rp);
			
			Demande dem=demande.findOne(valid.getId_demande());
			model.addAttribute("dem",dem);
			
			TypeCongee type=typecongee.findOne(dem.getId_type());
			model.addAttribute("type",type);
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat ann = new SimpleDateFormat("yyyy");
			Date dt=new Date();
			
			model.addAttribute("date",formatter.format(dt));
			
			int annee= Integer.parseInt(ann.format(dt));
			Solde s1= solde.find_by_id_employer_anne(emp_env.getId_employer(), annee);
			model.addAttribute("ac",s1);
			
			Solde s2= solde.find_by_id_employer_anne(emp_env.getId_employer(), (annee-1));
			model.addAttribute("ap",s2);
			
			long total = s1.getTotal_solde()+s2.getTotal_solde();
			model.addAttribute("t",total);
			
			long rest = total-dem.getPeriode();
			model.addAttribute("r",rest);			
			
			List<Validation>valide=validation.find_by_id_pr(emp.getId_employer(),'p');
			model.addAttribute("valid",valide);
			
			List<Validation> arch= validation.find_to_archive('p'); 
			model.addAttribute("arch",arch);
			
			
			return "demande_N3";
		}
	@Secured({"ROLE_RH", "ROLE_PR"})
	  @RequestMapping(value="/validation_N3" , method=RequestMethod.GET)
		public String validation_N3(Long id_validation, String date_archive, Long id_admin, Model model, HttpServletRequest httpServletRequest){
		
		  String username=log.getLogedUser(httpServletRequest);
			model.addAttribute("username",username);			
			
			List<Employer> ep=employer.find_by_nom_utilisateur(username);
			Employer emp=ep.get(0);
			
		  Validation valid=validation.findOne(id_validation);
		  
		  if(valid.getAction_rh()=='p'){
		  
		  validation.save(new Validation(id_validation,valid.getDate_validation_rd(),valid.getDate_validation_pr(),valid.getId_demande(),valid.getId_employer(),valid.getId_remplaçant(),
					 valid.getId_responsable(),valid.getId_premier_responsable(),'o','o','o',valid.getAvis_rd(),valid.getAvis_pr()));
		  
		  Demande dem = demande.findOne(valid.getId_demande());
		  demande.save(new Demande(dem.getId_demande(),dem.getDate_envoit(),dem.getDate_debut(),dem.getDate_fin(),dem.getPeriode(),dem.getId_employer(),dem.getId_type(),'o'));
		  TypeCongee type=typecongee.findOne(dem.getId_type());
		  Employer empl = employer.findOne(valid.getId_employer());
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy");
			Date dt=new Date();
			String s=format.format(dt);
			int annee= Integer.parseInt(s);
			Solde s1= solde.find_by_id_employer_anne(empl.getId_employer(), annee);
		
			if(type.getCode_type().equals("011100")){
				solde.save(new Solde(s1.getId_Solde(),s1.getTotal_solde(),s1.getSolde_materniter(),s1.getSolde_specifique(),s1.getSolde_maladie()-dem.getPeriode(),s1.getAnnee_solde(),s1.getId_employer(),s1.getMise_a_jours()));
			}
			if(type.getCode_type().equals("000110")){
				solde.save(new Solde(s1.getId_Solde(),s1.getTotal_solde(),s1.getSolde_materniter(),s1.getSolde_specifique()-dem.getPeriode(),s1.getSolde_maladie(),s1.getAnnee_solde(),s1.getId_employer(),s1.getMise_a_jours()));
			}
			if(type.getCode_type().equals("111100")){
				solde.save(new Solde(s1.getId_Solde(),s1.getTotal_solde(),s1.getSolde_materniter()-dem.getPeriode(),s1.getSolde_specifique(),s1.getSolde_maladie(),s1.getAnnee_solde(),s1.getId_employer(),s1.getMise_a_jours()));
			}
			
			if(type.getCode_type().equals("011110")){
				
			
			Solde s2= solde.find_by_id_employer_anne(empl.getId_employer(), (annee-1));
			long i1 = s1.getTotal_solde();
			long i2 = s2.getTotal_solde();
			if(i2>=dem.getPeriode())
			{
				
				solde.save(new Solde(s2.getId_Solde(),i2-dem.getPeriode(),s2.getSolde_materniter(),s2.getSolde_specifique(),s2.getSolde_maladie(),s2.getAnnee_solde(),s2.getId_employer(),s2.getMise_a_jours()));
			}
			else
			{
				
				int def=(int) (dem.getPeriode()-i2);
				i1=i1-def;
				solde.save(new Solde(s2.getId_Solde(),0,s2.getSolde_materniter(),s2.getSolde_specifique(),s2.getSolde_maladie(),s2.getAnnee_solde(),s2.getId_employer(),s2.getMise_a_jours()));
				
				solde.save(new Solde(s1.getId_Solde(),i1,s1.getSolde_materniter(),s1.getSolde_specifique(),s1.getSolde_maladie(),s1.getAnnee_solde(),s1.getId_employer(),s1.getMise_a_jours()));
			}
			
			}
			Date dvrd = null;
				 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					try {
						 dvrd = formatter.parse(date_archive);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				
					int i=archive.count(id_validation);
					if(i>0){
						Archive ar=archive.find_by_id_validation(id_validation);
						archive.save(new Archive(ar.getId_archive(),id_validation,id_admin,dvrd));
					}
					else
					{
					archive.save(new Archive(id_validation,id_admin,dvrd));
					}
					
					String chaine =" La demande est enregistrer correctement ";
					 model.addAttribute("chaine",chaine);
					 
					 
		  }
		  
  else{
			  
				String chaine =" La demande est deja enregistrer correctement ";
				 model.addAttribute("chaine",chaine);
			  
		  }
					List<Validation>valide=validation.find_by_id_pr(emp.getId_employer(),'p');
					model.addAttribute("valid",valide);
					List<Validation> arch= validation.find_to_archive('p'); 
					model.addAttribute("arch",arch);
				 
				 return "validation_N3";
		}
	
	  @Secured({"ROLE_RH","ROLE_PR"})
	  @RequestMapping(value="/reprise_rh")
		public String reprise_rh(Model model, HttpServletRequest httpServletRequest,Long id_demande, String date_envoit,String date_retour){
		  String username=log.getLogedUser(httpServletRequest);
			model.addAttribute("username",username);
			List<Employer> ep=employer.find_by_nom_utilisateur(username);
			Employer emp=ep.get(0);
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			
			Date date_rt = null;
			Date date_ev = null;	
			try {
				date_rt=format.parse(date_retour);
				date_ev=format.parse(date_envoit);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
				notification.save(new Notification_Reprise(emp.getId_employer(), id_demande,date_ev, date_rt));
			
			Demande d=demande.findOne(id_demande);
			
			demande.save(new Demande(id_demande,d.getDate_envoit(), d.getDate_debut(), d.getDate_fin(), d.getPeriode(), d.getId_employer(), d.getId_type(),'r'));
			
			TypeCongee type= typecongee.findOne(d.getId_type());
			
			long mil=1000*60*60*24;
			long delta = d.getDate_fin().getTime()-date_rt.getTime();
			long dif =delta/mil;
			String s= String.valueOf(dif);
			int def=Integer.parseInt(s);
			
			SimpleDateFormat year = new SimpleDateFormat("yyyy");
			int annee= Integer.parseInt(year.format(new Date()));
			Solde s1= solde.find_by_id_employer_anne(emp.getId_employer(), annee);
			
			if(type.getCode_type()=="011100"){
				solde.save(new Solde(s1.getId_Solde(), s1.getTotal_solde(), s1.getSolde_materniter(), s1.getSolde_specifique(), def+s1.getSolde_maladie() , s1.getAnnee_solde(), s1.getId_employer(), s1.getMise_a_jours()));
			}
			
			if(type.getCode_type()=="000110"){
				solde.save(new Solde(s1.getId_Solde(), s1.getTotal_solde(), s1.getSolde_materniter(), s1.getSolde_specifique()+def, s1.getSolde_maladie() , s1.getAnnee_solde(), s1.getId_employer(), s1.getMise_a_jours()));
			}
			
			if(type.getCode_type()=="111100"){
				solde.save(new Solde(s1.getId_Solde(), s1.getTotal_solde(), s1.getSolde_materniter()+def, s1.getSolde_specifique(), s1.getSolde_maladie() , s1.getAnnee_solde(), s1.getId_employer(), s1.getMise_a_jours()));
			}
			
			if(type.getCode_type()=="011110"){
				
				Solde s2= solde.find_by_id_employer_anne(emp.getId_employer(), (annee-1));
					if(s2.getTotal_solde()==0){
						solde.save(new Solde(s1.getId_Solde(), s1.getTotal_solde()+def, s1.getSolde_materniter(), s1.getSolde_specifique(), s1.getSolde_maladie() , s1.getAnnee_solde(), s1.getId_employer(), s1.getMise_a_jours()));
					}
					else{
						solde.save(new Solde(s2.getId_Solde(), s2.getTotal_solde()+def, s2.getSolde_materniter(), s2.getSolde_specifique(), s2.getSolde_maladie() , s2.getAnnee_solde(), s2.getId_employer(), s2.getMise_a_jours()));
					}
			}
			
			
			List<Validation> noti=validation.find_by_id_rd(emp.getId_employer(),'p');
			model.addAttribute("notif",noti);
			List<Validation>valid=validation.find_by_id_pr(emp.getId_employer(),'p');
			model.addAttribute("valid",valid);
			List<Validation> arch= validation.find_to_archive('p'); 
			model.addAttribute("arch",arch);
		
			String chaine ="votre reprise est enregistrer correctement";
			model.addAttribute("chaine",chaine);
		
		return "etat_demande_rh";
	 }
}
