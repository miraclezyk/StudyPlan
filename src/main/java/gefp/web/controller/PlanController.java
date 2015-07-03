package gefp.web.controller;

import java.util.ArrayList;
import java.util.List;

import gefp.model.Cell;
import gefp.model.Checkpoint;
import gefp.model.Department;
import gefp.model.Plan;
import gefp.model.Runway;
import gefp.model.Stage;
import gefp.model.User;
import gefp.model.dao.DepartmentDao;
import gefp.model.dao.PlanDao;
import gefp.model.dao.UserDao;
import gefp.security.SecurityUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"user","plan","department","advisor"})
public class PlanController {

	@Autowired
	PlanDao planDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	DepartmentDao departmentDao;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@RequestMapping(value = "/firstpage", method = RequestMethod.GET)
	public String firstpage(ModelMap models) {
		models.put("user", SecurityUtils.getUser());
		models.put("departments", departmentDao.getDepartments());
		return "firstpage";
	}
	
	@RequestMapping(value = "/department", method = RequestMethod.GET)
	public String department1(@RequestParam Long departmentId, ModelMap models) {
		models.put("department", departmentDao.getDepartment(departmentId));
		return "department";
	}
	
	@RequestMapping(value = "/department", method = RequestMethod.POST)
	public String department2(@RequestParam Long planId, ModelMap models) {
		Department curDepartment = (Department) models.get("department");
		for(Plan plan:curDepartment.getPlans()){
			if(plan.getId().equals(planId)){
				models.put("plan", plan);
			}
		}
		return "redirect:main";
	}
	
	@RequestMapping(value = "/addPlan", method = RequestMethod.POST)
	public String addPlan(@RequestParam String planName, ModelMap models) {
		Plan newPlan = new Plan();
		newPlan.setName(planName);
		Plan curPlan = planDao.savePlan(newPlan);
		Department curDepartment = (Department) models.get("department");
		curDepartment.getPlans().add(curPlan);
		departmentDao.saveDepartment(curDepartment);
		models.put("plan", curPlan);
		return "redirect:main.html";
	}
	
	@RequestMapping(value = "/designate", method = RequestMethod.GET)
	public String designate(@RequestParam Long planId, ModelMap models) {
		Department curDepartment = (Department) models.get("department");
		curDepartment.setCurrentPlan(planDao.getPlan(planId));
		departmentDao.saveDepartment(curDepartment);
		return "department";
	}
	
	@RequestMapping(value = "/studentPage", method = RequestMethod.GET)
	public String student(ModelMap models) {
		User curUser = SecurityUtils.getUser();
		models.put("plan",curUser.getPlan());
		models.put("user", curUser);
		return "studentPage";
	}
	
	@RequestMapping(value = "/advisorPage", method = RequestMethod.GET)
	public String advisor(ModelMap models) {
		models.put("advisor", SecurityUtils.getUser());
		return "advisorPage";
	}
	
	@RequestMapping(value = "/advisorPage", method = RequestMethod.POST)
	public String advisor(@RequestParam String info, ModelMap models) {
		List<User> newUsers = userDao.getUserByName(info.trim().toLowerCase());
		if(newUsers==null){
			newUsers = new ArrayList<User>();
			User newUser = userDao.getUserByCin(info.trim());
			if(newUser==null){
				newUser = userDao.getUserByEmail(info.trim());
			}
			if(newUser!=null){
				newUsers.add(newUser);
			}
		}
		if(newUsers!=null){
			models.put("users", newUsers);
		}
		return "searchResults";
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(@RequestParam String username, ModelMap models) {
		User curStudent = userDao.getUser(username);
		Plan plan = curStudent.getPlan();
		models.put("user", curStudent);
		models.put("plan", plan);
		return "redirect:main.html";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(ModelMap models) {
		models.put("departments", departmentDao.getDepartments());
		return "modify";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(@RequestParam Long departmentId, @RequestParam String password, ModelMap models) {
		User curUser = (User) models.get("user");
		if(!curUser.getMajor().getId().equals(departmentId)){
			Department curDepartment = departmentDao.getDepartment(departmentId);
			curUser.setMajor(curDepartment);
			curUser.setPlan(curDepartment.getCurrentPlan());
		}
		curUser.setPassword(passwordEncoder.encodePassword(password,null));
		userDao.saveUser(curUser);
		return "redirect:studentPage";
	}

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(ModelMap models) {
		models.put("plan", planDao.getPlan(((Plan) models.get("plan")).getId()));
		return "main";
	}

	@RequestMapping(value = "/addStage", method = RequestMethod.GET)
	public String addStage(ModelMap models) {
		models.put("stage", new Stage());
		return "addStage";
	}

	@RequestMapping(value = "/addStage", method = RequestMethod.POST)
	public String addStage(@ModelAttribute Stage stage, ModelMap models) {
		Plan curPlan = (Plan) models.get("plan");
		curPlan.getStages().add(stage);
		for(int i=0; i<curPlan.getRunways().size(); i++){
			Cell cell = new Cell();
			cell.setPlan(curPlan);
			cell.setStage(stage);
			cell.setRunway(curPlan.getRunways().get(i));
			curPlan.getCells().add(cell);
		}	
		planDao.savePlan(curPlan);
		return "redirect:main";
	}

	@RequestMapping(value = "/addRunway", method = RequestMethod.GET)
	public String addRunway(ModelMap models) {
		models.put("runway", new Runway());
		return "addRunway";
	}

	@RequestMapping(value = "/addRunway", method = RequestMethod.POST)
	public String addRunway(@ModelAttribute Runway runway, ModelMap models) {
		Plan curPlan = (Plan) models.get("plan");
		curPlan.getRunways().add(runway);
		for(int i=0; i<curPlan.getStages().size(); i++){
			Cell cell = new Cell();
			cell.setPlan(curPlan);
			cell.setRunway(runway);
			cell.setStage(curPlan.getStages().get(i));
			curPlan.getCells().add(cell);
		}
		planDao.savePlan(curPlan);
		return "redirect:main";
	}

	@RequestMapping(value = "/addCheckpoint", method = RequestMethod.GET)
	public String addCheckpoint(ModelMap models) {
		models.put("checkpoint", new Checkpoint());
		return "addCheckpoint";
	}

	@RequestMapping(value = "/addCheckpoint", method = RequestMethod.POST)
	public String addCheckpoint(@RequestParam Long stageId, @RequestParam Long runwayId, @ModelAttribute Checkpoint checkpoint, ModelMap models) {
		Plan curPlan = (Plan) models.get("plan");
		for(Cell cell:curPlan.getCells()){
			if(cell.getStage().getId().equals(stageId) && cell.getRunway().getId().equals(runwayId)){
				cell.getCheckpoints().add(checkpoint);
				break;
			}
		}
		planDao.savePlan(curPlan);
		return "redirect:main";
	}
	
	@RequestMapping(value = "/editCheckpoint", method = RequestMethod.GET)
	public String editCheckpoint(@RequestParam Long stageId, @RequestParam Long runwayId, @RequestParam Long checkpointId, ModelMap models) {
		Plan curPlan = (Plan) models.get("plan");
		for(Cell curCell:curPlan.getCells()){
			if(curCell.getStage().getId().equals(stageId) && curCell.getRunway().getId().equals(runwayId)){
				for(Checkpoint checkpoint:curCell.getCheckpoints()){
					if(checkpoint.getId().equals(checkpointId)){
						models.put("checkpoint", checkpoint.getDescription());
						break;
					}
				}
			}
		}
		models.put("stageId", stageId);
		models.put("runwayId", runwayId);
		models.put("checkpointId", checkpointId);
		return "editCheckpoint";
	}

	@RequestMapping(value = "/editCheckpoint", method = RequestMethod.POST)
	public String editCheckpoint(@RequestParam String action, @RequestParam Long stageIndex, @RequestParam Long runwayIndex, @RequestParam Long checkpointIndex, @RequestParam String checkpointDes, ModelMap models) {
		Plan curPlan = (Plan) models.get("plan");
		if(action.equals("Confirm")){
			boolean isSameCell = false;
			for(Cell curCell:curPlan.getCells()){
				if(curCell.getStage().getId().equals(stageIndex) && curCell.getRunway().getId().equals(runwayIndex)){
					for(Checkpoint checkpoint:curCell.getCheckpoints()){
						if(checkpoint.getId().equals(checkpointIndex)){
							isSameCell = true;
							checkpoint.setDescription(checkpointDes);
							break;
						}
					}
				}
			}
			if(!isSameCell){
				for(Cell curCell:curPlan.getCells()){
					for(Checkpoint checkpoint:curCell.getCheckpoints()){
						if(checkpoint.getId().equals(checkpointIndex)){
							curCell.getCheckpoints().remove(checkpoint);
							break;
						}
					}
				}
				for(Cell curCell:curPlan.getCells()){
					if(curCell.getStage().getId().equals(stageIndex) && curCell.getRunway().getId().equals(runwayIndex)){
						Checkpoint newCheckpoint = new Checkpoint();
						newCheckpoint.setDescription(checkpointDes);
						curCell.getCheckpoints().add(newCheckpoint);
						break;
					}
				}
			}
		}else{
			for(Cell curCell:curPlan.getCells()){
				if(curCell.getStage().getId().equals(stageIndex) && curCell.getRunway().getId().equals(runwayIndex)){
					for(Checkpoint checkpoint:curCell.getCheckpoints()){
						if(checkpoint.getId().equals(checkpointIndex)){
							curCell.getCheckpoints().remove(checkpoint);
							break;
						}
					}
				}
			}
		}
		
		planDao.savePlan(curPlan);
		return "redirect:main";
	}
	
	@RequestMapping(value = "/editRunway", method = RequestMethod.GET)
	public String editRunway(@RequestParam Long runwayId, ModelMap models) {
		Plan curPlan = (Plan) models.get("plan");
		for(Runway curRunway:curPlan.getRunways()){
			if(curRunway.getId().equals(runwayId)){
				models.put("runwayName", curRunway.getName());
			}
		}
		models.put("runwayId", runwayId);
		return "editRunway";
	}

	@RequestMapping(value = "/editRunway", method = RequestMethod.POST)
	public String editRunway(@RequestParam String runwayNewName, @RequestParam Long runwayIndex, ModelMap models) {
		Plan curPlan = (Plan) models.get("plan");
		for(Runway curRunway:curPlan.getRunways()){
			if(curRunway.getId().equals(runwayIndex)){
				curRunway.setName(runwayNewName);
			}
		}	
		planDao.savePlan(curPlan);
		return "redirect:main";
	}
	
	@RequestMapping(value = "/editStage", method = RequestMethod.GET)
	public String editStage(@RequestParam Long stageId, ModelMap models) {
		Plan curPlan = (Plan) models.get("plan");
		for(Stage curStage:curPlan.getStages()){
			if(curStage.getId().equals(stageId)){
				models.put("stageName", curStage.getName());
			}
		}
		models.put("stageId", stageId);
		return "editStage";
	}

	@RequestMapping(value = "/editStage", method = RequestMethod.POST)
	public String editStage(@RequestParam String stageNewName, @RequestParam Long stageIndex, ModelMap models) {
		Plan curPlan = (Plan) models.get("plan");
		for(Stage curStage:curPlan.getStages()){
			if(curStage.getId().equals(stageIndex)){
				curStage.setName(stageNewName);
			}
		}
		planDao.savePlan(curPlan);
		return "redirect:main";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void update(@RequestParam Long checkpointId, ModelMap models) {
		User curUser = ((User) models.get("user"));
		for(Checkpoint checkpoint:curUser.getCheckpoints()){
			if(checkpoint.getId().equals(checkpointId)){
				curUser.getCheckpoints().remove(checkpoint);
				models.put("user", userDao.saveUser(curUser));
				return;
			}
		}
		Plan curPlan = (Plan) models.get("plan");
		for(Cell cell:curPlan.getCells()){
			for(Checkpoint checkpoint:cell.getCheckpoints()){
				if(checkpoint.getId().equals(checkpointId)){
					curUser.getCheckpoints().add(checkpoint);
					models.put("user", userDao.saveUser(curUser));
					return;
				}
			}
		}
		
		
	}
	
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void order(@RequestParam String direction, @RequestParam int orginIndex, @RequestParam int preIndex, ModelMap models) {
		Plan curPlan = (Plan) models.get("plan");
		List<Stage> stages = new ArrayList<Stage>();
		for(int k=0; k<curPlan.getStages().size(); k++){
			stages.add(curPlan.getStages().get(k).clone());
		}
		
		Stage stage = stages.get(preIndex);
		stages.set(preIndex, stages.get(orginIndex));
		
		if(direction.equals("down")){			
			int i;
			for(i=orginIndex; i<preIndex-1; i++){
				stages.set(i, stages.get(i+1));
			}
			stages.set(i, stage);
		}else{
			int i;	
			for(i=orginIndex; i>preIndex+1; i--){
				stages.set(i, stages.get(i-1));
			}
			stages.set(i, stage);
		}
			
		curPlan.getStages().clear();
		planDao.savePlan(curPlan);
		
		for(int j=0; j<stages.size(); j++){
			curPlan.getStages().add(stages.get(j));
		}
		planDao.savePlan(curPlan);
		return;
	}
}
