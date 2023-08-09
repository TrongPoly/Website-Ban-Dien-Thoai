let host = "http://localhost:8080/admin/rest/order";
const app = angular.module("orderMngt", []);
app.controller("orderMngtCtrl", function($scope, $http) {
	$scope.form = {};
	$scope.items = [];
	$scope.activity = [];

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
		$scope.ghct = [];

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
	
	$scope.huyDon = function(id){
		var url = `${host}/cancelOrder/${id}`;
		
		$http
			.put(url)
			.then((resp) => {
				alert("success!")
				location.reload();
			})
			.catch((error) => {
				alert("Lỗi");
			});
	};
	$scope.khongHuyDon = function(id){
		var url = `${host}/notCancelOrder/${id}`;
		
		$http
			.put(url)
			.then((resp) => {
				alert("success!")
				location.reload();
			})
			.catch((error) => {
				alert("Lỗi");
			});
	};
	$scope.vanChuyen = function(id){
		var url = `${host}/delivery/${id}`;
		$http
			.put(url)
			.then((resp) => {
				alert("success!")
				location.reload();
			})
			.catch((error) => {
				alert("Lỗi");
			});
	};
	$scope.hoanTat = function(id){
		var url = `${host}/successfulDelivery/${id}`;
		$http
			.put(url)
			.then((resp) => {
				alert("success!")
				location.reload();
			})
			.catch((error) => {
				alert("Lỗi");
			});
	}
	$scope.loadMessage = function(){
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
	$scope.seeMessage = function(id,orderId){
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
