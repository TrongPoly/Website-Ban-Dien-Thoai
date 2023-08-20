let host = "http://localhost:8080/admin/rest/order";
const app = angular.module("orderMngt", []);
app.controller("orderMngtCtrl", function($scope, $http) {
	$scope.form = {};
	$scope.items = [];
	$scope.activity = [];
	$scope.ghct = [];

	$scope.load_all = function() {
		var url = `${host}/index`; // Sử dụng dấu nháy kép để bao quanh biểu thức ${host}

		$http
			.get(url)
			.then((resp) => {

				$scope.items = resp.data;
			})
			.catch((error) => {
				alert("Lỗi");
			});
	};



	$scope.invoiceDetail = function(id) {
		var url = `${host}/details/${id}`;


		$http
			.get(url)
			.then((resp) => {

				$scope.ghct = resp.data;
				$scope.orderActivity(id);

			})
			.catch((error) => {
				alert("Lỗi");
			});
	};
	$scope.total = function() {
		let sum = 0;
		for (let i = 0; i < $scope.ghct.length; i++) {
			sum += $scope.ghct[i].donGia * $scope.ghct[i].soLuong;
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
		var url = `${host}/cancelOrder/${id}`;
		if (confirm("Đồng ý hủy đơn?")) {
			$http
				.put(url)
				.then((resp) => {
					alert("Đã hủy đơn!")
					$scope.load_all();
					$scope.loadMessage();
				})
				.catch((error) => {
					alert("Lỗi");
				});
		}
	};
	$scope.khongHuyDon = function(id) {
		var url = `${host}/notCancelOrder/${id}`;
		if (confirm("Từ chối hủy đơn?")) {
			$http
				.put(url)
				.then((resp) => {
					alert("Từ chối thành công!")
					$scope.load_all();
					$scope.loadMessage();
				})
				.catch((error) => {
					alert("Lỗi");
				});
		}
	};
	$scope.vanChuyen = function(id) {
		var url = `${host}/delivery/${id}`;
		if (confirm("Xác nhận giao hàng?")) {
			$http
				.put(url)
				.then((resp) => {
					alert("Xác nhận giao hàng thành công!")
					$scope.load_all();
					$scope.loadMessage();
				})
				.catch((error) => {
					alert("Lỗi");
				});
		}
	};
	$scope.hoanTat = function(id) {
		var url = `${host}/successfulDelivery/${id}`;
		if (confirm("Xác nhận giao hàng thành công")) {
			$http
				.put(url)
				.then((resp) => {
					alert("Giao hàng thành công!")
					$scope.load_all();
					$scope.loadMessage();
				})
				.catch((error) => {
					alert("Lỗi");
				});
		}
	}
	$scope.loadMessage = function() {
		var url = `${host}/loadMessage`;
		$scope.msg = [];
		$http
			.get(url)
			.then((resp) => {
				$scope.msg = resp.data
			})
			.catch((error) => {
				alert("Lỗi");
			});
	}
	$scope.seeMessage = function(id, orderId) {
		var url = `${host}/seeMessage/${id}`;
		$http
			.put(url)
			.then((resp) => {
				$scope.invoiceDetail(orderId);
			})
			.catch((error) => {
				alert("Lỗi");
			});
	}

	$scope.load_all();
	$scope.loadMessage();
});
