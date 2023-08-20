let host = "http://localhost:8080/rest/order";
const app = angular.module("orderApp", []);
app.controller("orderCtrl", function($scope, $http) {
	$scope.form = {};
	$scope.dhList = [];
	$scope.dhct = [];
	$scope.activity = [];

	$scope.load_all = function() {
		var url = `${host}/get`; // Sử dụng dấu nháy kép để bao quanh biểu thức ${host}

		$http
			.get(url)
			.then((resp) => {
				$scope.dhList = resp.data;
			})
			.catch((error) => {
				alert(error.status)
				alert("Lỗi");
			});
	};


	$scope.invoiceDetail = function(id) {
		var url = `${host}/details/${id}`;
		$http
			.get(url)
			.then((resp) => {

				$scope.dhct = resp.data;
				$scope.orderActivity(id);
			})
			.catch((error) => {
				alert(error.status);
			});
	}
	$scope.total = function() {
		let sum = 0;
		for (let i = 0; i < $scope.dhct.length; i++) {
			sum += $scope.dhct[i].donGia * $scope.dhct[i].soLuong;
		}
		return sum;
	}
	$scope.orderActivity = function(orderId) {
		var url = `${host}/activity/${orderId}`;
		$http
			.get(url)
			.then((resp) => {
				$scope.activity = resp.data;
			})
			.catch((error) => {
				alert(error.status)
				alert("Lỗi");
			});
	}

	$scope.huyDon = function(id) {
		var url = `${host}/requestCancel/${id}`;
		if (confirm("Gửi yêu cầu hủy đơn?")) {
			$http
				.put(url)
				.then((resp) => {
					alert("Đã yêu cầu hủy đơn!");
					$scope.load_all();
				})
				.catch((error) => {
					alert("Lỗi");
				});
		}
	}
	$scope.hoanTat = function(id) {
		var url = `${host}/requestOrderSuccess/${id}`;
		if (confirm("Xác nhận đã nhận hàng?")) {
			$http
				.put(url)
				.then((resp) => {
					alert("Đã xác nhận!")
					$scope.load_all();
				})
				.catch((error) => {
					alert("Lỗi");
				});
		}
	}

	$scope.load_all();
});
