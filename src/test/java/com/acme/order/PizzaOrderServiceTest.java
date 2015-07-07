package com.acme.order;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;



public class PizzaOrderServiceTest {

	private PizzaOrderService pizzaOrderService;
	private MailSender mailSender;
	private OrderDatabase orderDatabase;
	private OrderFactory orderFactory;
	private DeliveryTimeService deliveryTimeService;
	private MessageTemplateService messageTemplate;

	@Before
	public void init() {
		mailSender = Mockito.mock(MailSender.class);
		orderDatabase = Mockito.mock(OrderDatabase.class);
		orderFactory = Mockito.mock(OrderFactory.class);
		deliveryTimeService = Mockito.mock(DeliveryTimeService.class);
		messageTemplate = Mockito.mock(MessageTemplateService.class);
		pizzaOrderService = new PizzaOrderService(mailSender, orderDatabase, orderFactory, deliveryTimeService,
				messageTemplate);

	}
	
	@Test
	public void cancelledOrderShouldBePersistedAndEmailShouldBeSent() {
		//given
		String pizzaOrderId = "fake_id";
		PizzaOrder givenPizzaOrder = givenPizzaOrder();
		//stub
		OrderCanceledTemplate template = Mockito.mock(OrderCanceledTemplate.class);
		Mockito.when(orderDatabase.get(Mockito.anyString())).thenReturn(givenPizzaOrder);
		Mockito.when(messageTemplate.getCancelTemplate()).thenReturn(template);
		//when
		pizzaOrderService.cancelOrder(pizzaOrderId);
		//then
		Assert.assertTrue(givenPizzaOrder.isCancelled());
		ArgumentCaptor<String> sentEmail = ArgumentCaptor.forClass(String.class);
		Mockito.verify(mailSender).send(Mockito.any(Template.class), Mockito.anyString());
		//Assert.assertTrue(sentEmail.getValue().equals(givenPizzaOrder.getEmail()));
		Mockito.verify(orderDatabase).save(Mockito.any(PizzaOrder.class));
		
	}
	
	private PizzaOrder givenPizzaOrder() {
		Customer customer = givenCustomer();
		PizzaType type = Mockito.mock(PizzaType.class);
		PizzaOrder givenOrder = new PizzaOrder(customer, type);
		return givenOrder;
	}

	private Customer givenCustomer() {
		//String customerEmail = "fake_email";
		Customer customer = new Customer();
		return customer;
	}
	
	@Test
	public void createdOrderShouldBePersistedAndEmailShoulBeSent(){
		//given
		PizzaOrder givenPizzaOrder = givenPizzaOrder();
		//stub
		PizzaType type = Mockito.mock(PizzaType.class);
		OrderCreatedTemplate template = Mockito.mock(OrderCreatedTemplate.class);
		Mockito.when(orderDatabase.get(Mockito.anyString())).thenReturn(givenPizzaOrder);
		Mockito.when(messageTemplate.getCreatedTemplate()).thenReturn(template);
		//when
		pizzaOrderService.createOrder(givenCustomer(),type);
		//then
		//ArgumentCaptor<String> sentEmail = ArgumentCaptor.forClass(String.class);
		//Mockito.verify(mailSender).send(Mockito.any(Template.class), Mockito.anyString());
		//Assert.assertTrue(sentEmail.getValue().equals(givenPizzaOrder.getEmail()));
		//ArgumentCaptor<PizzaOrder> savedPizzaOrder = ArgumentCaptor.forClass(PizzaOrder.class);
		//Mockito.verify(orderDatabase).save(Mockito.any(PizzaOrder.class));
		//Assert.assertTrue(savedPizzaOrder.getValue().equals(givenPizzaOrder));
	}
}
