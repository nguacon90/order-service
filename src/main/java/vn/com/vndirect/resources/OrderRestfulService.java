package vn.com.vndirect.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import vn.com.vndirect.model.Order;
import vn.com.vndirect.model.OrderResult;
import vn.com.vndirect.model.ResponseMessage;
import vn.com.vndirect.stock.OrderServiceSender;
import vn.com.vndirect.validator.Validator;
import vn.com.vndirect.validator.exception.ValidateException;

@Path("orders")
public class OrderRestfulService{

	private static final Logger logger = Logger.getLogger(OrderRestfulService.class);
	
	private Validator orderValidator;
	
	private OrderServiceSender orderServiceSender;
	
	@POST
	@Path("/placeOrder")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response placeOrder(Order  order){
		ResponseMessage responseMessage = new ResponseMessage(); 
		try {
			orderValidator.validate(order);
			String orderId = orderServiceSender.placeOrder(order);
			order.setOrderId(orderId);
			responseMessage = ResponseMessage.SUCCESS;
		} catch (ValidateException e) {
			logger.error(e.getMessage());
			responseMessage.setCode(e.getCode());
			responseMessage.setMessage(e.getMessage());
		}
		OrderResult result = new OrderResult(responseMessage, order);
		return Response.status(Status.OK).entity(result).build();
	}
	
	public void setOrderValidator(Validator orderValidator) {
		this.orderValidator = orderValidator;
	}

	public void setOrderServiceSender(OrderServiceSender orderServiceSender) {
		this.orderServiceSender = orderServiceSender;
	}

}
