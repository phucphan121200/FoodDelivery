<?php
//$connect = mysqli_connect("mysql-27536-0.cloudclusters.net","vidiep","123456789","delivery");
$connect = mysqli_connect("localhost","root","","delivery");
mysqli_query($connect, "SET NAMES 'utf8");
class User{
		function __construct($id,$name, $gender, $phone, $address, $email, $idnumber, $username, $password, $activationcode, $resetpasswordcode, $state, $driverlicensenumber, $typeofuser){
			$this->ID = $id;
			$this->Name=$name;
			$this->Gender=$gender;
			$this->Phone=$phone;
			$this->Address=$address;
			$this->Email=$email;
			$this->IdNumber=$idnumber;
			$this->Username=$username;
			$this->Password=$password;
			$this->ActivationCode=$activationcode;
			$this->ResetPasswordCode=$resetpasswordcode;
			$this->State=$state;
			$this->DriverLicenseNumber=$driverlicensenumber;
			$this->TypeUser=$typeofuser;
		}
	}
/**
 * 
 */
class Order
{
	function __construct($id,$iduser,$pickupaddress,$deliveryaddress,$mass,$receivername,$receiverphone,$description,$postage,$total,$state,$idpayment,$startTime, $endTime,$iddriver)
	{
		$this->ID = $id;
		$this->IDUser = $iduser;
		$this->PickupAddress = $pickupaddress;
		$this->DeliveryAddress = $deliveryaddress;
		$this->Mass = $mass;
		$this->ReceiverName= $receivername;
		$this->ReceiverPhone = $receiverphone;
		$this->Description= $description;
		$this->Postage = $postage;
		$this->Total =$total;
		$this->State= $state;
		$this->IDPayment=$idpayment;
		$this->StartTime= $startTime;
		$this->EndTime = $endTime;
		$this->IDDriver=$iddriver;
	}
}
/**
 * 
 */
class Contact 
{
	
	function __construct($id,$iduser,$title,$message)
	{
		$this->ID= $id;
		$this->IDUser= $iduser;
		$this->Title = $title;
		$this->Message = $message;
	}
}

/**
 * 
 */
class Feedback
{
	
	function __construct($id, $idorder, $rating, $note)
	{
		$this->ID= $id;
		$this->IDOrder= $idorder;
		$this->Rating = $rating;
		$this->Note = $note;
	}
}
?>