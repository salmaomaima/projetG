package mon.pfe.controlle;


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
public class LienControlle {
	
	@Autowired
	public EmployerRepository employer;	
	@Autowired
	public DepartementRepository departement;
	@Autowired
	public RolesRepository role;
	@Autowired
	public TypeCongeeRepository typecongee;
	@Autowired
	public DemandeRepository demande;
	@Autowired
	public LoginController rhrc;
	@Autowired
	public SoldeRepository solde;
	@Autowired
	public NotificationRepriseRepository notification;
	@Autowired
	public ValidationRepository validation;
	@Autowired
	public ArchiveRepository archive;


	// lien des pages pour ressource humaine
	
	@SuppressWarnings("null")
	@Secured(value={"ROLE_RH","ROLE_PR"})
	@RequestMapping(value="/index_ressource_humaine")
	public String index_ressource_humaine(Model model, HttpServletRequest httpServletRequest){
		
		String username=rhrc.getLogedUser(httpServletRequest);
		model.addAttribute("username",username);
		
	List<Employer> empl=employer.find_by_nom_utilisateur(username);
	Employer emp=empl.get(0);
	
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
	Date dt=new Date();
	String s=formatter.format(dt);
	int annee= Integer.parseInt(s);
	Solde s1= solde.find_by_id_employer_anne(emp.getId_employer(), annee);
	Solde s2= solde.find_by_id_employer_anne(emp.getId_employer(), (annee-1));
	
	long i = s1.getTotal_solde()+s2.getTotal_solde();
	model.addAttribute("sum",i);
	
	
	int j=demande.count(emp.getId_employer());
	model.addAttribute("count",j);
	
	List<Validation> noti=validation.find_by_id_rd(emp.getId_employer(),'p');
	model.addAttribute("notif",noti);
	List<Validation>valid=validation.find_by_id_pr(emp.getId_employer(),'p');
	model.addAttribute("valid",valid);
	
	List<Notification_Reprise> reprise= notification.findAll();
	
	
	
	if(reprise.size()<1){
		String rep="il n'ya aucune reprise pour le moment";
		model.addAttribute("rp",rep);
	}
	else{
	List<String> rep_emp = null;
	String chaine;
	for(int co=0;co<=reprise.size();co++){
		Notification_Reprise rp=reprise.get(co);
		Employer emp_rep=employer.findOne(rp.getId_employer());
		chaine=emp_rep.getNom()+" repris son travaile le "+rp.getDate_retour();
		rep_emp.add(chaine);
	}
	
	model.addAttribute("rp", rep_emp);
	
	}
	
	List<Demande>dem_ann=demande.find_dem_ann('a');
	model.addAttribute("dem_ann",dem_ann);
	
	
	
	List<Validation> arch= validation.find_to_archive('p'); 
	model.addAttribute("arch",arch);
	
	int dn=demande.count_demande_en_cours(emp.getId_employer(), 'n');
	model.addAttribute("dn",dn);
	int d=demande.count_demande_en_cours(emp.getId_employer(), 'o');
	model.addAttribute("do",d);
	
	
	 return "index_ressource_humaine";
 }
	
	@Secured(value={"ROLE_RH","ROLE_PR"})
	@RequestMapping(value="/mot_de_passe_ressource")
	public String profil_ressource(Model model, HttpServletRequest httpServletRequest){
		String username=rhrc.getLogedUser(httpServletRequest);
		model.addAttribute("username",username);
		List<Employer> ep=employer.find_by_nom_utilisateur(username);
		Employer emp=ep.get(0);
		model.addAttribute("emp",emp);
		List<Validation> noti=validation.find_by_id_rd(emp.getId_employer(),'p');
		model.addAttribute("notif",noti);
		List<Validation>valid=validation.find_by_id_pr(emp.getId_employer(),'p');
		model.addAttribute("valid",valid);
		List<Validation> arch= validation.find_to_archive('p'); 
		model.addAttribute("arch",arch);
	 return "mot_de_passe_rh";
 }
	
	@Secured(value={"ROLE_RH","ROLE_PR"})
	@RequestMapping(value="/profil_ressource")
	public String mot_de_passe_rh(Model model, HttpServletRequest httpServletRequest){
		String username=rhrc.getLogedUser(httpServletRequest);
		model.addAttribute("username",username);
		List<Employer> ep=employer.find_by_nom_utilisateur(username);
		Employer emp=ep.get(0);
		model.addAttribute("emp",emp);
		List<Validation> noti=validation.find_by_id_rd(emp.getId_employer(),'p');
		model.addAttribute("notif",noti);
		List<Validation>valid=validation.find_by_id_pr(emp.getId_employer(),'p');
		model.addAttribute("valid",valid);
		List<Validation> arch= validation.find_to_archive('p'); 
		model.addAttribute("arch",arch);
	 return "profil_ressource_humaine";
 }
	
	// fonction de la departement 
	

	@Secured(value={"ROLE_RH","ROLE_PR"})
	@RequestMapping(value="/list_departement")
	public String list_departement (Model model, HttpServletRequest httpServletRequest){
		String username=rhrc.getLogedUser(httpServletRequest);
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
	
	@Secured(value={"ROLE_RH","ROLE_PR"})
	@RequestMapping(value="/formulaire_ajouter_departement")
	public String formulaire_departement(Model model, HttpServletRequest httpServletRequest){
		String username=rhrc.getLogedUser(httpServletRequest);
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
	 return "formulaire_ajouter_departement";
 }
	
	@Secured(value={"ROLE_RH","ROLE_PR"})
	@RequestMapping(value="/formulaire_modifier_departement")
	public String modifier_departement(HttpServletRequest httpServletRequest, Model model){
		String username=rhrc.getLogedUser(httpServletRequest);
		model.addAttribute("username",username);
		List<Employer> ep=employer.find_by_nom_utilisateur(username);
		Employer emp=ep.get(0);
		List<Validation> noti=validation.find_by_id_rd(emp.getId_employer(),'p');
		model.addAttribute("notif",noti);
		List<Validation>valid=validation.find_by_id_pr(emp.getId_employer(),'p');
		model.addAttribute("valid",valid);
		List<Validation> arch= validation.find_to_archive('p'); 
		model.addAttribute("arch",arch);
	 return "modifier_departement";
 }
	@Secured(value={"ROLE_RH","ROLE_PR"})
	@RequestMapping(value="/formulaire_supprimer_departement")
	public String supprimer_departement(HttpServletRequest httpServletRequest, Model model){
		String username=rhrc.getLogedUser(httpServletRequest);
		model.addAttribute("username",username);
		List<Employer> ep=employer.find_by_nom_utilisateur(username);
		Employer emp=ep.get(0);
		List<Validation> noti=validation.find_by_id_rd(emp.getId_employer(),'p');
		model.addAttribute("notif",noti);
		List<Validation>valid=validation.find_by_id_pr(emp.getId_employer(),'p');
		model.addAttribute("valid",valid);
		List<Validation> arch= validation.find_to_archive('p'); 
		model.addAttribute("arch",arch);
	 return "supprimer_departement";
 }
	
	
	// fonction de l'employer
	@Secured(value={"ROLE_RH","ROLE_PR"})
	@RequestMapping(value="/formulaire_ajouter_employer")
	public String formulaire_employer(Model model, HttpServletRequest httpServletRequest){
		String username=rhrc.getLogedUser(httpServletRequest);
		model.addAttribute("username",username);
		List<Employer> list = employer.findAll();
		model.addAttribute("emp",list);
		List<Departement> list1 = departement.findAll();
		model.addAttribute("dep",list1);
		List<Roles> list2 = role.findAll();
		model.addAttribute("rol",list2);
		List<Employer> ep=employer.find_by_nom_utilisateur(username);
		Employer emp=ep.get(0);
		List<Validation> noti=validation.find_by_id_rd(emp.getId_employer(),'p');
		model.addAttribute("notif",noti);
		List<Validation>valid=validation.find_by_id_pr(emp.getId_employer(),'p');
		model.addAttribute("valid",valid);
		List<Validation> arch= validation.find_to_archive('p'); 
		model.addAttribute("arch",arch);
	 return "formulaire_ajouter_employer";
 }
	@Secured(value={"ROLE_RH","ROLE_PR"})
	@RequestMapping(value="/formulaire_modifier_employer")
	public String modifier_employer(HttpServletRequest httpServletRequest, Model model){
		String username=rhrc.getLogedUser(httpServletRequest);
		model.addAttribute("username",username);
		List<Employer> ep=employer.find_by_nom_utilisateur(username);
		Employer emp=ep.get(0);
		List<Validation> noti=validation.find_by_id_rd(emp.getId_employer(),'p');
		model.addAttribute("notif",noti);
		List<Validation>valid=validation.find_by_id_pr(emp.getId_employer(),'p');
		model.addAttribute("valid",valid);
		List<Validation> arch= validation.find_to_archive('p'); 
		model.addAttribute("arch",arch);
	 return "formulaire_modifier_employer";
 }
	@Secured(value={"ROLE_RH","ROLE_PR"})
	@RequestMapping(value="/formulaire_supprimer_employer")
	public String supprimer_employer(HttpServletRequest httpServletRequest, Model model){
		String username=rhrc.getLogedUser(httpServletRequest);
		model.addAttribute("username",username);
		List<Employer> ep=employer.find_by_nom_utilisateur(username);
		Employer emp=ep.get(0);
		List<Validation> noti=validation.find_by_id_rd(emp.getId_employer(),'p');
		model.addAttribute("notif",noti);
		List<Validation>valid=validation.find_by_id_pr(emp.getId_employer(),'p');
		model.addAttribute("valid",valid);
		List<Validation> arch= validation.find_to_archive('p'); 
		model.addAttribute("arch",arch);
	 return "formulaire_supprimer_employer";
 }
	@Secured(value={"ROLE_RH","ROLE_PR"})
	@RequestMapping(value="/list_employer" , method=RequestMethod.GET)
	public String list_employer (Model model, HttpServletRequest httpServletRequest){
		String username=rhrc.getLogedUser(httpServletRequest);
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
	
	
	//fonction de type de congee
	
	@Secured(value={"ROLE_RH","ROLE_PR"})
	@RequestMapping(value="/list_type_congee")
	public String list_type_congee(Model model, HttpServletRequest httpServletRequest){
		String username=rhrc.getLogedUser(httpServletRequest);
	model.addAttribute("username",username);
		List<TypeCongee> list = typecongee.findAll();
	model.addAttribute("type",list);
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
	
	@Secured(value={"ROLE_RH","ROLE_PR"})
	@RequestMapping(value="/formulaire_ajouter_type_congee")
	public String ajouter_type_congee(HttpServletRequest httpServletRequest, Model model){
		String username=rhrc.getLogedUser(httpServletRequest);
		model.addAttribute("username",username);
		List<Employer> ep=employer.find_by_nom_utilisateur(username);
		Employer emp=ep.get(0);
		List<Validation> noti=validation.find_by_id_rd(emp.getId_employer(),'p');
		model.addAttribute("notif",noti);
		List<Validation>valid=validation.find_by_id_pr(emp.getId_employer(),'p');
		model.addAttribute("valid",valid);
		List<Validation> arch= validation.find_to_archive('p'); 
		model.addAttribute("arch",arch);
	 return "formulaire_ajouter_type_congee";
 }
	
	@Secured(value={"ROLE_RH","ROLE_PR"})
	@RequestMapping(value="/formulaire_modifier_type_congee")
	public String modifier_type_congee(HttpServletRequest httpServletRequest, Model model){
		String username=rhrc.getLogedUser(httpServletRequest);
		model.addAttribute("username",username);
		List<Employer> ep=employer.find_by_nom_utilisateur(username);
		Employer emp=ep.get(0);
		List<Validation> noti=validation.find_by_id_rd(emp.getId_employer(),'p');
		model.addAttribute("notif",noti);
		List<Validation>valid=validation.find_by_id_pr(emp.getId_employer(),'p');
		model.addAttribute("valid",valid);
		List<Validation> arch= validation.find_to_archive('p'); 
		model.addAttribute("arch",arch);
	 return "formulaire_modifier_type_congee";
 }
	@Secured(value={"ROLE_RH","ROLE_PR"})
	@RequestMapping(value="/formulaire_supprimer_type_congee")
	public String supprimer_type_congee(){
	 return "formulaire_supprimer_type_congee";
 }
	
	//fonction de la demande
	@Secured(value={"ROLE_RH","ROLE_PR"})
	@RequestMapping(value="/remplir_demande_rh")
	public String remplir_demande_rh(HttpServletRequest httpServletRequest, Model model){
		String username=rhrc.getLogedUser(httpServletRequest);
		model.addAttribute("username",username);
		List<Employer> emp =employer.find_by_nom_utilisateur(username);
		Employer ep =emp.get(0);
		model.addAttribute("emp", ep);
		Departement dep =departement.findOne(ep.getId_departement());
		model.addAttribute("dep", dep);
		List<TypeCongee> typ = typecongee.findAll();
		model.addAttribute("type",typ);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dt=new Date();
		
		model.addAttribute("date",formatter.format(dt));
		
		List<Validation> noti=validation.find_by_id_rd(ep.getId_employer(),'p');
		model.addAttribute("notif",noti);
		List<Validation>valid=validation.find_by_id_pr(ep.getId_employer(),'p');
		model.addAttribute("valid",valid);
		List<Validation> arch= validation.find_to_archive('p'); 
		model.addAttribute("arch",arch);
		
	 return "remplir_demande_rh";
 }
	@Secured(value={"ROLE_RH","ROLE_PR"})
	@RequestMapping(value="/reprise_demande_rh")
	public String reprise_demande_rh(HttpServletRequest httpServletRequest, Model model){
		String username=rhrc.getLogedUser(httpServletRequest);
		model.addAttribute("username",username);
		List<Employer> ep=employer.find_by_nom_utilisateur(username);
		Employer emp=ep.get(0);
		model.addAttribute("emp",emp);
		List<Demande> dem=demande.find_to_reprise(emp.getId_employer(),'o',new Date());
		
		if(dem.size()==0){
			String chaine =" vous n'êtes pas en congé pour le moment ";
			model.addAttribute("chaine",chaine);
		}
		
		else{
			Demande d = dem.get(0);
		
		model.addAttribute("dem",d);
		TypeCongee type=typecongee.findOne(d.getId_type());
		model.addAttribute("type",type);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dt=new Date();
		model.addAttribute("date",formatter.format(dt));
		}
		
		List<Validation> noti=validation.find_by_id_rd(emp.getId_employer(),'p');
		model.addAttribute("notif",noti);
		List<Validation>valid=validation.find_by_id_pr(emp.getId_employer(),'p');
		model.addAttribute("valid",valid);
		List<Validation> arch= validation.find_to_archive('p'); 
		model.addAttribute("arch",arch);
		
	 return "reprise_demande_rh";
 }
	@Secured(value={"ROLE_RD","ROLE_EM","ROLE_RH","ROLE_PR"})
	@RequestMapping(value="/etat_demande_rh")
	public String etat_demande_rh(HttpServletRequest httpServletRequest, Model model){
		String username=rhrc.getLogedUser(httpServletRequest);
		model.addAttribute("username",username);
		List<Employer> ep=employer.find_by_nom_utilisateur(username);
		Employer emp=ep.get(0);
		List<Validation> noti=validation.find_by_id_rd(emp.getId_employer(),'p');
		model.addAttribute("notif",noti);
		List<Validation>valid=validation.find_by_id_pr(emp.getId_employer(),'p');
		model.addAttribute("valid",valid);
		List<Validation> arch= validation.find_to_archive('p'); 
		model.addAttribute("arch",arch);
		
		List<Demande> demd=demande.find_id_employer(emp.getId_employer());
		int d=demande.count(emp.getId_employer());
		if(d>0){
			
		Demande dem=demd.get(d-1);
		model.addAttribute("d",dem);
		
		Validation val=validation.find_id_emp_dem(emp.getId_employer(),dem.getId_demande());
		model.addAttribute("v",val);
		
		TypeCongee ty=typecongee.findOne(dem.getId_type());
		String t=" dans une type de congé "+ty.getNom_type();
		model.addAttribute("t",t);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String de="qui vous avez deposé le :"+formatter.format(dem.getDate_envoit());
		model.addAttribute("de",de);

		String dd="qui commence le "+formatter.format(dem.getDate_debut());
		model.addAttribute("dd",dd);
		
		String df=" et se termine le : "+formatter.format(dem.getDate_fin());
		model.addAttribute("df",df);
		
		if(val.getAction_rd()=='p'){ 
			String chaine ="votre responsable direct pas encors verifier votre demande";
			model.addAttribute("chaine",chaine);	}
		if (val.getAction_rd()=='n') {
			String chaine ="votre dernier demande est refusé par votre responsable direct";
			model.addAttribute("chaine",chaine);
		}
		if ( val.getAction_rd() == 'o') {
			String chaine ="votre dernier demande est accepté par votre responsable directe et pas encors verifié par le premier responsable";
			model.addAttribute("chaine",chaine);
		}
		if (val.getAction_pr() == 'n') {
			String chaine ="votre dernier demande est refusé par votre premier responsable";
			model.addAttribute("chaine",chaine);
		}
		if ( val.getAction_pr() == 'o') {
			String chaine ="votre dernier demande est accepté par votre premier responsable et pas encors confirmé par l'administration ";
			model.addAttribute("chaine",chaine);
		}
		if ( val.getAction_rh() == 'o') {
			String chaine ="votre dernier demande est confirmé par l'administration ";
			model.addAttribute("chaine",chaine);
		}
		
		
		}
		else {
			String chaine ="vous n'avez pas de demande envoyé";
			model.addAttribute("chaine",chaine);
		}

		
		
	 return "etat_demande_rh";
 }
	
	@Secured(value={"ROLE_RH","ROLE_PR"})
	@RequestMapping(value="/list_demande_rh")
	public String list_demande_rh(HttpServletRequest httpServletRequest, Model model){
		String username=rhrc.getLogedUser(httpServletRequest);
		model.addAttribute("username",username);
		List<Employer> emp =employer.find_by_nom_utilisateur(username);
		Employer ep=emp.get(0);
		
		List<Demande>dem_to_annuler=demande.find_to_annule(ep.getId_employer(),new Date(),'o');		
		model.addAttribute("dem_to_annuler",dem_to_annuler);
		
		List<Demande>dem_cant_annuler=demande.cant_annul(ep.getId_employer(),new Date(),'a');		
		model.addAttribute("dem_cant_annuler",dem_cant_annuler);
		
		List<Validation> noti=validation.find_by_id_rd(ep.getId_employer(),'p');
		model.addAttribute("notif",noti);
		List<Validation>valid=validation.find_by_id_pr(ep.getId_employer(),'p');
		model.addAttribute("valid",valid);
		List<Validation> arch= validation.find_to_archive('p'); 
		model.addAttribute("arch",arch);
		
	 return "list_demande_rh";
 }
	
 
	
	// lien des pages commune entr le acteur
	@Secured(value={"ROLE_PR","ROLE_RD","ROLE_EM"})
	@RequestMapping(value="/remplir_demande")
	public String remplir_demande(HttpServletRequest httpServletRequest, Model model){
		String username=rhrc.getLogedUser(httpServletRequest);
		model.addAttribute("username",username);
		List<Employer> emp =employer.find_by_nom_utilisateur(username);
		model.addAttribute("emp", emp);
		List<TypeCongee> typ = typecongee.findAll();
		model.addAttribute("type",typ);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dt=new Date();
		model.addAttribute("date",formatter.format(dt));
		Employer ep=emp.get(0);
		Departement dep =departement.findOne(ep.getId_departement());
		model.addAttribute("dep", dep);
		List<Validation> noti=validation.find_by_id_rd(ep.getId_employer(),'p');
		model.addAttribute("notif",noti);
		List<Validation>valid=validation.find_by_id_pr(ep.getId_employer(),'p');
		model.addAttribute("valid",valid);
	 return "remplir_demande";
 }
	@Secured(value={"ROLE_PR","ROLE_RD","ROLE_EM"})	
	@RequestMapping(value="/reprise_demande")
	public String reprise_demande(HttpServletRequest httpServletRequest, Model model){
		String username=rhrc.getLogedUser(httpServletRequest);
		model.addAttribute("username",username);
		List<Employer> ep=employer.find_by_nom_utilisateur(username);
		Employer emp=ep.get(0);
		model.addAttribute("emp",emp);
		List<Demande> dem=demande.find_to_reprise(emp.getId_employer(),'o',new Date());
		
		if(dem.size()==0){
			String chaine =" vous n'ete pas en conge pour faire une reprise tu peut annuler vos demande valider ou attender le debut de congee pour faire une reprise de demande ";
			model.addAttribute("chaine",chaine);
		}
		
		else{
			Demande d = dem.get(0);
		
		model.addAttribute("dem",d);
		TypeCongee type=typecongee.findOne(d.getId_type());
		model.addAttribute("type",type);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dt=new Date();
		model.addAttribute("date",formatter.format(dt));
		}
		
		List<Validation> noti=validation.find_by_id_rd(emp.getId_employer(),'p');
		model.addAttribute("notif",noti);
		List<Validation>valid=validation.find_by_id_pr(emp.getId_employer(),'p');
		model.addAttribute("valid",valid);
		return "reprise_demande";
		
 }
	

	@Secured(value={"ROLE_EM"})	
	@RequestMapping(value="/etat_demande")
	public String etat_demande(HttpServletRequest httpServletRequest, Model model){
		String username=rhrc.getLogedUser(httpServletRequest);
		model.addAttribute("username",username);
		List<Employer> ep=employer.find_by_nom_utilisateur(username);
		Employer emp=ep.get(0);
		List<Validation> noti=validation.find_by_id_rd(emp.getId_employer(),'p');
		model.addAttribute("notif",noti);
		List<Validation>valid=validation.find_by_id_pr(emp.getId_employer(),'p');
		model.addAttribute("valid",valid);
		List<Demande> demd=demande.find_id_employer(emp.getId_employer());
		int d=demande.count(emp.getId_employer());
		if(d>0){
			
		Demande dem=demd.get(d-1);
		model.addAttribute("d",dem);
		
		Validation val=validation.find_id_emp_dem(emp.getId_employer(),dem.getId_demande());
		model.addAttribute("v",val);
		
		TypeCongee ty=typecongee.findOne(dem.getId_type());
		String t=" dans une type de congé "+ty.getNom_type();
		model.addAttribute("t",t);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		String de="que tu as deposser le "+formatter.format(dem.getDate_envoit());
		model.addAttribute("de",de);

		String dd="que commence le "+formatter.format(dem.getDate_debut());
		model.addAttribute("dd",dd);
		
		String df=" et se termine le "+formatter.format(dem.getDate_fin());
		model.addAttribute("df",df);
		
		if(val.getAction_rd()=='p'){ 
			String chaine ="votre responsable direct pas en cours verifier votre demande";
			model.addAttribute("chaine",chaine);	}
		if (val.getAction_rd()=='n') {
			String chaine ="votre dernier demande est refuser par votre responsable direct";
			model.addAttribute("chaine",chaine);
		}
		if ( val.getAction_rd() == 'o') {
			String chaine ="votre dernier demande est accepter par votre responsable directe et pas en cours verifier par le premier responsable";
			model.addAttribute("chaine",chaine);
		}
		if (val.getAction_pr() == 'n') {
			String chaine ="votre dernier demande est refuser par votre premier responsable";
			model.addAttribute("chaine",chaine);
		}
		if ( val.getAction_pr() == 'o') {
			String chaine ="votre dernier demande est accepter par votre premier responsable et pas encours confirmer par l'administration ";
			model.addAttribute("chaine",chaine);
		}
		if ( val.getAction_rh() == 'o') {
			String chaine ="votre dernier demande est confirmer par l'administration ";
			model.addAttribute("chaine",chaine);
		}
		
		
		}
		else {
			String chaine ="vous n'avez pas de demande envoyer";
			model.addAttribute("chaine",chaine);
		}
		
		
	 return "etat_demande";
 }
	
	@Secured(value={"ROLE_PR","ROLE_RD","ROLE_EM"})
	@RequestMapping(value="/list_demande")
	public String list_demande(Model model, HttpServletRequest httpServletRequest){
		String username=rhrc.getLogedUser(httpServletRequest);
		model.addAttribute("username",username);
		List<Employer> emp =employer.find_by_nom_utilisateur(username);
		Employer ep=emp.get(0);
		List<Demande>dem_to_annuler=demande.find_to_annule(ep.getId_employer(),new Date(),'o');		
		model.addAttribute("dem_to_annuler",dem_to_annuler);
		
		List<Demande>dem_cant_annuler=demande.cant_annul(ep.getId_employer(),new Date(),'a');		
		model.addAttribute("dem_cant_annuler",dem_cant_annuler);
		List<Demande>dem=demande.find_id_employer(ep.getId_employer());
		model.addAttribute("dem",dem);

		
		List<Validation> noti=validation.find_by_id_rd(ep.getId_employer(),'p');
		model.addAttribute("notif",noti);
		List<Validation>valid=validation.find_by_id_pr(ep.getId_employer(),'p');
		model.addAttribute("valid",valid);
		
	 return "list_demande";
 }
	
	@Secured(value={"ROLE_PR","ROLE_RD","ROLE_EM"})
	@RequestMapping(value="/profil")
	public String profil(HttpServletRequest httpServletRequest, Model model){
		
		String username=rhrc.getLogedUser(httpServletRequest);
		model.addAttribute("username",username);
		List<Employer> ep=employer.find_by_nom_utilisateur(username);
		Employer emp=ep.get(0);
		model.addAttribute("emp",emp);
		List<Validation> noti=validation.find_by_id_rd(emp.getId_employer(),'p');
		model.addAttribute("notif",noti);
		List<Validation>valid=validation.find_by_id_pr(emp.getId_employer(),'p');
		model.addAttribute("valid",valid);
		
	 return "profil";
 }
	@Secured(value={"ROLE_PR","ROLE_RD","ROLE_EM"})
	@RequestMapping(value="/mot_de_passe")
	public String mot_de_passe(Model model, HttpServletRequest httpServletRequest){
		String username=rhrc.getLogedUser(httpServletRequest);
		model.addAttribute("username",username);
		List<Employer> ep=employer.find_by_nom_utilisateur(username);
		Employer emp=ep.get(0);
		model.addAttribute("emp",emp);
		List<Validation> noti=validation.find_by_id_rd(emp.getId_employer(),'p');
		model.addAttribute("notif",noti);
		List<Validation>valid=validation.find_by_id_pr(emp.getId_employer(),'p');
		model.addAttribute("valid",valid);
		List<Validation> arch= validation.find_to_archive('p'); 
		model.addAttribute("arch",arch);
	 return "mot_de_passe";
 }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// lien des pages pour premier responsable
	@SuppressWarnings("null")
	@Secured(value={"ROLE_PR"})
	@RequestMapping(value="/index_premier_responsable")
	public String index_premier_responsable(HttpServletRequest httpServletRequest, Model model){
		String username=rhrc.getLogedUser(httpServletRequest);
		model.addAttribute("username",username);
		
		List<Employer> empl=employer.find_by_nom_utilisateur(username);
		Employer emp=empl.get(0);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		Date dt=new Date();
		String s=formatter.format(dt);
		int annee= Integer.parseInt(s);
		Solde s1= solde.find_by_id_employer_anne(emp.getId_employer(), annee);
		Solde s2= solde.find_by_id_employer_anne(emp.getId_employer(), (annee-1));
		
		long i = s1.getTotal_solde()+s2.getTotal_solde();
		model.addAttribute("sum",i);
		
		
		int j=demande.count(emp.getId_employer());
		model.addAttribute("count",j);
		
		List<Validation> noti=validation.find_by_id_rd(emp.getId_employer(),'p');
		model.addAttribute("notif",noti);
		List<Validation>valid=validation.find_by_id_pr(emp.getId_employer(),'p');
		model.addAttribute("valid",valid);
		
		List<Notification_Reprise> reprise= notification.findAll();
		
		
		
		if(reprise.size()<1){
			String rep="il n'ya aucune reprise pour le moment";
			model.addAttribute("rp",rep);
		}
		else{
		List<String> rep_emp = null;
		String chaine;
		for(int co=0;co<=reprise.size();co++){
			Notification_Reprise rp=reprise.get(co);
			Employer emp_rep=employer.findOne(rp.getId_employer());
			chaine=emp_rep.getNom()+" repris son travaile le "+rp.getDate_retour();
			rep_emp.add(chaine);
		}
		
		model.addAttribute("rp", rep_emp);
		
		}
		
		List<Demande>dem_ann=demande.find_dem_ann('a');
		model.addAttribute("dem_ann",dem_ann);
		
		
		
		List<Validation> arch= validation.find_to_archive('p'); 
		model.addAttribute("arch",arch);
		
		int dn=demande.count_demande_en_cours(emp.getId_employer(), 'n');
		model.addAttribute("dn",dn);
		int d=demande.count_demande_en_cours(emp.getId_employer(), 'o');
		model.addAttribute("do",d);
		
		
		
	 return "index_premier_responsable";
 }
	@Secured(value={"ROLE_PR"})
	@RequestMapping(value="/profil_premier_responsable")
	public String profil_premier_responsable(Model model, HttpServletRequest httpServletRequest){
		String username=rhrc.getLogedUser(httpServletRequest);
		model.addAttribute("username",username);
		List<Employer> ep=employer.find_by_nom_utilisateur(username);
		Employer emp=ep.get(0);
		model.addAttribute("emp",emp);
		
		List<Validation> noti=validation.find_by_id_rd(emp.getId_employer(),'p');
		model.addAttribute("notif",noti);
		List<Validation>valid=validation.find_by_id_pr(emp.getId_employer(),'p');
		model.addAttribute("valid",valid);
		
	 return "profil_premier_responsable";
 }
	
	// lien des pages pour responsable direct
	@SuppressWarnings("null")
	@Secured(value={"ROLE_RD"})
	@RequestMapping(value="/index_responsable_direct")
	public String index_responsable_direct(HttpServletRequest httpServletRequest, Model model){
		
		String username=rhrc.getLogedUser(httpServletRequest);
		model.addAttribute("username",username);
		
		List<Employer> ep=employer.find_by_nom_utilisateur(username);
		Employer emp=ep.get(0);
		
		List<Validation> noti=validation.find_by_id_rd(emp.getId_employer(),'p');
		model.addAttribute("notif",noti);
		String nbt="nouvel demande";
		model.addAttribute("nbt",nbt);
		List<Validation>valid=validation.find_by_id_pr(emp.getId_employer(),'p');
		model.addAttribute("valid",valid);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		Date dt=new Date();
		String s=formatter.format(dt);
		int annee= Integer.parseInt(s);
		Solde s1= solde.find_by_id_employer_anne(emp.getId_employer(), annee);
		Solde s2= solde.find_by_id_employer_anne(emp.getId_employer(), (annee-1));
		
		long i = s1.getTotal_solde()+s2.getTotal_solde();
		model.addAttribute("sum",i);
		
		int j=demande.count(emp.getId_employer());
		model.addAttribute("count",j);
		
		int dn=demande.count_demande_en_cours(emp.getId_employer(), 'n');
		model.addAttribute("dn",dn);
		int d=demande.count_demande_en_cours(emp.getId_employer(), 'o');
		model.addAttribute("do",d);
		
		List<Notification_Reprise> reprise= notification.findAll();
		if(reprise.size()<1){
			String rep="il n'ya aucune reprise pour le moment";
			model.addAttribute("rp",rep);
		}
		else{
		List<String> rep_emp = null;
		String chaine;
		for(int co=0;co<=reprise.size();co++){
			Notification_Reprise rp=reprise.get(co);
			Employer emp_rep=employer.findOne(rp.getId_employer());
			chaine=emp_rep.getNom()+" repris son travaile le "+rp.getDate_retour();
			rep_emp.add(chaine);
		}
		
		model.addAttribute("rp", rep_emp);
		
		}
		
		List<Demande>dem_ann=demande.find_dem_ann('a');
		model.addAttribute("dem_ann",dem_ann);
		
	 return "index_responsable_direct";
 }
	@Secured(value={"ROLE_RD"})
	@RequestMapping(value="/profil_responsable_direct")
	public String profil_responsable_direct(Model model, HttpServletRequest httpServletRequest){
		String username=rhrc.getLogedUser(httpServletRequest);
		model.addAttribute("username",username);
		List<Employer> ep=employer.find_by_nom_utilisateur(username);
		Employer emp=ep.get(0);
		model.addAttribute("emp",emp);
		
		List<Validation> noti=validation.find_by_id_rd(emp.getId_employer(),'p');
		model.addAttribute("notif",noti);
		List<Validation>valid=validation.find_by_id_pr(emp.getId_employer(),'p');
		model.addAttribute("valid",valid);
		
	 return "profil_responsable_direct";
 }
	
	// lien des pages pour employer
	@SuppressWarnings("null")
	@Secured(value={"ROLE_EM"})
	@RequestMapping(value="/index_employer")
	public String index_employer(HttpServletRequest httpServletRequest, Model model){
		
		String username=rhrc.getLogedUser(httpServletRequest);
		model.addAttribute("username",username);
		
		List<Employer> ep=employer.find_by_nom_utilisateur(username);
		Employer emp=ep.get(0);
		
		List<Validation> noti=validation.find_by_id_rd(emp.getId_employer(),'p');
		model.addAttribute("notif",noti);
		List<Validation>valid=validation.find_by_id_pr(emp.getId_employer(),'p');
		model.addAttribute("valid",valid);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		Date dt=new Date();
		String s=formatter.format(dt);
		int annee= Integer.parseInt(s);
		Solde s1= solde.find_by_id_employer_anne(emp.getId_employer(), annee);
		Solde s2= solde.find_by_id_employer_anne(emp.getId_employer(), (annee-1));
		
		long i = s1.getTotal_solde()+s2.getTotal_solde();
		model.addAttribute("sum",i);
		
		int j=demande.count(emp.getId_employer());
		model.addAttribute("count",j);
		
		int dn=demande.count_demande_en_cours(emp.getId_employer(), 'n');
		model.addAttribute("dn",dn);
		int d=demande.count_demande_en_cours(emp.getId_employer(), 'o');
		model.addAttribute("do",d);
		
		List<Notification_Reprise> reprise= notification.findAll();
		if(reprise.size()<1){
			String rep="il n'ya aucune reprise pour le moment";
			model.addAttribute("rp",rep);
		}
		else{
		List<String> rep_emp = null;
		String chaine;
		for(int co=0;co<=reprise.size();co++){
			Notification_Reprise rp=reprise.get(co);
			Employer emp_rep=employer.findOne(rp.getId_employer());
			chaine=emp_rep.getNom()+" repris son travaile le "+rp.getDate_retour();
			rep_emp.add(chaine);
		}
		
		model.addAttribute("rp", rep_emp);
		
		}
		
		List<Demande>dem_ann=demande.find_dem_ann('a');
		model.addAttribute("dem_ann",dem_ann);
		
	 return "index_employer";
 }
	@Secured(value={"ROLE_EM"})
	@RequestMapping(value="/profil_employer")
	public String profil_employer(Model model, HttpServletRequest httpServletRequest){
		String username=rhrc.getLogedUser(httpServletRequest);
		model.addAttribute("username",username);
		List<Employer> ep=employer.find_by_nom_utilisateur(username);
		Employer emp=ep.get(0);
		model.addAttribute("emp",emp);
		List<Validation> noti=validation.find_by_id_rd(emp.getId_employer(),'p');
		model.addAttribute("notif",noti);
		List<Validation>valid=validation.find_by_id_pr(emp.getId_employer(),'p');
		model.addAttribute("valid",valid);
		
	 return "profil_employer";
 }
	
	@RequestMapping("/acceuil2")
	public String index() {
		return "acceuil2";
}
	
}
