let host = "http://localhost:8080/rest"
var app = angular.module("cartApp", []);
app.controller("cartCtrl", function($scope, $http) {
	$scope.form = {};
	$scope.ghct = [];
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

		$http
			.post(url, data)
			.then((resp) => {
				alert("Thêm địa chỉ thành công");
				$scope.load_address();
				$scope.load_all();
			})
			.catch((error) => {
				alert(error.status)
			});


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
		$http
			.post(url)
			.then((resp) => {
				alert("Đặt hàng thành công");
				$scope.load_all();
				$scope.load_address();
			})
			.catch((error) => {
				if (error.status === 404) {
					alert("Số lượng san phẩm còn lại không đủ")
				}
				if (error.status === 400) {
					alert("Vui lòng chọn địa chỉ")
				}
				$scope.load_all();
			});
	}

	$scope.load_address();
	$scope.load_all();


	/*$scope.delete = function(maGioHang, maSanPham) {

		var url = `${ host }/delete/${ maGioHang } /${maSanPham}`;
		if (confirm("Bạn có muốn xóa")) {
			$http
				.delete(url)
				.then((resp) => {
					alert("Xóa thành công")
					location.reload();
				})
				.catch((error) => {
					if (error.status === 404) {
						alert("Không tồn tại")
					}
				});
		}
	}
	$scope.add = function(item) {
		alert(item);
		var url = `${host}/add/${item}`;
		$http
			.post(url, data)
			.then((resp) => {
				alert("Thêm thành công")
			})
			.catch((error) => {
				alert(error.status)
			});

	} */


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
		var url = `${host}/cart/add/${productId}`;
		$http
			.post(url)
			.then((resp) => {
				alert("thêm thành công");
			})
			.catch((error) => {
				alert("Vui Lòng đăng nhập");
			});
	}
	
	$scope.load_all();

});

/*
function addToCart(id) {
	$.ajax({
		url: "/rest/cart",
		type: "POST",
		data: {
			"productId": id
		},
		success: function(data) {
			alert("Thêm thành công")
		},
		error: function(xhr, status, error) {
			location.href = "http://localhost:8080/auth/login/form";
			alert("Vui lòng đăng nhập trước")

		}

	});

}

function deleteProduct(maGH, maSP) {
	if (confirm("Bạn có muốn xóa sản phẩm khỏi giỏ hàng?")) {
		$.ajax({
			url: "/rest/cart",
			type: "DELETE",
			data: {
				"productId": maSP,
				"cartId": maGH
			},
			success: function(data) {
				location.href = "http://localhost:8080/cart/index"

			},
			error: function(xhr, status, error) {
			}

		});
	}
}

function checkedProduct(input, maGH, maSP) {
	let checked
	if (input.checked) {
		checked = true
	}
	else {
		checked = false
	}
	$.ajax(
		{
			url: "/rest/cart/checked",
			type: "PUT",
			data: {
				"productId": maSP,
				"cartId": maGH,
				"productCheck": checked
			},
			success: function(data) {
				location.href = "http://localhost:8080/cart/index"

			},
			error: function(xhr, status, error) {
				alert(status)
			}
		}
	);
}

function updateCart(input, maGH, maSP) {
	let soluong = $(input).val();

	$.ajax(
		{
			url: "/rest/cart",
			type: "PUT",
			data: {
				"productId": maSP,
				"cartId": maGH,
				"soLuongSP": soluong
			},
			success: function(data) {
				location.href = "http://localhost:8080/cart/index"

			},
			error: function(error) {
				if (error.status === 400) {
					location.reload();
				} else {
					alert("Có lỗi xảy ra. Vui lòng thử lại sau.");
				}
			}
		}
	);
}
*/

