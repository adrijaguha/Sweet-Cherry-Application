package com.capgemini.sweetcherry.service;

import java.util.List;
import java.util.Optional;

import com.capgemini.sweetcherry.dto.AddressDto;
import com.capgemini.sweetcherry.dto.OrdersDisplayDto;
import com.capgemini.sweetcherry.dto.OrdersDto;
import com.capgemini.sweetcherry.dto.PaymentDisplayDto;
import com.capgemini.sweetcherry.dto.PaymentDto;
import com.capgemini.sweetcherry.dto.UserDetailsDto;
import com.capgemini.sweetcherry.dto.UserDisplayDto;
import com.capgemini.sweetcherry.exceptions.InvalidIdException;
import com.capgemini.sweetcherry.exceptions.NoAddressExistsException;
import com.capgemini.sweetcherry.exceptions.NoPaymentExistsException;
import com.capgemini.sweetcherry.exceptions.NoSuchAddressExistsException;
import com.capgemini.sweetcherry.exceptions.NoSuchCupcakeCategoryExistsException;
import com.capgemini.sweetcherry.exceptions.NoSuchCupcakeExistsException;
import com.capgemini.sweetcherry.exceptions.NoSuchOrderExistsException;
import com.capgemini.sweetcherry.exceptions.NoSuchUserExistsException;
import com.capgemini.sweetcherry.exceptions.PaymentFailedException;
import com.capgemini.sweetcherry.exceptions.UserNameAndPasswordDoNotMatchRegularExpressionException;
import com.capgemini.sweetcherry.model.Address;
import com.capgemini.sweetcherry.model.CupcakeCategory;
import com.capgemini.sweetcherry.model.CupcakeDetails;
import com.capgemini.sweetcherry.model.Orders;
import com.capgemini.sweetcherry.model.Payment;
import com.capgemini.sweetcherry.model.UserDetails;

public interface SweetCherryService {
	// Login Module
	public Optional<UserDetails> viewUserDetailsById(int userId);
	
	// LOGIN MODULE -------------------------------------------------------------------------------
	
	//Accessible to Both Administrator and Customer
	public String login(String userName, String password) throws NoSuchUserExistsException, UserNameAndPasswordDoNotMatchRegularExpressionException ; 
	public String logout() ;
		
		
	//Administrator services
	public List<UserDisplayDto> allDetailsOfAdminAndUser() throws NoSuchUserExistsException; 
		

	//Customer Services
	public Optional<UserDetails> allUserDetailsById(int userId) throws InvalidIdException;
	public String updateCustomerProfile(UserDetailsDto customer) throws UserNameAndPasswordDoNotMatchRegularExpressionException;
	public UserDetails registerCustomer(UserDetailsDto user) throws UserNameAndPasswordDoNotMatchRegularExpressionException;
	public String modifyPassword(int userId, String oldPassword, String newPassword) throws UserNameAndPasswordDoNotMatchRegularExpressionException;
	
		// CUPCAKE MODULE-------------------------------------------------------------------------------
		
		public CupcakeDetails addCupcakeDetails(CupcakeDetails cupcakedetails) throws NoSuchCupcakeExistsException;
		public List<CupcakeDetails> showCupcakeDetails() throws NoSuchCupcakeExistsException;
		public Optional<CupcakeDetails> findCupcakeDetailsById(int cupcakeId) throws NoSuchCupcakeExistsException;
		public String modifyCupcakeRating(int cupcakeId,int rating) throws NoSuchCupcakeExistsException;
		public String addCupcakeToCart(OrdersDto order) throws NoSuchOrderExistsException;
		public Payment addPaymentDetails(PaymentDto payment) throws NoSuchOrderExistsException;
		public CupcakeCategory addCupcakeCategory(CupcakeCategory cupcakeCategory);
		public String updateCupcakePriceByCupcakeId(int cupcakeId, double price) throws NoSuchCupcakeExistsException;
		public String modifyCupcakeName(int cupcakeId, String cupcakeName) throws NoSuchCupcakeExistsException;
		public String removeCupcakeDetails(int cupcakeId) throws NoSuchCupcakeExistsException;
		public Optional<CupcakeCategory> getCupcakeCategoryById(int cupcakecategoryId) throws NoSuchCupcakeCategoryExistsException;
		public List<CupcakeCategory> showAllCupcakeCategories() throws NoSuchCupcakeCategoryExistsException;
		public String updateCupcakeQuantityById(int cupcakeId,int quantity) throws NoSuchCupcakeExistsException;
	
	// Order module
	public Payment confirmPayment(int paymentId, String status) throws PaymentFailedException;
	public Orders cancelOnlineOrder(int orderId) throws NoSuchOrderExistsException;
	public List<OrdersDisplayDto> showOrderDetailsByUserId(int userId) throws NoSuchOrderExistsException;
	public void addDeliveryAddress(AddressDto address) throws NoSuchUserExistsException;
	public void modifyDeliveryAddress(AddressDto address) throws NoSuchUserExistsException;
	public boolean deleteDeliveryAddress(int addressId) throws NoSuchAddressExistsException;
	public List<OrdersDisplayDto> getAllOrderDetails() throws NoSuchOrderExistsException;
	public List<AddressDto> showAllAddress() throws NoAddressExistsException;
	public List<AddressDto> showAddressByUserId(int userId) throws NoSuchAddressExistsException;
	public Optional<Address> getDeliveryAddress(int addressId) throws NoSuchAddressExistsException;
	public OrdersDisplayDto makeOnlineOrder(int orderId,int addressId) throws NoSuchOrderExistsException;
	public OrdersDisplayDto confirmOrderStatus(int orderId, String status) throws NoSuchOrderExistsException;
	public OrdersDisplayDto getOrderDetailsById(int orderId) throws NoSuchOrderExistsException;
	public List<PaymentDisplayDto> showPaymentById(int orderId) throws NoSuchOrderExistsException;

	public List<PaymentDisplayDto> showAllPayment() throws NoPaymentExistsException;
	
}
