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
			array_push($arrayUser, new User($row['id'], $row['name'], $row['gender'], $row['phone'], $row['address'], $row['email'], $row['idnumber'], $row['username'], $row['password'], $row['activationcode'], $row['resetpasswordcode'], $row['state'], $row['driverlicensenumber'], $row['typeofuser']));}
			echo json_encode($arrayUser);
			break;
		case "login":
			$username =$_POST['username'];
			$password=$_POST['password'];
			$query = "Select * from user where username = '$username' and password ='$password'";
			$data = mysqli_query($connect, $query);
			if(mysqli_num_rows($data) != 0){
				while ($row = mysqli_fetch_assoc($data)) {
					$user = new User($row['id'], $row['name'], $row['gender'], $row['phone'], $row['address'], $row['email'], $row['idnumber'], $row['username'], $row['password'], $row['activationcode'], $row['resetpasswordcode'], $row['state'], $row['driverlicensenumber'], $row['typeofuser']);}
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
			array_push($arrayUser, new User($row['id'], $row['name'], $row['gender'], $row['phone'], $row['address'], $row['email'], $row['idnumber'], $row['username'], $row['password'], $row['activationcode'], $row['resetpasswordcode'], $row['state'], $row['driverlicensenumber'], $row['typeofuser']));}
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
		default:
			echo "Lỗi";
			break;
	}


?>