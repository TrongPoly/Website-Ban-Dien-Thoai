let host = "http://localhost:8080/rest"
var app = angular.module("s", []);
app.controller("s", function($scope, $http) {	
	$scope.productDetails = function(id) {
		alert("OK");
		var url = `${host}/sanpham/${id}`; // Sử dụng dấu nháy kép để bao quanh biểu thức ${host}
		$scope.sp = {};
		$http
			.get(url)
			.then((resp) => {
				$scope.sp = resp.data;
			})
			.catch((error) => {
				alert(error.status)
				alert("Lỗi");
			});

	};


});