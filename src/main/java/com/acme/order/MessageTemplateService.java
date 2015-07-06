package com.acme.order;

public interface MessageTemplateService {

	DeliveryTemplate getDeliveryTemplpate();

	OrderCanceledTemplate getCancelTemplate();
	
	OrderCreatedTemplate getCreatedTemplate();

}
