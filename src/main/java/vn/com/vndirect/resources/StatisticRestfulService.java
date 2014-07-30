package vn.com.vndirect.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import vn.com.vndirect.model.Order;
import vn.com.vndirect.model.ResponseMessage;
import vn.com.vndirect.model.TopAccountResult;
import vn.com.vndirect.model.TopOrderResult;
import vn.com.vndirect.services.StatisticService;

@Path("statistic/getTop")
public class StatisticRestfulService {

	private int topOrderNumber;
	private int topAccountNumber;
	
	private StatisticService statisticService;
	
	@GET
	@Path("/biggestordervalue")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTopBiggestOrderValue() {
		List<Order> topOrders = statisticService.getTopOrder(topOrderNumber);
		TopOrderResult topOrderResult =  new TopOrderResult(ResponseMessage.SUCCESS, topOrders);
		return Response.status(Status.OK).entity(topOrderResult).build();
	}
	
	@GET
	@Path("/account")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTopAccount() {
		List<String> accounts = statisticService.getTopAccount(topAccountNumber);
		TopAccountResult topAccountResult =  new TopAccountResult(ResponseMessage.SUCCESS, accounts);
		return Response.status(Status.OK).entity(topAccountResult).build();
	}

	public void setTopOrderNumber(int topOrderNumber) {
		this.topOrderNumber = topOrderNumber;
	}

	public void setStatisticService(StatisticService statisticService) {
		this.statisticService = statisticService;
	}

	public void setTopAccountNumber(int topAccountNumber) {
		this.topAccountNumber = topAccountNumber;
	}
}
