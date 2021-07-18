<?php
	
	require "dbCon.php";
	$type =$_POST['type'];
	//$type="getData";
	switch ($type) {
		case "getData":
			$query = "SELECT * FROM user ";
			$data = mysqli_query($connect, $query);
			$arrayUser = array();
			while ($row = mysqli_fetch_assoc($data)) {
			array_push($arrayUser, new User($row['id'], $row['name'], $row['gender'], $row['phone'], $row['address'], $row['email'], $row['idnumber'], $row['username'], $row['password'], $row['activationcode'], $row['resetpasswordcode'], $row['state'], $row['driverlicensenumber'], $row['typeofuser'],$row['isReceiveNotification']));}
			echo json_encode($arrayUser);
			break;
		case "login":
			$username =$_POST['username'];
			$password=$_POST['password'];
			$query = "Select * from user where username = '$username' and password ='$password'";
			$data = mysqli_query($connect, $query);
			if(mysqli_num_rows($data) != 0){
				while ($row = mysqli_fetch_assoc($data)) {
					$user = new User($row['id'], $row['name'], $row['gender'], $row['phone'], $row['address'], $row['email'], $row['idnumber'], $row['username'], $row['password'], $row['activationcode'], $row['resetpasswordcode'], $row['state'], $row['driverlicensenumber'], $row['typeofuser'],$row['isReceiveNotification']);}
				echo json_encode($user);
			}else{
				echo "Error";
			};
			break;
		case "register":
			$phone =$_POST['phone'];
			$username =$_POST['username'];
			$password=$_POST['password'];
			$activationcode=$_POST['activationcode'];
			$state=$_POST['state'];
			$typeofuser=$_POST['typeofuser'];
			$query = "Insert into user(phone,username,password,activationcode,state,typeofuser) values('$phone','$username','$password','$activationcode','$state','$typeofuser')";
			if(mysqli_query($connect,$query))
			{
				echo "Registration Successfully";
			}else{
				echo "Something went wrong";
			}
			break;
		case 'forgotpassword':
			$phone=$_POST['phone'];
			$newpassword=$_POST['newpassword'];
			$resetpassword=$_POST['resetpassword'];
			$query = "Update user set password='$newpassword', resetpasswordcode='$resetpassword' where phone='$phone' ";
			$data = mysqli_query($connect, $query);
			if(mysqli_query($connect,$query))
			{
				echo "Change password Successfully";
			}else{
				echo "Something went wrong";
			}
			break;
		case "getDataCustomer":
			$query = "SELECT * FROM user where typeofuser = 'CUSTOMER' ";
			$data = mysqli_query($connect, $query);
			$arrayUser = array();
			while ($row = mysqli_fetch_assoc($data)) {
			array_push($arrayUser, new User($row['id'], $row['name'], $row['gender'], $row['phone'], $row['address'], $row['email'], $row['idnumber'], $row['username'], $row['password'], $row['activationcode'], $row['resetpasswordcode'], $row['state'], $row['driverlicensenumber'], $row['typeofuser'],$row['isReceiveNotification']));}
			echo json_encode($arrayUser);
			break;
		case "newaccountcustomer":
			$phone =$_POST['phone'];
			$username =$_POST['username'];
			$password=$_POST['password'];
			$state=$_POST['state'];
			$typeofuser=$_POST['typeofuser'];
			$name=$_POST['name'];
			$query = "Insert into user(phone,username,password,state,typeofuser, name) values('$phone','$username','$password','$state','$typeofuser','$name')";
			if(mysqli_query($connect,$query))
			{
				echo "Registration Successfully";
			}else{
				echo "Something went wrong";
			}
			break;
		case "checkExistPhone":
			$phone = $_POST['phone'];
			$query = "select * from user where phone = '$phone'";
			$data  = mysqli_query($connect,$query);
			if(mysqli_num_rows($data) != 0)
			{
				echo "Exist Phone";
			}else{
				echo "No Exist Phone";
			}
			break;
		case "editStateUser":
			$phone=$_POST['phone'];
			$state =$_POST['state'];
			$query = "update user set state='$state' where phone ='$phone'";
			if(mysqli_query($connect,$query))
			{
				echo "Successful";
			}else{
				echo "Something went wrong";
			}
			break;
		case "editUser":
			$id=$_POST['id'];
			$name=$_POST['name'];
			$gender =$_POST['gender'];
			$phone =$_POST['phone'];
			$address =$_POST['address'];
			$email =$_POST['email'];
			$isReceiveNotification =$_POST['isReceiveNotification'];
			
			$query = "UPDATE `user` SET `name`='$name',`gender`='$gender',`phone`='$phone',`address`='$address',`email`='$email',`isReceiveNotification`='$isReceiveNotification' WHERE `id`='$id'";
			if(mysqli_query($connect,$query))
			{
				echo "Successful";
			}else{
				echo "Something went wrong";
			}
			break;
		case "getDataDriver":
			$query = "SELECT * FROM user where typeofuser = 'DRIVER' ";
			$data = mysqli_query($connect, $query);
			$arrayUser = array();
			while ($row = mysqli_fetch_assoc($data)) {
			array_push($arrayUser, new User($row['id'], $row['name'], $row['gender'], $row['phone'], $row['address'], $row['email'], $row['idnumber'], $row['username'], $row['password'], $row['activationcode'], $row['resetpasswordcode'], $row['state'], $row['driverlicensenumber'], $row['typeofuser']));}
			echo json_encode($arrayUser);
			break;
		case "addnewdriver":
			$phone =$_POST['phone'];
			$username =$_POST['username'];
			$password=$_POST['password'];
			$state=$_POST['state'];
			$typeofuser=$_POST['typeofuser'];
			$name=$_POST['name'];
			$email=$_POST['email'];
			$gender=$_POST['gender'];
			$address=$_POST['address'];
			$license=$_POST['license'];
			$idnumber=$_POST['idnumber'];
			$query = "Insert into user(phone,username,password,state,typeofuser, name,email, address,gender,idnumber,driverlicensenumber) values('$phone','$username','$password','$state','$typeofuser','$name','$email','$address','$gender','$idnumber','$license')";
			if(mysqli_query($connect,$query))
			{
				echo "Registration Successfully";
			}else{
				echo "Something went wrong";
			}
			break;
		case 'checkExist':
			$typeCheck=$_POST['typecheck'];
			switch ($typeCheck) {
					case 'phone':
						$phone = $_POST['phone'];
						$query = "select * from user where phone = '$phone'";
						$data  = mysqli_query($connect,$query);
						if(mysqli_num_rows($data) != 0)
						{
							echo "Exist Phone";
						}else{
							echo "No Exist Phone";
						}
						break;
					case 'driverlicense':
						$license = $_POST['driverlicense'];
						$query = "select * from user where driverlicensenumber = '$license'";
						$data  = mysqli_query($connect,$query);
						if(mysqli_num_rows($data) != 0)
						{
							echo "Exist License";
						}else{
							echo "No Exist License";
						}
						break;
					case 'idnumber':
						$idnumber = $_POST['idnumber'];
						$query = "select * from user where idnumber = '$idnumber'";
						$data  = mysqli_query($connect,$query);
						if(mysqli_num_rows($data) != 0)
						{
							echo "Exist Idnumber";
						}else{
							echo "No Exist Idnumber";
						}
						break;
					default:
						break;
				}	
			break;
		case 'getDataContact':
			$query = "SELECT * FROM contact";
			$data = mysqli_query($connect, $query);
			$arrayContact = array();
			while ($row = mysqli_fetch_assoc($data)) {
				array_push($arrayContact, new Contact($row['id'], $row['iduser'], $row['title'], $row['content']));
			}
			echo json_encode($arrayContact);
			break;
		case 'getDataFeedback':
			$query = "SELECT * FROM feedback";
			$data = mysqli_query($connect, $query);
			$arrayfeedback = array();
			while ($row = mysqli_fetch_assoc($data)) {
				array_push($arrayfeedback, new Feedback($row['id'], $row['idorder'], $row['danhgiadichvu'], $row['ghichu']));
			}
			echo json_encode($arrayfeedback);
			break;
		case "createOrder":
				$iduser =$_POST['iduser'];
				$pickupaddress =$_POST['pickupaddress'];
				$deliveryaddress =$_POST['deliveryaddress'];
				$mass =$_POST['mass'];
				$receivername=$_POST['receivername'];
				$receiverphone=$_POST['receiverphone'];
				$postage=$_POST['postage'];
				$description=$_POST['description'];
				$total=$_POST['total'];
				$state=$_POST['state'];
				$startTime=$_POST['startTime'];

				$query = "INSERT INTO `bill`(`iduser`, `pickupaddress`, `deliveryaddress`, `mass`, `receivername`, `receiverphone`, `description`, 
				`postage`, `total`, `state`, `idpayment`, `startTime`, `endTime`, `iddriver`) VALUES ($iduser,'$pickupaddress','$deliveryaddress',
				$mass,'$receivername','$receiverphone','$description','$postage','$total','$state',null,'$startTime','$startTime',null)";

				if(mysqli_query($connect,$query))
				{
					echo "Successfully";
				}else{
					echo "Error";
				}
				break;
		case 'getOrderByStatus':
					$status =$_POST['status'];
					$iduser =$_POST['iduser'];
					$query = "SELECT * FROM bill where state = '$status' and iduser = '$iduser'";
					//$query = "SELECT * FROM bill" where state = '$status' and iduser = $iduser";
					$data = mysqli_query($connect, $query);
					$arrayOrder = array();
					if(mysqli_num_rows($data) != 0){
						while ($row = mysqli_fetch_assoc($data)) {

							array_push($arrayOrder, 
							new Order($row['id'], 
							$row['iduser'], 
							$row['pickupaddress'], 
							$row['deliveryaddress'], 
							$row['mass'], 
							$row['receivername'], 
							$row['receiverphone'], 
							$row['description'], 
							$row['postage'], 
							$row['total'],  
							$row['state'],
							$row['idpayment'],
							$row['startTime'],
							$row['endTime'],
							$row['iddriver']));
						}
						echo json_encode($arrayOrder);
					}
					else
					{
						echo "Error";
					}
					break;
			case "cancelOrder":
						$idorder =$_POST['idorder'];
						$query = "update bill set state='DAHUY' where id ='$idorder'";
						if(mysqli_query($connect,$query))
						{
							echo "Successful";
						}else{
							echo "Something went wrong";
						}
						break;
		default:
			echo "Lỗi";
			break;
	}


?>
