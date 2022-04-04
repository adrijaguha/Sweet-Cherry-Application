package com.capgemini.sweetcherry.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.sweetcherry.dto.AddressDto;
import com.capgemini.sweetcherry.dto.OrdersDisplayDto;
import com.capgemini.sweetcherry.dto.OrdersDto;
import com.capgemini.sweetcherry.dto.PaymentDto;
import com.capgemini.sweetcherry.dto.UserDetailsDto;
import com.capgemini.sweetcherry.model.Address;
import com.capgemini.sweetcherry.model.CupcakeCategory;
import com.capgemini.sweetcherry.model.CupcakeDetails;
import com.capgemini.sweetcherry.model.Orders;
import com.capgemini.sweetcherry.model.Payment;
import com.capgemini.sweetcherry.model.Role;
import com.capgemini.sweetcherry.model.UserDetails;
import com.capgemini.sweetcherry.repository.AddressRepository;
import com.capgemini.sweetcherry.repository.CupcakeCategoryRepository;
import com.capgemini.sweetcherry.repository.CupcakeDetailsRepository;
import com.capgemini.sweetcherry.repository.OrderRepository;
import com.capgemini.sweetcherry.repository.PaymentRepository;
import com.capgemini.sweetcherry.repository.RoleRepository;
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
    @Autowired
    RoleRepository role_rep; 
    
    public boolean checkUserName(String firstName,String lastName) {
    	Pattern validname=Pattern.compile("(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$");
    	Matcher matcher = validname.matcher(firstName);
    	Matcher matcher2 = validname.matcher(lastName);
    	if(matcher.find() && matcher2.find())
    		return true;
    	return false;
    	
    }
    public boolean checkEmail(String email) {
    	Pattern validname = Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
    	Matcher matcher = validname.matcher(email);
    	if(matcher.find()) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    public boolean checkPassword(String password) {
    	 String regex = "^(?=.*[0-9])"
                 + "(?=.*[a-z])(?=.*[A-Z])"
                 + "(?=.*[@#$%^&+=])"
                 + "(?=\\S+$).{8,20}$";
    	Pattern validname = Pattern.compile(regex);
    	Matcher matcher = validname.matcher(password);
    	if(matcher.find()) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    @Override
    
    // Login Module Services
	public String login(String email, String password) {
    	UserDetails u =user_rep.login(email, password);
		if(user_rep.login(email,password)==null)
			return "Login Unsuccessful";
		if(u.getRole().getRoleName().equalsIgnoreCase("Admin"))
			return "Welcome Administrator....";
		else
			return "Welcome"+" "+u.getFirstName()+" "+u.getLastName();
		
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
		if(user_rep.existsById(userId)) {
			Optional<UserDetails> users = user_rep.findById(userId);
			return users;
		}
		return null;
	}

	@Override
	public String updateCustomerProfile(UserDetailsDto customer){
		
		if(!checkUserName(customer.getFirstName(),customer.getLastName())) {
			return "Invalid userName";
		}
		
		
		if(user_rep.existsById(customer.getUserId())) {
			if(user_rep.login(customer.getEmail(), customer.getPassword())!=null) {
			Optional<UserDetails> updusr = user_rep.findById(customer.getUserId());
			UserDetails usr = updusr.get();
			usr.setFirstName(customer.getFirstName());
			usr.setLastName(customer.getLastName());
			usr.setEmail(customer.getEmail());
			user_rep.save(usr);
			}
			else
				return "Wrong username/password";
			
		}
		else
			return "Invalid User";
		return "Profile updated...";
	}

	
	@Override
	public UserDetails registerCustomer(UserDetailsDto user){
		
		if(checkUserName(user.getFirstName(),user.getLastName()) && checkPassword(user.getPassword()) && checkEmail(user.getEmail())) {
			
		
				
		if(!role_rep.existsById(2)) {
			Role role = new Role();
			role.setRoleId(2);
			role.setRoleName("Customer");
			role_rep.save(role);
			
		}
		Optional<Role> r = role_rep.findById(2);
		
		UserDetails usr = new UserDetails();
		usr.setFirstName(user.getFirstName());
		usr.setLastName(user.getLastName());
		usr.setRole(r.get());
		usr.setEmail(user.getEmail());
		usr.setPassword(user.getPassword());
		
		return user_rep.save(usr);
	}
		return null;
	}
	

	@Override
	public String modifyPassword(int userId,String oldPassword, String newPassword) {
		Optional<UserDetails> ou = user_rep.findById(userId);
		if(ou==null)
			return "Invalid User";
		
		UserDetails u = user_rep.login(ou.get().getEmail(), oldPassword);
		
		if(u==null)
			return "Invalid";
		user_rep.updatePassword(userId,oldPassword,newPassword);
		return "Password updated successfully...";
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
		Orders newOrder=null;
		Map<CupcakeDetails, Integer> cupcakeList =null;
		if(!user_rep.existsById(order.getUserId()))
			return null;
		if(!order_rep.existsById(order.getOrderId())) {
			newOrder = new Orders();
			cupcakeList = new HashMap<CupcakeDetails, Integer>();
		}
		else {
			Optional<Orders> o = order_rep.findById(order.getOrderId());
			newOrder = o.get();
			cupcakeList = newOrder.getCupcakeDetails();
		}
			Optional<CupcakeDetails> c = cupcakedetails_rep.findById(order.getCupcakeId());
			CupcakeDetails cupcake =c.get();
			if(cupcake.getQuantity() < order.getQuantity())
				return "not available";
			Optional<UserDetails> user = user_rep.findById(order.getUserId());
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
	public Payment addPaymentDetails(PaymentDto paymentdto){
		Payment pay=new Payment();
		if(!order_rep.existsById(paymentdto.getOrderId()))
			return null;
		Optional<Orders> order=order_rep.findById(paymentdto.getOrderId());
		pay.setOrder(order.get());
		pay.setCardHolderName(paymentdto.getCardHolderName());
		pay.setCardNo(paymentdto.getCardNo());
		pay.setCvv(paymentdto.getCvv());
		pay.setExpiryDate(paymentdto.getExpiryDate());
		pay.setStatus("Pending");
		return payment_rep.save(pay);
	}
	
	@Override
	public Payment makeOnlinePayment(int paymentId, String status) {
		Optional<Payment> pay = payment_rep.findById(paymentId);
		Optional<Orders> order = order_rep.findById(pay.get().getOrder().getOrderId());
		pay.get().setStatus(status);
		if(status.equalsIgnoreCase("failed")) {
			order.get().setOrderStatus("Rejected");
			order_rep.save(order.get());
			payment_rep.save(pay.get());
			return null;
		}
		return payment_rep.save(pay.get());
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
	public List<OrdersDisplayDto> showOrderDetailsByUserId(int userId) {
		if(user_rep.existsById(userId)) {
			List<Orders> order = order_rep.findByuserId(userId);
			List<OrdersDisplayDto> orderList = new ArrayList<OrdersDisplayDto>();
			OrdersDisplayDto ord = null;
			for(Orders o : order) {
				ord = new OrdersDisplayDto();
				ord.setAddressId(o.getAddressId());
				ord.setOrderDate(o.getOrderDate());
				ord.setOrderId(o.getOrderId());
				ord.setOrderStatus(o.getOrderStatus());
				ord.setTotalPrice(o.getTotalPrice());
				ord.setUserId(userId);
				orderList.add(ord);
			}
			
			return orderList;
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
			u1.setAddress(adList);
			address_rep.save(newaddress);
			user_rep.save(u1);
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
			u1.setAddress(adList);
			user_rep.save(u1);
			}
			else {
				addDeliveryAddress(address);
			}
		}
	}

	@Override
	public boolean deleteDeliveryAddress(int addressId) {
		if(address_rep.existsById(addressId)) {
			Optional<Address> addr = address_rep.findById(addressId);
			Optional<UserDetails> user = user_rep.findById(addr.get().getUser().getUserId());
			Set<Address> adList = user.get().getAddress();
			adList.remove(addr.get());
			user.get().setAddress(adList);
			user_rep.save(user.get());
			return true;
		}
		return false;
	}

	@Override
	public List<OrdersDisplayDto> getAllOrderDetails() {
		List<Orders> order = order_rep.findAll();
		List<OrdersDisplayDto> orderList = new ArrayList<OrdersDisplayDto>();
		OrdersDisplayDto ord = null;
		for(Orders o : order) {
			ord = new OrdersDisplayDto();
			ord.setAddressId(o.getAddressId());
			ord.setOrderDate(o.getOrderDate());
			ord.setOrderId(o.getOrderId());
			ord.setOrderStatus(o.getOrderStatus());
			ord.setTotalPrice(o.getTotalPrice());
			ord.setUserId(o.getUserDetails().getUserId());
			orderList.add(ord);
		}
		
		return orderList;
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
	public OrdersDisplayDto makeOnlineOrder(int orderId, int addressId){
		if(order_rep.existsById(orderId)) {
			Optional<Orders> orders=order_rep.findById(orderId);
			orders.get().setAddressId(addressId);
			orders.get().setOrderDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
			orders.get().setOrderStatus("Pending");
			order_rep.save(orders.get());
			OrdersDisplayDto ord = new OrdersDisplayDto();
			Orders o = orders.get();
			ord.setAddressId(o.getAddressId());
			ord.setOrderDate(o.getOrderDate());
			ord.setOrderId(o.getOrderId());
			ord.setOrderStatus(o.getOrderStatus());
			ord.setTotalPrice(o.getTotalPrice());
			ord.setUserId(o.getUserDetails().getUserId());
			return ord;
		}
		return null;
	}

	@Override
	public OrdersDisplayDto getOrderDetailsById(int orderId) {
		if(order_rep.existsById(orderId)) {
			Optional<Orders> orders=order_rep.findById(orderId);
			OrdersDisplayDto ord = new OrdersDisplayDto();
			Orders o = orders.get();
			ord.setAddressId(o.getAddressId());
			ord.setOrderDate(o.getOrderDate());
			ord.setOrderId(o.getOrderId());
			ord.setOrderStatus(o.getOrderStatus());
			ord.setTotalPrice(o.getTotalPrice());
			ord.setUserId(o.getUserDetails().getUserId());
			return ord;
		}
		return null;
	}

	@Override
	public OrdersDisplayDto confirmOrderStatus(int orderId, String status) {
		if(order_rep.existsById(orderId)) {
			Optional<Orders> orders=order_rep.findById(orderId);
			orders.get().setOrderStatus(status);
			order_rep.save(orders.get());
			OrdersDisplayDto ord = new OrdersDisplayDto();
			Orders o = orders.get();
			ord.setAddressId(o.getAddressId());
			ord.setOrderDate(o.getOrderDate());
			ord.setOrderId(o.getOrderId());
			ord.setOrderStatus(o.getOrderStatus());
			ord.setTotalPrice(o.getTotalPrice());
			ord.setUserId(o.getUserDetails().getUserId());
			return ord;
			}
		return null;
	}
}
