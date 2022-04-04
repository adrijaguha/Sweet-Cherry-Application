package com.capgemini.sweetcherry.service;

import java.util.List;
import java.util.Optional;

import com.capgemini.sweetcherry.dto.AddressDto;
import com.capgemini.sweetcherry.dto.OrdersDisplayDto;
import com.capgemini.sweetcherry.dto.OrdersDto;
import com.capgemini.sweetcherry.dto.PaymentDto;
import com.capgemini.sweetcherry.dto.UserDetailsDto;
import com.capgemini.sweetcherry.exceptions.InvalidIdException;
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
	public List<UserDetails> allDetailsOfAdminAndUser() throws NoSuchUserExistsException; 
		

	//Customer Services
	public Optional<UserDetails> allUserDetailsById(int userId) throws InvalidIdException;
	public String updateCustomerProfile(UserDetailsDto customer) throws UserNameAndPasswordDoNotMatchRegularExpressionException;
	public UserDetails registerCustomer(UserDetailsDto user) throws UserNameAndPasswordDoNotMatchRegularExpressionException;
	public String modifyPassword(int userId, String oldPassword, String newPassword) throws UserNameAndPasswordDoNotMatchRegularExpressionException;
	
		// CUPCAKE MODULE-------------------------------------------------------------------------------
		
		public CupcakeDetails addCupcakeDetails(CupcakeDetails cupcakedetails) throws NoSuchCupcakeExistsException;
		public List<CupcakeDetails> showCupcakeDetails() throws NoSuchCupcakeExistsException;
		public Optional<CupcakeDetails> findCupcakeDetailsById(int cupcakeId) throws NoSuchCupcakeExistsException;
		public Optional<CupcakeDetails> modifyCupcakeRating(int cupcakeId,int rating) throws NoSuchCupcakeExistsException;
		public String addCupcakeToCart(OrdersDto order) throws NoSuchOrderExistsException;
		public Payment addPaymentDetails(PaymentDto payment) throws NoSuchOrderExistsException;
		public CupcakeCategory addCupcakeCategory(CupcakeCategory cupcakeCategory);
		public Optional<CupcakeDetails> updateCupcakePriceByCupcakeId(int cupcakeId, double price) throws NoSuchCupcakeExistsException;
		public Optional<CupcakeDetails> modifyCupcakeName(int cupcakeId, String cupcakeName) throws NoSuchCupcakeExistsException;
		public String removeCupcakeDetails(int cupcakeId) throws NoSuchCupcakeExistsException;
		public Optional<CupcakeCategory> getCupcakeCategoryById(int cupcakecategoryId) throws NoSuchCupcakeCategoryExistsException;
		public List<CupcakeCategory> showAllCupcakeCategories() throws NoSuchCupcakeCategoryExistsException;
	
	// Order module
	public Payment makeOnlinePayment(int paymentId, String status) throws PaymentFailedException;
	public Orders cancelOnlineOrder(int orderId) throws NoSuchOrderExistsException;
	public List<OrdersDisplayDto> showOrderDetailsByUserId(int userId) throws NoSuchOrderExistsException;
	public void addDeliveryAddress(AddressDto address) throws NoSuchUserExistsException;
	public void modifyDeliveryAddress(AddressDto address) throws NoSuchUserExistsException;
	public boolean deleteDeliveryAddress(int addressId) throws NoSuchAddressExistsException;
	public List<OrdersDisplayDto> getAllOrderDetails() throws NoSuchOrderExistsException;
	public Optional<Address> getDeliveryAddress(int addressId) throws NoSuchAddressExistsException;
	public OrdersDisplayDto makeOnlineOrder(int orderId,int addressId) throws NoSuchOrderExistsException;
	public OrdersDisplayDto confirmOrderStatus(int orderId, String status) throws NoSuchOrderExistsException;
	public OrdersDisplayDto getOrderDetailsById(int orderId) throws NoSuchOrderExistsException;
	public Payment getPaymentById(int paymentid);
	
}
