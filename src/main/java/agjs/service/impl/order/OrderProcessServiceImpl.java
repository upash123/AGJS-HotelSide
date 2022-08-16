package agjs.service.impl.order;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.objenesis.instantiator.annotations.Typology;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import agjs.bean.journey.JourneyItemPo;
import agjs.bean.order.ECPayVo;
import agjs.bean.order.OrderSubmitdVo;
import agjs.bean.order.SalesOrderHeaderPo;
import agjs.bean.order.SalesOrderItemPo;
import agjs.bean.room.RoomPo;
import agjs.bean.room.RoomUsedRecordPo;
import agjs.bean.user.UserPo;
import agjs.dao.journey.JourneyItemDao;
import agjs.dao.order.SalesOrderHeaderDao_2;
import agjs.dao.order.SalesOrderItemDao_2;
import agjs.dao.room.RoomDao_2;
import agjs.dao.room.RoomUsedRecordDao;
import agjs.dao.user.UserDao_3;
import agjs.ecpay.payment.integration.service.AllInOneServiceImpl;
import agjs.service.order.OrderProcessService;
import agjs.service.order.SalesOrderItemService;

@Service
public class OrderProcessServiceImpl implements OrderProcessService {

	@Autowired
	private SalesOrderItemService salesOrderItemService;

	private AllInOneServiceImpl allInOneService;;

	@Autowired
	private UserDao_3 userDao3;
	@Autowired
	private RoomDao_2 roomDao2;
	@Autowired
	private RoomUsedRecordDao recordDao;
	@Autowired
	private SalesOrderItemDao_2 salesOrderItemDao_2;
	@Autowired
	private SalesOrderHeaderDao_2 salesOrderHeaderDao_2;
	@Autowired
	private JourneyItemDao journeyItemDao;

	@Override
	@Transactional(readOnly = true)
	public UserPo checkOrderUser(UserPo user) {

		if ("".equals(user.getUserName()) || "".equals(user.getUserIdentityNumber())
				|| "".equals(user.getUserBirthday())) {
			return null;
		} else {
			return userDao3.selectOrderUser(user);
		}
	}

	@Override
	@Transactional
	public SalesOrderHeaderPo orderProcess(OrderSubmitdVo orderSubmitdVo) throws Exception {

		UserPo user = checkOrderUser(orderSubmitdVo.getUser());

		try {
			if (user != null && checkSOH(orderSubmitdVo.getSoh())) {
				orderSubmitdVo.getSoh().setMsg("歡迎您再次光臨，即將前往綠界支付");
				SalesOrderHeaderPo po;
				po = createOrder(orderSubmitdVo, user);
				System.out.println(po);
				return po;

			} else if (user == null) {
				// 建立會員資料

				orderSubmitdVo.getSoh().setMsg("已將此資料建立會員，即將前往綠界支付");
				SalesOrderHeaderPo po = createOrder(orderSubmitdVo, user);
				System.out.println(po);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	};

	@Override
	@Transactional(rollbackFor = Exception.class)
	public SalesOrderHeaderPo createOrder(OrderSubmitdVo orderSubmitdVo, UserPo user) throws Exception {

		Integer sohId = createSOH(orderSubmitdVo.getSoh(), user.getUserId());
		System.out.println("建立訂單:" + sohId);
		System.out.println("建立訂單明細:" + createSalesOrderItem(orderSubmitdVo.getSoiList(), sohId));
		System.out.println("建立行程明細:" + createjourneyItem(orderSubmitdVo.getJiList(), sohId));
		System.out.println(
				"建立房間使用紀錄:" + createRoomUsedRecord(user, orderSubmitdVo.getSoh(), orderSubmitdVo.getSoiList(), sohId));
		orderSubmitdVo.getSoh().setSalesOrderHeaderId(sohId);
		orderSubmitdVo.getSoh().setSalesOrderStatusId(1);
		orderSubmitdVo.getSoh().setTradeDesc(orderSubmitdVo.getTradeDesc());
		return orderSubmitdVo.getSoh();
	}

	@Override
	@Transactional
	public Integer createSOH(SalesOrderHeaderPo salesOrderHeaderPo, Integer userId) {

		salesOrderHeaderPo.setUserId(userId);
		salesOrderHeaderPo.setSalesOrderStatusId(1);
		System.out.println("po=" + salesOrderHeaderPo);
		Serializable id = salesOrderHeaderDao_2.insert(salesOrderHeaderPo);
		if (id != null) {
			return Integer.parseInt(id.toString());
		} else {
			return null;
		}

	}

	@Override
	public Boolean checkSOH(SalesOrderHeaderPo salesOrderHeaderPo) {

		if (salesOrderHeaderPo != null && salesOrderHeaderPo.getCreateDate() != null
				&& salesOrderHeaderPo.getOrderEndDate() != null && salesOrderHeaderPo.getOrderStartDate() != null
				&& salesOrderHeaderPo.getRoomPrice() != null) {
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public List<Integer> createSalesOrderItem(List<SalesOrderItemPo> salesOrderItemPoList, Integer sohId) {

		if (salesOrderItemPoList != null && sohId != null) {
			List<SalesOrderItemPo> poList = checkSalesOrderItem(salesOrderItemPoList, sohId);

			if (poList != null) {
				List<Integer> soiIdList = new ArrayList<Integer>();
				for (int i = 0; i < poList.size(); i++) {
					System.out.println("po=" + poList.get(i));
					Serializable sli = salesOrderItemDao_2.insert(poList.get(i));
					System.out.println("建立 房型明細:" + sli);
					soiIdList.add(Integer.parseInt(sli.toString()));
				}
				return soiIdList;
			}
			return null;
		} else {
			return null;
		}
	}

	@Override
	public List<SalesOrderItemPo> checkSalesOrderItem(List<SalesOrderItemPo> salesOrderItemPoList, Integer sohId) {

		for (int i = 0; i < salesOrderItemPoList.size(); i++) {
			SalesOrderItemPo po = salesOrderItemPoList.get(i);
			if (po.getOrderRoomPrice() != null && po.getOrderRoomQuantity() != null && po.getRoomStyleId() != null) {
				salesOrderItemPoList.get(i).setSalesOrderHeaderId(sohId);
			} else {
				return null;
			}
		}
		return salesOrderItemPoList;
	}

	@Override
	@Transactional
	public List<Integer> createjourneyItem(List<JourneyItemPo> journeyItemPoList, Integer sohId) {

		if (journeyItemPoList != null && sohId != null) {
			List<JourneyItemPo> poList = checkjourneyItem(journeyItemPoList, sohId);

			if (poList != null) {
				List<Integer> soiIdList = new ArrayList<Integer>();
				for (int i = 0; i < poList.size(); i++) {
					Serializable sli = journeyItemDao.insert(poList.get(i));
					System.out.println("insert 行程訂單:" + sli);
					soiIdList.add(Integer.parseInt(sli.toString()));
				}
				return soiIdList;
			}
			return null;
		} else {
			return null;
		}
	}

	@Override
	public List<JourneyItemPo> checkjourneyItem(List<JourneyItemPo> journeyItemPoList, Integer sohId) {

		for (int i = 0; i < journeyItemPoList.size(); i++) {
			JourneyItemPo po = journeyItemPoList.get(i);
			if (po.getJourneyId() != null && po.getJourneyDate() != null && po.getAdults() != null) {
				journeyItemPoList.get(i).setSohId(sohId);
			} else {
				return null;
			}
		}
		return journeyItemPoList;
	}

	@Override
	public String callAllInOneService(SalesOrderHeaderPo po) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String orderDate = sdf.format(new Date());
		allInOneService = new AllInOneServiceImpl();

		ECPayVo ecPayVo = new ECPayVo();
		Integer amount = po.getJourneyPrice() + po.getRoomPrice();
		ecPayVo.setMerchantTradeNo(po.getSalesOrderHeaderId().toString());
		ecPayVo.setItemName(po.getSalesOrderHeaderId().toString());
		ecPayVo.setMerchantTradeDate(orderDate);
		ecPayVo.setTotalAmount(amount.toString());
		ecPayVo.setTradeDesc(po.getTradeDesc());
		ecPayVo.setClientBackURL("http://localhost:8081/AGJS/user_login.html");
		ecPayVo.setReturnURL("http://localhost:8081/AGJS/main/orderprocess/ecpay/success");

		return allInOneService.payment(ecPayVo);
	}

	@Override
	public List<RoomUsedRecordPo> createRoomUsedRecord(UserPo user, SalesOrderHeaderPo salesOrderHeaderPo,
			List<SalesOrderItemPo> salesOrderItemPoList, Integer sohId) {

		List<RoomUsedRecordPo> poList = checkRoomUsedRecord(user, salesOrderHeaderPo, salesOrderItemPoList, sohId);
		for (RoomUsedRecordPo po : poList) {
			if (po != null) {
				System.out.println("insert:" + po);
				recordDao.update(po);
			}
		}
		return poList;
	}

	@Override
	public List<RoomUsedRecordPo> checkRoomUsedRecord(UserPo user, SalesOrderHeaderPo salesOrderHeaderPo,
			List<SalesOrderItemPo> salesOrderItemPoList, Integer sohId) {

		if (salesOrderItemPoList != null && sohId != null) {

			List<RoomUsedRecordPo> roomUsedRecordPoList = new ArrayList<RoomUsedRecordPo>();

			for (SalesOrderItemPo po : salesOrderItemPoList) {
				if (po.getOrderRoomQuantity() != null && po.getRoomStyleId() != null) {

					List<?> roomPoList = roomDao2.selectForRoomStyleId(salesOrderHeaderPo.getOrderStartDate(),
							salesOrderHeaderPo.getOrderEndDate(), po.getRoomStyleId(), po.getOrderRoomQuantity());
					System.out.println("room==" + roomPoList);

					if (roomPoList.size() == po.getOrderRoomQuantity()) {
						for (int i = 0; i < po.getOrderRoomQuantity(); i++) {
							RoomUsedRecordPo roomUsedRecordPo = new RoomUsedRecordPo();
							roomUsedRecordPo.setStartDate(salesOrderHeaderPo.getOrderStartDate());
							roomUsedRecordPo.setEndDate(salesOrderHeaderPo.getOrderEndDate());
							roomUsedRecordPo.setOderHeaderId(sohId);
							roomUsedRecordPo.setUserName(user.getUserName());
							roomUsedRecordPo.setRoomId((Integer) roomPoList.get(i));
							roomUsedRecordPoList.add(roomUsedRecordPo);
						}
					} else {
						System.out.println("數量錯誤");
						return null;
					}
				} else {
					return null;
				}
			}
			return roomUsedRecordPoList;
		} else {
			return null;
		}
	}

}
