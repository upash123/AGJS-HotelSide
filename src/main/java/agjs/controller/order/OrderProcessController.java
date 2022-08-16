package agjs.controller.order;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import agjs.bean.order.OrderSubmitdVo;
import agjs.bean.order.SalesOrderHeaderPo;
import agjs.service.order.OrderProcessService;

@RestController
@RequestMapping(path = { "/main/orderprocess" })
public class OrderProcessController {

	@Autowired
	private OrderProcessService orderProcessService;

	// 訂單主流程
	@PostMapping("/check/user")
	public SalesOrderHeaderPo ordrSubmit(@RequestBody OrderSubmitdVo orderSubmitdVo) {

		System.out.println("提交訂單 流程");
		System.out.println(orderSubmitdVo.getSoh());
		System.out.println(orderSubmitdVo.getSoiList());
		System.out.println(orderSubmitdVo.getJiList());

		try {
			return orderProcessService.orderProcess(orderSubmitdVo);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@PostMapping("/ecpay/success")
	public SalesOrderHeaderPo ordrSubmit(HttpServletRequest request) {

		System.out.println("綠介成功返回");
		System.out.println(request);

		return null;
	}

//	@PostMapping(path = "/ecpay/pay")
//	public ResponseEntity<String> ordrSubmit(@RequestBody SalesOrderHeaderPo salesOrderHeaderPo) throws IOException {
//
//		System.out.println("綠介 流程");
//		String takeOrder = orderProcessService.callAllInOneService(salesOrderHeaderPo);
//		System.out.println(takeOrder);
//		return ResponseEntity.ok(takeOrder);
//	}

//	@PostMapping(path = "/ecpay/pay")
//	public String ordrSubmit(@RequestBody SalesOrderHeaderPo salesOrderHeaderPo) throws IOException {
//
//		System.out.println("綠介 流程");
//		String takeOrder = orderProcessService.callAllInOneService(salesOrderHeaderPo);
//		System.out.println(takeOrder);
//		return takeOrder;
//	}

}
