let host = "http://localhost:8080/rest/report";
const app = angular.module("reportApp", []);
app.controller("reportCtrl", function($scope, $http) {
	$scope.doanhThu = []

	$scope.getDoanhThu = function() {
		let trangThai = angular.copy($scope.trangThai);
		let thang = angular.copy($scope.thang);
		let nam = angular.copy($scope.nam);
		if (thang !== 0 && nam === 0) {
			alert("Vui lòng chọn năm");
		} else {
			var url = `${host}/doanhThu?trangThai=${trangThai}&thang=${thang}&nam=${nam}`; // Sử dụng dấu nháy kép để bao quanh biểu thức ${host}
			$http
				.get(url)
				.then((resp) => {
					$scope.doanhThu = resp.data;
				})
				.catch((error) => {
					alert(error.status);
				});
		}
	}
	$scope.total = function() {
		let sum = 0;
		for (let i = 0; i < $scope.doanhThu.length; i++) {
			sum += $scope.doanhThu[i].tongTien;
		}
		return sum;
	}
	$scope.getYear = function() {
		var url = `${host}/getYear`; // Sử dụng dấu nháy kép để bao quanh biểu thức ${host}
		$scope.years = []
		$http
			.get(url)
			.then((resp) => {
				$scope.years = resp.data;
				$scope.trangThai = 3;
				$scope.thang = 0;
				$scope.nam = 0;
				$scope.getDoanhThu();
			})
			.catch((error) => {
				alert(error.status);
			});
	}
	$scope.getYear();
});