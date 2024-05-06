package mon.pfe.controlle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import mon.pfe.entity.Demande;
import mon.pfe.entity.Employer;
import mon.pfe.entity.Notification_Reprise;
import mon.pfe.entity.Solde;
import mon.pfe.entity.Validation;
import mon.pfe.repository.DemandeRepository;
import mon.pfe.repository.EmployerRepository;
import mon.pfe.repository.NotificationRepriseRepository;
import mon.pfe.repository.SoldeRepository;
import mon.pfe.repository.ValidationRepository;

@Controller
@Secured(value={"ROLE_RH","ROLE_PR","ROLE_RD","ROLE_EM"})
public class LoginController {
	
	@Autowired
	public EmployerRepository employer;
	@Autowired
	public SoldeRepository solde;
	@Autowired
	public DemandeRepository demande;
	@Autowired
	public NotificationRepriseRepository notification;
	@Autowired
	public ValidationRepository validation ;
	
	public String getLogedUser(HttpServletRequest httpServletRequest ){
		HttpSession httpSession=httpServletRequest.getSession();
		SecurityContext securityContext=(SecurityContext) 
				httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		String login = securityContext.getAuthentication().getName();
		Map<String, Object> params=new HashMap<>();
		params.put("login",login);
		return login;
				
	}

	 @SuppressWarnings("null")
	@RequestMapping(value="/getrolesuser")
		public String getRolesUser(HttpServletRequest httpServletRequest, Model model ){
		 String username=getLogedUser(httpServletRequest);
			model.addAttribute("username",username);
			List<Employer> empl=employer.find_by_nom_utilisateur(username);
			Employer emp=empl.get(0);
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
			
			Date dt=new Date();
			String s=formatter.format(dt);
			int annee= Integer.parseInt(s);
			
			int ts=solde.count_by_id_employer_anne(emp.getId_employer(),annee);
			
			if(ts>0){
			Solde sol=solde.find_by_id_employer_anne(emp.getId_employer(),annee );
				Date dm=dt;
				SimpleDateFormat month = new SimpleDateFormat("MM");
				int mois=Integer.parseInt(month.format(dt));
				long mil=1000*60*60*24;
				long dif=( dm.getTime() - sol.getMise_a_jours().getTime() )/mil;
				
				if((mois==1)||(mois==3)||(mois==5)||(mois==7)||(mois==8)||(mois==10)||(mois==12)){
					long i= (long) (dif/31*2.5);
					solde.save(new Solde(sol.getId_Solde(), sol.getTotal_solde()+i, sol.getSolde_materniter(), sol.getSolde_specifique(), sol.getSolde_maladie() , sol.getAnnee_solde(), sol.getId_employer(), dt));
				}
				
				if((mois==4)||(mois==6)||(mois==9)||(mois==11)){
					long i= (long) (dif/30*2.5);
					solde.save(new Solde(sol.getId_Solde(), sol.getTotal_solde()+i, sol.getSolde_materniter(), sol.getSolde_specifique(), sol.getSolde_maladie() , sol.getAnnee_solde(), sol.getId_employer(),dt));
				}
				
				if(mois==2){
					if((annee/4)==0){
						long i= (long) (dif/29*2.5);
						solde.save(new Solde(sol.getId_Solde(), sol.getTotal_solde()+i, sol.getSolde_materniter(), sol.getSolde_specifique(), sol.getSolde_maladie() , sol.getAnnee_solde(), sol.getId_employer(), dt));
					}
					else {
						long i= (long) (dif/28*2.5);
						solde.save(new Solde(sol.getId_Solde(), sol.getTotal_solde()+i, sol.getSolde_materniter(), sol.getSolde_specifique(), sol.getSolde_maladie() , sol.getAnnee_solde(), sol.getId_employer(), dt));
					}
					
				}
					
				
				
			}
			else {
				
				solde.save(new Solde(0,60,6,28,annee,emp.getId_employer(),dt));
				
			}
			List<Validation> noti=validation.find_by_id_rd(emp.getId_employer(),'p');
			model.addAttribute("notif",noti);
			
			List<Validation>valid=validation.find_by_id_pr(emp.getId_employer(),'p');
			model.addAttribute("valid",valid);
			
			 
			int dn=demande.count_demande_en_cours(emp.getId_employer(), 'n');
			model.addAttribute("dn",dn);
			int d=demande.count_demande_en_cours(emp.getId_employer(), 'o');
			model.addAttribute("do",d);
			
			int i=solde.sum(emp.getId_employer());
			model.addAttribute("sum",i);
			int j=demande.count(emp.getId_employer());
			model.addAttribute("count",j);
			
			List<Notification_Reprise> list=notification.findAll();
			model.addAttribute("list_eprise",list);
			
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
			
			
			HttpSession httpSession=httpServletRequest.getSession();
			SecurityContext securityContext=(SecurityContext) 
					httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
			List<String> roles=new ArrayList<>();
			for(GrantedAuthority ga:securityContext.getAuthentication().getAuthorities()){
				roles.add(ga.getAuthority());
			}
			if (roles.get(0).equals("ROLE_RH")){
				
				List<Validation> arch= validation.find_to_archive('p');
				model.addAttribute("arch",arch);
				
				
				return "index_ressource_humaine";
			}
			else if (roles.get(0).equals("ROLE_PR")){
				
				return "index_premier_responsable";
			}
			else if (roles.get(0).equals("ROLE_RD")){
				
				return "index_responsable_direct";
			}
			else if (roles.get(0).equals("ROLE_EM")){
				
				return "index_employer";
			}
			else{
			
				return "error";
			}
					
		}

}
