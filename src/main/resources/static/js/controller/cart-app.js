/*
var app = angular.module("myApp", []);
app.controller("myCtrl", function($scope, $http) {
	
	$scope.cart={
		items:[],
		add(id){
			var item = this.items.find(item => item.id == id);
			if(item){
				item.soLuong++;
				this.saveToLocalStorage();
			}else{
				$htttp.get('/rest/product/${id}').then(resp =>{
					resp.data.soLuong = 1;
					this.items.push(resp.data);
					this.saveToLocalStorage();
				})
			}
		},
		saveToLlocalStorage(){
			var json = JSON.stringify(angular.copy(this.items));
			localStorage.setItem("cart",json);
		}
		
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
	$.ajax({
		url: "/rest/cart",
		type: "DELETE",
		data: {
			"productId": maSP,
			"cartId": maGH
		},
		success: function(data) {
			location.href = "http://localhost:8080/cart/index"
			alert("Đã xóa")
		},
		error: function(xhr, status, error) {
		}

	});
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
			error: function(xhr, status, error) {
				alert(status)
			}
		}
	);
}