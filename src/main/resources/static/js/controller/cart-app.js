/*
let host = "http://localhost:8080/rest/cart"
var app = angular.module("myApp", []);
app.controller("myCtrl", function($scope, $http) {


	$scope.delete = function(maGioHang, maSanPham) {

		var url = `${host}/delete/${maGioHang}/${maSanPham}`;
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
	$scope.add = function(item){
		alert(item);
		var url = `${host}/add/${item}`;
		$http
			.post(url,data)
			.then((resp) => {
				alert("Thêm thành công")
			})
			.catch((error) => {
				alert(error.status)
			});
			
	}


});
*/
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
			location.href = "http://localhost:8080/login";
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

