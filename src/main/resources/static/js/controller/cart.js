let host = "http://localhost:8080/rest/order";
const app = angular.module("orderApp", []);
app.controller("orderCtrl", function($scope, $http) {
	$scope.form = {};
	$scope.gh = [];
	$scope.ghct = [];
	$scope.activity = [];

	$scope.load_all = function() {
		var url = `${host}/get`; // Sử dụng dấu nháy kép để bao quanh biểu thức ${host}

		$http
			.get(url)
			.then((resp) => {
				$scope.gh = resp.data;
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
				
				$scope.ghct = resp.data;
				$scope.orderActivity(id);
			})
			.catch((error) => {
				alert(error.status);
			});
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
	
	$scope.huyDon = function(id){
		var url = `${host}/requestCancel/${id}`;
		
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
	$scope.hoanTat = function(id){
		var url = `${host}/requestOrderSuccess/${id}`;
		
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
	
	$scope.load_all();
});
