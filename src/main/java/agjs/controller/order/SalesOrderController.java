package agjs.controller.order;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import agjs.bean.journey.JourneyItemPo;
import agjs.bean.journey.JourneyItemSelectVo;
import agjs.bean.journey.JourneyItemVo_2;
import agjs.bean.order.SalesOrderFrontendAdminVo;
import agjs.bean.order.SalesOrderHeaderPo;
import agjs.bean.order.SalesOrderItemPo;
import agjs.bean.order.SalesOrderItemVo;
import agjs.bean.order.SalesOrderVo;
import agjs.service.order.SalesOrderHeaderService;
import agjs.service.order.SalesOrderItemService;
import agjs.service.user.UserService;

@RestController
@RequestMapping(path = {"/admin/order"})
public class SalesOrderController {
	
	@Autowired
	private SalesOrderHeaderService service;
	
	@Autowired
	private SalesOrderItemService itemService;
	
	@Autowired
	private MessageSource messageSource;
	
//	@RequestMapping(path = {"/main/reservation_details_.html"})
//	public String handlerMethod(Model model, String username, HttpSession session) {
//		
//		Map<String, String> errors = new HashMap<String, String>();
//		model.addAttribute("errors", errors);
//		
//		if(username == null || username.length() == 0) {
//			errors.put("username", "???????????????(Controller)");
//		}
//		if(errors!= null && errors.isEmpty()) {
//			return "????????????";
//		}
//		return "reservation_details_.html";
//	}
	
	//???????????? (?????????????????????????????????service)
//	public String handlerMethod(Model model, HttpSession session, String USER_NAME, String USER_IDENTITYNUMBER, String USER_PHONE) {
//		Map<String, String> errors = new HashMap<String, String>();
//		model.addAttribute("errors", errors);
//		
//		if(USER_NAME == null || USER_NAME.length() == 0) {
//			errors.put("USER_NAME", "???????????????");
//		}
//		if(USER_IDENTITYNUMBER == null || USER_IDENTITYNUMBER.length() == 0) {
//			errors.put("USER_IDENTITYNUMBER", "????????????????????????");
//		} //????????????????????????????????????????????????????????? else if(USER_IDENTITYNUMBER == ) {errors.put("USER_IDENTITYNUMBER", "???????????????????????????????????????????????????????");}
//		
//		if(USER_PHONE == null || USER_PHONE.length() ==0) {
//			errors.put("USER_PHONE", "???????????????");
//		}
//		// else if(USER_PHONE) {
//			//????????????09??????
//		//	errors.put("USER_PHONE", "??????????????????");
//		//}
//		if(errors!= null && errors.isEmpty()) {
//			return "reservation_details_.html";
//		}
//		
//		//session.setAttribute("user", bean);
//		
//		return "redirect: xxxx.html"; //?????????????????????
//	}
	
	//????????????
	@GetMapping("/create/odr")
	public SalesOrderHeaderPo create() {
		//UserPo customer = UserService.getusername();
		//if??????
		
		return null;
	}
	
	//????????????????????????
//	@PostMapping("/search/odr")
//	public List<SalesOrderHeaderPo> getHeader(Model model) {
//		return service.getAll();
//	}
	
	//??????????????????(????????????????????????????????????????????????)
	@PostMapping("/search/odr")
	public List<SalesOrderVo> getAll(Model model) {
		return service.selectOrder();
	}

//????????????????????????????????????
	@PostMapping("/search/date")
	public List<SalesOrderHeaderPo> selecctByOrderStartDate(Date date) {
		System.out.println("select order by start date(Controller):");
		System.out.println(date);
		return service.selecctByOrderStartDate(date);
	}
	
//?????????????????????????????????
	@PostMapping("/search/roomItem")
		public List<SalesOrderItemVo> selectByIdForRoom(@RequestBody SalesOrderItemVo vo) {
			Integer id = vo.getSalesOrderHeaderId();
		return itemService.getOrderItemByHeaderId(id);
	}
	
//?????????????????????????????????
	@PostMapping("/search/journeyItem")
	public List<JourneyItemVo_2> selectByIdForJourney(@RequestBody SalesOrderVo vo) {
		Integer id = vo.getSalesOrderHeaderId();
		return service.selectJourneyItems(id);
	}
	
//?????????????????????????????????????????????post?
	@GetMapping("/search/byUser")
	public List<SalesOrderHeaderPo> selectByUserId(Integer userId){
		return service.selectByUserId(userId);
	}
	
//?????????????????????????????????
//	
//	@DeleteMapping("/delete")
//	public boolean delete(@RequestBody Integer id) {
//		return service.delete(id);
//	}

//??????
	@PatchMapping("/update/odr")
	public SalesOrderFrontendAdminVo updateOrder(@RequestBody SalesOrderFrontendAdminVo salesOrderFrontendAdminVo) throws Exception{
		System.out.println("??????!!!");
		salesOrderFrontendAdminVo.getSalesOrderHeaderId();
		salesOrderFrontendAdminVo.getSalesOrderStartDate();
		salesOrderFrontendAdminVo.getSalesOrderEndDate();
		salesOrderFrontendAdminVo.getSalesOrderStatus();
		
		System.out.println("?????????????????????:");
		System.out.println(salesOrderFrontendAdminVo.getSalesOrderStartDate());
		System.out.println(salesOrderFrontendAdminVo.getSalesOrderEndDate());
		System.out.println(salesOrderFrontendAdminVo.getSalesOrderStatus());
		
		service.updateSalesOrder(salesOrderFrontendAdminVo);
		 
		 return salesOrderFrontendAdminVo;
	}
	
}
