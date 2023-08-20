let host = "http://localhost:8080/rest"
var app = angular.module("cartApp", []);
app.controller("cartCtrl", function($scope, $http) {
	$scope.form = {};
	$scope.ghct = [];

	$scope.load_tinh = function() {
		var url = `${host}/address/getProvince`;
		$scope.tinh = [];
		$http
			.get(url)
			.then((resp) => {
				$scope.tinh = resp.data;

			})
			.catch((error) => {
				alert(error.status)
			});
	}
	$scope.load_quan = function() {
		let idTinh = $scope.form.tinh;
		var url = `${host}/address/getDistrict/${idTinh}`;
		$scope.quan = [];
		$http
			.get(url)
			.then((resp) => {
				$scope.quan = resp.data;
			})
			.catch((error) => {
				alert(error.status)
			});
	}
	$scope.load_phuong = function() {
		let idQuan = $scope.form.quan;
		var url = `${host}/address/getWards/${idQuan}`;
		$scope.phuong = [];
		$http
			.get(url)
			.then((resp) => {
				$scope.phuong = resp.data;
			})
			.catch((error) => {
				alert(error.status)
			});
	}

	$scope.load_all = function() {
		var url = `${host}/cart/get`; // Sử dụng dấu nháy kép để bao quanh biểu thức ${host}

		$http
			.get(url)
			.then((resp) => {
				$scope.ghct = resp.data;
			})
			.catch((error) => {
				alert(error.status)
				alert("Lỗi");
			});

	};
	$scope.total = function() {
		let sum = 0;
		for (let i = 0; i < $scope.ghct.length; i++) {
			if ($scope.ghct[i].chonMua) {
				sum += $scope.ghct[i].maSanPham.donGia * $scope.ghct[i].soLuong;
			}
		}
		return sum;
	}
	$scope.load_address = function() {
		var url = `${host}/address/getAll`; // Sử dụng dấu nháy kép để bao quanh biểu thức ${host}
		$scope.address = [];
		$http
			.get(url)
			.then((resp) => {
				$scope.address = resp.data;
			})
			.catch((error) => {
				alert(error.status)
			});

	};
	$scope.create_address = function() {
		var url = `${host}/address/create`; // Sử dụng dấu nháy kép để bao quanh biểu thức ${host}
		var data = angular.copy($scope.form);
		if ($scope.form.phuong == null) {
			alert("Chưa chọn địa chỉ");
		} else {

			$http
				.post(url, data)
				.then((resp) => {
					alert("Thêm địa chỉ thành công");
					$scope.load_address();
					$scope.load_all();
				})
				.catch((error) => {
				});

		}
	};
	$scope.xoa = function(cartId, productId) {
		var url = `${host}/cart/delete/${cartId}/${productId}`;
		$http
			.delete(url)
			.then((resp) => {
				$scope.load_all();
			})
			.catch((error) => {
				alert(error.status);
			});
	}
	$scope.update = function(cartId, productId, item) {

		var sl = angular.copy(item.soLuong);
		var chonMua = angular.copy(item.chonMua);

		var url = `${host}/cart/update/${cartId}/${productId}?soLuong=${sl}&chonMua=${chonMua}`; // Sử dụng dấu nháy kép để bao quanh biểu thức ${host}
		$http
			.put(url)
			.then((resp) => {
				$scope.load_all();
			})
			.catch((error) => {
				if (error.status === 400) {
					alert("Số lượng phải lớn hơn 0 và nhỏ hơn số lượng sản phẩm còn lại")
				}
				$scope.load_all();
			});
	};

	$scope.order = function() {

		let addressId = angular.copy($scope.adr);

		var url = `${host}/order?diaChiId=${addressId}`;
		if (confirm("Xác nhận đặt hàng?")) {
			$http
				.post(url)
				.then((resp) => {
					alert("Đặt hàng thành công");
					$scope.load_all();
					$scope.load_address();
				})
				.catch((error) => {
					if (error.status === 404) {
						alert("Số lượng sản phẩm còn lại không đủ")
					}
					if (error.status === 400) {
						alert("Vui lòng chọn địa chỉ")
					}
					$scope.load_all();
				});
		}
	}


	$scope.load_address();
	$scope.load_all();

});
app.controller("indexCtrl", function($scope, $http) {
	$scope.productList = [];
	$scope.load_all = function() {
		var url = `${host}/sanpham`; // Sử dụng dấu nháy kép để bao quanh biểu thức ${host}

		$http
			.get(url)
			.then((resp) => {
				$scope.productList = resp.data;
			})
			.catch((error) => {
				alert(error.status)
				alert("Lỗi");
			});

	};
	$scope.productDetails = function(id) {
		var url = `${host}/sanpham/${id}`; // Sử dụng dấu nháy kép để bao quanh biểu thức ${host}
		$scope.sp = {};
		$scope.ghct = []
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

	$scope.addToCart = function(productId) {
		var sl = angular.copy($scope.soluong);
		if (sl == null) {
			sl = 1;
		}
		var url = `${host}/cart/add/${productId}?soLuong=${sl}`;
		$http
			.post(url)
			.then((resp) => {
				alert("thêm thành công");
				$scope.load_cart();
			})
			.catch((error) => {
				alert("Vui Lòng đăng nhập");
				location.href = "http://localhost:8080/auth/login/form";
			});
	}
	$scope.load_cart = function() {
		var url = `${host}/cart/get`; // Sử dụng dấu nháy kép để bao quanh biểu thức ${host}

		$http
			.get(url)
			.then((resp) => {
				$scope.ghct = resp.data;
			})
			.catch((error) => {

			});

	};

	$scope.load_all();
	$scope.load_cart();

});

