package com.capgemini.sweetcherry.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.sweetcherry.dto.AddressDto;
import com.capgemini.sweetcherry.dto.OrdersDto;
import com.capgemini.sweetcherry.dto.PaymentDto;
import com.capgemini.sweetcherry.exceptions.NoSuchAddressExistsException;
import com.capgemini.sweetcherry.exceptions.NoSuchOrderExistsException;
import com.capgemini.sweetcherry.exceptions.NoSuchUserExistsException;
import com.capgemini.sweetcherry.exceptions.PaymentFailedException;
import com.capgemini.sweetcherry.model.Address;
import com.capgemini.sweetcherry.model.CupcakeCategory;
import com.capgemini.sweetcherry.model.CupcakeDetails;
import com.capgemini.sweetcherry.model.Orders;
import com.capgemini.sweetcherry.model.Payment;
import com.capgemini.sweetcherry.model.UserDetails;
import com.capgemini.sweetcherry.repository.AddressRepository;
import com.capgemini.sweetcherry.repository.CupcakeCategoryRepository;
import com.capgemini.sweetcherry.repository.CupcakeDetailsRepository;
import com.capgemini.sweetcherry.repository.OrderRepository;
import com.capgemini.sweetcherry.repository.PaymentRepository;
import com.capgemini.sweetcherry.repository.UserDetailsRepository;

@Service
public class SweetCherryServiceImpl implements SweetCherryService {
	@Autowired
	PaymentRepository payment_rep;
	
	@Autowired
	UserDetailsRepository user_rep;
	
	@Autowired
	OrderRepository order_rep;
	
	@Autowired
	AddressRepository address_rep;
	
	@Autowired
	CupcakeDetailsRepository cupcakedetails_rep;
    @Autowired
    CupcakeCategoryRepository cupcakecategory_rep;
    @Override
    
    // Login Module Services
	public String login(String email, String password) {
		user_rep.login(email,password);
		return "Login Successful";	
	}

	@Override
	public String logout() {
		return "Logout successful";
	}

	@Override
	public List<UserDetails> allDetailsOfAdminAndUser(){
		return user_rep.findAll();
	}

	@Override
	public Optional<UserDetails> allUserDetailsById(int userId) {
		return user_rep.findById(userId);
	}

	@Override
	public UserDetails updateCustomerProfile(UserDetails customer){
		 return user_rep.save(customer);
	}

	@Override
	public UserDetails registerCustomer(UserDetails registerCustomer){
		
		return user_rep.save(registerCustomer);
	}

	@Override
	public String modifyPassword(int userId,String oldPassword, String newPassword) {
		 user_rep.updatePassword(userId,oldPassword,newPassword);
		return "Password Modified";
	}

	
	
	
	//Cupcake Module Services
	
	@Override
	public CupcakeDetails addCupcakeDetails(CupcakeDetails cupcakedetails){
			return cupcakedetails_rep.save(cupcakedetails);
	}

	@Override
	public List<CupcakeDetails> showCupcakeDetails(){
		return cupcakedetails_rep.findAll();
	}
	@Override
	public List<CupcakeCategory> showAllCupcakeCategories() {
		return cupcakecategory_rep.findAll();
	}

	@Override
	public Optional<CupcakeDetails> findCupcakeDetailsById(int cupcakeId){
		if(cupcakedetails_rep.existsById(cupcakeId)) {
			Optional<CupcakeDetails> cupcake=cupcakedetails_rep.findById(cupcakeId);
			return cupcake;
		}
		else
			return null;
	}
	
	@Override
	public Optional<CupcakeCategory> getCupcakeCategoryById(int cupcakecategoryId){
		if(cupcakecategory_rep.existsById(cupcakecategoryId)) {
		Optional<CupcakeCategory> cupcakecategory=cupcakecategory_rep.findById(cupcakecategoryId);
		return cupcakecategory;
		}
		else
			return null;
	}
	
	@Override
	public Optional<CupcakeDetails> modifyCupcakeRating(int cupcakeId, int rating){
		if(cupcakedetails_rep.existsById(cupcakeId)) {
		   cupcakedetails_rep.updateRating(cupcakeId, rating);
			return cupcakedetails_rep.findById(cupcakeId);
		}
		else
			return null;
	}

	@Override
	public String addCupcakeToCart(OrdersDto order){
		if(order_rep.existsById(order.getOrderId())) {
			Optional<Orders> o = order_rep.findById(order.getOrderId());
			Orders newOrder = o.get();
			Optional<CupcakeDetails> c = cupcakedetails_rep.findById(order.getCupcakeId());
			CupcakeDetails cupcake =c.get();
			if(cupcake.getQuantity() < order.getQuantity())
				return "not available";
			Optional<UserDetails> user = user_rep.findById(order.getUserId());
			Map<CupcakeDetails, Integer> cupcakeList = newOrder.getCupcakeDetails();
			cupcakeList.put(cupcake,cupcakeList.getOrDefault(cupcake, 0)+order.getQuantity());
			cupcake.setQuantity(cupcake.getQuantity()-order.getQuantity());
			newOrder.setOrderId(order.getOrderId());
			newOrder.setCupcakeDetails(cupcakeList);
			newOrder.setOrderDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
			newOrder.setOrderStatus("Pending");
			newOrder.setUserDetails(user.get());
			newOrder.setTotalPrice(newOrder.getTotalPrice()+order.getQuantity()*cupcake.getPrice());
			order_rep.save(newOrder);
			return "added to cart";
		}
		return null;
	}

	@Override
	public Payment addPaymentDetails(PaymentDto paymentdto){
		Payment pay=new Payment();
		Optional<Orders> order=order_rep.findById(paymentdto.getOrderId());
		pay.setPaymentId(paymentdto.getPaymentId());
		pay.setOrder(order.get());
		pay.setCardHolderName(paymentdto.getCardHolderName());
		pay.setCardNo(paymentdto.getCardNo());
		pay.setCvv(paymentdto.getCvv());
		pay.setStatus(paymentdto.getStatus());
		pay.setExpiryDate(paymentdto.getExpiryDate());
			return payment_rep.save(pay);
	}

	@Override
	public CupcakeCategory addCupcakeCategory(CupcakeCategory cupcakeCategory) {
		return cupcakecategory_rep.save(cupcakeCategory);
	}

	@Override
	public Optional<CupcakeDetails> updateCupcakePriceByCupcakeId(int cupcakeId, double price){	
		if(cupcakedetails_rep.existsById(cupcakeId)) {
			 cupcakedetails_rep.updatePrice(cupcakeId, price);
		return cupcakedetails_rep.findById(cupcakeId);
		}
		return null;
	}
	@Override
	public Optional<CupcakeDetails> modifyCupcakeName(int cupcakeId, String cupcakeName){
		if(cupcakedetails_rep.existsById(cupcakeId)) {
		  cupcakedetails_rep.updateCupcakeName(cupcakeId, cupcakeName);
		return cupcakedetails_rep.findById(cupcakeId);
		}
		else
			return null;
	}

	@Override
	public String removeCupcakeDetails(int cupcakeId){
		  cupcakedetails_rep.deleteById(cupcakeId);
			return "cupcake removed";
	}
	
	//Order Module Services
	
	@Override
	public Optional<UserDetails> viewUserDetailsById(int userId) {
		if(user_rep.existsById(userId)) {
		Optional<UserDetails> user = user_rep.findById(userId);
			return user;
		}
		return null;
	}
	
	@Override
	public Payment makeOnlinePayment(PaymentDto payment) {
		if(!payment_rep.existsById(payment.getPaymentId())) {
			Optional<Orders> order = order_rep.findById(payment.getOrderId());
			Payment pay = new Payment();
			pay.setCardHolderName(payment.getCardHolderName());
			pay.setCardNo(payment.getCardNo());
			pay.setCvv(payment.getCvv());
			pay.setExpiryDate(payment.getExpiryDate());
			pay.setPaymentId(payment.getPaymentId());
			pay.setStatus(payment.getStatus());
			pay.setOrder(order.get());
			payment_rep.save(pay);
		}
		return null;
	}
	
	public Payment getPaymentById(int paymentid) {
		if(payment_rep.existsById(paymentid)) {
			Optional<Payment> payment = payment_rep.findById(paymentid);
			return payment.get();
		}
		return null;
	}

	@Override
	public Orders cancelOnlineOrder(int orderId) {
		if(order_rep.existsById(orderId)) {
			order_rep.deleteById(orderId);
		}
		return null;
	}
	
	

	@Override
	public List<Orders> showOrderDetailsByUserId(int userId) {
		if(order_rep.existsById(userId)) {
			List<Orders> order = order_rep.findByuserId(userId);
			return order;
		}
		return null;
	}

	
	@Override
	public void addDeliveryAddress(AddressDto address){
		if(user_rep.existsById(address.getUserId())) {
			Optional<UserDetails> user = user_rep.findById(address.getUserId());
			UserDetails u1 =  user.get();
			Set<Address> adList = u1.getAddress();
			Address newaddress = new Address();
			newaddress.setAddressId(address.getAddressId());
			newaddress.setCity(address.getCity());
			newaddress.setHouseNo(address.getHouseNo());
			newaddress.setPinCode(address.getPinCode());
			newaddress.setState(address.getState());
			newaddress.setLandmark(address.getLandmark());
			newaddress.setUser(u1);
			adList.add(newaddress);
			address_rep.save(newaddress);
		}
	}

	@Override
	public void modifyDeliveryAddress(AddressDto address) {
		if(user_rep.existsById(address.getUserId())) {
			if(address_rep.existsById(address.getAddressId())) {
			Optional<UserDetails> user = user_rep.findById(address.getUserId());
			UserDetails u1 =  user.get();
			Optional<Address> address1 = address_rep.findById(address.getAddressId());
			Set<Address> adList = u1.getAddress();
			adList.remove(address1.get());
			Address newaddress = new Address();
			newaddress.setAddressId(address.getAddressId());
			newaddress.setCity(address.getCity());
			newaddress.setHouseNo(address.getHouseNo());
			newaddress.setPinCode(address.getPinCode());
			newaddress.setState(address.getState());
			newaddress.setLandmark(address.getLandmark());
			newaddress.setUser(u1);
			adList.add(newaddress);
			address_rep.save(newaddress);
			}
			else {
				addDeliveryAddress(address);
			}
		}
	}

	@Override
	public boolean deleteDeliveryAddress(int addressId) {
		if(address_rep.existsById(addressId)) {
			address_rep.deleteById(addressId);
			return true;
		}
		return false;
	}

	@Override
	public List<Orders> getAllOrderDetails() {
			return order_rep.findAll();
	}

	@Override
	public Optional<Address> getDeliveryAddress(int addressId) {
		if(address_rep.existsById(addressId)) {
			Optional<Address> address = address_rep.findById(addressId);
			return address;
		}
		return null;
	}

	@Override
	public Optional<Orders> makeOnlineOrder(int orderId){
		if(order_rep.existsById(orderId)) {
			Optional<Orders> orders=order_rep.findById(orderId);
			orders.get().setOrderDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
			orders.get().setOrderStatus("Order Placed");
			order_rep.save(orders.get());
			return orders;
		}
		return null;
	}

	@Override
	public Optional<Orders> getOrderDetailsById(int orderId) {
		if(order_rep.existsById(orderId)) {
			Optional<Orders> orders=order_rep.findById(orderId);
			return orders;
		}
		return null;
		
	}

}
