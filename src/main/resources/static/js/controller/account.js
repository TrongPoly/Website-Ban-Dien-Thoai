let host = "http://localhost:8080/rest";
const app = angular.module("accountApp", []);
app.controller("accountCtrl", function($scope, $http) {
	$scope.form = {};
	$scope.register = function() {
		var url = `${host}/auth/register`; // Sử dụng dấu nháy kép để bao quanh biểu thức ${host}
		var data = angular.copy($scope.form);
		
		if ($scope.form.matKhau != $scope.confirm) {
			alert("Xác nhận mật khẩu không đúng");
		} else {
			$http
				.post(url, data)
				.then((resp) => {
					alert("Tạo tài khoản thành công");
					location.href="http://localhost:8080/auth/login/form";
				})
				.catch((error) => {
					if(error.status === 400){
						alert("Tài khoản đã tồn tại")
					}
				});

		}
	};
});